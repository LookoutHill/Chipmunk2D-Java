#include <jni.h>
#include <memory.h>
#include <stdio.h>

#include "cpjCore.h"

#include "cpjBody.c"
#include "cpjBodyType.c"
#include "cpjChipmunk.c"
#include "cpjSpace.c"
#include "Collisions\cpjArbiter.c"
#include "Collisions\cpjCollisionSet.c"
#include "Collisions\cpjCollisionType.c"
#include "Collisions\cpjContactPointSet.c"
#include "Collisions\cpjPointQueryInfo.c"
#include "Collisions\cpjSegmentQueryInfo.c"
#include "Collisions\cpjShapeFilter.c"
#include "Collisions\cpjShapeQueryInfo.c"
#include "Constraints\cpjConstraint.c"
#include "Constraints\cpjDampedRotarySpring.c"
#include "Constraints\cpjDampedSpring.c"
#include "Constraints\cpjGearJoint.c"
#include "Constraints\cpjGrooveJoint.c"
#include "Constraints\cpjPinJoint.c"
#include "Constraints\cpjPivotJoint.c"
#include "Constraints\cpjRatchetJoint.c"
#include "Constraints\cpjRotaryLimitJoint.c"
#include "Constraints\cpjSimpleMotor.c"
#include "Constraints\cpjSlideJoint.c"
#include "Debug\cpjSpaceDebugDrawOptions.c"
#include "Shapes\cpjCircleShape.c"
#include "Shapes\cpjPolyShape.c"
#include "Shapes\cpjSegmentShape.c"
#include "Shapes\cpjShape.c"

#include "cpjBB.c"               // This is for the _BB class, not the BB class.
#include "cpjVect.c"             // This is for the _Vect class, not the Vect class.
#include "Shapes\cpjTransform.c" // This is for the _Transform class, not the Transform class.

extern JavaVM* jvm;

extern jfieldID cpArbiterPtr;
extern jfieldID cpBodyPtr;
extern jfieldID _cpBodyType;
extern jfieldID cpCollisionHandlerPtr;
extern jfieldID _cpCollisionType;
extern jfieldID cpConstraintPtr;
extern jfieldID cpContactPointSetPtr;
extern jfieldID cpPointQueryInfoPtr;
extern jfieldID cpSegmentQueryInfoPtr;
extern jfieldID cpjShapeQueryInfoPtr;
extern jfieldID cpShapePtr;
extern jfieldID cpSpacePtr;
extern jfieldID cpSpaceDebugDrawOptionsPtr;

extern jfieldID cpBBPtr;        // This is for the _BB class, not the BB class.
extern jfieldID cpVectPtr;      // This is for the _Vect class, not the Vect class.
extern jfieldID cpTransformPtr; // This is for the _Transform class, not the Transform class.

inline JNIEnv* cpjGetEnv() {
	JNIEnv* env;
	jint jniStatus = (*jvm)->GetEnv(jvm, (void**) &env, JNI_VERSION_1_6);
	cpAssertHard(jniStatus == JNI_OK, "Failed to get JVM env.");
	return env;
}

inline cpArbiter* cpjGetCpArbiter(JNIEnv* env, jobject this) {
	return (cpArbiter*)(*env)->GetLongField(env, this, cpArbiterPtr);
}

inline cpBody* cpjGetCpBody(JNIEnv* env, jobject this) {
	return (cpBody*) (*env)->GetLongField(env, this, cpBodyPtr);
}

inline cpBodyType cpjGetCpBodyType(JNIEnv* env, jobject this) {
	return (*env)->GetIntField(env, this, _cpBodyType);
}

inline cpCollisionHandler* cpjGetCpCollisionSet(JNIEnv* env, jobject this) {
	return (cpCollisionHandler*) (*env)->GetLongField(env, this, cpCollisionHandlerPtr);
}

inline cpCollisionType cpjGetCpCollisionType(JNIEnv* env, jobject this) {
	return (*env)->GetLongField(env, this, _cpCollisionType);
}

inline cpConstraint* cpjGetCpConstraint(JNIEnv* env, jobject this) {
	return (cpConstraint*) (*env)->GetLongField(env, this, cpConstraintPtr);
}

inline cpContactPointSet* cpjGetCpContactPointSet(JNIEnv* env, jobject this) {
	return (cpContactPointSet*)(*env)->GetLongField(env, this, cpContactPointSetPtr);
}

inline cpContactPointSet* cpjNewCpContactPointSet() {
	return (cpContactPointSet*) cpcalloc(1, sizeof(cpContactPointSet));
}

