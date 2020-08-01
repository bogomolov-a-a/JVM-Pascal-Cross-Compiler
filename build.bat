cls
@echo off
cmd /C "mvnw clean install > output.log"
if %ErrorLevel% equ 0 (echo Build Success) else (echo Build failed with error code %ErrorLevel%
type output.log | findstr /r /c:"ERROR" > con
)