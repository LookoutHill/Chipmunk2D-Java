#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_SlideJoint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_SlideJoint__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdoubleArray _anchorA, jdoubleArray _anchorB, jdouble min, jdouble max) {
	cpBody* bodyA   = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB   = cpjGetCpBody(env, _bodyB);
	cpVect  anchorA = cpjVectFromArray(env, _anchorA);
	cpVect  anchorB = cpjVectFromArray(env, _anchorB);
	return (jlong) cpSlideJointNew(bodyA, bodyB, anchorA, anchorB, min, max);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_SlideJoint__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpSlideJointNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpSlideJointGetAnchorA(constraint), cpSlideJointGetAnchorB(constraint), cpSlideJointGetMin(constraint), cpSlideJointGetMax(constraint));
	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_SlideJoint__1getAnchorA(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpSlideJointGetAnchorA(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_SlideJoint__1getAnchorB(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpSlideJointGetAnchorB(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_SlideJoint_getMax(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpSlideJointGetMax(constraint);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_SlideJoint_getMin(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpSlideJointGetMin(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_SlideJoint__1setAnchorA(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpSlideJointSetAnchorA(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_SlideJoint__1setAnchorB(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpSlideJointSetAnchorB(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_SlideJoint_setMax(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpSlideJointSetMax(constraint, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_SlideJoint_setMin(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpSlideJointSetMin(constraint, value);
}

