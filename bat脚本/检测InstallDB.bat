@echo off & setlocal enabledelayedexpansion

::��ʼ��
if exist "error" (
	del error
)

if exist "@@list.txt" (
	del @@list.txt
)

echo ��ʼ���

if not exist "installDB.sql" (
	echo "installDB.sql ������" >> error
	goto end
)

findstr "^@@" installDB.sql >> @@list.txt

:begin
if exist "filelist.txt" (
	del filelist.txt
)

for /f "delims=" %%i in (@@list.txt) do (
	set var=%%i
	rem %var:ԭ�ַ�=���ַ�%
	set var=!var:@@=!
	set var=!var:;=!
	set var=!var:/=\!
	echo !var!>>filelist.txt
)
del @@list.txt
::1��δ�����κνű� ֱ�ӽ���
if not exist "filelist.txt" (
	goto end
)
::2�����filelist
for /f "delims=" %%i in (filelist.txt) do (
	set file=%%i
	if not exist !file! (
		echo !file! ������ >>error
	)
)
::3����ȡfilelist
for /f "delims=" %%i in (filelist.txt) do (
	set file=%%i
	if exist !file! (
		rem ���ļ���
		if exist !file!\ (
			echo.
		) else (
			rem Ҫ������^^
			findstr "^^@@" !file! >>@@list.txt
			echo.>>@@list.txt
		)
	)
)
::4��ѭ��
goto begin

::����
:end
del @@list.txt
cls
if exist "error" (
	echo �����������ɲ鿴 error �ļ�
	ping -n 3 127.0>nul
	exit
) else (
	echo �������3����Զ��˳�
	ping -n 3 127.0>nul
	exit
)