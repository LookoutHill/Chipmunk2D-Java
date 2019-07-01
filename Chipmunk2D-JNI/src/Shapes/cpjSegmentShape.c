#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Shapes\cpjShape.h"
#include "chipmunk\chipmunk_unsafe.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_SegmentShape_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_SegmentShape__1new(JNIEnv *env, jclass _class, jobject _body, jdoubleArray _pointA, jdoubleArray _pointB, jdouble r) {
	cpBody* body   = cpjGetCpBody(env, _body);
	cpVect  pointA = cpjVectFromArray(env, _pointA);
	cpVect  pointB = cpjVectFromArray(env, _pointB);
	return (jlong) cpSegmentShapeNew(body, pointA, pointB, r);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_SegmentShape__1clone(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShape* clone = cpSegmentShapeNew(cpShapeGetBody(shape), cpSegmentShapeGetA(shape), cpSegmentShapeGetB(shape), cpSegmentShapeGetRadius(shape));
	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_SegmentShape__1getPointA(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpSegmentShapeGetA(shape);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_SegmentShape__1getPointB(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpSegmentShapeGetB(shape);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_SegmentShape__1getNormal(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpSegmentShapeGetNormal(shape);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_SegmentShape_getRadius(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpSegmentShapeGetRadius(shape);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_SegmentShape__1setEndpoints(JNIEnv *env, jobject this, jdoubleArray _pointA, jdoubleArray _pointB) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   pointA = cpjVectFromArray(env, _pointA);
	cpVect   pointB = cpjVectFromArray(env, _pointB);
	cpSegmentShapeSetEndpoints(shape, pointA, pointB);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_SegmentShape_setRadius(JNIEnv *env, jobject this, jdouble value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpSegmentShapeSetRadius(shape, value);
}

