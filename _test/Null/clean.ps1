#!/usr/local/bin/pwsh
Param()

Set-StrictMode -Version Latest

Patch-Environment

$local:files = @(
	,"$PSScriptRoot\*.class"
	,"$PSScriptRoot\*.jar"
	,"$PSScriptRoot\*.dll"
	,"$PSScriptRoot\hs*.log"
)
del -fo $files -wa SilentlyContinue

