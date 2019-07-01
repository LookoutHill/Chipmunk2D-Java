#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jclass _class;

jfieldID cpSpaceDebugDrawOptionsPtr;

jmethodID _colorForShape;
jmethodID _drawCircle;
jmethodID _drawDot;
jmethodID _drawFatSegment;
jmethodID _drawPolygon;
jmethodID _drawSegment;

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_init(JNIEnv *env, jclass __class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_class = (jclass) (*env)->NewGlobalRef(env, __class);

	cpSpaceDebugDrawOptionsPtr = (*env)->GetFieldID(env, _class, "cpSpaceDebugDrawOptionsPtr", "J");

	_colorForShape  = (*env)->GetStaticMethodID(env, _class, "_colorForShape",  "(JJ)[F");
	_drawCircle     = (*env)->GetStaticMethodID(env, _class, "_drawCircle",	    "([DDD[F[FJ)V");
	_drawDot        = (*env)->GetStaticMethodID(env, _class, "_drawDot",		"(D[D[FJ)V");
	_drawFatSegment = (*env)->GetStaticMethodID(env, _class, "_drawFatSegment", "([D[DD[F[FJ)V");
	_drawPolygon    = (*env)->GetStaticMethodID(env, _class, "_drawPolygon",	"([DD[F[FJ)V");
	_drawSegment    = (*env)->GetStaticMethodID(env, _class, "_drawSegment",	"([D[D[FJ)V");

	return (jlong) NULL;
}

cpSpaceDebugColor cpjColorForShape(cpShape* shape, void* options) {
	jvalue args[2];
	args[0].j = (jlong) shape;
	args[1].j = (jlong) options;

	JNIEnv* env = cpjGetEnv();

	jfloatArray _color = (jfloatArray) (*env)->CallStaticObjectMethodA(env, _class, _colorForShape, args);
	return cpjColorFromArray(env, _color);
}

void cpjDrawCircle(cpVect position, cpFloat angle, cpFloat radius, cpSpaceDebugColor outlineColor, cpSpaceDebugColor fillColor, void* options) {
	JNIEnv* env = cpjGetEnv();

	jvalue args[6];
	args[0].l = cpjVectToArray(env, &position);
	args[1].d = (jdouble) angle;
	args[2].d = (jdouble) radius;
	args[3].l = cpjColorToArray(env, &outlineColor);
	args[4].l = cpjColorToArray(env, &fillColor);
	args[5].j = (jlong) options;

	(*env)->CallStaticVoidMethodA(env, _class, _drawCircle, args);
}

void cpjDrawDot(cpFloat size, cpVect position, cpSpaceDebugColor color, void* options) {
	JNIEnv* env = cpjGetEnv();

	jvalue args[4];
	args[0].d = (jdouble) size;
	args[1].l = cpjVectToArray(env, &position);
	args[2].l = cpjColorToArray(env, &color);
	args[3].j = (jlong) options;

	(*env)->CallStaticVoidMethodA(env, _class, _drawDot, args);
}

void cpjDrawFatSegment(cpVect pointA, cpVect pointB, cpFloat radius, cpSpaceDebugColor outlineColor, cpSpaceDebugColor fillColor, void* options) {
	JNIEnv* env = cpjGetEnv();

	jvalue args[6];
	args[0].l = cpjVectToArray(env, &pointA);
	args[1].l = cpjVectToArray(env, &pointB);
	args[2].d = (jdouble) radius;
	args[3].l = cpjColorToArray(env, &outlineColor);
	args[4].l = cpjColorToArray(env, &fillColor);
	args[5].j = (jlong) options;

	(*env)->CallStaticVoidMethodA(env, _class, _drawFatSegment, args);
}

void cpjDrawPolygon(int count, const cpVect *vertices, cpFloat radius, cpSpaceDebugColor outlineColor, cpSpaceDebugColor fillColor, void* options) {
	JNIEnv* env    = cpjGetEnv();

	jvalue args[5];
	args[0].l = cpjVectsToArray(env, count, vertices);
	args[1].d = (jdouble) radius;
	args[2].l = cpjColorToArray(env, &outlineColor);
	args[3].l = cpjColorToArray(env, &fillColor);
	args[4].j = (jlong) options;

	(*env)->CallStaticVoidMethodA(env, _class, _drawPolygon, args);
}

