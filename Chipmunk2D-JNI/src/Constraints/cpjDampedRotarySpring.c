#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdouble restAngle, jdouble stiffness, jdouble damping) {
	cpBody* bodyA = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB = cpjGetCpBody(env, _bodyB);
	return (jlong) cpDampedRotarySpringNew(bodyA, bodyB, restAngle, stiffness, damping);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpDampedRotarySpringNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpDampedRotarySpringGetRestAngle(constraint), cpDampedRotarySpringGetStiffness(constraint), cpDampedRotarySpringGetDamping(constraint));
	return (jlong) clone;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_getDamping(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpDampedRotarySpringGetDamping(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_getRestAngle(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpDampedRotarySpringGetRestAngle(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_getStiffness(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpDampedRotarySpringGetStiffness(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_setDamping(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpDampedRotarySpringSetDamping(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_setRestAngle(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpDampedRotarySpringSetRestAngle(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_setStiffness(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpDampedRotarySpringSetStiffness(constraint, value);
}

