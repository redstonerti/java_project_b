@echo off
javac *.java
if %errorlevel% equ 0 (
    java Main
    del /q *.class
)