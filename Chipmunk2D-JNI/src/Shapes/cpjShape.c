#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jfieldID cpShapePtr;

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_Shape_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpShapePtr = (*env)->GetFieldID(env, _class, "cpShapePtr", "J");

	return (jlong) NULL;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_free(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShapeFree(shape);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Shapes_Shape_isSensor(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpShapeGetSensor(shape);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_configSensor(JNIEnv *env, jobject this, jboolean value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShapeSetSensor(shape, value);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_Shape__1cacheBB(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpBB     bb    = cpShapeCacheBB(shape);
	return cpjBBToArray(env, &bb);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_Shape__1update(JNIEnv *env, jobject this, jdoubleArray _transform) {
	cpShape*    shape     = cpjGetCpShape(env, this);
	cpTransform transform = cpjTransformFromArray(env, _transform);
	cpBB        bb        = cpShapeUpdate(shape, transform);
	return cpjBBToArray(env, &bb);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_Shape__1pointQuery(JNIEnv *env, jobject this, jdoubleArray _point) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   point = cpjVectFromArray(env, _point);

	cpPointQueryInfo* info = cpjNewCpPointQueryInfo();
	cpShapePointQuery(shape, point, info);
	return (jlong) info;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_Shape__1pointQueryDistance(JNIEnv *env, jobject this, jdoubleArray _point) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   point = cpjVectFromArray(env, _point);

	return cpShapePointQuery(shape, point, NULL);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_Shape__1segmentQuery(JNIEnv *env, jobject this, jdoubleArray _pointA, jdoubleArray _pointB, jdouble radius) {
	cpShape* shape  = cpjGetCpShape(env, this);
	cpVect   pointA = cpjVectFromArray(env, _pointA);
	cpVect   pointB = cpjVectFromArray(env, _pointB);

	cpSegmentQueryInfo* info = cpjNewCpSegmentQueryInfo();
	return cpShapeSegmentQuery(shape, pointA, pointB, radius, info);
	return (jlong) info;
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Shapes_Shape__1segmentQueryMatch(JNIEnv *env, jobject this, jdoubleArray _pointA, jdoubleArray _pointB, jdouble radius) {
	cpShape* shape  = cpjGetCpShape(env, this);
	cpVect   pointA = cpjVectFromArray(env, _pointA);
	cpVect   pointB = cpjVectFromArray(env, _pointB);

	return cpShapeSegmentQuery(shape, pointA, pointB, radius, NULL);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_Shape_getArea(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpShapeGetArea(shape);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_Shape__1getBB(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpBB     bb    = cpShapeGetBB(shape);
	return cpjBBToArray(env, &bb);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_Shape__1getBody(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpBody*  body  = cpShapeGetBody(shape);
	return (jlong) body;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_Shape__1getCenterOfGravity(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpShapeGetCenterOfGravity(shape);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_Shape__1getCollisionType(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return (jlong) cpShapeGetCollisionType(shape);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_Shape__1getContactPointSet(JNIEnv *env, jobject _this, jobject _that) {
	cpShape* this = cpjGetCpShape(env, _this);
	cpShape* that = cpjGetCpShape(env, _that);
	return (jlong) cpjCloneCpContactPointSet(cpShapesCollide(this, that));
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_Shape_getDensity(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpShapeGetDensity(shape);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_Shape_getElasticity(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpShapeGetElasticity(shape);
}

JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Shapes_Shape__1getFilter(JNIEnv *env, jobject this) {
	cpShape*      shape  = cpjGetCpShape(env, this);
	cpShapeFilter filter = cpShapeGetFilter(shape);
	return cpjShapeFilterToArray(env, &filter);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_Shape_getFriction(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpShapeGetFriction(shape);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_Shape_getMass(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpShapeGetMass(shape);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_Shape_getMoment(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpShapeGetMoment(shape);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_Shape__1getSpace(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpSpace* space = cpShapeGetSpace(shape);
	return (jlong) space;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_Shape__1getSurfaceVelocity(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpShapeGetSurfaceVelocity(shape);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_setBody(JNIEnv *env, jobject this, jobject _body) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpBody*  body  = cpjGetCpBody(env, _body);
	cpShapeSetBody(shape, body);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_setCollisionType(JNIEnv *env, jobject this, jobject _ctype) {
	cpShape*        shape = cpjGetCpShape(env, this);
	cpCollisionType ctype = cpjGetCpCollisionType(env, _ctype);
	cpShapeSetCollisionType(shape, ctype);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_setDensity(JNIEnv *env, jobject this, jdouble value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShapeSetDensity(shape, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_setElasticity(JNIEnv *env, jobject this, jdouble value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShapeSetElasticity(shape, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_setFilter(JNIEnv *env, jobject this, jlongArray _filter) {
	cpShape*      shape  = cpjGetCpShape(env, this);
	cpShapeFilter filter = cpjShapeFilterFromArray(env, _filter);
	cpShapeSetFilter(shape, filter);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_setFriction(JNIEnv *env, jobject this, jdouble value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShapeSetFriction(shape, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape_setMass(JNIEnv *env, jobject this, jdouble value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpShapeSetMass(shape, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_Shape__1setSurfaceVelocity(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpjVectFromArray(env, _vect);
	cpShapeSetSurfaceVelocity(shape, vect);
}

