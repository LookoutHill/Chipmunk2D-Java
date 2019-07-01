#pragma once
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ChipmunkJava_Collisions_ContactPointSet */

#ifndef _Included_ChipmunkJava_Collisions_ContactPointSet
#define _Included_ChipmunkJava_Collisions_ContactPointSet
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_init
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    free
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_free
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    getCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_getCount
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    getDistance
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_ContactPointSet_getDistance
  (JNIEnv *, jobject, jint);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _getDistances
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getDistances
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _getNormal
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getNormal
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _getPointA
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointA
  (JNIEnv *, jobject, jint);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _getPointB
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointB
  (JNIEnv *, jobject, jint);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _getPointsA
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointsA
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _getPointsB
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1getPointsB
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _setNormal
 * Signature: ([D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setNormal
  (JNIEnv *, jobject, jdoubleArray);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _setPointA
 * Signature: (I[D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointA
  (JNIEnv *, jobject, jint, jdoubleArray);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _setPointB
 * Signature: (I[D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointB
  (JNIEnv *, jobject, jint, jdoubleArray);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _setPointsA
 * Signature: ([D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointsA
  (JNIEnv *, jobject, jdoubleArray);

/*
 * Class:     ChipmunkJava_Collisions_ContactPointSet
 * Method:    _setPointsB
 * Signature: ([D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_ContactPointSet__1setPointsB
  (JNIEnv *, jobject, jdoubleArray);

#ifdef __cplusplus
}
#endif
#endif