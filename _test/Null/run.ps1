#!/usr/local/bin/pwsh
Param(
	[switch] $vClass,
	[switch] $vGc,
	[switch] $vJni,
	[switch] $xcJni,
	[switch] $xxGcd,
	[switch] $xxGcts
)

Set-StrictMode -Version Latest

Patch-Environment

$local:class     = 'Test'
$local:classPath = "$PSScriptRoot;$PSScriptRoot\Chipmunk2D-Java.jar"

$local:params = @()
              $params += (, '-cp', $classPath)
if($vClass) { $params += (, '-verbose:class') }
if($vGc)    { $params += (, '-verbose:gc') }
if($vJni)   { $params += (, '-verbose:jni') }
if($xcJni)  { $params += (, '-Xcheck:jni') }
if($xxGcd)  { $params += (, '-XX:+PrintGCDetails') }
if($xxGcts) { $params += (, '-XX:+PrintGCTimeStamps') }
              $params += (, $class)

& java.exe @params

