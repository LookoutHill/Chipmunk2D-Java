#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_GrooveJoint_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdoubleArray _groove_bodyA, jdoubleArray _groove_bodyB, jdoubleArray _anchorB) {
	cpBody* bodyA        = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB        = cpjGetCpBody(env, _bodyB);
	cpVect  groove_bodyA = cpjVectFromArray(env, _groove_bodyA);
	cpVect  groove_bodyB = cpjVectFromArray(env, _groove_bodyB);
	cpVect  anchorB      = cpjVectFromArray(env, _anchorB);
	return (jlong) cpGrooveJointNew(bodyA, bodyB, groove_bodyA, groove_bodyB, anchorB);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpGrooveJointNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpGrooveJointGetGrooveA(constraint), cpGrooveJointGetGrooveB(constraint),  cpGrooveJointGetAnchorB(constraint));
	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1getAnchorB(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpGrooveJointGetAnchorB(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1getGrooveA(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpGrooveJointGetGrooveA(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1getGrooveB(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpGrooveJointGetGrooveB(constraint);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1setAnchorB(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpGrooveJointSetAnchorB(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1setGrooveA(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpGrooveJointSetGrooveA(constraint, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_GrooveJoint__1setGrooveB(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpVect        vect       = cpjVectFromArray(env, _vect);
	cpGrooveJointSetGrooveB(constraint, vect);
}

