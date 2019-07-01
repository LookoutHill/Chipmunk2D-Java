#pragma once

#include <jni.h>
#include <stdio.h>
#include "cpjCore.h"

extern JavaVM* jvm;

jclass _class;

jfieldID cpBodyPtr;

jmethodID _invokePositionUpdateBodyHandler;
jmethodID _invokeVelocityUpdateBodyHandler;

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Body_init(JNIEnv *env, jclass __class) {
	if(jvm == NULL) {
		jint jniStatus = (*env)->GetJavaVM(env, &jvm);
		cpAssertHard(jniStatus == JNI_OK, "Failed to get the JVM.");
	}

	_class = (jclass) (*env)->NewGlobalRef(env, __class);

	cpBodyPtr = (*env)->GetFieldID(env, _class, "cpBodyPtr", "J");

	_invokePositionUpdateBodyHandler = (*env)->GetStaticMethodID(env, _class, "_invokePositionUpdateBodyHandler", "(JD)V");
	_invokeVelocityUpdateBodyHandler = (*env)->GetStaticMethodID(env, _class, "_invokeVelocityUpdateBodyHandler", "(J[DDD)V");

	return (jlong) NULL;
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Body__1new__DD(JNIEnv *env, jclass _class, jdouble mass, jdouble moment) {
	return (jlong) cpBodyNew(mass, moment);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Body__1new__LChipmunkJava_BodyType_2(JNIEnv *env, jclass _class, jobject _bodyType) {
	cpBodyType btype = cpjGetCpBodyType(env, _bodyType);

	if(btype == CP_BODY_TYPE_STATIC)    return (jlong) cpBodyNewStatic();
	if(btype == CP_BODY_TYPE_KINEMATIC) return (jlong) cpBodyNewKinematic();
	
	return (jlong)cpBodyNew(INFINITY, INFINITY);
}

JNIEXPORT jlong JNICALL Java_ChipmunkJava_Body__1clone(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);

	cpBody* clone        = cpBodyNew(cpBodyGetMass(body), cpBodyGetMoment(body));
	clone->position_func = body->position_func;
	clone->velocity_func = body->velocity_func;
	cpBodySetAngle(clone, cpBodyGetAngle(body));
	cpBodySetAngularVelocity(clone, cpBodyGetAngularVelocity(body));
	cpBodySetCenterOfGravity(clone, cpBodyGetCenterOfGravity(body));
	cpBodySetForce(clone, cpBodyGetForce(body));
	cpBodySetPosition(clone, cpBodyGetPosition(body));
	cpBodySetTorque(clone, cpBodyGetTorque(body));
	cpBodySetType(clone, cpBodyGetType(body));
	cpBodySetUserData(clone, cpBodyGetUserData(body));
	cpBodySetVelocity(clone, cpBodyGetVelocity(body));

	return (jlong) clone;
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body_free(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodyFree(body);
}

void cpjBodyUpdatePosition(cpBody* body, cpFloat dt) {
	jvalue args[2];
	args[0].j = (jlong)   body;
	args[1].d = (jdouble) dt;

	JNIEnv* env    = cpjGetEnv();
	(*env)->CallStaticVoidMethodA(env, _class, _invokePositionUpdateBodyHandler, args);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1disablePositionUpdateFunc(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetPositionUpdateFunc(body, cpBodyUpdatePosition);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1enablePositionUpdateFunc(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetPositionUpdateFunc(body, cpjBodyUpdatePosition);
}

void cpjBodyUpdateVelocity(cpBody* body, cpVect gravity, cpFloat damping, cpFloat dt) {
	JNIEnv* env    = cpjGetEnv();

	jvalue args[4];
	args[0].j = (jlong)   body;
	args[1].l = cpjVectToArray(env, &gravity);
	args[2].d = (jdouble) damping;
	args[3].d = (jdouble) dt;

	(*env)->CallStaticVoidMethodA(env, _class, _invokeVelocityUpdateBodyHandler, args);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1disableVelocityUpdateFunc(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetVelocityUpdateFunc(body, cpBodyUpdateVelocity);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1enableVelocityUpdateFunc(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetVelocityUpdateFunc(body, cpjBodyUpdateVelocity);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Body_getAngle(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	return cpBodyGetAngle(body);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Body_getAngularVelocity(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	return cpBodyGetAngularVelocity(body);
}

JNIEXPORT jint JNICALL Java_ChipmunkJava_Body__1getBodyType(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	return (jint) cpBodyGetType(body);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1getCenterOfGravity(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpBodyGetCenterOfGravity(body);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1getForce(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpBodyGetForce(body);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Body_getKineticEnergy(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);

	return cpBodyKineticEnergy(body);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Body_getMass(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	return cpBodyGetMass(body);
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Body_getMoment(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	return cpBodyGetMoment(body);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1getPosition(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpBodyGetPosition(body);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1getRotation(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpBodyGetRotation(body);
	return cpjVectToArray(env, &vect);
}
     
JNIEXPORT jlong JNICALL Java_ChipmunkJava_Body__1getSpace(JNIEnv *env, jobject this) {
	cpBody*  body  = cpjGetCpBody(env, this);
	cpSpace* space = cpBodyGetSpace(body);
	return (jlong) space;
}

JNIEXPORT jdouble JNICALL Java_ChipmunkJava_Body_getTorque(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	return cpBodyGetTorque(body);
}

JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1getVelocity(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpBodyGetVelocity(body);
	return cpjVectToArray(env, &vect);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1getVelocityAtLocalPoint(JNIEnv *env, jobject this, jdoubleArray _point) {
	cpBody* body  = cpjGetCpBody(env, this);
	cpVect  point = cpjVectFromArray(env, _point);
	cpVect  vect  = cpBodyGetVelocityAtLocalPoint(body, point);
	return cpjVectToArray(env, &vect);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1getVelocityAtWorldPoint(JNIEnv *env, jobject this, jdoubleArray _point) {
	cpBody* body  = cpjGetCpBody(env, this);
	cpVect  point = cpjVectFromArray(env, _point);
	cpVect  vect  = cpBodyGetVelocityAtWorldPoint(body, point);
	return cpjVectToArray(env, &vect);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1localToWorld(JNIEnv *env, jobject this, jdoubleArray _point) {
	cpBody* body  = cpjGetCpBody(env, this);
	cpVect  point = cpjVectFromArray(env, _point);
	cpVect  vect  = cpBodyLocalToWorld(body, point);
	return cpjVectToArray(env, &vect);
}
     
JNIEXPORT jdoubleArray JNICALL Java_ChipmunkJava_Body__1worldToLocal(JNIEnv *env, jobject this, jdoubleArray _point) {
	cpBody* body  = cpjGetCpBody(env, this);
	cpVect  point = cpjVectFromArray(env, _point);
	cpVect  vect  = cpBodyWorldToLocal(body, point);
	return cpjVectToArray(env, &vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body_setAngle(JNIEnv *env, jobject this, jdouble value) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetAngle(body, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body_setAngularVelocity(JNIEnv *env, jobject this, jdouble value) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetAngularVelocity(body, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body_setBodyType(JNIEnv *env, jobject this, jobject jbtype) {
	cpBody*    body  = cpjGetCpBody(env, this);
	cpBodyType btype = cpjGetCpBodyType(env, jbtype);
	cpBodySetType(body, btype);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1setCenterOfGravity(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpjVectFromArray(env, _vect);
	cpBodySetCenterOfGravity(body, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1setForce(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpjVectFromArray(env, _vect);
	cpBodySetForce(body, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body_setMass(JNIEnv *env, jobject this, jdouble value) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetMass(body, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body_setMoment(JNIEnv *env, jobject this, jdouble value) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetMoment(body, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1setPosition(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpjVectFromArray(env, _vect);
	cpBodySetPosition(body, vect);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body_setTorque(JNIEnv *env, jobject this, jdouble value) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySetTorque(body, value);
}

JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1setVelocity(JNIEnv *env, jobject this, jdoubleArray _vect) {
	cpBody* body = cpjGetCpBody(env, this);
	cpVect  vect = cpjVectFromArray(env, _vect);
	cpBodySetVelocity(body, vect);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1applyForceAtLocalPoint(JNIEnv *env, jobject this, jdoubleArray _force, jdoubleArray _point) {
	cpBody* body  = cpjGetCpBody(env, this);
	cpVect  force = cpjVectFromArray(env, _force);
	cpVect  point = cpjVectFromArray(env, _point);
	cpBodyApplyForceAtLocalPoint(body, force, point);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1applyForceAtWorldPoint(JNIEnv *env, jobject this, jdoubleArray _force, jdoubleArray _point) {
	cpBody* body  = cpjGetCpBody(env, this);
	cpVect  force = cpjVectFromArray(env, _force);
	cpVect  point = cpjVectFromArray(env, _point);
	cpBodyApplyForceAtWorldPoint(body, force, point);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1applyImpulseAtLocalPoint(JNIEnv *env, jobject this, jdoubleArray _impulse, jdoubleArray _point) {
	cpBody* body    = cpjGetCpBody(env, this);
	cpVect  impulse = cpjVectFromArray(env, _impulse);
	cpVect  point   = cpjVectFromArray(env, _point);
	cpBodyApplyImpulseAtLocalPoint(body, impulse, point);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body__1applyImpulseAtWorldPoint(JNIEnv *env, jobject this, jdoubleArray _impulse, jdoubleArray _point) {
	cpBody* body    = cpjGetCpBody(env, this);
	cpVect  impulse = cpjVectFromArray(env, _impulse);
	cpVect  point   = cpjVectFromArray(env, _point);
	cpBodyApplyImpulseAtWorldPoint(body, impulse, point);
}

JNIEXPORT jboolean JNICALL Java_ChipmunkJava_Body_isSleeping(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	return cpBodyIsSleeping(body);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body_activate(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodyActivate(body);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body_activateStatic(JNIEnv *env, jobject this, jobject _shape) {
	cpBody*  body  = cpjGetCpBody(env, this);
	cpShape* shape = cpjGetCpShape(env, _shape);
	cpBodyActivateStatic(body, shape);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body_sleep(JNIEnv *env, jobject this) {
	cpBody* body = cpjGetCpBody(env, this);
	cpBodySleep(body);
}
     
JNIEXPORT void JNICALL Java_ChipmunkJava_Body_sleepWithGroup(JNIEnv *env, jobject this, jobject _groupMember) {
	cpBody* body        = cpjGetCpBody(env, this);
	cpBody* groupMember = cpjGetCpBody(env, _groupMember);
	cpBodySleepWithGroup(body, groupMember);
}

