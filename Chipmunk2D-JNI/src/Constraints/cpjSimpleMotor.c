#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Constraints\cpjConstraint.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_SimpleMotor_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_SimpleMotor__1new(JNIEnv *env, jclass _class, jobject _bodyA, jobject _bodyB, jdouble rate) {
	cpBody* bodyA = cpjGetCpBody(env, _bodyA);
	cpBody* bodyB = cpjGetCpBody(env, _bodyB);
	return (jlong) cpSimpleMotorNew(bodyA, bodyB, rate);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_SimpleMotor__1clone(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpConstraint* clone      = cpSimpleMotorNew(cpConstraintGetBodyA(constraint), cpConstraintGetBodyB(constraint), cpSimpleMotorGetRate(constraint));
	return (jlong) clone;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_SimpleMotor_getRate(JNIEnv *env, jobject this) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	return cpSimpleMotorGetRate(constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_SimpleMotor_setRate(JNIEnv *env, jobject this, jdouble value) {
	cpConstraint* constraint = cpjGetCpConstraint(env, this);
	cpSimpleMotorSetRate(constraint, value);
}

