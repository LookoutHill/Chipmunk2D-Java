#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_PivotJoint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_PivotJoint__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdoubleArray _pivot) {
	cpBody* bodyA = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB = cpjGetCpBody(env, _bodyB);
	cpVect  pivot = cpjVectFromArray(env, _pivot);
	return (jlong) cpPivotJointNew(bodyA, bodyB, pivot);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_PivotJoint__1new2(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdoubleArray _anchorA, jdoubleArray _anchorB) {
	cpBody* bodyA   = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB   = cpjGetCpBody(env, _bodyB);
	cpVect  anchorA = cpjVectFromArray(env, _anchorA);
	cpVect  anchorB = cpjVectFromArray(env, _anchorB);
	return (jlong) cpPivotJointNew2(bodyA, bodyB, anchorA, anchorB);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_PivotJoint__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpPivotJointNew2(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpPivotJointGetAnchorA(constraint),  cpPivotJointGetAnchorB(constraint));
	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_PivotJoint__1getAnchorA(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpPivotJointGetAnchorA(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_PivotJoint__1getAnchorB(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpPivotJointGetAnchorB(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_PivotJoint__1setAnchorA(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpPivotJointSetAnchorA(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_PivotJoint__1setAnchorB(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpPivotJointSetAnchorB(constraint, vect);
}

