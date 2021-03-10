SET HOMEDIR=%cd%
rem STOPING THE SERVER
echo stoping server
cd C:\Users\jc808853\personal_development\servers\apache-tomcat-9.0.39\bin
call shutdown.bat
rem IF ERRORLEVEL 1 SET ERRORLEVEL=0

REM REMOVING THE LAST DEPLOYMENT
echo removing last deployment
cd C:\Users\jc808853\personal_development\servers\apache-tomcat-9.0.39\webapps
rmdir /s /q game-store
del game-store.war

REM STARTING THE SERVER
echo starting server
cd C:\Users\jc808853\personal_development\servers\apache-tomcat-9.0.39\bin
call startup.bat

REM REDEPLOYING
echo redeploying
cd C:\Users\jc808853\Dropbox\projectss\Fork - SuperaRH\PS-Java
call mvn clean package 

xcopy "C:\Users\jc808853\Dropbox\projectss\Fork - SuperaRH\PS-Java\target\game-store-1.0.0-SNAPSHOT.war" "C:\Users\jc808853\personal_development\servers\apache-tomcat-9.0.39\webapps\"

REM GOING BACK TO HOME
CD %HOMEDIR%
pause