inline cpContactPointSet* cpjCloneCpContactPointSet(cpContactPointSet cps) {
	cpContactPointSet* clone = (cpContactPointSet*) cpcalloc(1, sizeof(cpContactPointSet));
	memcpy(clone, &cps, sizeof(cpContactPointSet));
	return clone;
}

inline cpShape* cpjGetCpShape(JNIEnv* env, jobject this) {
	return (cpShape*) (*env)->GetLongField(env, this, cpShapePtr);
}

inline cpPointQueryInfo* cpjGetCpPointQueryInfo(JNIEnv* env, jobject this) {
	return (cpPointQueryInfo*) (*env)->GetLongField(env, this, cpPointQueryInfoPtr);
}

inline cpPointQueryInfo* cpjNewCpPointQueryInfo() {
	return (cpPointQueryInfo*) cpcalloc(1, sizeof(cpPointQueryInfo));
}

inline cpSegmentQueryInfo* cpjGetCpSegmentQueryInfo(JNIEnv* env, jobject this) {
	return (cpSegmentQueryInfo*) (*env)->GetLongField(env, this, cpSegmentQueryInfoPtr);
}

inline cpSegmentQueryInfo* cpjNewCpSegmentQueryInfo() {
	return (cpSegmentQueryInfo*) cpcalloc(1, sizeof(cpSegmentQueryInfo));
}

inline cpjShapeQueryInfo* cpjGetCpjShapeQueryInfo(JNIEnv* env, jobject this) {
	return (cpjShapeQueryInfo*) (*env)->GetLongField(env, this, cpjShapeQueryInfoPtr);
}

inline cpjShapeQueryInfo* cpjNewCpShapeQueryInfo() {
	return (cpjShapeQueryInfo*) cpcalloc(1, sizeof(cpjShapeQueryInfo));
}

inline cpSpace* cpjGetCpSpace(JNIEnv* env, jobject this) {
	return (cpSpace*) (*env)->GetLongField(env, this, cpSpacePtr);
}

inline cpSpaceDebugDrawOptions* cpjGetCpSpaceDebugDrawOptions(JNIEnv* env, jobject this) {
	return (cpSpaceDebugDrawOptions*) (*env)->GetLongField(env, this, cpSpaceDebugDrawOptionsPtr);
}

inline cpBB cpjBBFromArray(JNIEnv* env, jdoubleArray array) {
	cpBB bb;
	(*env)->GetDoubleArrayRegion(env, array, 0, 4, (jdouble*) &bb);
	return bb;
}

inline jdoubleArray cpjBBToArray(JNIEnv* env, cpBB* bb) {
	jdoubleArray array = (*env)->NewDoubleArray(env, 4);
	(*env)->SetDoubleArrayRegion(env, array, 0, 4, (jdouble*) bb);
	return array;
}

inline cpVect cpjVectFromArray(JNIEnv* env, jdoubleArray array) {
	cpVect vect;
	(*env)->GetDoubleArrayRegion(env, array, 0, 2, (jdouble*) &vect);
	return vect;
}

inline jdoubleArray cpjVectToArray(JNIEnv* env, cpVect* vect) {
	jdoubleArray array = (*env)->NewDoubleArray(env, 2);
	(*env)->SetDoubleArrayRegion(env, array, 0, 2, (jdouble*) vect);
	return array;
}

inline cpVect* cpjVectsFromArray(JNIEnv* env, jdoubleArray array) {
	jsize   count = (*env)->GetArrayLength(env, array);
	cpVect* vects = (cpVect*) cpcalloc(count/2, sizeof(cpVect));
	(*env)->GetDoubleArrayRegion(env, array, 0, count, (jdouble*) vects);
	return vects;
}

inline jdoubleArray cpjVectsToArray(JNIEnv* env, jsize count, const cpVect* vects) {
	jdoubleArray array = (*env)->NewDoubleArray(env, 2*count);
	(*env)->SetDoubleArrayRegion(env, array, 0, 2*count, (jdouble*) vects);
	return array;
}

inline cpTransform cpjTransformFromArray(JNIEnv* env, jdoubleArray array) {
	cpTransform transform;
	(*env)->GetDoubleArrayRegion(env, array, 0, 6, (jdouble*) &transform);
	return transform;
}

inline jdoubleArray cpjTransformToArray(JNIEnv* env, cpTransform* transform) {
	jdoubleArray array = (*env)->NewDoubleArray(env, 6);
	(*env)->SetDoubleArrayRegion(env, array, 0, 6, (jdouble*) transform);
	return array;
}

inline cpShapeFilter cpjShapeFilterFromArray(JNIEnv* env, jlongArray _array) {
	jlong array[3];
	(*env)->GetLongArrayRegion(env, _array, 0, 3, array);

	cpShapeFilter filter;
	filter.group      = (cpGroup)   array[0];
	filter.categories = (cpBitmask) array[1];
	filter.mask       = (cpBitmask) array[2];
	return filter;
}

