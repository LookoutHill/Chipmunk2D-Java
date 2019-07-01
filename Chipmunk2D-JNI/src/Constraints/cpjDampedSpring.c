#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedSpring_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_DampedSpring__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdoubleArray _anchorA, jdoubleArray _anchorB, jdouble restLength, jdouble stiffness, jdouble damping) {
	cpBody* bodyA   = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB   = cpjGetCpBody(env, _bodyB);
	cpVect  anchorA = cpjVectFromArray(env, _anchorA);
	cpVect  anchorB = cpjVectFromArray(env, _anchorB);
	return (jlong) cpDampedSpringNew(bodyA, bodyB, anchorA, anchorB, restLength, stiffness, damping);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_DampedSpring__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpDampedSpringNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpDampedSpringGetAnchorA(constraint), cpDampedSpringGetAnchorB(constraint), cpDampedSpringGetRestLength(constraint), cpDampedSpringGetStiffness(constraint), cpDampedSpringGetDamping(constraint));
	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_DampedSpring__1getAnchorA(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpDampedSpringGetAnchorA(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_DampedSpring__1getAnchorB(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpDampedSpringGetAnchorB(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedSpring_getDamping(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpDampedSpringGetDamping(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedSpring_getRestLength(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpDampedSpringGetRestLength(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedSpring_getStiffness(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpDampedSpringGetStiffness(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedSpring_setAnchorA(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpDampedSpringSetAnchorA(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedSpring_setAnchorB(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpDampedSpringSetAnchorB(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedSpring_setDamping(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpDampedSpringSetDamping(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedSpring_setRestLength(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpDampedSpringSetRestLength(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedSpring_setStiffness(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpDampedSpringSetStiffness(constraint, value);
}

