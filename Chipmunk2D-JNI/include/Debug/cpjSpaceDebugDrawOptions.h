#pragma once
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ChipmunkJava_Debug_SpaceDebugDrawOptions */

#ifndef _Included_ChipmunkJava_Debug_SpaceDebugDrawOptions
#define _Included_ChipmunkJava_Debug_SpaceDebugDrawOptions
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_init
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    _new
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1new
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    free
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_free
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    isDrawingCollisionPoints
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_isDrawingCollisionPoints
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    isDrawingConstraints
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_isDrawingConstraints
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    isDrawingShapes
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_isDrawingShapes
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    configDrawingCollisionPoints
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_configDrawingCollisionPoints
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    configDrawingConstraints
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_configDrawingConstraints
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    configDrawingShapes
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_configDrawingShapes
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    _getCollisionPointColor
 * Signature: ()[F
 */
JNIEXPORT jfloatArray JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1getCollisionPointColor
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    _getConstraintColor
 * Signature: ()[F
 */
JNIEXPORT jfloatArray JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1getConstraintColor
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    _getShapeOutlineColor
 * Signature: ()[F
 */
JNIEXPORT jfloatArray JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions__1getShapeOutlineColor
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    setCollisionPointColor
 * Signature: ([F)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_setCollisionPointColor
  (JNIEnv *, jobject, jfloatArray);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    setConstraintColor
 * Signature: ([F)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_setConstraintColor
  (JNIEnv *, jobject, jfloatArray);

/*
 * Class:     ChipmunkJava_Debug_SpaceDebugDrawOptions
 * Method:    setShapeOutlineColor
 * Signature: ([F)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Debug_SpaceDebugDrawOptions_setShapeOutlineColor
  (JNIEnv *, jobject, jfloatArray);

#ifdef __cplusplus
}
#endif
#endif
