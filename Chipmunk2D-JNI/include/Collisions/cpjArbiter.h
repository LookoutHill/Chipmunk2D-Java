#pragma once
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ChipmunkJava_Collisions_Arbiter */

#ifndef _Included_ChipmunkJava_Collisions_Arbiter
#define _Included_ChipmunkJava_Collisions_Arbiter
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_Arbiter_init
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    ignore
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Collisions_Arbiter_ignore
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    isFirstContact
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Collisions_Arbiter_isFirstContact
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    isRemoval
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Collisions_Arbiter_isRemoval
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getBodies
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getBodies
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getContactPointSet
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getContactPointSet
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    getCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_ChipmunkJava_Collisions_Arbiter_getCount
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    getDepth
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter_getDepth
  (JNIEnv *, jobject, jint);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    getDepths
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter_getDepths
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    getFriction
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter_getFriction
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getNormal
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getNormal
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getPointA
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointA
  (JNIEnv *, jobject, jint);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getPointB
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointB
  (JNIEnv *, jobject, jint);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getPointsA
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointsA
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getPointsB
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getPointsB
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    getRestitution
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter_getRestitution
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getShapes
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getShapes
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getSurfaceVelocity
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getSurfaceVelocity
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _getTotalImpulse
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Collisions_Arbiter__1getTotalImpulse
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    getTotalKE
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Collisions_Arbiter_getTotalKE
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    setContactPointSet
 * Signature: (LChipmunkJava/Collisions/ContactPointSet;)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter_setContactPointSet
  (JNIEnv *, jobject, jobject);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    setFriction
 * Signature: (D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter_setFriction
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    setRestitution
 * Signature: (D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter_setRestitution
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     ChipmunkJava_Collisions_Arbiter
 * Method:    _setSurfaceVelocity
 * Signature: ([D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Collisions_Arbiter__1setSurfaceVelocity
  (JNIEnv *, jobject, jdoubleArray);

#ifdef __cplusplus
}
#endif
#endif
