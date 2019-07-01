package ChipmunkJava;

import ChipmunkJava.Collisions.*;
import ChipmunkJava.Constraints.*;
import ChipmunkJava.Debug.*;
import ChipmunkJava.Handlers.*;
import ChipmunkJava.Shapes.*;

import java.util.Map;
import java.util.Hashtable;

public class Space {
	private native static long init();

	protected static final long cpNullPtr;

	static {
		System.loadLibrary("chipmunkJni");
		cpNullPtr = init();
	}

	protected static Map<Long, Space> _spaces = new Hashtable<Long, Space>();

	protected final long cpSpacePtr;

	private native static long _new();

	public Space() {
		cpSpacePtr = _new();
		_spaces.put(cpSpacePtr, this);
	}

	public static Space get(long cpSpacePtr) {
		if(cpSpacePtr == cpNullPtr) return null;
		Space space = _spaces.getOrDefault(cpSpacePtr, null);
		if(space != null) return space;

		throw new IllegalArgumentException("Invalid cpSpacePtr used to resolve Space: " + cpSpacePtr);
	}

	public static void remove(Space that) {
		_spaces.remove(that.cpSpacePtr);
	}

	private native void free();

	protected void finalize() {
		System.err.println("Space.free(): " + cpSpacePtr);
		free();
	}

	public void addCollisionType(String name) {
		CollisionType.add(name);
	}

	public CollisionType getCollisionType(String name) {
		return CollisionType.get(name);
	}

	public void putCollisionType(String name) {
		CollisionType.put(name);
	}

	public void setCollisionHandler(CollisionType ctype1, CollisionType ctype2, CollisionHandler handler) {
		CollisionSet.setCollisionHandler(this, ctype1, ctype2, handler);
	}

	public void setCollisionHandler(String _ctype1, String _ctype2, CollisionHandler handler) {
		CollisionType ctype1 = CollisionType.get(_ctype1);
		CollisionType ctype2 = CollisionType.get(_ctype2);

		CollisionSet.setCollisionHandler(this, ctype1, ctype2, handler);
	}

	public void resetCollisionHandler(CollisionType ctype1, CollisionType ctype2, CollisionHandler handler) {
		CollisionSet.resetCollisionHandler(this, ctype1, ctype2, handler);
	}

	public void resetCollisionHandler(String _ctype1, String _ctype2, CollisionHandler handler) {
		CollisionType ctype1 = CollisionType.get(_ctype1);
		CollisionType ctype2 = CollisionType.get(_ctype2);

		CollisionSet.resetCollisionHandler(this, ctype1, ctype2, handler);
	}

	public native void addBody(Body body);
	public native void removeBody(Body body);
	public native void addConstraint(Constraint constraint);
	public native void removeConstraint(Constraint constraint);
	public native void addShape(Shape shape);
	public native void removeShape(Shape shape);

	public native boolean containsBody(Body body);
	public native boolean containsConstraint(Constraint constraint);
	public native boolean containsShape(Shape shape);
	public native boolean isLocked();

	public native void reindexShape(Shape shape);
	public native void reindexShapesForBody(Body body);
	public native void reindexStatic();

	private native long[] _BBQuery(double[] bb, ShapeFilter filter);
	private native long[] _pointQuery(double[] point, double radius, ShapeFilter filter);
	private native long   _pointQueryNearest(double[] point, double radius, ShapeFilter filter);
	private native long[] _segmentQuery(double[] pointA, double[] pointB, double radius, ShapeFilter filter);
	private native long   _segmentQueryFirst(double[] pointA, double[] pointB, double radius, ShapeFilter filter);
	private native long[] _shapeQuery(Shape shape);

	public  native double   getCollisionBias();
	public  native long     _getCollisionPersistence();
	public  native double   getCollisionSlop();
	public  native double   getCurrentTimeStep();
	public  native double   getDamping();
	private native double[] _getGravity();
	public  native double   getIdleSpeedThreshold();
	public  native int      getIterations();
	public  native double   getSleepTimeThreshold();
	private native long     _getStaticBody();
	public  native long     getStepCount();

	public  native void setCollisionBias(double value);
	public  native void setCollisionPersistence(long value);
	public  native void setCollisionSlop(double value);
	public  native void setDamping(double value);
	private native void _setGravity(double[] vect);
	public  native void setIdleSpeedThreshold(double value);
	public  native void setIterations(int value);
	public  native void setSleepTimeThreshold(double value);

	public native void useSpatialHash(double size, int count);
	public native void debugDraw(SpaceDebugDrawOptions options);
	public native void step(double interval);

	public Shape[] BBQuery(BB bb, ShapeFilter filter) {
		long[]  ptrList = _BBQuery(bb.toArray(), filter);
		Shape[] shapes  = new Shape[ptrList.length];
		for(int pos = 0; pos < ptrList.length; pos++)
			shapes[pos] = Shape.get(ptrList[pos]);
		return shapes;
	}

	public PointQueryInfo[] pointQuery(Vect point, double radius, ShapeFilter filter) {
		long[]           ptrList  = _pointQuery(point.toArray(), radius, filter);
		PointQueryInfo[] infoList = new PointQueryInfo[ptrList.length];
		for(int pos = 0; pos < ptrList.length; pos++)
			infoList[pos] = new PointQueryInfo(ptrList[pos]);
		return infoList;
	}

	public PointQueryInfo[] pointQuery(Vect point, double radius) {
		return pointQuery(point, radius, ShapeFilter.filterNothing);
	}

	public Shape pointQueryNearest(Vect point, double radius, ShapeFilter filter) {
		return Shape.get(_pointQueryNearest(point.toArray(), radius, filter));
	}

	public Shape pointQueryNearest(Vect point, double radius) {
		return Shape.get(_pointQueryNearest(point.toArray(), radius, ShapeFilter.filterNothing));
	}

	public SegmentQueryInfo[] segmentQuery(Vect pointA, Vect pointB, double radius, ShapeFilter filter) {
		long[]             ptrList  = _segmentQuery(pointA.toArray(), pointB.toArray(), radius, filter);
		SegmentQueryInfo[] infoList = new SegmentQueryInfo[ptrList.length];
		for(int pos = 0; pos < ptrList.length; pos++)
			infoList[pos] = new SegmentQueryInfo(ptrList[pos]);
		return infoList;
	}

	public SegmentQueryInfo[] segmentQuery(Vect pointA, Vect pointB, double radius) {
		return segmentQuery(pointA, pointB, radius, ShapeFilter.filterNothing);
	}

	public Shape segmentQueryFirst(Vect pointA, Vect pointB, double radius, ShapeFilter filter) {
		return Shape.get(_segmentQueryFirst(pointA.toArray(), pointB.toArray(), radius, filter));
	}

	public Shape segmentQueryFirst(Vect pointA, Vect pointB, double radius) {
		return Shape.get(_segmentQueryFirst(pointA.toArray(), pointB.toArray(), radius, ShapeFilter.filterNothing));
	}

	public ShapeQueryInfo[] shapeQuery(Shape shape) {
		long[]           ptrList  = _shapeQuery(shape);
		ShapeQueryInfo[] infoList = new ShapeQueryInfo[ptrList.length];
		for(int pos = 0; pos < ptrList.length; pos++)
			infoList[pos] = new ShapeQueryInfo(ptrList[pos]);
		return infoList;
	}

	public Vect getGravity() {
		return new Vect(_getGravity());
	}

	public Body getStaticBody() {
		return Body.get(_getStaticBody());
	}

	public void setGravity(Vect vect) {
		_setGravity(vect.toArray());
	}
}

