#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jclass _class;

jfieldID cpTransformPtr;

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_init(JNIEnv *env, jclass __class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_class = (jclass) (*env)->NewGlobalRef(env, __class);

	cpTransformPtr = (*env)->GetFieldID(env, _class, "cpTransformPtr", "J");
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1new(JNIEnv *env, jclass _class, jdouble a, jdouble b, jdouble c, jdouble d, jdouble tx, jdouble ty) {
	return (jlong) cpjNewCpTransform(a, b, c, d, tx, ty);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1clone(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return (jlong) cpjCloneCpTransform(*this);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_free(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	cpfree(this);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes__1Transform_getA(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return this->a;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes__1Transform_getB(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return this->b;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes__1Transform_getC(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return this->c;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes__1Transform_getD(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return this->d;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes__1Transform_getTX(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return this->tx;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes__1Transform_getTY(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return this->ty;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_setA(JNIEnv *env, jobject _this, jdouble value) {
	cpTransform* this = cpjGetCpTransform(env, _this);

	this->a = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_setB(JNIEnv *env, jobject _this, jdouble value) {
	cpTransform* this = cpjGetCpTransform(env, _this);

	this->b = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_setC(JNIEnv *env, jobject _this, jdouble value) {
	cpTransform* this = cpjGetCpTransform(env, _this);

	this->c = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_setD(JNIEnv *env, jobject _this, jdouble value) {
	cpTransform* this = cpjGetCpTransform(env, _this);

	this->d = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_setTX(JNIEnv *env, jobject _this, jdouble value) {
	cpTransform* this = cpjGetCpTransform(env, _this);

	this->tx = value;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes__1Transform_setTY(JNIEnv *env, jobject _this, jdouble value) {
	cpTransform* this = cpjGetCpTransform(env, _this);

	this->ty = value;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1invert(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return (jlong) cpjCloneCpTransform(cpTransformInverse(*this));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1mult(JNIEnv *env, jobject _this, jobject _that) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	cpTransform* that = cpjGetCpTransform(env, _that);
	return (jlong) cpjCloneCpTransform(cpTransformMult(*this, *that));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1rigidInvert(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return (jlong) cpjCloneCpTransform(cpTransformRigidInverse(*this));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1transformBB(JNIEnv *env, jobject _this, jobject _bb) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	cpBB*        bb   = cpjGetCpBB(env, _bb);
	return (jlong) cpjCloneCpBB(cpTransformbBB(*this, *bb));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1transformPoint(JNIEnv *env, jobject _this, jobject _point) {
	cpTransform* this  = cpjGetCpTransform(env, _this);
	cpVect*      point = cpjGetCpVect(env, _point);
	return (jlong) cpjCloneCpVect(cpTransformPoint(*this, *point));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1transformVect(JNIEnv *env, jobject _this, jobject _vect) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	cpVect*      vect = cpjGetCpVect(env, _vect);
	return (jlong) cpjCloneCpVect(cpTransformVect(*this, *vect));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1transpose__(JNIEnv *env, jobject _this) {
	cpTransform* this = cpjGetCpTransform(env, _this);
	return (jlong) cpjCloneCpTransform(cpTransformNewTranspose(this->a, this->b, this->c, this->d, this->tx, this->ty));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1transpose__DDDDDD(JNIEnv *env, jclass _class, jdouble a, jdouble b, jdouble c, jdouble d, jdouble tx, jdouble ty) {
	return (jlong) cpjCloneCpTransform(cpTransformNewTranspose(a, b, c, d, tx, ty));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1axialScale(JNIEnv *env, jclass _class, jobject _axis, jobject _pivot, jdouble scale) {
	cpVect* axis  = cpjGetCpVect(env, _axis);
	cpVect* pivot = cpjGetCpVect(env, _pivot);
	return (jlong) cpjCloneCpTransform(cpTransformAxialScale(*axis, *pivot, scale));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1boneScale(JNIEnv *env, jclass _class, jobject _offsetA, jobject _offsetB) {
	cpVect* offsetA = cpjGetCpVect(env, _offsetA);
	cpVect* offsetB = cpjGetCpVect(env, _offsetB);
	return (jlong) cpjCloneCpTransform(cpTransformBoneScale(*offsetA, *offsetB));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1ortho(JNIEnv *env, jclass _class, jobject _bb) {
	cpBB* bb = cpjGetCpBB(env, _bb);
	return (jlong) cpjCloneCpTransform(cpTransformOrtho(*bb));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1rigid(JNIEnv *env, jclass _class, jobject _offset, jdouble angle) {
	cpVect* offset = cpjGetCpVect(env, _offset);
	return (jlong) cpjCloneCpTransform(cpTransformRigid(*offset, angle));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1rotate(JNIEnv *env, jclass _class, jdouble angle) {
	return (jlong) cpjCloneCpTransform(cpTransformRotate(angle));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1scale(JNIEnv *env, jclass _class, jdouble scaleX, jdouble scaleY) {
	return (jlong) cpjCloneCpTransform(cpTransformScale(scaleX, scaleY));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1translate(JNIEnv *env, jclass _class, jobject _offset) {
	cpVect* offset = cpjGetCpVect(env, _offset);
	return (jlong) cpjCloneCpTransform(cpTransformTranslate(*offset));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1wrap(JNIEnv *env, jclass _class, jobject _outer, jobject _inner) {
	cpTransform* outer = cpjGetCpTransform(env, _outer);
	cpTransform* inner = cpjGetCpTransform(env, _inner);
	return (jlong) cpjCloneCpTransform(cpTransformWrap(*outer, *inner));
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes__1Transform__1wrapInverse(JNIEnv *env, jclass _class, jobject _outer, jobject _inner) {
	cpTransform* outer = cpjGetCpTransform(env, _outer);
	cpTransform* inner = cpjGetCpTransform(env, _inner);
	return (jlong) cpjCloneCpTransform(cpTransformWrapInverse(*outer, *inner));
}

