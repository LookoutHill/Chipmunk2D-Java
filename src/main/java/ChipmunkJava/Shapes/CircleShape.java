package ChipmunkJava.Shapes;

import ChipmunkJava.*;

public class CircleShape extends Shape {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected CircleShape() {
		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Shapes.CircleShapeTest")  return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private native static long _new(Body body, double radius, double[] position);

	public CircleShape(Body body, double radius, Vect position) {
		super(_new(body, radius, position.toArray()));
		_shapes.put(cpShapePtr, this);
	}

	public CircleShape(Body body, double radius, Vect position, Color _color) {
		super(_new(body, radius, position.toArray()));
		_shapes.put(cpShapePtr, this);
		color = _color;
	}

	private native long _clone();

	public CircleShape(CircleShape that) {
		super(that._clone());
		_shapes.put(cpShapePtr, this);
		color = new Color(that.color);
	}

	@Override
	public CircleShape clone() {
		return new CircleShape(this);
	}

	private native double[] _getOffset();
	public  native double   getRadius();

	private native void _setOffset(double[] vect);
	public  native void setRadius(double value);

	public Vect getOffset() {
		return new Vect(_getOffset());
	}

	public void setOffset(Vect vect) {
		_setOffset(vect.toArray());
	}
}

