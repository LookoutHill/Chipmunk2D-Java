package ChipmunkJava.Collisions;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

public class Arbiter {
	private native static long init();

	private static final long cpNullPtr;

	static {
		System.loadLibrary("chipmunkJni");
		cpNullPtr = init();
	}

	private final long cpArbiterPtr;

	protected Arbiter() {
		cpArbiterPtr = cpNullPtr;

		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Collisions.ArbiterTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	public Arbiter(long _cpArbiterPtr) {
		cpArbiterPtr = _cpArbiterPtr;
	}

	public static Arbiter get(long cpArbiterPtr) {
		return new Arbiter(cpArbiterPtr);
	}

	public native boolean ignore();

	public native boolean isFirstContact();
	public native boolean isRemoval();

	private native long[]   _getBodies();
	private native long     _getContactPointSet();
	public  native int      getCount();
	public  native double   getDepth(int pos);
	public  native double[] getDepths();
	public  native double   getFriction();
	private native double[] _getNormal();
	private native double[] _getPointA(int pos);
	private native double[] _getPointB(int pos);
	private native double[] _getPointsA();
	private native double[] _getPointsB();
	public  native double   getRestitution();
	private native long[]   _getShapes();
	private native double[] _getSurfaceVelocity();
	private native double[] _getTotalImpulse();
	public  native double   getTotalKE();

	public  native void setContactPointSet(ContactPointSet cps);
	public  native void setFriction(double value);
	public  native void setRestitution(double value);
	private native void _setSurfaceVelocity(double[] vect);

	public Body[] getBodies() {
		return Body.get(_getBodies());
	}

	public ContactPointSet getContactPointSet() {
		return new ContactPointSet(_getContactPointSet());
	}

	public Vect getNormal() {
		return new Vect(_getNormal());
	}

	public Vect getPointA(int pos) {
		return new Vect(_getPointA(pos));
	}

	public Vect getPointB(int pos) {
		return new Vect(_getPointB(pos));
	}

	public Vect[] getPointsA() {
		return Vect.toVectArray(_getPointsA());
	}

	public Vect[] getPointsB() {
		return Vect.toVectArray(_getPointsB());
	}

	public Shape[] getShapes() {
		return Shape.get(_getShapes());
	}

	public Vect getSurfaceVelocity() {
		return new Vect(_getSurfaceVelocity());
	}

	public Vect getTotalImpulse() {
		return new Vect(_getTotalImpulse());
	}

	public void setSurfaceVelocity(Vect vect) {
		_setSurfaceVelocity(vect.toArray());
	}
}

