#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID cpContactPointSetPtr;

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpContactPointSetPtr = (*env)->GetFieldID(env, _class, "cpContactPointSetPtr", "J");
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_free(JNIEnv *env, jobject this) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);
	cpfree(cps);
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_getCount(JNIEnv *env, jobject this) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	return cps->count;
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_getDistance(JNIEnv *env, jobject this, jint pos) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	return cps->points[pos].distance;
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getDistances(JNIEnv *env, jobject this) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	double _values[2]; // Assumes: cps->count <= 2
	jsize count = cps->count;
	for(jsize pos = 0; pos < count; pos++) {
		_values[pos] = cps->points[pos].distance;
	}

	jdoubleArray values = (*env)->NewDoubleArray(env, count);
	(*env)->SetDoubleArrayRegion(env, values, 0, count, _values);

	return values;
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getNormal(JNIEnv *env, jobject this) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	return cpjVectToArray(env, &cps->normal);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointA(JNIEnv *env, jobject this, jint pos) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	return cpjVectToArray(env, &cps->points[pos].pointA);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointB(JNIEnv *env, jobject this, jint pos) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	return cpjVectToArray(env, &cps->points[pos].pointB);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointsA(JNIEnv *env, jobject this) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	cpVect vects[2]; // Assumes: cps->count <= 2
	jsize count = cps->count;
	for(jsize pos = 0; pos < count; pos++) {
		vects[pos] = cps->points[pos].pointA;
	}

	return cpjVectsToArray(env, count, vects);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointsB(JNIEnv *env, jobject this) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	cpVect vects[2]; // Assumes: cps->count <= 2
	jsize count = cps->count;
	for(jsize pos = 0; pos < count; pos++) {
		vects[pos] = cps->points[pos].pointB;
	}

	return cpjVectsToArray(env, count, vects);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setNormal(JNIEnv *env, jobject this, jdoubleArray vect) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	cps->normal = cpjVectFromArray(env, vect);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointA(JNIEnv *env, jobject this, jint pos, jdoubleArray vect) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	cps->points[pos].pointA = cpjVectFromArray(env, vect);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointB(JNIEnv *env, jobject this, jint pos, jdoubleArray vect) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	cps->points[pos].pointB = cpjVectFromArray(env, vect);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointsA(JNIEnv *env, jobject this, jdoubleArray _vects) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	cpVect* vects = cpjVectsFromArray(env, _vects);
	jsize   count = (*env)->GetArrayLength(env, _vects);
	for(jsize pos = 0; pos < count; pos++) {
		cps->points[pos].pointA = vects[pos];
	}
	cpfree(vects);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointsB(JNIEnv *env, jobject this, jdoubleArray _vects) {
	cpContactPointSet* cps = cpjGetCpContactPointSet(env, this);

	cpVect* vects = cpjVectsFromArray(env, _vects);
	jsize   count = (*env)->GetArrayLength(env, _vects);
	for(jsize pos = 0; pos < count; pos++) {
		cps->points[pos].pointB = vects[pos];
	}
	cpfree(vects);
}

