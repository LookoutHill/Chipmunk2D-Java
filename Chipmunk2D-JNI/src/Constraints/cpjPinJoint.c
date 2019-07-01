#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_PinJoint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_PinJoint__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdoubleArray _anchorA, jdoubleArray _anchorB) {
	cpBody* bodyA   = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB   = cpjGetCpBody(env, _bodyB);
	cpVect  anchorA = cpjVectFromArray(env, _anchorA);
	cpVect  anchorB = cpjVectFromArray(env, _anchorB);
	return (jlong) cpPinJointNew(bodyA, bodyB, anchorA, anchorB);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_PinJoint__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpPinJointNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpPinJointGetAnchorA(constraint), cpPinJointGetAnchorB(constraint));
	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_PinJoint__1getAnchorA(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpPinJointGetAnchorA(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_PinJoint__1getAnchorB(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpPinJointGetAnchorB(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_PinJoint_getDistance(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpPinJointGetDist(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_PinJoint__1setAnchorA(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpPinJointSetAnchorA(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_PinJoint__1setAnchorB(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpPinJointSetAnchorB(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_PinJoint_setDistance(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpPinJointSetDist(constraint, value);
}

