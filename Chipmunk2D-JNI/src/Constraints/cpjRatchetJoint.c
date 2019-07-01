#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_RatchetJoint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_RatchetJoint__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdouble phase, jdouble ratchet) {
	cpBody* bodyA = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB = cpjGetCpBody(env, _bodyB);
	return (jlong) cpRatchetJointNew(bodyA, bodyB, phase, ratchet);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_RatchetJoint__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpRatchetJointNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpRatchetJointGetPhase(constraint),  cpRatchetJointGetRatchet(constraint));
	return (jlong) clone;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_RatchetJoint_getAngle(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpRatchetJointGetAngle(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_RatchetJoint_getPhase(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpRatchetJointGetPhase(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_RatchetJoint_getRatchet(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpRatchetJointGetRatchet(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_RatchetJoint_setAngle(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpRatchetJointSetAngle(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_RatchetJoint_setPhase(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpRatchetJointSetPhase(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_RatchetJoint_setRatchet(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpRatchetJointSetRatchet(constraint, value);
}

