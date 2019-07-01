#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID _cpCollisionType;

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionType_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_cpCollisionType = (*env)->GetFieldID(env, _class, "cpCollisionType", "J");
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_CollisionType_cpCollisionTypeGetWildcard(JNIEnv *env, jclass _class) {
	return (jlong) CP_WILDCARD_COLLISION_TYPE;
}

