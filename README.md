# Chipmunk2D-Java
Java Bindings for the Chipmunk2D physics engine.

## Quickstart

Currently the C portion of this project only compiles with Visual Studio.

Chipmunk2D-Java uses JNI to bind C functions in Chipmunk2D to objects and methods in Java.
* Chipmunk2D-JNI - JNI C library that wraps the functions from the Chipmunk2D library.
* Chipmunk2D-Java - Java library that exposes the Chipmunk2D library as a set of objects.

This library requires a two step compliation process.
1. First compile Chipmunk-JNI with Visual Studio.
   * You may need to tweak the Chipmunk-JNI Visual Studio project to fix the additional include paths to contain the path(s) to your Java installation's JNI headers.
   * Set the build configuration to Release as the gradle compilation step only copies the release library.
2. Then compile Chipmunk-Java with Gradle.
   * When this compilation is finished the build/libs folder contains the Chipmunk2D-Java.jar and chipmunkJni.dll libraries.

