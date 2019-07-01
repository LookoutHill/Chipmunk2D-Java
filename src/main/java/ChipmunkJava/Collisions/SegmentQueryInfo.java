package ChipmunkJava.Collisions;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

public class SegmentQueryInfo {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected final long cpSegmentQueryInfoPtr;

	public SegmentQueryInfo(long _cpSegmentQueryInfoPtr) {
		cpSegmentQueryInfoPtr = _cpSegmentQueryInfoPtr;
	}

	private native void free();

	protected void finalize() {
		System.err.println("SegmentQueryInfo.free(): " + cpSegmentQueryInfoPtr);
		free();
	}

	public  native double   getAlpha();
	private native double[] _getNormal();
	private native double[] _getPoint();
	private native long     _getShape();

	public Vect getNormal() {
		return new Vect(_getNormal());
	}

	public Vect getPoint() {
		return new Vect(_getPoint());
	}

	public Shape getShape() {
		return Shape.get(_getShape());
	}
}

