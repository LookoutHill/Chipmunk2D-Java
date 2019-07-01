#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jclass _class;

jfieldID cpCollisionHandlerPtr;

jmethodID _invokeBeginCollisionHandler;
jmethodID _invokePreSolveCollisionHandler;
jmethodID _invokePostSolveCollisionHandler;
jmethodID _invokeSeparateCollisionHandler;

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet_init(JNIEnv *env, jclass __class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_class = (jclass) (*env)->NewGlobalRef(env, __class);

	cpCollisionHandlerPtr = (*env)->GetFieldID(env, _class, "cpCollisionHandlerPtr", "J");

	_invokeBeginCollisionHandler     = (*env)->GetStaticMethodID(env, _class, "_invokeBeginCollisionHandler",     "(JJJ)Z");
	_invokePreSolveCollisionHandler  = (*env)->GetStaticMethodID(env, _class, "_invokePreSolveCollisionHandler",  "(JJJ)Z");
	_invokePostSolveCollisionHandler = (*env)->GetStaticMethodID(env, _class, "_invokePostSolveCollisionHandler", "(JJJ)V");
	_invokeSeparateCollisionHandler  = (*env)->GetStaticMethodID(env, _class, "_invokeSeparateCollisionHandler",  "(JJJ)V");
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1new(JNIEnv *env, jclass _class, jobject _space, jobject _ctypeA, jobject _ctypeB) {
	cpSpace*            space        = cpjGetCpSpace(env, _space);
	cpCollisionType     ctypeA       = cpjGetCpCollisionType(env, _ctypeA);
	cpCollisionType     ctypeB       = cpjGetCpCollisionType(env, _ctypeB);

	cpCollisionHandler* collisionSet;
	if       (ctypeA != CP_WILDCARD_COLLISION_TYPE && ctypeB != CP_WILDCARD_COLLISION_TYPE) {
		collisionSet = cpSpaceAddCollisionHandler(space, ctypeA, ctypeB);
	} else if(ctypeA != CP_WILDCARD_COLLISION_TYPE) {
		collisionSet = cpSpaceAddWildcardHandler(space, ctypeA);
	} else if(ctypeB != CP_WILDCARD_COLLISION_TYPE) {
		collisionSet = cpSpaceAddWildcardHandler(space, ctypeB);
	} else {
		collisionSet = cpSpaceAddDefaultCollisionHandler(space);
	}

	collisionSet->userData = collisionSet;

	return (jlong) collisionSet;
}

cpBool cpjAlwaysCollide(cpArbiter* arbiter, cpSpace* space, void* collisionSet) {
	return cpTrue;
}

void cpjDoNothing(cpArbiter* arbiter, cpSpace* space, void* collisionSet) {}

cpBool cpjBeginCollisionHandler(cpArbiter* arbiter, cpSpace* space, void* collisionSet) {
	jvalue args[3];
	args[0].j = (jlong) arbiter;
	args[1].j = (jlong) space;
	args[2].j = (jlong) collisionSet;

	JNIEnv* env = cpjGetEnv();
	return (cpBool) (*env)->CallStaticBooleanMethodA(env, _class, _invokeBeginCollisionHandler, args);
}

cpBool cpjPreSolveCollisionHandler(cpArbiter* arbiter, cpSpace* space, void* collisionSet) {
	jvalue args[3];
	args[0].j = (jlong) arbiter;
	args[1].j = (jlong) space;
	args[2].j = (jlong) collisionSet;

	JNIEnv* env = cpjGetEnv();
	return (cpBool) (*env)->CallStaticBooleanMethodA(env, _class, _invokePreSolveCollisionHandler, args);
}

void cpjPostSolveCollisionHandler(cpArbiter* arbiter, cpSpace* space, void* collisionSet) {
	jvalue args[3];
	args[0].j = (jlong) arbiter;
	args[1].j = (jlong) space;
	args[2].j = (jlong) collisionSet;

	JNIEnv* env = cpjGetEnv();
	(*env)->CallStaticVoidMethodA(env, _class, _invokePostSolveCollisionHandler, args);
}

void cpjSeparateCollisionHandler(cpArbiter* arbiter, cpSpace* space, void* collisionSet) {
	jvalue args[3];
	args[0].j = (jlong) arbiter;
	args[1].j = (jlong) space;
	args[2].j = (jlong) collisionSet;

	JNIEnv* env = cpjGetEnv();
	(*env)->CallStaticVoidMethodA(env, _class, _invokeSeparateCollisionHandler, args);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1disableBeginCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);

	collisionSet->beginFunc = cpjAlwaysCollide;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1enableBeginCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);

	collisionSet->beginFunc = cpjBeginCollisionHandler;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1disablePreSolveCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);

	collisionSet->preSolveFunc = cpjAlwaysCollide;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1enablePreSolveCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);

	collisionSet->preSolveFunc = cpjPreSolveCollisionHandler;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1disablePostSolveCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);
	collisionSet->postSolveFunc = cpjDoNothing;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1enablePostSolveCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);
	collisionSet->postSolveFunc = cpjPostSolveCollisionHandler;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1disableSeparateCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);
	collisionSet->separateFunc = cpjDoNothing;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_CollisionSet__1enableSeparateCollisionHandler(JNIEnv *env, jobject this) {
	cpCollisionHandler* collisionSet = cpjGetCpCollisionSet(env, this);
	collisionSet->separateFunc = cpjSeparateCollisionHandler;
}

