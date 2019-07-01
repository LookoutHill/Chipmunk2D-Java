#pragma once
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ChipmunkJava_Constraints_DampedRotarySpring */

#ifndef _Included_ChipmunkJava_Constraints_DampedRotarySpring
#define _Included_ChipmunkJava_Constraints_DampedRotarySpring
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_init
  (JNIEnv *, jclass);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    _new
 * Signature: (LChipmunkJava/Body;LChipmunkJava/Body;DDD)J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring__1new
  (JNIEnv *, jclass, jobject, jobject, jdouble, jdouble, jdouble);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    _clone
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring__1clone
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    getDamping
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_getDamping
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    getRestAngle
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_getRestAngle
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    getStiffness
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_getStiffness
  (JNIEnv *, jobject);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    setDamping
 * Signature: (D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_setDamping
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    setRestAngle
 * Signature: (D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_setRestAngle
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     ChipmunkJava_Constraints_DampedRotarySpring
 * Method:    setStiffness
 * Signature: (D)V
 */
JNIEXPORT void JNICALL Java_ChipmunkJava_Constraints_DampedRotarySpring_setStiffness
  (JNIEnv *, jobject, jdouble);

#ifdef __cplusplus
}
#endif
#endif
