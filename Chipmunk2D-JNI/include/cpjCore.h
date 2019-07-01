#pragma once

#ifndef _Included_Core
#define _Included_Core
#ifdef __cplusplus
extern "C" {
#endif

#include "chipmunk\chipmunk_structs.h"

#include "cpjBody.h"
#include "cpjBodyType.h"
#include "cpjChipmunk.h"
#include "cpjSpace.h"
#include "Collisions\cpjArbiter.h"
#include "Collisions\cpjCollisionSet.h"
#include "Collisions\cpjCollisionType.h"
#include "Collisions\cpjContactPointSet.h"
#include "Collisions\cpjPointQueryInfo.h"
#include "Collisions\cpjSegmentQueryInfo.h"
#include "Collisions\cpjShapeFilter.h"
#include "Collisions\cpjShapeQueryInfo.h"
#include "Constraints\cpjConstraint.h"
#include "Constraints\cpjDampedRotarySpring.h"
#include "Constraints\cpjDampedSpring.h"
#include "Constraints\cpjGearJoint.h"
#include "Constraints\cpjGrooveJoint.h"
#include "Constraints\cpjPinJoint.h"
#include "Constraints\cpjPivotJoint.h"
#include "Constraints\cpjRatchetJoint.h"
#include "Constraints\cpjRotaryLimitJoint.h"
#include "Constraints\cpjSimpleMotor.h"
#include "Constraints\cpjSlideJoint.h"
#include "Debug\cpjSpaceDebugDrawOptions.h"
#include "Shapes\cpjCircleShape.h"
#include "Shapes\cpjPolyShape.h"
#include "Shapes\cpjSegmentShape.h"
#include "Shapes\cpjShape.h"

#include "cpjBB.h"               // This is for the _BB class, not the BB class.
#include "cpjVect.h"             // This is for the _Vect class, not the Vect class.
#include "Shapes\cpjTransform.h" // This is for the _Transform class, not the Transform class.

extern inline JNIEnv*                  cpjGetEnv();

extern inline cpArbiter*               cpjGetCpArbiter(JNIEnv*, jobject);
extern inline cpBody*                  cpjGetCpBody(JNIEnv*, jobject);
extern inline cpBodyType               cpjGetCpBodyType(JNIEnv*, jobject);
extern inline cpCollisionHandler*      cpjGetCpCollisionSet(JNIEnv*, jobject);
extern inline cpCollisionType          cpjGetCpCollisionType(JNIEnv*, jobject);
extern inline cpConstraint*            cpjGetCpConstraint(JNIEnv*, jobject);
extern inline cpContactPointSet*       cpjGetCpContactPointSet(JNIEnv*, jobject);
extern inline cpContactPointSet*       cpjNewCpContactPointSet();
extern inline cpContactPointSet*       cpjCloneCpContactPointSet(cpContactPointSet);
extern inline cpPointQueryInfo*        cpjGetCpPointQueryInfo(JNIEnv*, jobject);
extern inline cpPointQueryInfo*        cpjNewCpPointQueryInfo();
extern inline cpSegmentQueryInfo*      cpjGetCpSegmentQueryInfo(JNIEnv*, jobject);
extern inline cpSegmentQueryInfo*      cpjNewCpSegmentQueryInfo();

typedef struct cpjShapeQueryInfo {
	const cpShape*          shape;
	      cpContactPointSet cps;
} cpjShapeQueryInfo;

extern inline cpjShapeQueryInfo*       cpjGetCpjShapeQueryInfo(JNIEnv*, jobject);
extern inline cpjShapeQueryInfo*       cpjNewCpShapeQueryInfo();

extern inline cpShape*                 cpjGetCpShape(JNIEnv*, jobject);
extern inline cpSpace*                 cpjGetCpSpace(JNIEnv*, jobject);
extern inline cpSpaceDebugDrawOptions* cpjGetCpSpaceDebugDrawOptions(JNIEnv*, jobject);

extern inline cpBB                     cpjBBFromArray(JNIEnv*, jdoubleArray);
extern inline jdoubleArray             cpjBBToArray(JNIEnv*, cpBB*);

extern inline cpVect                   cpjVectFromArray(JNIEnv*, jdoubleArray);
extern inline jdoubleArray             cpjVectToArray(JNIEnv*, cpVect*);
extern inline cpVect*                  cpjVectsFromArray(JNIEnv*, jdoubleArray);
extern inline jdoubleArray             cpjVectsToArray(JNIEnv*, jsize, const cpVect*);

extern inline cpTransform              cpjTransformFromArray(JNIEnv*, jdoubleArray);
extern inline jdoubleArray             cpjTransformToArray(JNIEnv*, cpTransform*);

extern inline cpShapeFilter            cpjShapeFilterFromArray(JNIEnv*, jlongArray);
extern inline jlongArray               cpjShapeFilterToArray(JNIEnv*, cpShapeFilter*);

extern inline cpSpaceDebugColor        cpjColorFromRGBIII(int, int, int);
extern inline cpSpaceDebugColor        cpjColorFromArray(JNIEnv*, jfloatArray);
extern inline jfloatArray              cpjColorToArray(JNIEnv*, cpSpaceDebugColor*);

typedef struct cpjPtrArrayList {
	int    count;
	jlong* array;
} cpjPtrArrayList;

extern inline cpjPtrArrayList          cpjNewPtrArrayList();
extern inline void                     cpjFreePtrArrayList(cpjPtrArrayList*);
extern inline void                     cpjAddToPtrArrayList(cpjPtrArrayList*, void*);

extern inline cpBB*                    cpjGetCpBB(JNIEnv*, jobject);
extern inline cpBB*                    cpjNewCpBB(cpFloat, cpFloat, cpFloat, cpFloat);
extern inline cpBB*                    cpjCloneCpBB(cpBB);

extern inline cpVect*                  cpjGetCpVect(JNIEnv*, jobject);
extern inline cpVect*                  cpjNewCpVect(cpFloat, cpFloat);
extern inline cpVect*                  cpjCloneCpVect(cpVect);

extern inline cpTransform*             cpjGetCpTransform(JNIEnv*, jobject);
extern inline cpTransform*             cpjNewCpTransform(cpFloat, cpFloat, cpFloat, cpFloat, cpFloat, cpFloat);
extern inline cpTransform*             cpjCloneCpTransform(cpTransform);

#ifdef __cplusplus
}
#endif
#endif
