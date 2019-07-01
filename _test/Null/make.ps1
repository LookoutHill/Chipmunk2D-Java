#!/usr/local/bin/pwsh
Param()

Set-StrictMode -Version Latest

Patch-Environment

$local:classPath = "$PSScriptRoot;$PSScriptRoot\Chipmunk2D-Java.jar"

$local:srcFiles = @(
	,"$PSScriptRoot\Test.java"
)

del -fo "$PSScriptRoot\*.class" -wa SilentlyContinue

cp -fo "$PSScriptRoot\..\..\build\libs\Chipmunk2D-Java.jar" $PSScriptRoot -wa SilentlyContinue
cp -fo "$PSScriptRoot\..\..\build\libs\chipmunkJni.dll" $PSScriptRoot -wa SilentlyContinue

& javac.exe -cp $classPath $srcFiles

