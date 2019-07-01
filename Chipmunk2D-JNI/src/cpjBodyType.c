#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID _cpBodyType;

JNIEXPORT void JNICALL Java_ChipmunkJava_BodyType_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_cpBodyType = (*env)->GetFieldID(env, _class, "cpBodyType", "I");
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_BodyType_cpBodyTypeGetDynamic(JNIEnv *env, jclass _class) {
	return (jint) CP_BODY_TYPE_DYNAMIC;
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_BodyType_cpBodyTypeGetKinematic(JNIEnv *env, jclass _class) {
	return (jint) CP_BODY_TYPE_KINEMATIC;
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_BodyType_cpBodyTypeGetStatic(JNIEnv *env, jclass _class) {
	return (jint) CP_BODY_TYPE_STATIC;
}

