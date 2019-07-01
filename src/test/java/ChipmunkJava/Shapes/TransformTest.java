package ChipmunkJava.Shapes;

import ChipmunkJava.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TransformTest extends Transform {
	private final int steps = 1000;
	private final int min   = -steps;
	private final int max   = steps;

    @Test public void testNewTransformDoubleDoubleDoubleDoubleDoubleDouble() {
		for(int count = steps; count > 0; count--) {
			_testNewTransformDoubleDoubleDoubleDoubleDoubleDouble();
			_testNew_TransformDoubleDoubleDoubleDoubleDoubleDouble();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testNewTransformDoubleDoubleDoubleDoubleDoubleDouble() {
		double a  = Util.uniform(min, max);
		double b  = Util.uniform(min, max);
		double c  = Util.uniform(min, max);
		double d  = Util.uniform(min, max);
		double tx = Util.uniform(min, max);
		double ty = Util.uniform(min, max);

        Transform transform = new Transform(a, b, c, d, tx, ty);

        assertTrue(
			"new Transform(double, double, double, double)",
			transform instanceof Transform
		);
    }

    public void _testNew_TransformDoubleDoubleDoubleDoubleDoubleDouble() {
		double a  = Util.uniform(min, max);
		double b  = Util.uniform(min, max);
		double c  = Util.uniform(min, max);
		double d  = Util.uniform(min, max);
		double tx = Util.uniform(min, max);
		double ty = Util.uniform(min, max);

        _Transform transform = new _Transform(a, b, c, d, tx, ty);

        assertTrue(
			"new _Transform(double, double, double, double)",
			transform instanceof _Transform
		);
    }

    @Test public void testTransformRandom() {
		for(int count = steps; count > 0; count--) {
			_testTransformRandom();
			_test_TransformRandom();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTransformRandom() {
        Transform transform = Transform.random(min, max, min, max);

        assertTrue(
			"new Transform.random(...)",
			transform instanceof Transform
		);
    }

    public void _test_TransformRandom() {
        _Transform transform = _Transform.random(min, max, min, max);

        assertTrue(
			"new _Transform.random(...)",
			transform instanceof _Transform
		);
    }

    @Test public void testFromToArray() {
		for(int count = steps; count > 0; count--) {
			_testFromToArray();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testFromToArray() {
		double[] array = new double[6];
		for(int pos = 0; pos < array.length; pos++) {
			array[pos] = Util.uniform(min, max);
		}
        Transform transform = new Transform(array);
		double[]  _array    = transform.toArray();

        assertTrue(
			"array.length == _array.length",
			array.length == _array.length
		);

		for(int pos = 0; pos < array.length; pos++) {
        	assertTrue(
				"array[" + pos + "] == _array[" + pos + "]",
				array[pos] == _array[pos]
			);
		}
    }

    @Test public void testTransformClone() {
		for(int count = steps; count > 0; count--) {
			_testTransformClone();
			_test_TransformClone();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTransformClone() {
        Transform transform1 = Transform.random(min, max, min, max);
        Transform transform2 = transform1.clone();
        Transform transform3 = new Transform(transform1);

       	assertTrue(
			"transform.clone() instanceof Transform",
			transform2 instanceof Transform
		);

       	assertTrue(
			"new Transform(transform) instanceof Transform",
			transform3 instanceof Transform
		);
    }

    public void _test_TransformClone() {
        _Transform transform1 = _Transform.random(min, max, min, max);
        _Transform transform2 = transform1.clone();
        _Transform transform3 = new _Transform(transform1);

       	assertTrue(
			"_transform.clone() instanceof _Transform",
			transform2 instanceof _Transform
		);

       	assertTrue(
			"new _Transform(_transform) instanceof _Transform",
			transform3 instanceof _Transform
		);
    }

    @Test public void testToString() {
		double a  = Util.uniform(min, max);
		double b  = Util.uniform(min, max);
		double c  = Util.uniform(min, max);
		double d  = Util.uniform(min, max);
		double tx = Util.uniform(min, max);
		double ty = Util.uniform(min, max);

        Transform  transform  = new Transform(a, b, c, d, tx, ty);
        _Transform _transform = new _Transform(a, b, c, d, tx, ty);

        assertEquals(
			"transform.toString()",
			"Transform(a:" + a + ", b:" + b + ", c:" + c + ", d:" + d + ", tx:" + tx + ", ty:" + ty + ")",
			transform.toString()
		);

        assertEquals(
			"_transform.toString()",
			"_Transform(a:" + a + ", b:" + b + ", c:" + c + ", d:" + d + ", tx:" + tx + ", ty:" + ty + ")",
			_transform.toString()
		);
    }

    @Test public void testTransformArray() {
		for(int count = steps; count > 0; count--) {
			_testTransformArray();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTransformArray() {
		int      count = Util.randint(2, 5);
		double[] array = new double[6*count];
		for(int pos = 0; pos < array.length; pos++) {
			array[pos] = Util.uniform(min, max);
		}
        Transform[] transforms = Transform.toTransformArray(array);
		double[]    _array     = Transform.fromTransformArray(transforms);

        assertTrue(
			"Transform.toTransformArray(double[]).length == " + count,
			transforms.length == count
		);

        assertTrue(
			"array.length == _array.length",
			array.length == _array.length
		);

		for(int pos = 0; pos < array.length; pos++) {
        	assertTrue(
				"array[" + pos + "] == _array[" + pos + "]",
				array[pos] == _array[pos]
			);
		}
    }

    @Test public void testEquals() {
		for(int count = steps; count > 0; count--) {
			_testEquals();
			_test_Equals();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testEquals() {
        Transform transform1 = Transform.random(min, max, min, max);
        Transform transform2 = new Transform(transform1.a + 1, transform1.b + 1, transform1.c + 1, transform1.d + 1, transform1.tx + 1, transform1.ty + 1);
        Transform transform3 = transform1.clone();

        assertFalse(
			"transform1.equals(transform2)",
			transform1.equals(transform2)
		);

        assertFalse(
			"transform2.equals(transform1)",
			transform2.equals(transform1)
		);

        assertTrue(
			"transform1.equals(transform3)",
			transform1.equals(transform3)
		);

        assertTrue(
			"transform3.equals(transform1)",
			transform3.equals(transform1)
		);

        assertFalse(
			"transform2.equals(transform3)",
			transform2.equals(transform3)
		);

        assertFalse(
			"transform3.equals(transform2)",
			transform3.equals(transform2)
		);
    }

    public void _test_Equals() {
        _Transform transform1 = _Transform.random(min, max, min, max);
        _Transform transform2 = new _Transform(transform1.getA() + 1, transform1.getB() + 1, transform1.getC() + 1, transform1.getD() + 1, transform1.getTX() + 1, transform1.getTY() + 1);
        _Transform transform3 = transform1.clone();

        assertFalse(
			"_transform1.equals(_transform2)",
			transform1.equals(transform2)
		);

        assertFalse(
			"_transform2.equals(_transform1)",
			transform2.equals(transform1)
		);

        assertTrue(
			"_transform1.equals(_transform3)",
			transform1.equals(transform3)
		);

        assertTrue(
			"_transform3.equals(_transform1)",
			transform3.equals(transform1)
		);

        assertFalse(
			"_transform2.equals(_transform3)",
			transform2.equals(transform3)
		);

        assertFalse(
			"_transform3.equals(_transform2)",
			transform3.equals(transform2)
		);
    }

    @Test public void testAlmost() {
		for(int count = steps; count > 0; count--) {
			_testAlmost();
			_test_Almost();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testAlmost() {
        Transform transform1 = Transform.random(min, max, min, max);
        Transform transform2 = new Transform(transform1.a + 1, transform1.b + 1, transform1.c + 1, transform1.d + 1, transform1.tx + 1, transform1.ty + 1);
        Transform transform3 = transform1.clone();

		double t  = 10*Math.ulp(max);

        assertFalse(
			"transform1.almost(transform2, t)",
			transform1.almost(transform2, t)
		);

        assertFalse(
			"transform2.almost(transform1, t)",
			transform2.almost(transform1, t)
		);

        assertTrue(
			"transform1.almost(transform3, t)",
			transform1.almost(transform3, t)
		);

        assertTrue(
			"transform3.almost(transform1, t)",
			transform3.almost(transform1, t)
		);

        assertFalse(
			"transform2.almost(transform3, t)",
			transform2.almost(transform3, t)
		);

        assertFalse(
			"transform3.almost(transform2, t)",
			transform3.almost(transform2, t)
		);
    }

    public void _test_Almost() {
        _Transform transform1 = _Transform.random(min, max, min, max);
        _Transform transform2 = new _Transform(transform1.getA() + 1, transform1.getB() + 1, transform1.getC() + 1, transform1.getD() + 1, transform1.getTX() + 1, transform1.getTY() + 1);
        _Transform transform3 = transform1.clone();

		double t  = 10*Math.ulp(max);

        assertFalse(
			"_transform1.almost(_transform2, t)",
			transform1.almost(transform2, t)
		);

        assertFalse(
			"_transform2.almost(_transform1, t)",
			transform2.almost(transform1, t)
		);

        assertTrue(
			"_transform1.almost(_transform3, t)",
			transform1.almost(transform3, t)
		);

        assertTrue(
			"_transform3.almost(_transform1, t)",
			transform3.almost(transform1, t)
		);

        assertFalse(
			"_transform2.almost(_transform3, t)",
			transform2.almost(transform3, t)
		);

        assertFalse(
			"_transform3.almost(_transform2, t)",
			transform3.almost(transform2, t)
		);
    }

    @Test public void testTransform_TransformConversion() {
		for(int count = steps; count > 0; count--) {
			_testTransform_TransformConversion();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTransform_TransformConversion() {
        _Transform _transform1 = _Transform.random(min, max, min, max);
        Transform  transform2  = new Transform(_transform1);
        _Transform _transform3 = new _Transform(transform2);

        assertTrue(
			"_transform1.equals(transform2)",
			_transform1.getA() == transform2.a && _transform1.getB() == transform2.b && _transform1.getC() == transform2.c && _transform1.getD() == transform2.d && _transform1.getTX() == transform2.tx && _transform1.getTY() == transform2.ty
		);

        assertTrue(
			"_transform1.equals(_transform3)",
			_transform1.equals(_transform3)
		);

        assertTrue(
			"_transform3.equals(_transform1)",
			_transform3.equals(_transform1)
		);

        assertTrue(
			"transform2.equals(_transform3)",
			transform2.a == _transform3.getA() && transform2.b == _transform3.getB() && transform2.c == _transform3.getC() && transform2.d == _transform3.getD() && transform2.tx == _transform3.getTX() && transform2.ty == _transform3.getTY()
		);
	}

    @Test public void testInvert() {
		for(int count = steps; count > 0; count--) {
			_testInvert();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testInvert() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

        assertEquals(
			"transform.invert() equals _transform.invert()",
			transform.invert(),
			new Transform(_transform.invert())
		);
    }

    @Test public void testMult() {
		for(int count = steps; count > 0; count--) {
			_testMult();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testMult() {
        Transform  transform1  = Transform.random(min, max, min, max);
        Transform  transform2  = Transform.random(min, max, min, max);
        _Transform _transform1 = new _Transform(transform1);
        _Transform _transform2 = new _Transform(transform2);

        assertEquals(
			"transform1.mult(transform2) equals _transform1.mult(_transform2)",
			transform1.mult(transform2),
			new Transform(_transform1.mult(_transform2))
		);
    }

    @Test public void testTranspose() {
		for(int count = steps; count > 0; count--) {
			_testTranspose();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTranspose() {
        Transform  transform1  = Transform.random(min, max, min, max);
        Transform  transform2  = Transform.transpose(
			transform1.a,
			transform1.b,
			transform1.c,
			transform1.d,
			transform1.tx,
			transform1.ty
		).transpose().transpose().transpose();
        Transform  transform3  = transform1.transpose().transpose().transpose().transpose();
        _Transform _transform1 = new _Transform(transform1);
        _Transform _transform2 = _Transform.transpose(
			_transform1.getA(),
			_transform1.getB(),
			_transform1.getC(),
			_transform1.getD(),
			_transform1.getTX(),
			_transform1.getTY()
		).transpose().transpose().transpose();
        _Transform _transform3 = _transform1.transpose().transpose().transpose().transpose();

        assertEquals(
			"transform1 equals transform2",
			transform1,
			transform2
		);

        assertEquals(
			"transform1 equals transform3",
			transform1,
			transform3
		);

        assertEquals(
			"transform2 equals _transform2",
			transform2,
			new Transform(_transform2)
		);

        assertEquals(
			"transform3 equals _transform3",
			transform3,
			new Transform(_transform3)
		);
    }

    @Test public void testRigidInvert() {
		for(int count = steps; count > 0; count--) {
			_testRigidInvert();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testRigidInvert() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

        assertEquals(
			"transform.rigidInvert() equals _transform.rigidInvert()",
			transform.rigidInvert(),
			new Transform(_transform.rigidInvert())
		);
    }

    @Test public void testRotate() {
		for(int count = steps; count > 0; count--) {
			_testRotate();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testRotate() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		double angle = Util.uniform(-720, 720);

        assertTrue(
			"transform.rotate(angle).almost(_transform.rotate(angle), Math.ulp(max))",
			transform.rotate(angle).almost(new Transform(_transform.rotate(angle)), 10*Math.ulp(max))
		);
    }

    @Test public void testScale() {
		for(int count = steps; count > 0; count--) {
			_testScale();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testScale() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		double x = Util.uniform(min, max);
		double y = Util.uniform(min, max);

        assertEquals(
			"transform.scale(x, y) equals _transform.scale(x, y)",
			transform.scale(x, y),
			new Transform(_transform.scale(x, y))
		);
    }

    @Test public void testAxialScale() {
		for(int count = steps; count > 0; count--) {
			_testAxialScale();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testAxialScale() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		Vect   axis   = Vect.random(min, max, min, max);
		Vect   pivot  = Vect.random(min, max, min, max);
		_Vect  _axis  = new _Vect(axis);
		_Vect  _pivot = new _Vect(pivot);
		double scale  = Util.uniform(min, max);

        assertTrue(
			"transform.axialScale(axis, pivot, scale) equals _transform.axialScale(axis, pivot, scale)",
			transform.axialScale(axis, pivot, scale).almost(new Transform(_transform.axialScale(_axis, _pivot, scale)), 10*Math.ulp(max))
		);
    }

    @Test public void testBoneScale() {
		for(int count = steps; count > 0; count--) {
			_testBoneScale();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testBoneScale() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		Vect  offsetA  = Vect.random(min, max, min, max);
		Vect  offsetB  = Vect.random(min, max, min, max);
		_Vect _offsetA = new _Vect(offsetA);
		_Vect _offsetB = new _Vect(offsetB);

        assertEquals(
			"transform.boneScale(offsetA, offsetB) equals _transform.boneScale(offsetA, offsetB)",
			transform.boneScale(offsetA, offsetB),
			new Transform(_transform.boneScale(_offsetA, _offsetB))
		);
    }

    @Test public void testRigid() {
		for(int count = steps; count > 0; count--) {
			_testRigid();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testRigid() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		Vect   offset  = Vect.random(min, max, min, max);
		_Vect  _offset = new _Vect(offset);
		double angle   = Util.uniform(-720, 720);

        assertTrue(
			"transform.rigid(offset, angle) equals _transform.rigid(offset, angle)",
			transform.rigid(offset, angle).almost(new Transform(_transform.rigid(_offset, angle)), 10*Math.ulp(max))
		);
    }

    @Test public void testTranslate() {
		for(int count = steps; count > 0; count--) {
			_testTranslate();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTranslate() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		Vect  offset  = Vect.random(min, max, min, max);
		_Vect _offset = new _Vect(offset);

        assertEquals(
			"transform.translate(offset) equals _transform.translate(offset)",
			transform.translate(offset),
			new Transform(_transform.translate(_offset))
		);
    }

    @Test public void testTransformPoint() {
		for(int count = steps; count > 0; count--) {
			_testTransformPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTransformPoint() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		Vect  point  = Vect.random(min, max, min, max);
		_Vect _point = new _Vect(point);

        assertEquals(
			"transform.transformPoint(point) equals _transform.transformPoint(point)",
			transform.transformPoint(point),
			new Vect(_transform.transformPoint(_point))
		);
    }

    @Test public void testTransformVect() {
		for(int count = steps; count > 0; count--) {
			_testTransformVect();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTransformVect() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		Vect  vect  = Vect.random(min, max, min, max);
		_Vect _vect = new _Vect(vect);

        assertEquals(
			"transform.transformVect(vect) equals _transform.transformVect(vect)",
			transform.transformVect(vect),
			new Vect(_transform.transformVect(_vect))
		);
    }

    @Test public void testOrtho() {
		for(int count = steps; count > 0; count--) {
			_testOrtho();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testOrtho() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		BB  bb  = BB.random(min, max, min, max, max, max);
		_BB _bb = new _BB(bb);

        assertEquals(
			"transform.ortho(bb) equals _transform.ortho(bb)",
			transform.ortho(bb),
			new Transform(_transform.ortho(_bb))
		);
    }

    @Test public void testTransformBB() {
		for(int count = steps; count > 0; count--) {
			_testTransformBB();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testTransformBB() {
        Transform  transform  = Transform.random(min, max, min, max);
        _Transform _transform = new _Transform(transform);

		BB  bb  = BB.random(min, max, min, max, max, max);
		_BB _bb = new _BB(bb);

        assertEquals(
			"transform.transformBB(bb) equals _transform.transformBB(bb)",
			transform.transformBB(bb),
			new BB(_transform.transformBB(_bb))
		);
    }
}

