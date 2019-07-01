package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class SimpleMotor extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double rate);

	public SimpleMotor(Body bodyA, Body bodyB, double rate) {
		super(_new(bodyA, bodyB, rate));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public SimpleMotor(SimpleMotor that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public SimpleMotor clone() {
		return new SimpleMotor(this);
	}

	public native double getRate();

	public native void setRate(double value);
}

