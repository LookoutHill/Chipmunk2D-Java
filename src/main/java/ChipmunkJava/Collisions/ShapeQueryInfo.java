package ChipmunkJava.Collisions;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

public class ShapeQueryInfo {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected final long cpjShapeQueryInfoPtr;

	public ShapeQueryInfo(long _cpjShapeQueryInfoPtr) {
		cpjShapeQueryInfoPtr = _cpjShapeQueryInfoPtr;
	}

	private native void free();

	protected void finalize() {
		System.err.println("ShapeQueryInfo.free(): " + cpjShapeQueryInfoPtr);
		free();
	}

	private native long _getContactPointSet();
	private native long _getShape();

	public ContactPointSet getContactPointSet() {
		return new ContactPointSet(_getContactPointSet());
	}

	public Shape getShape() {
		return Shape.get(_getShape());
	}
}

