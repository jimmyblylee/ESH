@echo off
set BASEDIR=%~sdp0
call %BASEDIR%\setEnv.bat

echo removing the old
rmdir /S /Q %TOMCAT_HOME%\webapps\esh >NUL 2>NUL
del /F /Q %TOMCAT_HOME%\webapps\esh.war >NUL
echo deploying the new
copy /Y %PROJECT_HOME%\src\esh\target\esh.war %TOMCAT_HOME%\webapps\esh.war
echo done
timeout /t 1 >NUL 