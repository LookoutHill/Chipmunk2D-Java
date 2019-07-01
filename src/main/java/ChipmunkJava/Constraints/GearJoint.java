package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class GearJoint extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double phase, double ratio);

	public GearJoint(Body bodyA, Body bodyB, double phase, double ratio) {
		super(_new(bodyA, bodyB, phase, ratio));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public GearJoint(GearJoint that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public GearJoint clone() {
		return new GearJoint(this);
	}

	public native double getPhase();
	public native double getRatio();

	public native void setPhase(double value);
	public native void setRatio(double value);
}

