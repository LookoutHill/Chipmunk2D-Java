package ChipmunkJava.Constraints;

import ChipmunkJava.*;

import java.util.Map;
import java.util.Hashtable;

public abstract class Constraint {
	private native static long init();

	protected static final long cpNullPtr;

	static {
		System.loadLibrary("chipmunkJni");
		cpNullPtr = init();
	}

	protected static Map<Long, Constraint> _constraints = new Hashtable<Long, Constraint>();

	protected final long cpConstraintPtr;

	protected Constraint() {
		cpConstraintPtr = cpNullPtr;

		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Constraints.ConstraintTest")         return;
		if(className == "ChipmunkJava.Constraints.PinJointTest")           return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	protected Constraint(long _cpConstraintPtr) {
		cpConstraintPtr = _cpConstraintPtr;
	}

	public static Constraint get(long cpConstraintPtr) {
		if(cpConstraintPtr == cpNullPtr) return null;
		Constraint constraint = _constraints.getOrDefault(cpConstraintPtr, null);
		if(constraint != null) return constraint;

		throw new IllegalArgumentException("Invalid cpConstraintPtr used to resolve Constraint: " + cpConstraintPtr);
	}

	public static void remove(Constraint that) {
		_constraints.remove(that.cpConstraintPtr);
	}

	private native void free();

	protected void finalize() {
		System.err.println("Constraint.free(): " + cpConstraintPtr);
		free();
	}

	private native long    _getBodyA();
	private native long    _getBodyB();
	private native boolean getCollideBodies();
	private native double  getErrorBias();
	public  native double  getImpulse();
	private native double  getMaxBias();
	private native double  getMaxForce();
	private native long    _getSpace();

	public native void setCollideBodies(boolean value);
	public native void setErrorBias(double value);
	public native void setMaxBias(double value);
	public native void setMaxForce(double value);

	public Body getBodyA() {
		return Body.get(_getBodyA());
	}

	public Body getBodyB() {
		return Body.get(_getBodyB());
	}

	public Space getSpace() {
		return Space.get(_getSpace());
	}
}

