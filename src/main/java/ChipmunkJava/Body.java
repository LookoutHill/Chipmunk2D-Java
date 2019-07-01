package ChipmunkJava;

import ChipmunkJava.Collisions.*;
import ChipmunkJava.Handlers.*;
import ChipmunkJava.Shapes.*;

import java.util.Map;
import java.util.Hashtable;

public class Body {
	private native static long init();

	protected static final long cpNullPtr;

	static {
		System.loadLibrary("chipmunkJni");
		cpNullPtr = init();
	}

	protected static Map<Long, Body> _bodies = new Hashtable<Long, Body>();

	protected final long cpBodyPtr;

	protected Body() {
		cpBodyPtr = cpNullPtr;
//		System.err.println("Body(): " + cpBodyPtr); // MATT

		String className = this.getClass().getName();
		if(className == "ChipmunkJava.BodyTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private native static long _new(double mass, double moment);
	private native static long _new(BodyType btype);

	public Body(double mass, double moment) {
		cpBodyPtr = _new(mass, moment);
//		System.err.println("Body(mass, moment): " + cpBodyPtr); // MATT
		_bodies.put(cpBodyPtr, this);
	}

	public Body(BodyType btype) {
		if(btype == BodyType.DYNAMIC) throw new IllegalArgumentException("To create a dynamic body, please specify a mass and moment instead of the DYNAMIC BodyType.");
		cpBodyPtr = _new(btype);
//		System.err.println("Body(btype): " + cpBodyPtr); // MATT
		_bodies.put(cpBodyPtr, this);
	}

	public Body(long _cpBodyPtr) {
		cpBodyPtr = _cpBodyPtr;
//		System.err.println("Body(cpBodyPtr): " + cpBodyPtr); // MATT
		_bodies.put(cpBodyPtr, this);
	}

	public static Body get(long cpBodyPtr) {
		if(cpBodyPtr == cpNullPtr) return null;
		Body body = _bodies.getOrDefault(cpBodyPtr, null);
		if(body != null) return body;
		else             return new Body(cpBodyPtr);
	}

	public static Body[] get(long[] cpBodyPtrs) {
		Body[] bodies = new Body[cpBodyPtrs.length];
		for(int pos = 0; pos < cpBodyPtrs.length; pos++) {
			bodies[pos] = Body.get(cpBodyPtrs[pos]);
		}
		return bodies;
	}

	private native long _clone();

	public Body(Body that) {
		cpBodyPtr = that._clone();
//		System.err.println("Body(Body): " + cpBodyPtr); // MATT
		_bodies.put(cpBodyPtr, this);
	}

	@Override
	public Body clone() {
		return new Body(this);
	}

	public static void remove(Body that) {
		_bodies.remove(that.cpBodyPtr);
	}

	private native void free();

	protected void finalize() {
//		System.err.println("Body.free(): " + cpBodyPtr); // MATT
		free();
	}

	private static void _invokePositionUpdateBodyHandler(long cpBodyPtr, double dt) {
		Body body = get(cpBodyPtr);
		body.positionUpdateBodyHandler.invoke(dt);
	}

	private static void _invokeVelocityUpdateBodyHandler(long cpBodyPtr, double[] _gravity, double damping, double dt) {
		Body body    = get(cpBodyPtr);
		Vect gravity = new Vect(_gravity);
		body.velocityUpdateBodyHandler.invoke(gravity, damping, dt);
	}

	private native void _disablePositionUpdateFunc();
	private native void _enablePositionUpdateFunc();
	private native void _disableVelocityUpdateFunc();
	private native void _enableVelocityUpdateFunc();

	private PositionUpdateBodyHandler positionUpdateBodyHandler = null;
	private VelocityUpdateBodyHandler velocityUpdateBodyHandler = null;
 
	public void setUpdateBodyHandler(UpdateBodyHandler handler) {
		if     (handler instanceof PositionUpdateBodyHandler) setPositionUpdateBodyHandler((PositionUpdateBodyHandler) handler);
		else if(handler instanceof VelocityUpdateBodyHandler) setVelocityUpdateBodyHandler((VelocityUpdateBodyHandler) handler);
		else                                                  throw new IllegalArgumentException("Unknown handler type: " + handler.getClass().getName());
	}

	public void resetUpdateBodyHandler(UpdateBodyHandler handler) {
		if     (handler instanceof PositionUpdateBodyHandler) resetPositionUpdateBodyHandler((PositionUpdateBodyHandler) handler);
		else if(handler instanceof VelocityUpdateBodyHandler) resetVelocityUpdateBodyHandler((VelocityUpdateBodyHandler) handler);
		else                                                  throw new IllegalArgumentException("Unknown handler type: " + handler.getClass().getName());
	}

	private void setPositionUpdateBodyHandler(PositionUpdateBodyHandler handler) {
		positionUpdateBodyHandler = handler;
		_enablePositionUpdateFunc();
	}

	private void resetPositionUpdateBodyHandler(PositionUpdateBodyHandler handler) {
		_disablePositionUpdateFunc();
		positionUpdateBodyHandler = null;
	}

	private void setVelocityUpdateBodyHandler(VelocityUpdateBodyHandler handler) {
		velocityUpdateBodyHandler = handler;
		_enableVelocityUpdateFunc();
	}

	private void resetVelocityUpdateBodyHandler(VelocityUpdateBodyHandler handler) {
		_disableVelocityUpdateFunc();
		velocityUpdateBodyHandler = null;
	}

	public  native double   getAngle();
	public  native double   getAngularVelocity();
	private native int      _getBodyType();
	private native double[] _getCenterOfGravity();
	private native double[] _getForce();
	public  native double   getKineticEnergy();
	public  native double   getMass();
	public  native double   getMoment();
	private native double[] _getPosition();
	private native double[] _getRotation();
	private native long     _getSpace();
	public  native double   getTorque();
	private native double[] _getVelocity();
	private native double[] _getVelocityAtLocalPoint(double[] localPoint);
	private native double[] _getVelocityAtWorldPoint(double[] worldPoint);

	private native double[] _localToWorld(double[] localPoint);
	private native double[] _worldToLocal(double[] worldPoint);

	public  native void setAngle(double value);
	public  native void setAngularVelocity(double value);
	public  native void setBodyType(BodyType btype);
	private native void _setCenterOfGravity(double[] vect);
	private native void _setForce(double[] vect);
	public  native void setMass(double value);
	public  native void setMoment(double value);
	private native void _setPosition(double[] vect);
	public  native void setTorque(double value);
	private native void _setVelocity(double[] vect);

	private native void _applyForceAtLocalPoint(double[] force, double[] point);
	private native void _applyForceAtWorldPoint(double[] force, double[] point);
	private native void _applyImpulseAtLocalPoint(double[] impulse, double[] point);
	private native void _applyImpulseAtWorldPoint(double[] impulse, double[] point);

	public native boolean isSleeping();

	public native void activate();
	public native void activateStatic(Shape shape);
	public native void sleep();
	public native void sleepWithGroup(Body groupMember);

	public BodyType getBodyType() {
		return BodyType.get(_getBodyType());
	}

	public Vect getCenterOfGravity() {
		return new Vect(_getCenterOfGravity());
	}

	public Vect getForce() {
		return new Vect(_getForce());
	}

	public Vect getPosition() {
		return new Vect(_getPosition());
	}

	public Vect getRotation() {
		return new Vect(_getRotation());
	}

	public Space getSpace() {
		return Space.get(_getSpace());
	}

	public Vect getVelocity() {
		return new Vect(_getVelocity());
	}

	public Vect getVelocityAtLocalPoint(Vect vect) {
		return new Vect(_getVelocityAtLocalPoint(vect.toArray()));
	}

	public Vect getVelocityAtWorldPoint(Vect vect) {
		return new Vect(_getVelocityAtWorldPoint(vect.toArray()));
	}

	public Vect localToWorld(Vect vect) {
		return new Vect(_localToWorld(vect.toArray()));
	}

	public Vect worldToLocal(Vect vect) {
		return new Vect(_worldToLocal(vect.toArray()));
	}

	public void setCenterOfGravity(Vect vect) {
		_setCenterOfGravity(vect.toArray());
	}

	public void setForce(Vect vect) {
		_setForce(vect.toArray());
	}

	public void setPosition(Vect vect) {
		_setPosition(vect.toArray());
	}

	public void setVelocity(Vect vect) {
		_setVelocity(vect.toArray());
	}

	public void applyForceAtLocalPoint(Vect force, Vect point) {
		_applyForceAtLocalPoint(force.toArray(), point.toArray());
	}

	public void applyForceAtWorldPoint(Vect force, Vect point) {
		_applyForceAtWorldPoint(force.toArray(), point.toArray());
	}

	public void applyImpulseAtLocalPoint(Vect impulse, Vect point) {
		_applyImpulseAtLocalPoint(impulse.toArray(), point.toArray());
	}

	public void applyImpulseAtWorldPoint(Vect impulse, Vect point) {
		_applyImpulseAtWorldPoint(impulse.toArray(), point.toArray());
	}
}

