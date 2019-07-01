#pragma once

#include <jni.h>
#include <memory.h>
#include <stdio.h>
#include "cpjCore.h"
#include "Shapes\cpjShape.h"
#include "chipmunk\chipmunk_unsafe.h"

extern JavaVM* jvm;

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_PolyShape_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_PolyShape__1new(JNIEnv *env, jclass _class, jobject _body, jdoubleArray _vertices, jdouble radius) {
	cpBody* body = cpjGetCpBody(env, _body);

	cpVect* vertices = cpjVectsFromArray(env, _vertices);
	jsize   count    = (*env)->GetArrayLength(env, _vertices);

	cpShape* shape = cpPolyShapeNew(body, count, vertices, cpTransformIdentity, radius);

	cpfree(vertices);

	return (jlong) shape;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_PolyShape__1new2(JNIEnv *env, jclass _class, jobject _body, jdoubleArray _vertices, jdoubleArray _transform, jdouble radius) {
	cpBody* body = cpjGetCpBody(env, _body);

	cpVect* vertices = cpjVectsFromArray(env, _vertices);
	jsize   count    = (*env)->GetArrayLength(env, _vertices);

	cpTransform transform = cpjTransformFromArray(env, _transform);

	cpShape* shape = cpPolyShapeNew(body, count, vertices, transform, radius);

	cpfree(vertices);

	return (jlong) shape;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_PolyShape__1newBox(JNIEnv *env, jclass _class, jobject _body, jdouble width, jdouble height, jdouble radius) {
	cpBody* body = cpjGetCpBody(env, _body);

	return (jlong) cpBoxShapeNew(body, width, height, radius);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Shapes_PolyShape__1clone(JNIEnv *env, jobject this) {
	cpShape*     shape     = cpjGetCpShape(env, this);
	cpPolyShape* polyShape = (cpPolyShape*) shape;

	cpShape*     clone     = (cpShape*) cpPolyShapeAlloc();
	cpPolyShape* polyClone = (cpPolyShape*) clone;
	memcpy(clone, shape, sizeof(cpPolyShape));

	int count = polyShape->count;
	if(count > CP_POLY_SHAPE_INLINE_ALLOC) {
		polyClone->planes = (struct cpSplittingPlane*) cpcalloc(2*count, sizeof(struct cpSplittingPlane));
		memcpy(polyClone->planes, polyShape->planes, 2*count * sizeof(struct cpSplittingPlane));
	}

	return (jlong) clone;
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_PolyShape__1getVertex(JNIEnv *env, jobject this, jint pos) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpVect   vect  = cpPolyShapeGetVert(shape, pos);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Shapes_PolyShape__1getVertices(JNIEnv *env, jobject this) {
	cpShape*     shape     = cpjGetCpShape(env, this);
	cpPolyShape* polyShape = (cpPolyShape*) shape;

	jsize   count     = polyShape->count;
	cpVect* _vertices = (cpVect*) cpcalloc(count, sizeof(cpVect));
	for(jsize pos = 0; pos < count; pos++) {
		_vertices[pos] = cpPolyShapeGetVert(shape, pos);
	}

	jdoubleArray vertices = cpjVectsToArray(env, count, _vertices);

	cpfree(_vertices);

	return vertices;
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_Shapes_PolyShape_getCount(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpPolyShapeGetCount(shape);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Shapes_PolyShape_getRadius(JNIEnv *env, jobject this) {
	cpShape* shape = cpjGetCpShape(env, this);
	return cpPolyShapeGetRadius(shape);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_PolyShape__1setVertices___3D(JNIEnv *env, jobject this, jdoubleArray _vertices) {
	cpShape* shape = cpjGetCpShape(env, this);

	cpVect* vertices = cpjVectsFromArray(env, _vertices);
	jsize   count    = (*env)->GetArrayLength(env, _vertices);

	cpPolyShapeSetVerts(shape, count, vertices, cpTransformIdentity);

	cpfree(vertices);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_PolyShape__1setVertices___3D_3D(JNIEnv *env, jobject this, jdoubleArray _vertices, jdoubleArray _transform) {
	cpShape* shape = cpjGetCpShape(env, this);

	cpVect* vertices = cpjVectsFromArray(env, _vertices);
	jsize   count    = (*env)->GetArrayLength(env, _vertices);

	cpTransform transform = cpjTransformFromArray(env, _transform);

	cpPolyShapeSetVerts(shape, count, vertices, transform);

	cpfree(vertices);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Shapes_PolyShape_setRadius(JNIEnv *env, jobject this, jdouble value) {
	cpShape* shape = cpjGetCpShape(env, this);
	cpPolyShapeSetRadius(shape, value);
}

