#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ShapeFilter_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_ShapeFilter_getNoCollisionGroup(JNIEnv *env, jclass _class) {
	return (jlong) CP_NO_GROUP;
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_Collisions_ShapeFilter_getAllCollisionCategories(JNIEnv *env, jclass _class) {
	return (jint) CP_ALL_CATEGORIES;
}

