#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID cpSegmentQueryInfoPtr;

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpSegmentQueryInfoPtr = (*env)->GetFieldID(env, _class, "cpSegmentQueryInfoPtr", "J");
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo_free(JNIEnv *env, jobject this) {
	cpSegmentQueryInfo* info = cpjGetCpSegmentQueryInfo(env, this);
	cpfree(info);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo_getAlpha(JNIEnv *env, jobject this) {
	cpSegmentQueryInfo* info = cpjGetCpSegmentQueryInfo(env, this);
	return info->alpha;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo__1getNormal(JNIEnv *env, jobject this) {
	cpSegmentQueryInfo* info = cpjGetCpSegmentQueryInfo(env, this);
	return cpjVectToArray(env, &info->normal);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo__1getSegment(JNIEnv *env, jobject this) {
	cpSegmentQueryInfo* info = cpjGetCpSegmentQueryInfo(env, this);
	return cpjVectToArray(env, &info->point);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo__1getShape(JNIEnv *env, jobject this) {
	cpSegmentQueryInfo* info = cpjGetCpSegmentQueryInfo(env, this);
	return (jlong) info->shape;
}

