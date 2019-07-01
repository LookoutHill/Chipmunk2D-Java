#pragma once
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ChipmunkJava_Collisions_PointQueryInfo */

#ifndef _Included_ChipmunkJava_Collisions_PointQueryInfo
#define _Included_ChipmunkJava_Collisions_PointQueryInfo
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ChipmunkJava_Collisions_PointQueryInfo
 * Method:    init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo_init
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Collisions_PointQueryInfo
 * Method:    free
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo_free
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_PointQueryInfo
 * Method:    getDistance
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo_getDistance
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_PointQueryInfo
 * Method:    _getGradient
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo__1getGradient
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_PointQueryInfo
 * Method:    _getPoint
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo__1getPoint
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_PointQueryInfo
 * Method:    _getShape
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_PointQueryInfo__1getShape
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