inline jlongArray cpjShapeFilterToArray(JNIEnv* env, cpShapeFilter* filter) {
	jlong _array[3];
	_array[0] = (jlong) filter->group;
	_array[1] = (jlong) filter->categories;
	_array[2] = (jlong) filter->mask;

	jlongArray array = (*env)->NewLongArray(env, 3);
	(*env)->SetLongArrayRegion(env, array, 0, 3, _array);
	return array;
}

inline cpSpaceDebugColor cpjColorFromRGBIII(int r, int g, int b) {
	cpSpaceDebugColor color = { r/255.0f, g/255.0f, b/255.0f, 255/255.0f };
	return color;
}

inline cpSpaceDebugColor cpjColorFromArray(JNIEnv* env, jfloatArray array) {
	cpSpaceDebugColor color;
	(*env)->GetFloatArrayRegion(env, array, 0, 4, (jfloat*) &color);
	return color;
}

inline jfloatArray cpjColorToArray(JNIEnv* env, cpSpaceDebugColor* color) {
	jfloatArray array = (*env)->NewFloatArray(env, 4);
	(*env)->SetFloatArrayRegion(env, array, 0, 4, (jfloat*) color);
	return array;
}

inline cpjPtrArrayList cpjNewPtrArrayList() {
	cpjPtrArrayList list = { 0, NULL };
	return list;
}

inline void cpjFreePtrArrayList(cpjPtrArrayList* list) {
	cpfree(list->array);
}

inline void cpjAddToPtrArrayList(cpjPtrArrayList* list, void* ptr) {
	int pos = list->count;
	list->count++;
	list->array = (jlong*) cprealloc(list->array, list->count * sizeof(jlong));
	list->array[pos] = (jlong) ptr;
}

// This is for the _BB class, not the BB class.
inline cpBB* cpjGetCpBB(JNIEnv* env, jobject this) {
	return (cpBB*) (*env)->GetLongField(env, this, cpBBPtr);
}

// This is for the _BB class, not the BB class.
inline cpBB* cpjNewCpBB(cpFloat l, cpFloat b, cpFloat r, cpFloat t) {
	cpBB* this = (cpBB*) cpcalloc(1, sizeof(cpBB));

	this->l = l;
	this->b = b;
	this->r = r;
	this->t = t;
	return this;
}

// This is for the _BB class, not the BB class.
inline cpBB* cpjCloneCpBB(cpBB that) {
	cpBB* clone = (cpBB*) cpcalloc(1, sizeof(cpBB));

	clone->l = that.l;
	clone->b = that.b;
	clone->r = that.r;
	clone->t = that.t;
	return clone;
}


// This is for the _Vect class, not the Vect class.
inline cpVect* cpjGetCpVect(JNIEnv* env, jobject this) {
	return (cpVect*) (*env)->GetLongField(env, this, cpVectPtr);
}

// This is for the _Vect class, not the Vect class.
inline cpVect* cpjNewCpVect(cpFloat x, cpFloat y) {
	cpVect* this = (cpVect*) cpcalloc(1, sizeof(cpVect));

	this->x = x;
	this->y = y;
	return this;
}

// This is for the _Vect class, not the Vect class.
inline cpVect* cpjCloneCpVect(cpVect that) {
	cpVect* clone = (cpVect*) cpcalloc(1, sizeof(cpVect));

	clone->x = that.x;
	clone->y = that.y;
	return clone;
}

// This is for the _Transform class, not the Transform class.
inline cpTransform* cpjGetCpTransform(JNIEnv* env, jobject this) {
	return (cpTransform*) (*env)->GetLongField(env, this, cpTransformPtr);
}

// This is for the _Transform class, not the Transform class.
inline cpTransform* cpjNewCpTransform(cpFloat a, cpFloat b, cpFloat c, cpFloat d, cpFloat tx, cpFloat ty) {
	cpTransform* this = (cpTransform*) cpcalloc(1, sizeof(cpTransform));

	this->a  = a;
	this->b  = b;
	this->c  = c;
	this->d  = d;
	this->tx = tx;
	this->ty = ty;
	return this;
}

// This is for the _Transform class, not the Transform class.
inline cpTransform* cpjCloneCpTransform(cpTransform that) {
	cpTransform* clone = (cpTransform*) cpcalloc(1, sizeof(cpTransform));

	clone->a  = that.a;
	clone->b  = that.b;
	clone->c  = that.c;
	clone->d  = that.d;
	clone->tx = that.tx;
	clone->ty = that.ty;
	return clone;
}

