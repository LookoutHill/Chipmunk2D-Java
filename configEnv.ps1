#!/usr/local/bin/pwsh

if(!(Test-Path variable:PSScriptRoot)) { $PSScriptRoot = Split-Path $script:MyInvocation.MyCommand.Path }

$x  = $PSScriptRoot
$m  = "$x\src\main\java\ChipmunkJava"
$t  = "$x\src\test\java\ChipmunkJava"
$b  = "$x\src\*\java\ChipmunkJava"
$s  = "$x\Chipmunk2D-JNI\src"
$i  = "$x\Chipmunk2D-JNI\include"
$h  = "$x\build\generated\include"
$_s = "$x\Chipmunk2D\src"
$_i = "$x\Chipmunk2D\include\chipmunk"

