#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_GearJoint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_GearJoint__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdouble phase, jdouble ratio) {
	cpBody* bodyA = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB = cpjGetCpBody(env, _bodyB);
	return (jlong) cpGearJointNew(bodyA, bodyB, phase, ratio);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_GearJoint__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpGearJointNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpGearJointGetPhase(constraint), cpGearJointGetRatio(constraint));
	return (jlong) clone;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_GearJoint_getPhase(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpGearJointGetPhase(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_GearJoint_getRatio(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpGearJointGetRatio(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_GearJoint_setPhase(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpGearJointSetPhase(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_GearJoint_setRatio(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpGearJointSetRatio(constraint, value);
}

