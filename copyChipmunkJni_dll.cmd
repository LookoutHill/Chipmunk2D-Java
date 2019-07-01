@echo off

if not exist "%~dp0\build\libs\" mkdir "%~dp0\build\libs"
copy "%~dp0\Chipmunk2D-JNI\msvc\VS2017\Chipmunk2D-JNI\x64\Release\chipmunkJni.dll" "%~dp0\build\libs\"

