package ChipmunkJava;

public abstract class Chipmunk {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	public  native static double areaForCircle(double innerRadius, double outerRadius);
	private native static double _areaForPoly(double[] vertices, double radius);
	private native static double _areaForSegment(double[] pointA, double[] pointB, double radius);
	private native static double _momentForCircle(double mass, double innerRadius, double outerRadius, double[] offset);
	public  native static double momentForBox(double mass, double width, double height);
	private native static double _momentForPoly(double mass, double[] vertices, double[] offset, double radius);
	private native static double _momentForSegment(double mass, double[] pointA, double[] pointB, double radius);

	public static double areaForPoly(Vect[] vertices, double radius) {
		return _areaForPoly(Vect.fromVectArray(vertices), radius);
	}

	public static double areaForSegment(Vect pointA, Vect pointB, double radius) {
		return _areaForSegment(pointA.toArray(), pointB.toArray(), radius);
	}

	public static double momentForCircle(double mass, double innerRadius, double outerRadius, Vect offset) {
		return _momentForCircle(mass, innerRadius, outerRadius, offset.toArray());
	}

	public static double momentForPoly(double mass, Vect[] vertices, Vect offset, double radius) {
		return _momentForPoly(mass, Vect.fromVectArray(vertices), offset.toArray(), radius);
	}

	public static double momentForSegment(double mass, Vect pointA, Vect pointB, double radius) {
		return _momentForSegment(mass, pointA.toArray(), pointB.toArray(), radius);
	}
}

