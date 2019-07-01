#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID cpConstraintPtr;

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_Constraint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpConstraintPtr = (*env)->GetFieldID(env, _class, "cpConstraintPtr", "J");

	return (jlong) NULL;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_Constraint_free(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraintFree(constraint);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_Constraint__1getBodyA(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpBody*       body       = cpConstraintGetBodyA(constraint);
	return (jlong) body;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_Constraint__1getBodyB(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpBody*       body       = cpConstraintGetBodyB(constraint);
	return (jlong) body;
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Constraints_Constraint_getCollideBodies(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpConstraintGetCollideBodies(constraint);
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_Constraint_getErrorBias(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpConstraintGetErrorBias(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_Constraint_getImpulse(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpConstraintGetImpulse(constraint);
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_Constraint_getMaxBias(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpConstraintGetMaxBias(constraint);
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_Constraint_getMaxForce(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpConstraintGetMaxForce(constraint);
}
     
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_Constraint__1getSpace(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpSpace*      space      = cpConstraintGetSpace(constraint);
	return (jlong) space;
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_Constraint_setCollideBodies(JNIEnv *env, jobject this, jboolean value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraintSetCollideBodies(constraint, value);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_Constraint_setErrorBias(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraintSetErrorBias(constraint, value);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_Constraint_setMaxBias(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraintSetMaxBias(constraint, value);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_Constraint_setMaxForce(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraintSetMaxForce(constraint, value);
}

