package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class SlideJoint extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double[] anchorA, double[] anchorB, double min, double max);

	public SlideJoint(Body bodyA, Body bodyB, Vect anchorA, Vect anchorB, double min, double max) {
		super(_new(bodyA, bodyB, anchorA.toArray(), anchorB.toArray(), min, max));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public SlideJoint(SlideJoint that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public SlideJoint clone() {
		return new SlideJoint(this);
	}

	private native double[] _getAnchorA();
	private native double[] _getAnchorB();
	public  native double   getMax();
	public  native double   getMin();

	private native void _setAnchorA(double[] vect);
	private native void _setAnchorB(double[] vect);
	public  native void setMax(double value);
	public  native void setMin(double value);

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

