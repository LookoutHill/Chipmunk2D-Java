package ChipmunkJava.Shapes;

import ChipmunkJava.*;

public class PolyShape extends Shape {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	protected PolyShape() {
		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Shapes.PolyShapeTest")    return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private native static long _new(Body body, double[] vertices, double radius);

	public PolyShape(Body body, Vect[] vertices, double radius) {
		super(_new(body, Vect.fromVectArray(vertices), radius));
		_shapes.put(cpShapePtr, this);
	}

	public PolyShape(Body body, Vect[] vertices, double radius, Color _color) {
		super(_new(body, Vect.fromVectArray(vertices), radius));
		_shapes.put(cpShapePtr, this);
		color = _color;
	}

	private native static long _new2(Body body, double[] vertices, double[] transform, double radius);

	public PolyShape(Body body, Vect[] vertices, Transform transform, double radius) {
		super(_new2(body, Vect.fromVectArray(vertices), transform.toArray(), radius));
		_shapes.put(cpShapePtr, this);
	}

	public PolyShape(Body body, Vect[] vertices, Transform transform, double radius, Color _color) {
		super(_new2(body, Vect.fromVectArray(vertices), transform.toArray(), radius));
		_shapes.put(cpShapePtr, this);
		color = _color;
	}

	private native static long _newBox(Body body, double width, double height, double radius);

	public PolyShape(Body body, double width, double height, double radius) {
		super(_newBox(body, width, height, radius));
		_shapes.put(cpShapePtr, this);
	}

	public PolyShape(Body body, double width, double height, double radius, Color _color) {
		super(_newBox(body, width, height, radius));
		_shapes.put(cpShapePtr, this);
		color = _color;
	}

//	private native static long _newBox2(Body body, double[] bbox, double radius); // MATT

	private native long _clone();

	public PolyShape(PolyShape that) {
		super(that._clone());
		_shapes.put(cpShapePtr, this);
		color = new Color(that.color);
	}

	@Override
	public PolyShape clone() {
		return new PolyShape(this);
	}

	public  native int      getCount();
	public  native double   getRadius();
	private native double[] _getVertex(int pos);
	private native double[] _getVertices();

	public  native void setRadius(double value);
	private native void _setVertices(double[] vects);
	private native void _setVertices(double[] vects, double[] transform);

	public Vect getVertex(int pos) {
		return new Vect(_getVertex(pos));
	}

	public Vect[] getVertices() {
		return Vect.toVectArray(_getVertices());
	}

	public void setVertices(Vect[] vects) {
		_setVertices(Vect.fromVectArray(vects));
	}

	public void setVertices(Vect[] vects, Transform transform) {
		_setVertices(Vect.fromVectArray(vects), transform.toArray());
	}
}

