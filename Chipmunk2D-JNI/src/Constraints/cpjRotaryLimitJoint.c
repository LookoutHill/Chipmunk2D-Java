#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_RotaryLimitJoint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_RotaryLimitJoint__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdouble min, jdouble max) {
	cpBody* bodyA = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB = cpjGetCpBody(env, _bodyB);
	return (jlong) cpRotaryLimitJointNew(bodyA, bodyB, min, max);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_RotaryLimitJoint__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpRotaryLimitJointNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpRotaryLimitJointGetMin(constraint),  cpRotaryLimitJointGetMax(constraint));
	return (jlong) clone;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_RotaryLimitJoint_getMax(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpRotaryLimitJointGetMax(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_RotaryLimitJoint_getMin(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpRotaryLimitJointGetMin(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_RotaryLimitJoint_setMax(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpRotaryLimitJointSetMax(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_RotaryLimitJoint_setMin(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpRotaryLimitJointSetMin(constraint, value);
}

