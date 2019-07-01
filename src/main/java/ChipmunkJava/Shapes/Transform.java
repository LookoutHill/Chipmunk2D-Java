package ChipmunkJava.Shapes;

import ChipmunkJava.*;

public class Transform {
	public static final Transform identity = new Transform(1.0, 0.0, 0.0, 1.0, 0.0, 0.0);

	public final double a;
	public final double b;
	public final double c;
	public final double d;
	public final double tx;
	public final double ty;

	public Transform(double _a, double _b, double _c, double _d, double _tx, double _ty) {
		a  = _a;
		b  = _b;
		c  = _c;
		d  = _d;
		tx = _tx;
		ty = _ty;
	}

	public Transform() {
		a  = 1.0;
		b  = 0.0;
		c  = 0.0;
		d  = 1.0;
		tx = 0.0;
		ty = 0.0;
	}

	public Transform(double[] array) {
		a  = array[0];
		b  = array[1];
		c  = array[2];
		d  = array[3];
		tx = array[4];
		ty = array[5];
	}

	public static Transform[] toTransformArray(double[] array) {
		Transform[] transforms = new Transform[array.length/6];
		for(int pos = 0; pos < array.length; pos+=6) {
			transforms[pos/6] = new Transform(array[pos], array[pos+1], array[pos+2], array[pos+3], array[pos+4], array[pos+5]);
		}
		return transforms;
	}

	public static double[] fromTransformArray(Transform[] transforms) {
		double[] array = new double[6*transforms.length];
		for(int pos = 0; pos < transforms.length; pos++) {
			Transform transform = transforms[pos];

			array[6*pos]   = transform.a;
			array[6*pos+1] = transform.b;
			array[6*pos+2] = transform.c;
			array[6*pos+3] = transform.d;
			array[6*pos+4] = transform.tx;
			array[6*pos+5] = transform.ty;
		}
		return array;
	}

	public Transform(Transform that) {
		a  = that.a;
		b  = that.b;
		c  = that.c;
		d  = that.d;
		tx = that.tx;
		ty = that.ty;
	}

	public Transform clone() {
		return new Transform(this);
	}

	public Transform(_Transform that) {
		a  = that.getA();
		b  = that.getB();
		c  = that.getC();
		d  = that.getD();
		tx = that.getTX();
		ty = that.getTY();
	}

	public double[] toArray() {
		return new double[] { a, b, c, d, tx, ty };
	}

	@Override
	public String toString() {
		return "Transform(a:" + a + ", b:" + b + ", c:" + c + ", d:" + d + ", tx:" + tx + ", ty:" + ty + ")";
	}

	@Override
	public boolean equals(Object _that) {
		try {
			Transform that = (Transform) _that;
			return this.a == that.a && this.b == that.b && this.c == that.c && this.d == that.d && this.tx == that.tx && this.ty == that.ty;
		} catch(Exception err) {
			return false;
		}
	}

	public boolean almost(Transform that, double tolerance) {
		double da  = Math.abs(this.a  - that.a);
		double db  = Math.abs(this.b  - that.b);
		double dc  = Math.abs(this.c  - that.c);
		double dd  = Math.abs(this.d  - that.d);
		double dtx = Math.abs(this.tx - that.tx);
		double dty = Math.abs(this.ty - that.ty);
		return da <= tolerance && db <= tolerance && dc <= tolerance && dd <= tolerance && dtx <= tolerance && dty <= tolerance;
	}

	@Override
	public int hashCode() {
		long bitsA64  = Double.doubleToLongBits(a);
		long bitsB64  = Double.doubleToLongBits(b);
		long bitsC64  = Double.doubleToLongBits(c);
		long bitsD64  = Double.doubleToLongBits(d);
		long bitsTX64 = Double.doubleToLongBits(tx);
		long bitsTY64 = Double.doubleToLongBits(ty);

		byte  bitsA32  = (byte)  (bitsA64  ^ (bitsA64  >>> 56));
		byte  bitsB32  = (byte)  (bitsB64  ^ (bitsB64  >>> 56));
		byte  bitsC32  = (byte)  (bitsC64  ^ (bitsC64  >>> 56));
		byte  bitsD32  = (byte)  (bitsD64  ^ (bitsD64  >>> 56));
		short bitsTX32 = (short) (bitsTX64 ^ (bitsTX64 >>> 48));
		short bitsTY32 = (short) (bitsTY64 ^ (bitsTY64 >>> 48));

		long hash = 17;
		hash = 31 * hash + bitsA32;
		hash = 31 * hash + bitsB32;
		hash = 31 * hash + bitsC32;
		hash = 31 * hash + bitsD32;
		hash = 31 * hash + bitsTX32;
		hash = 31 * hash + bitsTY32;
		return (int) hash;
	}

