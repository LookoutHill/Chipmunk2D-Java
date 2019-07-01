#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

JavaVM* jvm;

jfieldID cpSpacePtr;

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Space_init(JNIEnv *env, jclass _class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	cpSpacePtr = (*env)->GetFieldID(env, _class, "cpSpacePtr", "J");

	return (jlong) NULL;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Space__1new(JNIEnv *env, jclass _class) {
	return (jlong) cpSpaceNew();
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_free(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceFree(space);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_addBody(JNIEnv *env, jobject this, jobject _body) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpBody*  body  = cpjGetCpBody(env, _body);
	cpSpaceAddBody(space, body);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_removeBody(JNIEnv *env, jobject this, jobject _body) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpBody*  body  = cpjGetCpBody(env, _body);
	cpSpaceRemoveBody(space, body);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_addConstraint(JNIEnv *env, jobject this, jobject _constraint) {
	cpSpace*      space      = cpjGetCpSpace(env, this);
	cpConstraint* constraint = cpjGetCpConstraint(env, _constraint);
	cpSpaceAddConstraint(space, constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_removeConstraint(JNIEnv *env, jobject this, jobject _constraint) {
	cpSpace*      space      = cpjGetCpSpace(env, this);
	cpConstraint* constraint = cpjGetCpConstraint(env, _constraint);
	cpSpaceRemoveConstraint(space, constraint);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_addShape(JNIEnv *env, jobject this, jobject _shape) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpShape* shape = cpjGetCpShape(env, _shape);
	cpSpaceAddShape(space, shape);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_removeShape(JNIEnv *env, jobject this, jobject _shape) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpShape* shape = cpjGetCpShape(env, _shape);
	cpSpaceRemoveShape(space, shape);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Space_containsBody(JNIEnv *env, jobject this, jobject _body) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpBody*  body  = cpjGetCpBody(env, _body);
	return cpSpaceContainsBody(space, body);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Space_containsConstraint(JNIEnv *env, jobject this, jobject _constraint) {
	cpSpace*      space      = cpjGetCpSpace(env, this);
	cpConstraint* constraint = cpjGetCpConstraint(env, _constraint);
	return cpSpaceContainsConstraint(space, constraint);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Space_containsShape(JNIEnv *env, jobject this, jobject _shape) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpShape* shape = cpjGetCpShape(env, _shape);
	return cpSpaceContainsShape(space, shape);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Space_isLocked(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceIsLocked(space);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_reindexShape(JNIEnv *env, jobject this, jobject _shape) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpShape* shape = cpjGetCpShape(env, _shape);
	cpSpaceReindexShape(space, shape);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_reindexShapesForBody(JNIEnv *env, jobject this, jobject _body) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpBody*  body  = cpjGetCpBody(env, _body);
	cpSpaceReindexShapesForBody(space, body);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_reindexStatic(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceReindexStatic(space);
}

static void _cpjBBQueryFunc(cpShape *shape, void *list) {
	cpjAddToPtrArrayList((cpjPtrArrayList*) list, shape);
}

JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Space__1BBQuery(JNIEnv *env, jobject this, jdoubleArray _bb, jlongArray _filter) {
	cpSpace*      space  = cpjGetCpSpace(env, this);
	cpBB          bb     = cpjBBFromArray(env, _bb);
	cpShapeFilter filter = cpjShapeFilterFromArray(env, _filter);

	cpjPtrArrayList ptrs = cpjNewPtrArrayList();
	cpSpaceBBQuery(space, bb, filter, _cpjBBQueryFunc, (void*) &ptrs);

	jlongArray array = (*env)->NewLongArray(env, ptrs.count);
	(*env)->SetLongArrayRegion(env, array, 0, ptrs.count, (jlong*) ptrs.array);

	cpjFreePtrArrayList(&ptrs);

	return array;
}

static void _cpjPointQueryFunc(cpShape *shape, cpVect point, cpFloat distance, cpVect gradient, void *list) {
	cpPointQueryInfo* info = cpjNewCpPointQueryInfo();

	info->shape    = shape;
	info->point    = point;
	info->distance = distance;
	info->gradient = gradient;

	cpjAddToPtrArrayList((cpjPtrArrayList*) list, info);
}

JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Space__1pointQuery(JNIEnv *env, jobject this, jdoubleArray _point, jdouble radius, jlongArray _filter) {
	cpSpace*      space  = cpjGetCpSpace(env, this);
	cpVect        point  = cpjVectFromArray(env, _point);
	cpShapeFilter filter = cpjShapeFilterFromArray(env, _filter);

	cpjPtrArrayList ptrs = cpjNewPtrArrayList();
	cpSpacePointQuery(space, point, radius, filter, _cpjPointQueryFunc, (void*) &ptrs);

	jlongArray array = (*env)->NewLongArray(env, ptrs.count);
	(*env)->SetLongArrayRegion(env, array, 0, ptrs.count, (jlong*) ptrs.array);

	cpjFreePtrArrayList(&ptrs);

	return array;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Space__1pointQueryNearest(JNIEnv *env, jobject this, jdoubleArray _point, jdouble radius, jlongArray _filter) {
	cpSpace*          space  = cpjGetCpSpace(env, this);
	cpVect            point  = cpjVectFromArray(env, _point);
	cpShapeFilter     filter = cpjShapeFilterFromArray(env, _filter);
	cpPointQueryInfo* info   = cpjNewCpPointQueryInfo();
	cpSpacePointQueryNearest(space, point, radius, filter, info);
	return (jlong) info;
}

static void _cpjSegmentQueryFunc(cpShape *shape, cpVect point, cpVect normal, cpFloat alpha, void *list) {
	cpSegmentQueryInfo* info = cpjNewCpSegmentQueryInfo();

	info->shape  = shape;
	info->point  = point;
	info->normal = normal;
	info->alpha  = alpha;

	cpjAddToPtrArrayList((cpjPtrArrayList*) list, info);
}

JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Space__1segmentQuery(JNIEnv *env, jobject this, jdoubleArray _pointA, jdoubleArray _pointB, jdouble radius, jlongArray _filter) {
	cpSpace*      space  = cpjGetCpSpace(env, this);
	cpVect        pointA = cpjVectFromArray(env, _pointA);
	cpVect        pointB = cpjVectFromArray(env, _pointB);
	cpShapeFilter filter = cpjShapeFilterFromArray(env, _filter);

	cpjPtrArrayList ptrs = cpjNewPtrArrayList();
	cpSpaceSegmentQuery(space, pointA, pointB, radius, filter, _cpjSegmentQueryFunc, (void*) &ptrs);

	jlongArray array = (*env)->NewLongArray(env, ptrs.count);
	(*env)->SetLongArrayRegion(env, array, 0, ptrs.count, (jlong*) ptrs.array);

	cpjFreePtrArrayList(&ptrs);

	return array;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Space__1segmentQueryFirst(JNIEnv *env, jobject this, jdoubleArray _pointA, jdoubleArray _pointB, jdouble radius, jlongArray _filter) {
	cpSpace*            space  = cpjGetCpSpace(env, this);
	cpVect              pointA = cpjVectFromArray(env, _pointA);
	cpVect              pointB = cpjVectFromArray(env, _pointB);
	cpShapeFilter       filter = cpjShapeFilterFromArray(env, _filter);
	cpSegmentQueryInfo* info   = cpjNewCpSegmentQueryInfo();
	cpSpaceSegmentQueryFirst(space, pointA, pointB, radius, filter, info);
	return (jlong) info;
}

static void _cpjShapeQueryFunc(cpShape *shape, cpContactPointSet *cps, void *list) {
	cpjShapeQueryInfo* info = cpjNewCpShapeQueryInfo();

	info->shape  = shape;
	info->cps    = *cps;

	cpjAddToPtrArrayList((cpjPtrArrayList*) list, info);
}

JNIEXPORT jlongArray JNICALL Java_ChipmunkJava_Space__1shapeQuery(JNIEnv *env, jobject this, jobject _shape) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpShape* shape = cpjGetCpShape(env, _shape);

	cpjPtrArrayList ptrs = cpjNewPtrArrayList();
	cpSpaceShapeQuery(space, shape, _cpjShapeQueryFunc, (void*) &ptrs);

	jlongArray array = (*env)->NewLongArray(env, ptrs.count);
	(*env)->SetLongArrayRegion(env, array, 0, ptrs.count, (jlong*) ptrs.array);

	cpjFreePtrArrayList(&ptrs);

	return array;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Space_getCollisionBias(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetCollisionBias(space);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Space__1getCollisionPersistence(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetCollisionPersistence(space);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Space_getCollisionSlop(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetCollisionSlop(space);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Space_getCurrentTimeStep(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetCurrentTimeStep(space);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Space_getDamping(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetDamping(space);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Space__1getGravity(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpVect   vect  = cpSpaceGetGravity(space);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Space_getIdleSpeedThreshold(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetIdleSpeedThreshold(space);
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_Space_getIterations(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetIterations(space);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Space_getSleepTimeThreshold(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return cpSpaceGetSleepTimeThreshold(space);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Space__1getStaticBody(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpBody*  body  = cpSpaceGetStaticBody(space);
	return (jlong) body;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Space_getStepCount(JNIEnv *env, jobject this) {
	cpSpace* space = cpjGetCpSpace(env, this);
	return space->stamp;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_setCollisionBias(JNIEnv *env, jobject this, jdouble value) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceSetCollisionBias(space, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_setCollisionPersistence(JNIEnv *env, jobject this, jlong value) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceSetCollisionPersistence(space, (cpTimestamp) value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_setCollisionSlop(JNIEnv *env, jobject this, jdouble value) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceSetCollisionSlop(space, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_setDamping(JNIEnv *env, jobject this, jdouble value) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceSetDamping(space, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space__1setGravity(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpVect   vect  = cpjVectFromArray(env, _vect);
	cpSpaceSetGravity(space, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_setIdleSpeedThreshold(JNIEnv *env, jobject this, jdouble value) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceSetIdleSpeedThreshold(space, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_setIterations(JNIEnv *env, jobject this, jint value) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceSetIterations(space, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_setSleepTimeThreshold(JNIEnv *env, jobject this, jdouble value) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceSetSleepTimeThreshold(space, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_useSpatialHash(JNIEnv *env, jobject this, jdouble size, jint count) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceUseSpatialHash(space, size, count);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_debugDraw(JNIEnv *env, jobject this, jobject _options) {
	cpSpace*                 space   = cpjGetCpSpace(env, this);
	cpSpaceDebugDrawOptions* options = cpjGetCpSpaceDebugDrawOptions(env, _options);
	cpSpaceDebugDraw(space, options);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Space_step(JNIEnv *env, jobject this, jdouble interval) {
	cpSpace* space = cpjGetCpSpace(env, this);
	cpSpaceStep(space, interval);
}

