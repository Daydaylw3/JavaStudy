@echo off & setlocal EnableDelayedExpansion
rem *********
rem Video目录
rem *********
cd Video
del *.jpg
cd ..
rem ***************
rem HDHeadImage目录
rem ***************
cd HDHeadImage
del *.jpg
cd ..
rem **************
rem Attachment目录
rem **************
cd Attachment
del *.jpg
rem 以_t结尾的gif都是不会动的
del *_t.gif
rem 初步筛选
dir /o:s /-c /a-d >> ../readme.txt
rem 拿到所有的gif的列表，存在giflist.txt中
cd ..
findstr "gif" readme.txt>>Attachment/giflist.txt
rem 执行java程序，找到应该留下的gif
java CleanAttachment
cd Attachment
set j=0
md remain
for /f "delims=""" %%i in (remaingif.txt) do (
set /lineStr j+=1
set con!j!=%%i
call set lineStr=%%con!j!%%
move !lineStr! remain
)
del *.gif
cd remain
move *.gif ..
cd ..
rd remain
del giflist.txt
del remaingif.txt
cd ..
del readme.txt
pause