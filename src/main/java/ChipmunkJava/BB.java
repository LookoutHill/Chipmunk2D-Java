package ChipmunkJava;

public class BB {
	public final double l;
	public final double b;
	public final double r;
	public final double t;

	public BB(double left, double bottom, double right, double top) {
		l = left;
		b = bottom;
		r = right;
		t = top;
	}

	public BB(Vect center, double halfwidth, double halfheight) {
		this(center.x - halfwidth, center.y - halfheight, center.x + halfwidth, center.y + halfheight);
	}

	public BB(Vect center, double radius) {
		this(center, radius, radius);
	}

	public BB() {
		l = 0.0;
		b = 0.0;
		r = 0.0;
		t = 0.0;
	}

	public BB(double[] array) {
		l = array[0];
		b = array[1];
		r = array[2];
		t = array[3];
	}

	public static BB[] toBBArray(double[] array) {
		BB[] bbs = new BB[array.length/4];
		for(int pos = 0; pos < array.length; pos+=4) {
			bbs[pos/4] = new BB(array[pos], array[pos+1], array[pos+2], array[pos+3]);
		}
		return bbs;
	}

	public static double[] fromBBArray(BB[] bbs) {
		double[] array = new double[4*bbs.length];
		for(int pos = 0; pos < bbs.length; pos++) {
			BB bb = bbs[pos];

			array[4*pos]   = bb.l;
			array[4*pos+1] = bb.b;
			array[4*pos+2] = bb.r;
			array[4*pos+3] = bb.t;
		}
		return array;
	}

	public BB(BB that) {
		l = that.l;
		b = that.b;
		r = that.r;
		t = that.t;
	}

	public BB clone() {
		return new BB(this);
	}

	public BB(_BB that) {
		l = that.getL();
		b = that.getB();
		r = that.getR();
		t = that.getT();
	}

	public double[] toArray() {
		return new double[] { l, b, r, t };
	}

	@Override
	public String toString() {
		return "BB(l:" + l + ", b:" + b + ", r:" + r + ", t:" + t + ")";
	}

	@Override
	public boolean equals(Object _that) {
		try {
			BB that = (BB) _that;
			return this.l == that.l && this.b == that.b && this.r == that.r && this.t == that.t;
		} catch(Exception err) {
			return false;
		}
	}

	public boolean almost(BB that, double tolerance) {
		double dl = Math.abs(this.l - that.l);
		double db = Math.abs(this.b - that.b);
		double dr = Math.abs(this.r - that.r);
		double dt = Math.abs(this.t - that.t);
		return dl <= tolerance && db <= tolerance && dr <= tolerance && dt <= tolerance;
	}

	@Override
	public int hashCode() {
		long bitsL64 = Double.doubleToLongBits(l);
		long bitsB64 = Double.doubleToLongBits(b);
		long bitsR64 = Double.doubleToLongBits(r);
		long bitsT64 = Double.doubleToLongBits(t);

		short bitsL32 = (short) (bitsL64 ^ (bitsL64 >>> 48));
		short bitsB32 = (short) (bitsB64 ^ (bitsB64 >>> 48));
		short bitsR32 = (short) (bitsR64 ^ (bitsR64 >>> 48));
		short bitsT32 = (short) (bitsT64 ^ (bitsT64 >>> 48));

		long hash = 17;
		hash = 31 * hash + bitsL32;
		hash = 31 * hash + bitsB32;
		hash = 31 * hash + bitsR32;
		hash = 31 * hash + bitsT32;
		return (int) hash;
	}

	public double area() {
		return (r - l)*(t - b);
	}

	public Vect center() {
		return new Vect(l, b).lerp(new Vect(r, t), 0.5);
	}

	public Vect clampVect(Vect point) {
		return new Vect(Util.clamp(point.x, l, r), Util.clamp(point.y, b, t));
	}

	public boolean containsBB(BB that) {
		return this.l <= that.l && this.r >= that.r && this.b <= that.b && this.t >= that.t;
	}

	public boolean containsPoint(Vect point) {
		return (l <= point.x && r >= point.x && b <= point.y && t >= point.y);
	}

	public BB expand(Vect point) {
		return new BB(
			Math.min(l, point.x),
			Math.min(b, point.y),
			Math.max(r, point.x),
			Math.max(t, point.y)
		);
	}

	public boolean intersects(BB that) {
		return this.l <= that.r && that.l <= this.r && this.b <= that.t && that.b <= this.t;
	}

	public boolean intersectsSegment(Vect pointA, Vect pointB) {
		return segmentQuery(pointA, pointB) != Double.POSITIVE_INFINITY;
	}

	public BB merge(BB that) {
		return new BB(
			Math.min(this.l, that.l),
			Math.min(this.b, that.b),
			Math.max(this.r, that.r),
			Math.max(this.t, that.t)
		);
	}

	public double mergedArea(BB that) {
		return (Math.max(this.r, that.r) - Math.min(this.l, that.l))*(Math.max(this.t, that.t) - Math.min(this.b, that.b));
	}

	public BB offset(Vect point) {
		return new BB(
			l + point.x,
			b + point.y,
			r + point.x,
			t + point.y
		);
	}

	public double segmentQuery(Vect pointA, Vect pointB) {
		Vect   delta = pointB.sub(pointA);
		double tmin  = Double.NEGATIVE_INFINITY;
		double tmax  = Double.POSITIVE_INFINITY;

		if(delta.x == 0.0) {
			if(pointA.x < l || r < pointA.x) return Double.POSITIVE_INFINITY;
		} else {
			double t1 = (l - pointA.x)/delta.x;
			double t2 = (r - pointA.x)/delta.x;

			tmin = Math.max(tmin, Math.min(t1, t2));
			tmax = Math.min(tmax, Math.max(t1, t2));
		}

		if(delta.y == 0.0) {
			if(pointA.y < b || t < pointA.y) return Double.POSITIVE_INFINITY;
		} else {
			double t1 = (b - pointA.y)/delta.y;
			double t2 = (t - pointA.y)/delta.y;

			tmin = Math.max(tmin, Math.min(t1, t2));
			tmax = Math.min(tmax, Math.max(t1, t2));
		}

		if(tmin <= tmax && 0.0 <= tmax && tmin <= 1.0) {
			return Math.max(tmin, 0.0);
		} else {
			return Double.POSITIVE_INFINITY;
		}
	}

	public Vect wrapVect(Vect point) {
		double dx = Math.abs(r - l);
		double modx = (point.x - l) % dx;
		double x = (modx > 0.0f) ? modx : modx + dx;

		double dy = Math.abs(t - b);
		double mody = (point.y - b) % dy;
		double y = (mody > 0.0f) ? mody : mody + dy;

		return new Vect(x + l, y + b);
	}

	public static BB random(double minL, double maxL, double minB, double maxB, double maxR, double maxT) {
		double l = Util.uniform(minL, maxL);
		double b = Util.uniform(minB, maxB);
		double r = Util.uniform(l,    maxR);
		double t = Util.uniform(b,    maxT);
		return new BB(l, b, r, t);
	}

	public static BB randomWithout(double minL, double maxL, double minB, double maxB, double maxR, double maxT, BB not) {
		BB bb = BB.random(minL, maxL, minB, maxB, maxR, maxT);
		while(bb.equals(not))
			bb = BB.random(minL, maxL, minB, maxB, maxR, maxT);
		return bb;
	}
}

