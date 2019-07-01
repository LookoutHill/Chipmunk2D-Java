#pragma once

#include <jni.h>
#include <stdio.h>
#include "chipmunk\chipmunk.h"
#include "cpjCore.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Chipmunk_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Chipmunk_areaForCircle(JNIEnv *env, jclass _class, jdouble innerRadius, jdouble outerRadius) {
	return cpAreaForCircle(innerRadius, outerRadius);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Chipmunk__1areaForPoly(JNIEnv *env, jclass _class, jdoubleArray _vertices, jdouble radius) {
	cpVect* vertices = cpjVectsFromArray(env, _vertices);
	jsize   count    = (*env)->GetArrayLength(env, _vertices);

	double area = cpAreaForPoly(count, vertices, radius);

	cpfree(vertices);

	return area;
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Chipmunk__1areaForSegment(JNIEnv *env, jclass _class, jdoubleArray _pointA, jdoubleArray _pointB, jdouble radius) {
	cpVect pointA = cpjVectFromArray(env, _pointA);
	cpVect pointB = cpjVectFromArray(env, _pointB);
	return cpAreaForSegment(pointA, pointB, radius);
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Chipmunk__1momentForCircle(JNIEnv *env, jclass _class, jdouble mass, jdouble innerRadius, jdouble outerRadius, jdoubleArray _offset) {
	cpVect offset = cpjVectFromArray(env, _offset);
	return cpMomentForCircle(mass, innerRadius, outerRadius, offset);
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Chipmunk_momentForBox(JNIEnv *env, jclass _class, jdouble mass, jdouble width, jdouble height) {
	return cpMomentForBox(mass, width, height);
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Chipmunk__1momentForPoly(JNIEnv *env, jclass _class, jdouble mass, jdoubleArray _vertices, jdoubleArray _offset, jdouble radius) {
	cpVect* vertices = cpjVectsFromArray(env, _vertices);
	jsize   count    = (*env)->GetArrayLength(env, _vertices);

	cpVect offset = cpjVectFromArray(env, _offset);

	double moment = cpMomentForPoly(mass, count, vertices, offset, radius);

	cpfree(vertices);

	return moment;
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Chipmunk__1momentForSegment(JNIEnv *env, jclass _class, jdouble mass, jdoubleArray _pointA, jdoubleArray _pointB, jdouble radius) {
	cpVect pointA = cpjVectFromArray(env, _pointA);
	cpVect pointB = cpjVectFromArray(env, _pointB);
	return cpMomentForSegment(mass, pointA, pointB, radius);
}

