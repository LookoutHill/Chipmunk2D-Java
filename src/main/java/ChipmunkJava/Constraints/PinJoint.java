package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class PinJoint extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected PinJoint() {
		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Constraints.PinJointTest")           return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private native static long _new(Body a, Body b, double[] anchorA, double[] anchorB);

	public PinJoint(Body a, Body b, Vect anchorA, Vect anchorB) {
		super(_new(a, b, anchorA.toArray(), anchorB.toArray()));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public PinJoint(PinJoint that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public PinJoint clone() {
		return new PinJoint(this);
	}

	private native double[] _getAnchorA();
	private native double[] _getAnchorB();
	public  native double   getDistance();

	private native void _setAnchorA(double[] vect);
	private native void _setAnchorB(double[] vect);
	public  native void setDistance(double value);

	public Vect getAnchorA() {
		return new Vect(_getAnchorA());
	}

	public Vect getAnchorB() {
		return new Vect(_getAnchorB());
	}

	public void setAnchorA(Vect vect) {
		_setAnchorA(vect.toArray());
	}

	public void setAnchorB(Vect vect) {
		_setAnchorB(vect.toArray());
	}
}