	public Transform invert() {
		double inv_det = 1.0/(a*d - c*b);
		return Transform.transpose(
		 	 d*inv_det,  -c*inv_det,  (c*ty - tx*d)*inv_det,
			-b*inv_det,   a*inv_det,  (tx*b - a*ty)*inv_det
		);
	}

	public Transform mult(Transform that) {
		return Transform.transpose(
			this.a*that.a + this.c*that.b,  this.a*that.c + this.c*that.d,  this.a*that.tx + this.c*that.ty + this.tx,
			this.b*that.a + this.d*that.b,  this.b*that.c + this.d*that.d,  this.b*that.tx + this.d*that.ty + this.ty
		);
	}

	public Transform rigidInvert() {
		return Transform.transpose(
		 	 d,  -c,  (c*ty - tx*d),
			-b,   a,  (tx*b - a*ty)
		);
	}

	public BB transformBB(BB bb) {
		double halfwidth      = (bb.r - bb.l)*0.5;
		double halfheight     = (bb.t - bb.b)*0.5;
		double _a             = a * halfwidth;
		double _b             = b * halfwidth;
		double _c             = c * halfheight;
		double _d             = d * halfheight;
		double halfwidth_max  = Math.max(Math.abs(_a + _c), Math.abs(_a - _c));
		double halfheight_max = Math.max(Math.abs(_b + _d), Math.abs(_b - _d));
		Vect   center         = bb.center();
		return new BB(transformPoint(center), halfwidth_max, halfheight_max);
	}

	public Vect transformPoint(Vect point) {
		return new Vect(
			a*point.x + c*point.y + tx,
			b*point.x + d*point.y + ty
		);
	}

	public Vect transformVect(Vect vect) {
		return new Vect(
			a*vect.x + c*vect.y,
			b*vect.x + d*vect.y
		);
	}

	public Transform transpose() {
		return new Transform(a, d, b, tx, c, ty);
	}

	public static Transform axialScale(Vect axis, Vect pivot, double scale) {
		double valueA = axis.x * axis.y * (scale-1.0);
		double valueB = axis.dot(pivot) * (1.0-scale);
		double sqax   = axis.x*axis.x;
		double sqay   = axis.y*axis.y;
	
		return Transform.transpose(
			scale * sqax + sqay,  valueA,               axis.x * valueB,
		    valueA,               sqax + scale * sqay,  axis.y * valueB
		);
	}

	public static Transform boneScale(Vect offsetA, Vect offsetB) {
		Vect diff = offsetB.sub(offsetA); 
		return Transform.transpose(
			diff.x,  -diff.y,  offsetA.x,
			diff.y,   diff.x,  offsetA.y
		);
	}

	public static Transform ortho(BB bb) {
		return Transform.transpose(
			2.0/(bb.r - bb.l),  0.0,                -(bb.r + bb.l)/(bb.r - bb.l),
			0.0,                2.0/(bb.t - bb.b),  -(bb.t + bb.b)/(bb.t - bb.b)
		);
	}

	public static Transform rigid(Vect offset, double angle) {
		Vect rot = Vect.forAngle(angle);
		return Transform.transpose(
			rot.x,  -rot.y,  offset.x,
			rot.y,   rot.x,  offset.y
		);
	}

	public static Transform rotate(double angle) {
		Vect rot = Vect.forAngle(angle);
		return Transform.transpose(
			rot.x,  -rot.y,  0.0,
			rot.y,   rot.x,  0.0
		);
	}

	public static Transform scale(double scaleX, double scaleY) {
		return Transform.transpose(
			scaleX,  0.0,     0.0,
			0.0,     scaleY,  0.0
		);
	}

	public static Transform translate(Vect offset) {
		return Transform.transpose(
			1.0,  0.0,  offset.x,
			0.0,  1.0,  offset.y
		);
	}

	public static Transform transpose(double a, double b, double c, double d, double tx,  double ty) {
		return new Transform(a, d, b, tx, c, ty);
	}

	public static Transform wrap(Transform outer, Transform inner) {
		return outer.invert().mult(inner.mult(outer));
	}

	public static Transform wrapInverse(Transform outer, Transform inner) {
		return outer.mult(inner.mult(outer.invert()));
	}

	public static Transform random(double min, double max, double minT, double maxT) {
		double a  = Util.uniform(min, max);
		double b  = Util.uniform(min, max);
		double c  = Util.uniform(min, max);
		double d  = Util.uniform(min, max);
		double tx = Util.uniform(minT, maxT);
		double ty = Util.uniform(minT, maxT);
		return new Transform(a, b, c, d, tx, ty);
	}

	public static Transform random(double min, double max, double minT, double maxT, Transform not) {
		Transform transform = Transform.random(min, max, minT, maxT);
		while(transform.equals(not))
			transform = Transform.random(min, max, minT, maxT);
		return transform;
	}
}

