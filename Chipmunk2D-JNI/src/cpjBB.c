#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jclass _class;

jfieldID cpBBPtr;

JNIEXPORT void JNICALL Java_ChipmunkJava__1BB_init(JNIEnv *env, jclass __class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_class = (jclass) (*env)->NewGlobalRef(env, __class);

	cpBBPtr = (*env)->GetFieldID(env, _class, "cpBBPtr", "J");
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1new(JNIEnv *env, jclass _class, jdouble l, jdouble b, jdouble r, jdouble t) {
	return (jlong) cpjNewCpBB(l, b, r, t);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1clone(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	return (jlong) cpjCloneCpBB(*this);
}

JNIEXPORT void JNICALL Java_ChipmunkJava__1BB_free(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	cpfree(this);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1BB_getL(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	return this->l;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1BB_getB(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	return this->b;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1BB_getR(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	return this->r;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1BB_getT(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	return this->t;
}

JNIEXPORT void JNICALL Java_ChipmunkJava__1BB_setL(JNIEnv *env, jobject _this, jdouble value) {
	cpBB* this = cpjGetCpBB(env, _this);

	this->l = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava__1BB_setB(JNIEnv *env, jobject _this, jdouble value) {
	cpBB* this = cpjGetCpBB(env, _this);

	this->b = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava__1BB_setR(JNIEnv *env, jobject _this, jdouble value) {
	cpBB* this = cpjGetCpBB(env, _this);

	this->r = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava__1BB_setT(JNIEnv *env, jobject _this, jdouble value) {
	cpBB* this = cpjGetCpBB(env, _this);

	this->t = value;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1BB_area(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	return cpBBArea(*this);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1center(JNIEnv *env, jobject _this) {
	cpBB* this = cpjGetCpBB(env, _this);
	return (jlong) cpjCloneCpVect(cpBBCenter(*this));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1clampVect(JNIEnv *env, jobject _this, jobject _vect) {
	cpBB*   this = cpjGetCpBB(env, _this);
	cpVect* vect = cpjGetCpVect(env, _vect);
	return (jlong) cpjCloneCpVect(cpBBClampVect(*this, *vect));
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava__1BB_containsBB(JNIEnv *env, jobject _this, jobject _that) {
	cpBB* this = cpjGetCpBB(env, _this);
	cpBB* that = cpjGetCpBB(env, _that);
	return cpBBContainsBB(*this, *that);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava__1BB_containsPoint(JNIEnv *env, jobject _this, jobject _point) {
	cpBB*   this  = cpjGetCpBB(env, _this);
	cpVect* point = cpjGetCpVect(env, _point);
	return cpBBContainsVect(*this, *point);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1expand(JNIEnv *env, jobject _this, jobject _vect) {
	cpBB*   this = cpjGetCpBB(env, _this);
	cpVect* vect = cpjGetCpVect(env, _vect);
	return (jlong) cpjCloneCpBB(cpBBExpand(*this, *vect));
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava__1BB_intersects(JNIEnv *env, jobject _this, jobject _that) {
	cpBB* this = cpjGetCpBB(env, _this);
	cpBB* that = cpjGetCpBB(env, _that);
	return cpBBIntersects(*this, *that);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava__1BB_intersectsSegment(JNIEnv *env, jobject _this, jobject _pointA, jobject _pointB) {
	cpBB*   this   = cpjGetCpBB(env, _this);
	cpVect* pointA = cpjGetCpVect(env, _pointA);
	cpVect* pointB = cpjGetCpVect(env, _pointB);
	return cpBBIntersectsSegment(*this, *pointA, *pointB);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1merge(JNIEnv *env, jobject _this, jobject _that) {
	cpBB* this = cpjGetCpBB(env, _this);
	cpBB* that = cpjGetCpBB(env, _that);
	return (jlong) cpjCloneCpBB(cpBBMerge(*this, *that));
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1BB_mergedArea(JNIEnv *env, jobject _this, jobject _that) {
	cpBB* this = cpjGetCpBB(env, _this);
	cpBB* that = cpjGetCpBB(env, _that);
	return cpBBMergedArea(*this, *that);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1offset(JNIEnv *env, jobject _this, jobject _vect) {
	cpBB*   this = cpjGetCpBB(env, _this);
	cpVect* vect = cpjGetCpVect(env, _vect);
	return (jlong) cpjCloneCpBB(cpBBOffset(*this, *vect));
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava__1BB_segmentQuery(JNIEnv *env, jobject _this, jobject _pointA, jobject _pointB) {
	cpBB*   this   = cpjGetCpBB(env, _this);
	cpVect* pointA = cpjGetCpVect(env, _pointA);
	cpVect* pointB = cpjGetCpVect(env, _pointB);
	return cpBBSegmentQuery(*this, *pointA, *pointB);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava__1BB__1wrapVect(JNIEnv *env, jobject _this, jobject _vect) {
	cpBB*   this = cpjGetCpBB(env, _this);
	cpVect* vect = cpjGetCpVect(env, _vect);
	return (jlong) cpjCloneCpVect(cpBBWrapVect(*this, *vect));
}

