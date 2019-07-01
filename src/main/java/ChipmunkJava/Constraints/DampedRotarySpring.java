package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class DampedRotarySpring extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double restAngle, double stiffness, double damping);

	public DampedRotarySpring(Body bodyA, Body bodyB, double restAngle, double stiffness, double damping) {
		super(_new(bodyA, bodyB, restAngle, stiffness, damping));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public DampedRotarySpring(DampedRotarySpring that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public DampedRotarySpring clone() {
		return new DampedRotarySpring(this);
	}

	public native double getDamping();
	public native double getRestAngle();
	public native double getStiffness();

	public native void setDamping(double value);
	public native void setRestAngle(double value);
	public native void setStiffness(double value);
}

