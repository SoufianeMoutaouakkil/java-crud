@echo off

REM Get the full path of the current directory
set current_dir=%cd%
set source_dir=%current_dir%\target\app-1
set target_dir=C:\tomcat\webapps\ROOT

REM Clean the target directory
echo Cleaning target directory...
rmdir /s /q "%target_dir%"
mkdir "%target_dir%"

REM Copy contents from source to target
echo Copying files to target directory...
xcopy "%source_dir%\*" "%target_dir%\" /s /e /h /i /y

echo Files copied successfully.
