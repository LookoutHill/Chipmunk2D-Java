package ChipmunkJava.Shapes;

import ChipmunkJava.*;

public class SegmentShape extends Shape {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected SegmentShape() {
		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Shapes.SegmentShapeTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private native static long _new(Body body, double[] pointA, double[] pointB, double r);

	public SegmentShape(Body body, Vect pointA, Vect pointB, double r) {
		super(_new(body, pointA.toArray(), pointB.toArray(), r));
		_shapes.put(cpShapePtr, this);
	}

	public SegmentShape(Body body, Vect pointA, Vect pointB, double r, Color _color) {
		super(_new(body, pointA.toArray(), pointB.toArray(), r));
		_shapes.put(cpShapePtr, this);
		color = _color;
	}

	private native long _clone();

	public SegmentShape(SegmentShape that) {
		super(that._clone());
		_shapes.put(cpShapePtr, this);
		color = new Color(that.color);
	}

	@Override
	public SegmentShape clone() {
		return new SegmentShape(this);
	}

	private native double[] _getPointA();
	private native double[] _getPointB();
	private native double[] _getNormal();
	public  native double getRadius();

	private native void _setEndpoints(double[] pointA, double[] pointB);
	public  native void setRadius(double value);

	public Vect getPointA() {
		return new Vect(_getPointA());
	}

	public Vect getPointB() {
		return new Vect(_getPointB());
	}

	public Vect getNormal() {
		return new Vect(_getNormal());
	}

	public void setEndpoints(Vect pointA, Vect pointB) {
		_setEndpoints(pointA.toArray(), pointB.toArray());
	}
}

