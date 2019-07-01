package ChipmunkJava.Collisions;

import ChipmunkJava.*;

public class ContactPointSet {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private long cpContactPointSetPtr;

	public ContactPointSet(long _cpContactPointSetPtr) {
		cpContactPointSetPtr = _cpContactPointSetPtr;
	}

	public static ContactPointSet get(long cpContactPointSetPtr) {
		return new ContactPointSet(cpContactPointSetPtr);
	}

	private native void free();

	protected void finalize() {
		System.err.println("ContactPointSet.free(): " + cpContactPointSetPtr);
		free();
	}

	public  native int      getCount();
	public  native double   getDistance(int pos);
	private native double[] _getDistances();
	private native double[] _getNormal();
	private native double[] _getPointA(int pos);
	private native double[] _getPointB(int pos);
	private native double[] _getPointsA();
	private native double[] _getPointsB();

	private native void _setNormal(double[] vect);
	private native void _setPointA(int pos, double[] vect);
	private native void _setPointB(int pos, double[] vect);
	private native void _setPointsA(double[] vects);
	private native void _setPointsB(double[] vects);

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

	public void setNormal(Vect vect) {
		_setNormal(vect.toArray());
	}

	public void setPointA(int pos, Vect vect) {
		_setPointA(pos, vect.toArray());
	}

	public void setPointB(int pos, Vect vect) {
		_setPointB(pos, vect.toArray());
	}

	public void setPointsA(Vect[] vects) {
		_setPointsA(Vect.fromVectArray(vects));
	}

	public void setPointsB(Vect[] vects) {
		_setPointsB(Vect.fromVectArray(vects));
	}
}

