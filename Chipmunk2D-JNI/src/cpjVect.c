#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jclass _class;

jfieldID cpVectPtr;

JNIEXPORT void JNICALL Java_ChipmunkJava__1Vect_init(JNIEnv *env, jclass __class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_class = (jclass) (*env)->NewGlobalRef(env, __class);

	cpVectPtr = (*env)->GetFieldID(env, _class, "cpVectPtr", "J");
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1new(JNIEnv *env, jclass _class, jdouble x, jdouble y) {
	return (jlong) cpjNewCpVect(x, y);
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1clone(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return (jlong) cpjCloneCpVect(*this);
}

inline JNIEXPORT void JNICALL Java_ChipmunkJava__1Vect_free(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpfree(this);
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1forAngle(JNIEnv *env, jclass _class, jdouble angle) {
	return (jlong) cpjCloneCpVect(cpvforangle(angle));
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_getX(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return this->x;
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_getY(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return this->y;
}

inline JNIEXPORT void JNICALL Java_ChipmunkJava__1Vect_setX(JNIEnv *env, jobject _this, jdouble x) {
	cpVect* this = cpjGetCpVect(env, _this);

	this->x = x;
}

inline JNIEXPORT void JNICALL Java_ChipmunkJava__1Vect_setY(JNIEnv *env, jobject _this, jdouble y) {
	cpVect* this = cpjGetCpVect(env, _this);

	this->y = y;
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_length(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return cpvlength(*this);
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_lengthSq(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return cpvlengthsq(*this);
}

inline JNIEXPORT jboolean JNICALL Java_ChipmunkJava__1Vect__1equals(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return cpveql(*this, *that);
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1add(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvadd(*this, *that));
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_cross(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return cpvcross(*this, *that);
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_dot(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return cpvdot(*this, *that);
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1div(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjNewCpVect(this->x/that->x, this->y/that->y);
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1mult(JNIEnv *env, jobject _this, jdouble scalar) {
	cpVect* this = cpjGetCpVect(env, _this);
	return (jlong) cpjCloneCpVect(cpvmult(*this, scalar));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1neg(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return (jlong) cpjCloneCpVect(cpvneg(*this));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1sub(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvsub(*this, *that));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1clamp(JNIEnv *env, jobject _this, jdouble length) {
	cpVect* this = cpjGetCpVect(env, _this);
	return (jlong) cpjCloneCpVect(cpvclamp(*this, length));
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_toAngle(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return cpvtoangle(*this);
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1normalize(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return (jlong) cpjCloneCpVect(cpvnormalize(*this));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1rotate(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvrotate(*this, *that));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1unrotate(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvunrotate(*this, *that));
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_dist(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return cpvdist(*this, *that);
}

inline JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1Vect_distSq(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return cpvdistsq(*this, *that);
}

inline JNIEXPORT jboolean JNICALL Java_ChipmunkJava__1Vect_near(JNIEnv *env, jobject _this, jobject _that, jdouble length) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return cpvnear(*this, *that, length);
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1perp(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return (jlong) cpjCloneCpVect(cpvperp(*this));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1rperp(JNIEnv *env, jobject _this) {
	cpVect* this = cpjGetCpVect(env, _this);
	return (jlong) cpjCloneCpVect(cpvrperp(*this));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1project(JNIEnv *env, jobject _this, jobject _that) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvproject(*this, *that));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1lerp(JNIEnv *env, jobject _this, jobject _that, jdouble count) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvlerp(*this, *that, count));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1lerpConst(JNIEnv *env, jobject _this, jobject _that, jdouble distance) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvlerpconst(*this, *that, distance));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1slerp(JNIEnv *env, jobject _this, jobject _that, jdouble count) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvslerp(*this, *that, count));
}

inline JNIEXPORT jlong JNICALL Java_ChipmunkJava__1Vect__1slerpConst(JNIEnv *env, jobject _this, jobject _that, jdouble angle) {
	cpVect* this = cpjGetCpVect(env, _this);
	cpVect* that = cpjGetCpVect(env, _that);
	return (jlong) cpjCloneCpVect(cpvslerpconst(*this, *that, angle));
}

