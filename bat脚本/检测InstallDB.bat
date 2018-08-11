@echo off & setlocal enabledelayedexpansion

::初始化
if exist "error" (
	del error
)

if exist "@@list.txt" (
	del @@list.txt
)

echo 开始检测

if not exist "installDB.sql" (
	echo "installDB.sql 不存在" >> error
	goto end
)

findstr "^@@" installDB.sql >> @@list.txt

:begin
if exist "filelist.txt" (
	del filelist.txt
)

for /f "delims=" %%i in (@@list.txt) do (
	set var=%%i
	rem %var:原字符=新字符%
	set var=!var:@@=!
	set var=!var:;=!
	set var=!var:/=\!
	echo !var!>>filelist.txt
)
del @@list.txt
::1、未调用任何脚本 直接结束
if not exist "filelist.txt" (
	goto end
)
::2、检查filelist
for /f "delims=" %%i in (filelist.txt) do (
	set file=%%i
	if not exist !file! (
		echo !file! 不存在 >>error
	)
)
::3、读取filelist
for /f "delims=" %%i in (filelist.txt) do (
	set file=%%i
	if exist !file! (
		rem 是文件夹
		if exist !file!\ (
			echo.
		) else (
			rem 要用两个^^
			findstr "^^@@" !file! >>@@list.txt
			echo.>>@@list.txt
		)
	)
)
::4、循环
goto begin

::结束
:end
del @@list.txt
cls
if exist "error" (
	echo 检查有误，详情可查看 error 文件
	ping -n 3 127.0>nul
	exit
) else (
	echo 检查无误，3秒后自动退出
	ping -n 3 127.0>nul
	exit
)