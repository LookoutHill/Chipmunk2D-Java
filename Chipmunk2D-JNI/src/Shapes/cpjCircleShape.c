#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Shapes\cpjShape.h"
#include "chipmunk\chipmunk_unsafe.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_CircleShape_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_CircleShape__1new(JNIEnv *env, jclass _class, jobject _body, jdouble radius, jdoubleArray offset) {
	cpBody* body = cpjGetCpBody(env, _body);
	cpVect  vect = cpjVectFromArray(env, offset);
	return (jlong) cpCircleShapeNew(body, radius, vect);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_CircleShape__1clone(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShape* clone = cpCircleShapeNew(cpShapeGetBody(shape), cpCircleShapeGetRadius(shape), cpCircleShapeGetOffset(shape));
	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_CircleShape__1getOffset(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpCircleShapeGetOffset(shape);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_CircleShape_getRadius(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpCircleShapeGetRadius(shape);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_CircleShape_setOffset(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpjVectFromArray(env, _vect);
	cpCircleShapeSetOffset(shape, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_CircleShape_setRadius(JNIEnv *env, jobject this, jdouble value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpCircleShapeSetRadius(shape, value);
}

