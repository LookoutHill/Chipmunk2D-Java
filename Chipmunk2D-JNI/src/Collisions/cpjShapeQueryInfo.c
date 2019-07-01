#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID cpjShapeQueryInfoPtr;

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ShapeQueryInfo_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpjShapeQueryInfoPtr = (*env)->GetFieldID(env, _class, "cpjShapeQueryInfoPtr", "J");
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ShapeQueryInfo_free(JNIEnv *env, jobject this) {
	cpjShapeQueryInfo* info = cpjGetCpjShapeQueryInfo(env, this);
	cpfree(info);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_ShapeQueryInfo__1getContactPointSet(JNIEnv *env, jobject this) {
	cpjShapeQueryInfo* info = cpjGetCpjShapeQueryInfo(env, this);
	return (jlong) cpjCloneCpContactPointSet(info->cps);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_ShapeQueryInfo__1getShape(JNIEnv *env, jobject this) {
	cpjShapeQueryInfo* info = cpjGetCpjShapeQueryInfo(env, this);
	return (jlong) info->shape;
}

