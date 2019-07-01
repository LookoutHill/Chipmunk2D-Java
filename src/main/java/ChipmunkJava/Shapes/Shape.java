package ChipmunkJava.Shapes;

import ChipmunkJava.*;
import ChipmunkJava.Collisions.*;

import java.util.Map;
import java.util.Hashtable;

public abstract class Shape {
	private native static long init();

	protected static Map<Long, Shape> _shapes = new Hashtable<Long, Shape>();

	protected static final long cpNullPtr;

	static {
		System.loadLibrary("chipmunkJni");
		cpNullPtr = init();
	}

	protected final long cpShapePtr;

	protected Shape() {
		cpShapePtr = cpNullPtr;

		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Shapes.ShapeTest")        return;
		if(className == "ChipmunkJava.Shapes.CircleShapeTest")  return;
		if(className == "ChipmunkJava.Shapes.PolyShapeTest")    return;
		if(className == "ChipmunkJava.Shapes.SegmentShapeTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	protected Shape(long _cpShapePtr) {
		cpShapePtr = _cpShapePtr;
	}

	protected Color color = null;

	public static Shape get(long cpShapePtr) {
		if(cpShapePtr == cpNullPtr) return null;
		Shape shape = _shapes.getOrDefault(cpShapePtr, null);
		if(shape != null) return shape;

		throw new IllegalArgumentException("Invalid cpShapePtr used to resolve Shape: " + cpShapePtr);
	}

	public static Shape[] get(long[] cpShapePtrs) {
		Shape[] shapes = new Shape[cpShapePtrs.length];
		for(int pos = 0; pos < cpShapePtrs.length; pos++) {
			shapes[pos] = Shape.get(cpShapePtrs[pos]);
		}
		return shapes;
	}

	public static void remove(Shape that) {
		_shapes.remove(that.cpShapePtr);
	}

	private native void free();

	protected void finalize() {
		System.err.println("Shape.free(): " + cpShapePtr);
		free();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color value) {
		color = value;
	}

	public native boolean isSensor();
	public native void    configSensor(boolean value);

	private native long    _pointQuery(double[] point);
	private native double  _pointQueryDistance(double[] point);
	private native long    _segmentQuery(double[] pointA, double[] pointB, double radius);
	private native boolean _segmentQueryMatch(double[] pointA, double[] pointB, double radius);

	private native double[] _cacheBB();
	private native double[] _update(double[] transform);

	public  native double   getArea();
	private native double[] _getBB();
	private native long     _getBody();
	private native double[] _getCenterOfGravity();
	private native long     _getCollisionType();
	private native long     _getContactPointSet(Shape that);
	public  native double   getDensity();
	public  native double   getElasticity();
	private native long[]   _getFilter();
	public  native double   getFriction();
	public  native double   getMass();
	public  native double   getMoment();
	private native long     _getSpace();
	private native double[] _getSurfaceVelocity();

	public  native void setBody(Body body);
	public  native void setCollisionType(CollisionType ctype);
	public  native void setDensity(double value);
	public  native void setElasticity(double value);
	public  native void setFilter(ShapeFilter filter);
	public  native void setFriction(double value);
	public  native void setMass(double value);
	private native void _setSurfaceVelocity(double[] vect);

	public PointQueryInfo pointQuery(Vect point) {
		return new PointQueryInfo(_pointQuery(point.toArray()));
	}

	public double pointQueryDistance(Vect point) {
		return _pointQueryDistance(point.toArray());
	}

	public SegmentQueryInfo segmentQuery(Vect pointA, Vect pointB, double radius) {
		return new SegmentQueryInfo(_segmentQuery(pointA.toArray(), pointB.toArray(), radius));
	}

	public boolean segmentQueryMatch(Vect pointA, Vect pointB, double radius) {
		return _segmentQueryMatch(pointA.toArray(), pointB.toArray(), radius);
	}

	public BB cacheBB() {
		return new BB(_cacheBB());
	}

	public BB update(Transform transform) {
		return new BB(_update(transform.toArray()));
	}

	public BB getBB() {
		return new BB(_getBB());
	}

	public Body getBody() {
		return Body.get(_getBody());
	}

	public Vect getCenterOfGravity() {
		return new Vect(_getCenterOfGravity());
	}

	public CollisionType getCollisionType() {
		return CollisionType.get(_getCollisionType());
	}

	public ContactPointSet getContactPointSet(Shape that) {
		return new ContactPointSet(_getContactPointSet(that));
	}

	public ShapeFilter getFilter() {
		return new ShapeFilter(_getFilter());
	}

	public Space getSpace() {
		return Space.get(_getSpace());
	}

	public Vect getSurfaceVelocity() {
		return new Vect(_getSurfaceVelocity());
	}

	public void setCollisionType(String ctype) {
		setCollisionType(CollisionType.get(ctype));
	}

	public void setSurfaceVelocity(Vect vect) {
		_setSurfaceVelocity(vect.toArray());
	}
}

