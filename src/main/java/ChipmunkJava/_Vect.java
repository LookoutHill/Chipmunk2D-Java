package ChipmunkJava;

public class _Vect {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	public static final _Vect zero = new _Vect(0, 0);
	public static final _Vect unit = new _Vect(1, 0);

	protected final long cpVectPtr;

	protected _Vect() {
		cpVectPtr = 0;

		String className = this.getClass().getName();
		if(className == "ChipmunkJava._VectTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private native static long _new(double x, double y);

	public _Vect(double x, double y) {
		cpVectPtr = _new(x, y);
	}

	public _Vect(long _cpVectPtr) {
		cpVectPtr = _cpVectPtr;
	}

	public static _Vect get(long cpVectPtr) {
		return new _Vect(cpVectPtr);
	}

	public static _Vect[] get(long[] cpVectPtrs) {
		_Vect[] vects = new _Vect[cpVectPtrs.length];
		for(int pos = 0; pos < cpVectPtrs.length; pos++) {
			vects[pos] = new _Vect(cpVectPtrs[pos]);
		}
		return vects;
	}

	private native long _clone();

	public _Vect(_Vect that) {
		cpVectPtr = that._clone();
	}

	@Override
	public _Vect clone() {
		return new _Vect(_clone());
	}

	public _Vect(Vect that) {
		cpVectPtr = _new(that.x, that.y);
	}

	private native void free();

	protected void finalize() {
		System.err.println("_Vect.free(): " + cpVectPtr);
		free();
	}

	public native double getX();
	public native double getY();

	public native void setX(double value);
	public native void setY(double value);

	@Override
	public String toString() {
		return "_Vect(x:" + getX() + ", y:" + getY() + ")";
	}

	private native boolean _equals(_Vect that); // Cannot override equals(Object that).

	@Override
	public boolean equals(Object that) {
		try {
			return _equals((_Vect) that);
		} catch(Exception err) {
			return false;
		}
	}

	public boolean almost(_Vect that, double tolerance) {
		double dx = Math.abs(this.getX() - that.getX());
		double dy = Math.abs(this.getY() - that.getY());
		return dx <= tolerance && dy <= tolerance;
	}

	@Override
	public int hashCode() {
		long bitsX64 = Double.doubleToLongBits(getX());
		long bitsY64 = Double.doubleToLongBits(getY());

		int bitsX32 = (int) (bitsX64 ^ (bitsX64 >>> 32));
		int bitsY32 = (int) (bitsY64 ^ (bitsY64 >>> 32));

		long hash = 17;
		hash = 31 * hash + bitsX32;
		hash = 31 * hash + bitsY32;
		return (int) hash;
	}

	public  native double  length();
	public  native double  lengthSq();

	private native long   _add(_Vect that);
	public  native double cross(_Vect that);
	public  native double dot(_Vect that);
	private native long   _div(_Vect that);
	private native long   _mult(double scalar);
	private native long   _neg();
	private native long   _sub(_Vect that);

	private native long   _clamp(double length);
	public  native double toAngle();
	private native long   _normalize();
	private native long   _rotate(_Vect that);
	private native long   _unrotate(_Vect that);

	public native double  dist(_Vect that);
	public native double  distSq(_Vect that);
	public native boolean near(_Vect that, double distance);

	private native long _perp();
	private native long _rperp();
	private native long _project(_Vect that);

	private native long _lerp(_Vect that, double count);
	private native long _lerpConst(_Vect that, double distance);
	private native long _slerp(_Vect that, double count);
	private native long _slerpConst(_Vect that, double angle);

	public _Vect add(_Vect that) {
		return new _Vect(_add(that));
	}

	public _Vect div(_Vect that) {
		return new _Vect(_div(that));
	}

	public _Vect div(double scalar) {
		return new _Vect(getX()/scalar, getY()/scalar);
	}

	public _Vect mult(_Vect that) {
		return new _Vect(this.getX()*that.getX(), this.getY()*that.getY());
	}

	public _Vect mult(double scalar) {
		return new _Vect(_mult(scalar));
	}

	public _Vect neg() {
		return new _Vect(_neg());
	}

	public _Vect sub(_Vect that) {
		return new _Vect(_sub(that));
	}

	public _Vect clamp(double length) {
		return new _Vect(_clamp(length));
	}

	public _Vect normalize() {
		return new _Vect(_normalize());
	}

	public _Vect rotate(_Vect that) {
		return new _Vect(_rotate(that));
	}

	public _Vect unrotate(_Vect that) {
		return new _Vect(_unrotate(that));
	}

	public _Vect perp() {
		return new _Vect(_perp());
	}

	public _Vect rperp() {
		return new _Vect(_rperp());
	}

	public _Vect project(_Vect that) {
		return new _Vect(_project(that));
	}

	public _Vect lerp(_Vect that, double count) {
		return new _Vect(_lerp(that, count));
	}

	public _Vect lerpConst(_Vect that, double distance) {
		return new _Vect(_lerpConst(that, distance));
	}

	public _Vect slerp(_Vect that, double count) {
		return new _Vect(_slerp(that, count));
	}

	public _Vect slerpConst(_Vect that, double angle) {
		return new _Vect(_slerpConst(that, angle));
	}

	private static native long _forAngle(double angle);

	public _Vect forAngle(double angle) {
		return new _Vect(_forAngle(angle));
	}

	public static _Vect random(double minX, double maxX, double minY, double maxY) {
		return new _Vect(Util.uniform(minX, maxX), Util.uniform(minY, maxY));
	}

	public static _Vect randomWithout(double minX, double maxX, double minY, double maxY, _Vect not) {
		_Vect vect = _Vect.random(minX, maxX, minY, maxY);
		while(vect.equals(not))
			vect = _Vect.random(minX, maxX, minY, maxY);
		return vect;
	}
}

