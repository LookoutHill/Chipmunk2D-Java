package ChipmunkJava.Debug;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

import java.util.Map;
import java.util.Hashtable;

public abstract class SpaceDebugDrawOptions {
	private native static long init();

	protected static final long cpNullPtr;

	static {
		System.loadLibrary("chipmunkJni");
		cpNullPtr = init();
	}

	protected static Map<Long, SpaceDebugDrawOptions> _optionsMap = new Hashtable<Long, SpaceDebugDrawOptions>();

    protected static final Color DYNAMIC_COLOR   = new Color( 52, 152, 219);
    protected static final Color STATIC_COLOR    = new Color(149, 165, 166);
    protected static final Color KINEMATIC_COLOR = new Color( 39, 174,  96);
    protected static final Color SLEEPING_COLOR  = new Color(114, 148, 168);

	protected final long cpSpaceDebugDrawOptionsPtr;

	private native static long _new();

	protected SpaceDebugDrawOptions() {
		cpSpaceDebugDrawOptionsPtr = _new();
		_optionsMap.put(cpSpaceDebugDrawOptionsPtr, this);
	}

	public static SpaceDebugDrawOptions get(long cpSpaceDebugDrawOptionsPtr) {
		if(cpSpaceDebugDrawOptionsPtr == cpNullPtr) return null;
		SpaceDebugDrawOptions options = _optionsMap.getOrDefault(cpSpaceDebugDrawOptionsPtr, null);
		if(options != null) return options;

		throw new IllegalArgumentException("Invalid cpSpaceDebugDrawOptionsPtr used to resolve SpaceDebugDrawOptions: " + cpSpaceDebugDrawOptionsPtr);
	}

	public static void remove(SpaceDebugDrawOptions that) {
		_optionsMap.remove(that.cpSpaceDebugDrawOptionsPtr);
	}

	private native static void free();

	protected void finalize() {
		System.err.println("SpaceDebugDrawOptions.free(): " + cpSpaceDebugDrawOptionsPtr);
		free();
	}

	private static float[] _colorForShape(long _shape, long _options) {
		SpaceDebugDrawOptions options = SpaceDebugDrawOptions.get(_options);
		Shape                 shape   = Shape.get(_shape);
		return options.colorForShape(shape).toFloatArray();
	}

	private static void _drawCircle(double[] _position, double angle, double radius, float[] _strokeColor, float[] _fillColor, long _options) {
		SpaceDebugDrawOptions options     = SpaceDebugDrawOptions.get(_options);
		Vect                  position    = new Vect(_position);
		Color                 strokeColor = new Color(_strokeColor);
		Color                 fillColor   = new Color(_fillColor);
		options.drawCircle(position, angle, radius, strokeColor, fillColor);
	}

	private static void _drawDot(double size, double[] _position, float[] _strokeColor, long _options) {
		SpaceDebugDrawOptions options     = SpaceDebugDrawOptions.get(_options);
		Vect                  position    = new Vect(_position);
		Color                 strokeColor = new Color(_strokeColor);
		options.drawDot(size, position, strokeColor);
	}

	private static void _drawFatSegment(double[] _pointA, double[] _pointB, double radius, float[] _strokeColor, float[] _fillColor, long _options) {
		SpaceDebugDrawOptions options     = SpaceDebugDrawOptions.get(_options);
		Vect                  pointA      = new Vect(_pointA);
		Vect                  pointB      = new Vect(_pointB);
		Color                 strokeColor = new Color(_strokeColor);
		Color                 fillColor   = new Color(_fillColor);
		options.drawFatSegment(pointA, pointB, radius, strokeColor, fillColor);
	}

	private static void _drawPolygon(double[] _vertices, double radius, float[] _strokeColor, float[] _fillColor, long _options) {
		SpaceDebugDrawOptions options      = SpaceDebugDrawOptions.get(_options);
		Vect[]                 vertices    = Vect.toVectArray(_vertices);
		Color                  strokeColor = new Color(_strokeColor);
		Color                  fillColor   = new Color(_fillColor);
		options.drawPolygon(vertices, radius, strokeColor, fillColor);
	}

	private static void _drawSegment(double[] _pointA, double[] _pointB, float[] _strokeColor, long _options) {
		SpaceDebugDrawOptions options     = SpaceDebugDrawOptions.get(_options);
		Vect                  pointA      = new Vect(_pointA);
		Vect                  pointB      = new Vect(_pointB);
		Color                 strokeColor = new Color(_strokeColor);
		options.drawSegment(pointA, pointB, strokeColor);
	}

	public Color colorForShape(Shape shape) {
        Color color = shape.getColor();
		if(color != null) return color;

        color = DYNAMIC_COLOR;

		Body body = shape.getBody();
        if(body != null) {
            BodyType btype = body.getBodyType();
            if     (btype == BodyType.STATIC)    color = STATIC_COLOR;
			else if(btype == BodyType.KINEMATIC) color = KINEMATIC_COLOR;
			else if(body.isSleeping())           color = SLEEPING_COLOR;
		}
                
        return color;
	}

	public abstract void drawCircle(Vect position, double angle, double radius, Color strokeColor, Color fillColor);
	public abstract void drawDot(double size, Vect position, Color strokeColor);
	public abstract void drawFatSegment(Vect pointA, Vect pointB, double radius, Color strokeColor, Color fillColor);
	public abstract void drawPolygon(Vect[] vertices, double radius, Color strokeColor, Color fillColor);
	public abstract void drawSegment(Vect pointA, Vect pointB, Color strokeColor);

	public native boolean isDrawingCollisionPoints();
	public native boolean isDrawingConstraints();
	public native boolean isDrawingShapes();

	public native void configDrawingCollisionPoints(boolean value);
	public native void configDrawingConstraints(boolean value);
	public native void configDrawingShapes(boolean value);
	
	private native float[] _getCollisionPointColor();
	private native float[] _getConstraintColor();
	private native float[] _getShapeOutlineColor();

	public native void setCollisionPointColor(float[] color);
	public native void setConstraintColor(float[] color);
	public native void setShapeOutlineColor(float[] color);

	public Color getCollisionPointColor() {
		return new Color(_getCollisionPointColor());
	}

	public Color getConstraintColor() {
		return new Color(_getConstraintColor());
	}

	public Color getShapeOutlineColor() {
		return new Color(_getShapeOutlineColor());
	}
}

