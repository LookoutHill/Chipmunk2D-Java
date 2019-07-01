package ChipmunkJava.Collisions;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

public class PointQueryInfo {
	private native static long init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected final long cpPointQueryInfoPtr;

	public PointQueryInfo(long _cpPointQueryInfoPtr) {
		cpPointQueryInfoPtr = _cpPointQueryInfoPtr;
	}

	private native void free();

	protected void finalize() {
		System.err.println("PointQueryInfo.free(): " + cpPointQueryInfoPtr);
		free();
	}

	public  native double   getDistance();
	private native double[] _getGradient();
	private native double[] _getPoint();
	private native long     _getShape();

	public Vect getGradient() {
		return new Vect(_getGradient());
	}

	public Vect getPoint() {
		return new Vect(_getPoint());
	}

	public Shape getShape() {
		return Shape.get(_getShape());
	}
}

