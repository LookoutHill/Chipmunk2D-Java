package ChipmunkJava;

public class _BB {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected final long cpBBPtr;

	protected _BB() {
		cpBBPtr = 0;

		String className = this.getClass().getName();
		if(className == "ChipmunkJava._BBTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private native static long _new(double left, double bottom, double right, double top);

	public _BB(double left, double bottom, double right, double top) {
		cpBBPtr = _new(left, bottom, right, top);
	}

	private native static long _newForExtents(_Vect center, double halfwidth, double halfheight);

	public _BB(_Vect center, double halfwidth, double halfheight) {
		cpBBPtr = _newForExtents(center, halfwidth, halfheight);
	}

	private native static long _newForCircle(_Vect center, double radius);

	public _BB(_Vect center, double radius) {
		cpBBPtr = _newForCircle(center, radius);
	}

	public _BB(long _cpBBPtr) {
		cpBBPtr = _cpBBPtr;
	}

	public static _BB[] get(long[] cpBBPtrs) {
		_BB[] bbs = new _BB[cpBBPtrs.length];
		for(int pos = 0; pos < cpBBPtrs.length; pos++) {
			bbs[pos] = new _BB(cpBBPtrs[pos]);
		}
		return bbs;
	}

	private native long _clone();

	public _BB(_BB that) {
		cpBBPtr = that._clone();
	}

	@Override
	public _BB clone() {
		return new _BB(_clone());
	}

	public _BB(BB that) {
		cpBBPtr = _new(that.l, that.b, that.r, that.t);
	}

	private native void free();

	protected void finalize() {
		System.err.println("_BB.free(): " + cpBBPtr);
		free();
	}

	public native double getL();
	public native double getB();
	public native double getR();
	public native double getT();

	public native void setL(double value);
	public native void setB(double value);
	public native void setR(double value);
	public native void setT(double value);

	@Override
	public String toString() {
		return "_BB(l:" + getL() + ", b:" + getB() + ", r:" + getR() + ", t:" + getT() + ")";
	}

	@Override
	public boolean equals(Object _that) {
		try {
			_BB that = (_BB) _that;
			return this.getL() == that.getL() && this.getB() == that.getB() && this.getR() == that.getR() && this.getT() == that.getT();
		} catch(Exception err) {
			return false;
		}
	}

	public boolean almost(_BB that, double tolerance) {
		double dl = Math.abs(this.getL() - that.getL());
		double db = Math.abs(this.getB() - that.getB());
		double dr = Math.abs(this.getR() - that.getR());
		double dt = Math.abs(this.getT() - that.getT());
		return dl <= tolerance && db <= tolerance && dr <= tolerance && dt <= tolerance;
	}

	@Override
	public int hashCode() {
		long bitsL64 = Double.doubleToLongBits(getL());
		long bitsB64 = Double.doubleToLongBits(getB());
		long bitsR64 = Double.doubleToLongBits(getR());
		long bitsT64 = Double.doubleToLongBits(getT());

		short bitsL32 = (short) (bitsL64 ^ (bitsL64 >>> 48));
		short bitsB32 = (short) (bitsB64 ^ (bitsB64 >>> 48));
		short bitsR32 = (short) (bitsR64 ^ (bitsR64 >>> 48));
		short bitsT32 = (short) (bitsT64 ^ (bitsT64 >>> 48));

		long hash = 17;
		hash = 31 * hash + bitsL32;
		hash = 31 * hash + bitsB32;
		hash = 31 * hash + bitsR32;
		hash = 31 * hash + bitsT32;
		return (int) hash;
	}

	public  native double  area();
	private native long    _center();
	private native long    _clampVect(_Vect vect);
	public  native boolean containsBB(_BB that);
	public  native boolean containsPoint(_Vect point);
	private native long    _expand(_Vect vect);
	public  native boolean intersects(_BB that);
	public  native boolean intersectsSegment(_Vect pointA, _Vect pointB);
	private native long    _merge(_BB that);
	public  native double  mergedArea(_BB that);
	private native long    _offset(_Vect vect);
	public  native double  segmentQuery(_Vect pointA, _Vect pointB);
	private native long    _wrapVect(_Vect vect);

	public _Vect center() {
		return new _Vect(_center());
	}

	public _Vect clampVect(_Vect vect) {
		return new _Vect(_clampVect(vect));
	}

	public _BB expand(_Vect vect) {
		return new _BB(_expand(vect));
	}

	public _BB merge(_BB that) {
		return new _BB(_merge(that));
	}

	public _BB offset(_Vect vect) {
		return new _BB(_offset(vect));
	}

	public _Vect wrapVect(_Vect vect) {
		return new _Vect(_wrapVect(vect));
	}

	public static _BB random(double minL, double maxL, double minB, double maxB, double maxR, double maxT) {
		double l = Util.uniform(minL, maxL);
		double b = Util.uniform(minB, maxB);
		double r = Util.uniform(l,    maxR);
		double t = Util.uniform(b,    maxT);
		return new _BB(l, b, r, t);
	}

	public static _BB randomWithout(double minL, double maxL, double minB, double maxB, double maxR, double maxT, _BB not) {
		_BB bb = _BB.random(minL, maxL, minB, maxB, maxR, maxT);
		while(bb.equals(not))
			bb = _BB.random(minL, maxL, minB, maxB, maxR, maxT);
		return bb;
	}
}

