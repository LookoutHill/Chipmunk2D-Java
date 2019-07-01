package ChipmunkJava.Constraints;

import ChipmunkJava.*;

public class GrooveJoint extends Constraint {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private native static long _new(Body bodyA, Body bodyB, double[] groove_bodyA, double[] groove_bodyB, double[] anchorB);

	public GrooveJoint(Body bodyA, Body bodyB, Vect groove_bodyA, Vect groove_bodyB, Vect anchorB) {
		super(_new(bodyA, bodyB, groove_bodyA.toArray(), groove_bodyB.toArray(), anchorB.toArray()));
		_constraints.put(cpConstraintPtr, this);
	}

	private native long _clone();

	public GrooveJoint(GrooveJoint that) {
		super(that._clone());
		_constraints.put(cpConstraintPtr, this);
	}

	@Override
	public GrooveJoint clone() {
		return new GrooveJoint(this);
	}

	private native double[] _getAnchorB();
	private native double[] _getGrooveA();
	private native double[] _getGrooveB();

	private native void _setAnchorB(double[] vect);
	private native void _setGrooveA(double[] vect);
	private native void _setGrooveB(double[] vect);

	public Vect getAnchorB() {
		return new Vect(_getAnchorB());
	}

	public Vect getGrooveA() {
		return new Vect(_getGrooveA());
	}

	public Vect getGrooveB() {
		return new Vect(_getGrooveB());
	}

	public void setAnchorB(Vect vect) {
		_setAnchorB(vect.toArray());
	}

	public void setGrooveA(Vect vect) {
		_setGrooveA(vect.toArray());
	}

	public void setGrooveB(Vect vect) {
		_setGrooveB(vect.toArray());
	}
}

