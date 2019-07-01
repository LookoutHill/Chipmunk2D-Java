package ChipmunkJava.Shapes;

import ChipmunkJava.*;

public class _Transform {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	public static final _Transform identity = new _Transform(1.0, 0.0, 0.0, 1.0, 0.0, 0.0);

	protected long cpTransformPtr;

	private native static long _new(double a, double b, double c, double d, double tx, double ty);

	public _Transform(double a, double b, double c, double d, double tx, double ty) {
		cpTransformPtr = _new(a, b, c, d, tx, ty);
	}

	public _Transform(long _cpTransformPtr) {
		cpTransformPtr = _cpTransformPtr;
	}

	private native long _clone();

	public _Transform(_Transform that) {
		cpTransformPtr = that._clone();
	}

	public _Transform clone() {
		return new _Transform(this);
	}

	public _Transform(Transform that) {
		cpTransformPtr = _new(that.a, that.b, that.c, that.d, that.tx, that.ty);
	}

	private native void free();

	protected void finalize() {
		System.err.println("_Transform.free(): " + cpTransformPtr);
		free();
	}

	public native double getA();
	public native double getB();
	public native double getC();
	public native double getD();
	public native double getTX();
	public native double getTY();

	public native void setA(double value);
	public native void setB(double value);
	public native void setC(double value);
	public native void setD(double value);
	public native void setTX(double value);
	public native void setTY(double value);

	@Override
	public String toString() {
		return "_Transform(a:" + getA() + ", b:" + getB() + ", c:" + getC() + ", d:" + getD() + ", tx:" + getTX() + ", ty:" + getTY() + ")";
	}

	@Override
	public boolean equals(Object _that) {
		try {
			_Transform that = (_Transform) _that;
			return this.getA() == that.getA() && this.getB() == that.getB() && this.getC() == that.getC() && this.getD() == that.getD() && this.getTX() == that.getTX() && this.getTY() == that.getTY();
		} catch(Exception err) {
			return false;
		}
	}

	public boolean almost(_Transform that, double tolerance) {
		double da  = Math.abs(this.getA()  - that.getA());
		double db  = Math.abs(this.getB()  - that.getB());
		double dc  = Math.abs(this.getC()  - that.getC());
		double dd  = Math.abs(this.getD()  - that.getD());
		double dtx = Math.abs(this.getTX() - that.getTX());
		double dty = Math.abs(this.getTY() - that.getTY());
		return da <= tolerance && db <= tolerance && dc <= tolerance && dd <= tolerance && dtx <= tolerance && dty <= tolerance;
	}

	@Override
	public int hashCode() {
		long bitsA64  = Double.doubleToLongBits(getA());
		long bitsB64  = Double.doubleToLongBits(getB());
		long bitsC64  = Double.doubleToLongBits(getC());
		long bitsD64  = Double.doubleToLongBits(getD());
		long bitsTX64 = Double.doubleToLongBits(getTX());
		long bitsTY64 = Double.doubleToLongBits(getTY());

		byte  bitsA32  = (byte)  (bitsA64  ^ (bitsA64  >>> 56));
		byte  bitsB32  = (byte)  (bitsB64  ^ (bitsB64  >>> 56));
		byte  bitsC32  = (byte)  (bitsC64  ^ (bitsC64  >>> 56));
		byte  bitsD32  = (byte)  (bitsD64  ^ (bitsD64  >>> 56));
		short bitsTX32 = (short) (bitsTX64 ^ (bitsTX64 >>> 48));
		short bitsTY32 = (short) (bitsTY64 ^ (bitsTY64 >>> 48));

		long hash = 17;
		hash = 31 * hash + bitsA32;
		hash = 31 * hash + bitsB32;
		hash = 31 * hash + bitsC32;
		hash = 31 * hash + bitsD32;
		hash = 31 * hash + bitsTX32;
		hash = 31 * hash + bitsTY32;
		return (int) hash;
	}

	private native long _invert();
	private native long _mult(_Transform that);
	private native long _rigidInvert();
	private native long _transformBB(_BB bb);
	private native long _transformPoint(_Vect point);
	private native long _transformVect(_Vect vect);
	private native long _transpose();

	public _Transform invert() {
		return new _Transform(_invert());
	}

	public _Transform mult(_Transform that) {
		return new _Transform(_mult(that));
	}

	public _Transform rigidInvert() {
		return new _Transform(_rigidInvert());
	}

	public _BB transformBB(_BB bb) {
		return new _BB(_transformBB(bb));
	}

	public _Vect transformPoint(_Vect point) {
		return new _Vect(_transformPoint(point));
	}

	public _Vect transformVect(_Vect vect) {
		return new _Vect(_transformVect(vect));
	}

	public _Transform transpose() {
		return new _Transform(_transpose());
	}

	private native static long _axialScale(_Vect axis, _Vect pivot, double scale);
	private native static long _boneScale(_Vect offsetA, _Vect offsetB);
	private native static long _ortho(_BB bb);
	private native static long _rigid(_Vect offset, double angle);
	private native static long _rotate(double angle);
	private native static long _scale(double scaleX, double scaleY);
	private native static long _translate(_Vect offset);
	private native static long _transpose(double a, double c, double tx, double b, double d, double ty);
	private native static long _wrap(_Transform outer, _Transform inner);
	private native static long _wrapInverse(_Transform outer, _Transform inner);

	public static _Transform axialScale(_Vect axis, _Vect pivot, double scale) {
		return new _Transform(_axialScale(axis, pivot, scale));
	}

	public static _Transform boneScale(_Vect offsetA, _Vect offsetB) {
		return new _Transform(_boneScale(offsetA, offsetB));
	}

	public static _Transform ortho(_BB bb) {
		return new _Transform(_ortho(bb));
	}

	public static _Transform rigid(_Vect offset, double angle) {
		return new _Transform(_rigid(offset, angle));
	}

	public static _Transform rotate(double angle) {
		return new _Transform(_rotate(angle));
	}

	public static _Transform scale(double scaleX, double scaleY) {
		return new _Transform(_scale(scaleX, scaleY));
	}

	public static _Transform translate(_Vect offset) {
		return new _Transform(_translate(offset));
	}

	public static _Transform transpose(double a, double b, double c, double d, double tx, double ty) {
		return new _Transform(_transpose(a, b, c, d, tx, ty));
	}

	public static _Transform wrap(_Transform outer, _Transform inner) {
		return new _Transform(_wrap(outer, inner));
	}

	public static _Transform wrapInverse(_Transform outer, _Transform inner) {
		return new _Transform(_wrapInverse(outer, inner));
	}

	public static _Transform random(double min, double max, double minT, double maxT) {
		double a  = Util.uniform(min, max);
		double b  = Util.uniform(min, max);
		double c  = Util.uniform(min, max);
		double d  = Util.uniform(min, max);
		double tx = Util.uniform(minT, maxT);
		double ty = Util.uniform(minT, maxT);
		return new _Transform(a, b, c, d, tx, ty);
	}

	public static _Transform random(double min, double max, double minT, double maxT, _Transform not) {
		_Transform transform = _Transform.random(min, max, minT, maxT);
		while(transform.equals(not))
			transform = _Transform.random(min, max, minT, maxT);
		return transform;
	}
}

