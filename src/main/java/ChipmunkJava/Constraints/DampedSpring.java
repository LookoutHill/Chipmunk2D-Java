package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class DampedSpring extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double[] anchorA, double[] anchorB, double restLength, double stiffness, double damping);

	public DampedSpring(Body bodyA, Body bodyB, Vect anchorA, Vect anchorB, double restLength, double stiffness, double damping) {
		super(_new(bodyA, bodyB, anchorA.toArray(), anchorB.toArray(), restLength, stiffness, damping));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public DampedSpring(DampedSpring that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public DampedSpring clone() {
		return new DampedSpring(this);
	}

	public native double[] _getAnchorA();
	public native double[] _getAnchorB();
	public native double   getDamping();
	public native double   getRestLength();
	public native double   getStiffness();

	private native void _setAnchorA(double[] vect);
	private native void _setAnchorB(double[] vect);
	public  native void setDamping(double value);
	public  native void setRestLength(double value);
	public  native void setStiffness(double value);

	public Vect getAnchorA() {
		return new Vect(_getAnchorA());
	}

	public Vect getAnchorB() {
		return new Vect(_getAnchorB());
	}

	public void _setAnchorA(Vect vect) {
		_setAnchorA(vect.toArray());
	}

	public void _setAnchorB(Vect vect) {
		_setAnchorB(vect.toArray());
	}
}

