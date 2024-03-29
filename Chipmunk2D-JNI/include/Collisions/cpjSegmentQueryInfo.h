#pragma once
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ChipmunkJava_Collisions_SegmentQueryInfo */

#ifndef _Included_ChipmunkJava_Collisions_SegmentQueryInfo
#define _Included_ChipmunkJava_Collisions_SegmentQueryInfo
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ChipmunkJava_Collisions_SegmentQueryInfo
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo_init
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Collisions_SegmentQueryInfo
 * Method:    free
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo_free
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_SegmentQueryInfo
 * Method:    getAlpha
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo_getAlpha
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_SegmentQueryInfo
 * Method:    _getNormal
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo__1getNormal
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_SegmentQueryInfo
 * Method:    _getPoint
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo__1getPoint
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_SegmentQueryInfo
 * Method:    _getShape
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_SegmentQueryInfo__1getShape
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