void cpjDrawSegment(cpVect pointA, cpVect pointB, cpSpaceDebugColor color, void* options) {
	JNIEnv* env    = cpjGetEnv();

	jvalue args[4];
	args[0].l = cpjVectToArray(env, &pointA);
	args[1].l = cpjVectToArray(env, &pointB);
	args[2].l = cpjColorToArray(env, &color);
	args[3].j = (jlong) options;

	(*env)->CallStaticVoidMethodA(env, _class, _drawSegment, args);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1new(JNIEnv *env, jclass _class) {
	cpSpaceDebugDrawOptions* options = (cpSpaceDebugDrawOptions*) cpcalloc(1, sizeof(cpSpaceDebugDrawOptions));

	options->colorForShape       = cpjColorForShape;
	options->drawCircle          = cpjDrawCircle;
	options->drawDot             = cpjDrawDot;
	options->drawFatSegment      = cpjDrawFatSegment;
	options->drawPolygon         = cpjDrawPolygon;
	options->drawSegment         = cpjDrawSegment;
	options->flags               = CP_SPACE_DEBUG_DRAW_SHAPES | CP_SPACE_DEBUG_DRAW_CONSTRAINTS | CP_SPACE_DEBUG_DRAW_COLLISION_POINTS;
	options->shapeOutlineColor   = cpjColorFromRGBIII( 44,  62,  80);
	options->constraintColor     = cpjColorFromRGBIII(142,  68, 173);
	options->collisionPointColor = cpjColorFromRGBIII(231,  76,  60);
	options->data                = options;

	return (jlong) options;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_free(JNIEnv *env, jobject this) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);
	cpfree(options);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_isDrasingCollisionPoints(JNIEnv *env, jobject this) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);
	return (options->flags & CP_SPACE_DEBUG_DRAW_COLLISION_POINTS) != 0;
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_isDrasingConstraints(JNIEnv *env, jobject this) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);
	return (options->flags & CP_SPACE_DEBUG_DRAW_CONSTRAINTS) != 0;
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_isDrawingShapes(JNIEnv *env, jobject this) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);
	return (options->flags & CP_SPACE_DEBUG_DRAW_SHAPES) != 0;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_configDrawingCollisionPoints(JNIEnv *env, jobject this, jboolean value) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);
	if(value) options->flags |= CP_SPACE_DEBUG_DRAW_COLLISION_POINTS;
	else      options->flags &= ~CP_SPACE_DEBUG_DRAW_COLLISION_POINTS;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_configDrawingConstraints(JNIEnv *env, jobject this, jboolean value) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);
	if(value) options->flags |= CP_SPACE_DEBUG_DRAW_CONSTRAINTS;
	else      options->flags &= ~CP_SPACE_DEBUG_DRAW_CONSTRAINTS;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_configDrawingShapes(JNIEnv *env, jobject this, jboolean value) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);
	if(value) options->flags |= CP_SPACE_DEBUG_DRAW_SHAPES;
	else      options->flags &= ~CP_SPACE_DEBUG_DRAW_SHAPES;
}

JNIEXPORT jfloatArray JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1getCollisionPointColor(JNIEnv *env, jobject this) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);

	return cpjColorToArray(env, &options->collisionPointColor);
}

JNIEXPORT jfloatArray JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1getConstraintColor(JNIEnv *env, jobject this) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);

	return cpjColorToArray(env, &options->constraintColor);
}

JNIEXPORT jfloatArray JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1getShapeOutlineColor(JNIEnv *env, jobject this) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);

	return cpjColorToArray(env, &options->shapeOutlineColor);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_setCollisionPointColor(JNIEnv *env, jobject this, jfloatArray color) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);

	options->collisionPointColor = cpjColorFromArray(env, color);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_setConstraintColor(JNIEnv *env, jobject this, jfloatArray color) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);

	options->constraintColor = cpjColorFromArray(env, color);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_setShapeOutlineColor(JNIEnv *env, jobject this, jfloatArray color) {
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, this);

	options->shapeOutlineColor = cpjColorFromArray(env, color);
}

