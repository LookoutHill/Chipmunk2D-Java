package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class PivotJoint extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double[] pivot);

	public PivotJoint(Body bodyA, Body bodyB, Vect pivot) {
		super(_new(bodyA, bodyB, pivot.toArray()));
		_constraints.put(cpConstraintPtr, this);
	}

	private native static long _new2(Body bodyA, Body bodyB, double[] anchorA, double[] anchorB);

	public PivotJoint(Body bodyA, Body bodyB, Vect anchorA, Vect anchorB) {
		super(_new2(bodyA, bodyB, anchorA.toArray(), anchorB.toArray()));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public PivotJoint(PivotJoint that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public PivotJoint clone() {
		return new PivotJoint(this);
	}

	private native double[] _getAnchorA();
	private native double[] _getAnchorB();

	private native void _setAnchorA(double[] vect);
	private native void _setAnchorB(double[] vect);

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

