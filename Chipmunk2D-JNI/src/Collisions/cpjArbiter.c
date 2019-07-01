#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID cpArbiterPtr;

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_Arbiter_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpArbiterPtr = (*env)->GetFieldID(env, _class, "cpArbiterPtr",         "J");

	return (jlong) NULL;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getContactPointSet(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return (jlong) cpjCloneCpContactPointSet(cpArbiterGetContactPointSet(arbiter));
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter_setContactPointSet(JNIEnv *env, jobject this, jobject _cps) {
	cpArbiter*         arbiter = cpjGetCpArbiter(env, this);
	cpContactPointSet* cps     = cpjGetCpContactPointSet(env, _cps);
	cpArbiterSetContactPointSet(arbiter, cps);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Collisions_Arbiter_isFirstContact(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterIsFirstContact(arbiter);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Collisions_Arbiter_isRemoval(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterIsRemoval(arbiter);
}

JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getBodies(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);

	cpBody* bodyA; cpBody* bodyB;
	cpArbiterGetBodies(arbiter, &bodyA, &bodyB);

	jlong cpBodyPtrs[2];
	cpBodyPtrs[0] = (jlong) bodyA;
	cpBodyPtrs[1] = (jlong) bodyB;

	jlongArray bodies = (*env)->NewLongArray(env, 2);
	(*env)->SetLongArrayRegion(env, bodies, 0, 2, cpBodyPtrs);

	return bodies;
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_Collisions_Arbiter_getCount(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterGetCount(arbiter);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter_getDepth(JNIEnv *env, jobject this, jint pos) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterGetDepth(arbiter, pos);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter_getDepths(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);

	double _values[2]; // Assumes: cpArbiterGetCount(arbiter) <= 2
	jsize count = cpArbiterGetCount(arbiter);
	for(jsize pos = 0; pos < count; pos++) {
		_values[pos] = cpArbiterGetDepth(arbiter, pos);
	}

	jdoubleArray values = (*env)->NewDoubleArray(env, count);
	(*env)->SetDoubleArrayRegion(env, values, 0, count, _values);

	return values;
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter_getFriction(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterGetFriction(arbiter);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getNormal(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpVect     vect    = cpArbiterGetNormal(arbiter);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointA(JNIEnv *env, jobject this, jint pos) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpVect     vect    = cpArbiterGetPointA(arbiter, pos);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointB(JNIEnv *env, jobject this, jint pos) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpVect     vect    = cpArbiterGetPointB(arbiter, pos);
	return cpjVectToArray(env, &vect);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointsA(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);

	cpVect vects[2]; // Assumes: cpArbiterGetCount(arbiter) <= 2
	jsize count = cpArbiterGetCount(arbiter);
	for(jsize pos = 0; pos < count; pos++) {
		vects[pos] = cpArbiterGetPointA(arbiter, pos);
	}

	return cpjVectsToArray(env, count, vects);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointsB(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);

	cpVect vects[2]; // Assumes: cpArbiterGetCount(arbiter) <= 2
	jsize count = cpArbiterGetCount(arbiter);
	for(jsize pos = 0; pos < count; pos++) {
		vects[pos] = cpArbiterGetPointB(arbiter, pos);
	}

	return cpjVectsToArray(env, count, vects);
}
     
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter_getRestitution(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterGetRestitution(arbiter);
}

JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getShapes(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);

	cpShape* shapeA; cpShape* shapeB;
	cpArbiterGetShapes(arbiter, &shapeA, &shapeB);

	jlong cpShapePtrs[2];
	cpShapePtrs[0] = (jlong) shapeA;
	cpShapePtrs[1] = (jlong) shapeB;

	jlongArray shapes = (*env)->NewLongArray(env, 2);
	(*env)->SetLongArrayRegion(env, shapes, 0, 2, cpShapePtrs);

	return shapes;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getSurfaceVelocity(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpVect     vect    = cpArbiterGetSurfaceVelocity(arbiter);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getTotalImpulse(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpVect     vect    = cpArbiterTotalImpulse(arbiter);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getTotalKE(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterTotalKE(arbiter);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Collisions_Arbiter_ignore(JNIEnv *env, jobject this) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	return cpArbiterIgnore(arbiter);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter_setFriction(JNIEnv *env, jobject this, jdouble value) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpArbiterSetFriction(arbiter, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter_setRestitution(JNIEnv *env, jobject this, jdouble value) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpArbiterSetRestitution(arbiter, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter__1setSurfaceVelocity(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpArbiter* arbiter = cpjGetCpArbiter(env, this);
	cpVect     vect    = cpjVectFromArray(env, _vect);
	cpArbiterSetSurfaceVelocity(arbiter, vect);
}

