package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class RatchetJoint extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double phase, double ratchet);

	public RatchetJoint(Body bodyA, Body bodyB, double phase, double ratchet) {
		super(_new(bodyA, bodyB, phase, ratchet));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public RatchetJoint(RatchetJoint that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public RatchetJoint clone() {
		return new RatchetJoint(this);
	}

	public native double getAngle();
	public native double getPhase();
	public native double getRatchet();

	public native void setAngle(double value);
	public native void setPhase(double value);
	public native void setRatchet(double value);
}

