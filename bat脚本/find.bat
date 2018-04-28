@echo off

set /p var=请输入文件名字： 
for /r c:\\ %%i in (%var%) do @echo %%i

for /r d:\\ %%i in (%var%) do @echo %%i

for /r e:\\ %%i in (%var%) do @echo %%i

for /r f:\\ %%i in (%var%) do @echo %%i

pause