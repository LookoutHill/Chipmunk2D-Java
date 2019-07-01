package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class RotaryLimitJoint extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double min, double max);

	public RotaryLimitJoint(Body bodyA, Body bodyB, double min, double max) {
		super(_new(bodyA, bodyB, min, max));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public RotaryLimitJoint(RotaryLimitJoint that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public RotaryLimitJoint clone() {
		return new RotaryLimitJoint(this);
	}

	public native double getMax();
	public native double getMin();

	public native void setMax(double value);
	public native void setMin(double value);
}

