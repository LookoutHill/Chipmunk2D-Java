#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID cpPointQueryInfoPtr;

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpPointQueryInfoPtr = (*env)->GetFieldID(env, _class, "cpPointQueryInfoPtr", "J");

	return (jlong) NULL;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo_free(JNIEnv *env, jobject this) {
	cpPointQueryInfo* info = cpjGetCpPointQueryInfo(env, this);
	cpfree(info);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo_getDistance(JNIEnv *env, jobject this) {
	cpPointQueryInfo* info = cpjGetCpPointQueryInfo(env, this);
	return info->distance;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo__1getGradient(JNIEnv *env, jobject this) {
	cpPointQueryInfo* info = cpjGetCpPointQueryInfo(env, this);
	return cpjVectToArray(env, &info->gradient);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo__1getPoint(JNIEnv *env, jobject this) {
	cpPointQueryInfo* info = cpjGetCpPointQueryInfo(env, this);
	return cpjVectToArray(env, &info->point);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo__1getShape(JNIEnv *env, jobject this) {
	cpPointQueryInfo* info = cpjGetCpPointQueryInfo(env, this);
	return (jlong) info->shape;
}

