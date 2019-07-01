package ChipmunkJava;

public class Vect {
	public static final Vect zero = new Vect(0, 0);
	public static final Vect unit = new Vect(1, 0);

	public final double x;
	public final double y;

	public Vect(double _x, double _y) {
		x = _x;
		y = _y;
	}

	public Vect() {
		x = 0.0;
		y = 0.0;
	}

	public Vect(double[] array) {
		x = array[0];
		y = array[1];
	}

	public static Vect[] toVectArray(double[] array) {
		Vect[] vects = new Vect[array.length/2];
		for(int pos = 0; pos < array.length; pos+=2) {
			vects[pos/2] = new Vect(array[pos], array[pos+1]);
		}
		return vects;
	}

	public static double[] fromVectArray(Vect[] vects) {
		double[] array = new double[2*vects.length];
		for(int pos = 0; pos < vects.length; pos++) {
			Vect vect = vects[pos];

			array[2*pos]   = vect.x;
			array[2*pos+1] = vect.y;
		}
		return array;
	}

	public Vect(Vect that) {
		x = that.x;
		y = that.y;
	}

	public Vect clone() {
		return new Vect(this);
	}

	public Vect(_Vect that) {
		x = that.getX();
		y = that.getY();
	}

	public double[] toArray() {
		return new double[] { x, y };
	}

	@Override
	public boolean equals(Object _that) {
		try {
			Vect that = (Vect) _that;
			return this.x == that.x && this.y == that.y;
		} catch(Exception err) {
			return false;
		}
	}

	public boolean almost(Vect that, double tolerance) {
		double dx = Math.abs(this.x - that.x);
		double dy = Math.abs(this.y - that.y);
		return dx <= tolerance && dy <= tolerance;
	}

	@Override
	public int hashCode() {
		long bitsX64 = Double.doubleToLongBits(x);
		long bitsY64 = Double.doubleToLongBits(y);

		int bitsX32 = (int) (bitsX64 ^ (bitsX64 >>> 32));
		int bitsY32 = (int) (bitsY64 ^ (bitsY64 >>> 32));

		long hash = 17;
		hash = 31 * hash + bitsX32;
		hash = 31 * hash + bitsY32;
		return (int) hash;
	}

	@Override
	public String toString() {
		return "Vect(x:" + x + ", y:" + y + ")";
	}

	public double length() {
		return Math.sqrt(this.dot(this));
	}

	public double lengthSq() {
		return this.dot(this);
	}

	public Vect add(Vect that) {
		return new Vect(this.x+that.x, this.y+that.y);
	}

	public double cross(Vect that) {
		return this.x*that.y - this.y*that.x;
	}

	public double dot(Vect that) {
		return this.x*that.x + this.y*that.y;
	}

	public Vect div(Vect that) {
		return new Vect(this.x/that.x, this.y/that.y);
	}

	public Vect div(double scalar) {
		return new Vect(x/scalar, y/scalar);
	}

	public Vect mult(Vect that) {
		return new Vect(this.x*that.x, this.y*that.y);
	}

	public Vect mult(double scalar) {
		return new Vect(x*scalar, y*scalar);
	}

	public Vect neg() {
		return new Vect(-x, -y);
	}

	public Vect sub(Vect that) {
		return new Vect(this.x-that.x, this.y-that.y);
	}

	public Vect clamp(double length) {
		return (this.dot(this) > length*length) ? this.normalize().mult(length) : this;
	}

	public double toAngle() {
		return Math.atan2(y, x);
	}

	public Vect normalize() {
		return this.mult(1.0/(Util.littleMore(length())));
	}

	public Vect rotate(Vect that) {
		return new Vect(this.x*that.x - this.y*that.y, this.x*that.y + this.y*that.x);
	}

	public Vect unrotate(Vect that) {
		return new Vect(this.x*that.x + this.y*that.y, this.y*that.x - this.x*that.y);
	}

	public double dist(Vect that) {
		return this.sub(that).length();
	}

	public double distSq(Vect that) {
		return this.sub(that).lengthSq();
	}

	public boolean near(Vect that, double distance) {
		return this.distSq(that) < distance*distance;
	}

	public Vect perp() {
		return new Vect(-y, x);
	}

	public Vect rperp() {
		return new Vect(y, -x);
	}

	public Vect project(Vect that) {
		return that.mult(this.dot(that) / that.dot(that));
	}

	public Vect lerp(Vect that, double step) {
		return this.mult(1.0-step).add(that.mult(step));
	}

	public Vect lerpConst(Vect that, double distance) {
		return this.add(that.sub(this).clamp(distance));
	}

	public Vect slerp(Vect that, double step) {
		double dot   = this.normalize().dot(that.normalize());
		double omega = Math.acos(Util.clamp(dot, -1.0, 1.0));
	
		if(omega < 0.001) {
			// If the angle between two vectors is very small, lerp instead to avoid precision issues.
			return this.lerp(that, step);
		} else {
			return this.mult(Math.sin((1.0-step)*omega)).add(that.mult(Math.sin(step*omega))).div(Math.sin(omega));
		}
	}

	public Vect slerpConst(Vect that, double angle) {
		double dot   = this.normalize().dot(that.normalize());
		double omega = Math.acos(Util.clamp(dot, -1.0, 1.0));
	
		return this.slerp(that, Math.min(angle, omega)/omega);
	}

	public static Vect forAngle(double angle) {
		return new Vect(Math.cos(angle), Math.sin(angle));
	}

	public static Vect random(double minX, double maxX, double minY, double maxY) {
		return new Vect(Util.uniform(minX, maxX), Util.uniform(minY, maxY));
	}

	public static Vect randomWithout(double minX, double maxX, double minY, double maxY, Vect not) {
		Vect vect = Vect.random(minX, maxX, minY, maxY);
		while(vect.equals(not))
			vect = Vect.random(minX, maxX, minY, maxY);
		return vect;
	}
}

