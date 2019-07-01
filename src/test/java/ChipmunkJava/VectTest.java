package ChipmunkJava;

import org.junit.Test;
import static org.junit.Assert.*;

public class VectTest extends Vect {
	private final int steps = 1000;
	private final int min   = -steps;
	private final int max   = steps;

    @Test public void testNewVectDoubleDouble() {
		for(int count = steps; count > 0; count--) {
			_testNewVectDoubleDouble();
			_testNew_VectDoubleDouble();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testNewVectDoubleDouble() {
		double x = Util.uniform(min, max);
		double y = Util.uniform(min, max);

        Vect vect = new Vect(x, y);

        assertTrue(
			"new " + vect.toString(),
			vect instanceof Vect
		);
    }

    public void _testNew_VectDoubleDouble() {
		double x = Util.uniform(min, max);
		double y = Util.uniform(min, max);

        _Vect vect = new _Vect(x, y);

        assertTrue(
			"new " + vect.toString(),
			vect instanceof _Vect
		);
    }

    @Test public void testVectRandom() {
		for(int count = steps; count > 0; count--) {
			_testVectRandom();
			_test_VectRandom();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testVectRandom() {
        Vect vect = Vect.random(min, max, min, max);

        assertTrue(
			"new Vect.random(...)",
			vect instanceof Vect
		);
    }

    public void _test_VectRandom() {
        _Vect vect = _Vect.random(min, max, min, max);

        assertTrue(
			"new _Vect.random(...)",
			vect instanceof _Vect
		);
    }

    @Test public void testVectClone() {
		for(int count = steps; count > 0; count--) {
			_testVectClone();
			_test_VectClone();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testVectClone() {
        Vect v1 = Vect.random(min, max, min, max);
        Vect v2 = v1.clone();
        Vect v3 = new Vect(v1);

       	assertTrue(
			"vect.clone() instanceof Vect",
			v2 instanceof Vect
		);

       	assertTrue(
			"new Vect(vect) instanceof Vect",
			v3 instanceof Vect
		);
    }

    public void _test_VectClone() {
        _Vect v1 = _Vect.random(min, max, min, max);
        _Vect v2 = v1.clone();
        _Vect v3 = new _Vect(v1);

       	assertTrue(
			"_vect.clone() instanceof _Vect",
			v2 instanceof _Vect
		);

       	assertTrue(
			"new _Vect(_vect) instanceof _Vect",
			v3 instanceof _Vect
		);
    }

    @Test public void testEquals() {
		for(int count = steps; count > 0; count--) {
			_testEquals();
			_test_Equals();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testEquals() {
        Vect v1 = Vect.random(min, max, min, max);
        Vect v2 = new Vect(v1.x + 1, v1.y + 1);
        Vect v3 = v1.clone();

        assertFalse(
			"v1.equals(v2)",
			v1.equals(v2)
		);

        assertFalse(
			"v2.equals(v1)",
			v2.equals(v1)
		);

        assertTrue(
			"v1.equals(v3)",
			v1.equals(v3)
		);

        assertTrue(
			"v3.equals(v1)",
			v3.equals(v1)
		);

        assertFalse(
			"v2.equals(v3)",
			v2.equals(v3)
		);

        assertFalse(
			"v3.equals(v2)",
			v3.equals(v2)
		);
    }

    public void _test_Equals() {
        _Vect v1 = _Vect.random(min, max, min, max);
        _Vect v2 = new _Vect(v1.getX() + 1, v1.getY() + 1);
        _Vect v3 = v1.clone();

        assertFalse(
			"_v1.equals(_v2)",
			v1.equals(v2)
		);

        assertFalse(
			"_v2.equals(_v1)",
			v2.equals(v1)
		);

        assertTrue(
			"_v1.equals(_v3)",
			v1.equals(v3)
		);

        assertTrue(
			"_v3.equals(_v1)",
			v3.equals(v1)
		);

        assertFalse(
			"_v2.equals(_v3)",
			v2.equals(v3)
		);

        assertFalse(
			"_v3.equals(_v2)",
			v3.equals(v2)
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
        Vect   v1 = Vect.random(min, max, min, max);
        Vect   v2 = new Vect(v1.x + 1, v1.y + 1);
        Vect   v3 = v1.clone();
		double t  = 10*Math.ulp(max);

        assertFalse(
			"v1.almost(v2, t)",
			v1.almost(v2, t)
		);

        assertFalse(
			"v2.almost(v1, t)",
			v2.almost(v1, t)
		);

        assertTrue(
			"v1.almost(v3, t)",
			v1.almost(v3, t)
		);

        assertTrue(
			"v3.almost(v1, t)",
			v3.almost(v1, t)
		);

        assertFalse(
			"v2.almost(v3, t)",
			v2.almost(v3, t)
		);

        assertFalse(
			"v3.almost(v2, t)",
			v3.almost(v2, t)
		);
    }

    public void _test_Almost() {
        _Vect  v1 = _Vect.random(min, max, min, max);
        _Vect  v2 = new _Vect(v1.getX() + 1, v1.getY() + 1);
        _Vect  v3 = v1.clone();
		double t  = 10*Math.ulp(max);

        assertFalse(
			"_v1.almost(_v2, t)",
			v1.almost(v2, t)
		);

        assertFalse(
			"_v2.almost(_v1, t)",
			v2.almost(v1, t)
		);

        assertTrue(
			"_v1.almost(_v3, t)",
			v1.almost(v3, t)
		);

        assertTrue(
			"_v3.almost(_v1, t)",
			v3.almost(v1, t)
		);

        assertFalse(
			"_v2.almost(_v3, t)",
			v2.almost(v3, t)
		);

        assertFalse(
			"_v3.almost(_v2, t)",
			v3.almost(v2, t)
		);
    }

    @Test public void testVect_VectConversion() {
		for(int count = steps; count > 0; count--) {
			_testVect_VectConversion();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testVect_VectConversion() {
        _Vect _v1 = _Vect.random(min, max, min, max);
        Vect  v2  = new Vect(_v1);
        _Vect _v3 = new _Vect(v2);

        assertTrue(
			"_v1.equals(v2)",
			_v1.getX() == v2.x && _v1.getY() == v2.y
		);

        assertTrue(
			"_v1.equals(_v3)",
			_v1.equals(_v3)
		);

        assertTrue(
			"_v3.equals(_v1)",
			_v3.equals(_v1)
		);

        assertTrue(
			"v2.equals(_v3)",
			v2.x == _v3.getX() && v2.y == _v3.getY()
		);
	}

    @Test public void testVectZero() {
        assertEquals(
			"Vect.zero equals new Vect(0, 0)",
			Vect.zero,
			new Vect(0, 0)
		);
    }

    @Test public void test_VectZero() {
        assertEquals(
			"_Vect.zero equals new _Vect(0, 0)",
			_Vect.zero,
			new _Vect(0, 0)
		);
    }

    @Test public void testVectUnit() {
        assertEquals(
			"Vect.unit equals new Vect(1, 0)",
			Vect.unit,
			new Vect(1, 0)
		);
    }

    @Test public void test_VectUnit() {
        assertEquals(
			"_Vect.unit equals new _Vect(1, 0)",
			_Vect.unit,
			new _Vect(1, 0)
		);
    }

    @Test public void testLength() {
        assertEquals(
			"1 equals Vect.unit.length()",
			1,
			Vect.unit.length(),
			10*Math.ulp(1)
		);

        assertEquals(
			"0 equals Vect.zero.length()",
			0,
			Vect.zero.length(),
			10*Math.ulp(0)
		);

		for(int count = steps; count > 0; count--) {
			_testLength();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testLength() {
        Vect  vect  = Vect.random(min, max, min, max);
        _Vect _vect = new _Vect(vect);

        assertEquals(
			"Vect.length() equals _Vect.length()",
			vect.length(),
			_vect.length(),
			10*Math.ulp(vect.length())
		);

        double pos = Util.uniform(min, max);

        assertEquals(
			"Math.abs(pos) equals new Vect(0, pos).length()",
			Math.abs(pos),
        	new Vect(0, pos).length(),
			10*Math.ulp(Math.abs(pos))
		);

        assertEquals(
			"Math.abs(pos) equals new Vect(pos, 0).length()",
			Math.abs(pos),
        	new Vect(pos, 0).length(),
			10*Math.ulp(Math.abs(pos))
		);
    }

    @Test public void testLengthSq() {
        assertEquals(
			"1 equals Vect.unit.lengthSq()",
			1,
			Vect.unit.lengthSq(),
			10*Math.ulp(1)
		);

        assertEquals(
			"0 equals Vect.zero.lengthSq()",
			0,
			Vect.zero.lengthSq(),
			10*Math.ulp(0)
		);

		for(int count = steps; count > 0; count--) {
			_testLengthSq();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testLengthSq() {
        Vect  vect  = Vect.random(min, max, min, max);
        _Vect _vect = new _Vect(vect);

        assertEquals(
			"Vect.lengthSq() equals _Vect.lengthSq()",
			vect.lengthSq(),
			_vect.lengthSq(),
			10*Math.ulp(vect.lengthSq())
		);

        double pos = Util.uniform(min, max);

        assertEquals(
			"pos*pos equals new Vect(0, pos).lengthSq()",
			pos*pos,
        	new Vect(0, pos).lengthSq(),
			10*Math.ulp(pos*pos)
		);

        assertEquals(
			"pos*pos equals new Vect(pos, 0).lengthSq()",
			pos*pos,
        	new Vect(pos, 0).lengthSq(),
			10*Math.ulp(pos*pos)
		);
    }

    @Test public void testToString() {
		double x   = Util.uniform(min, max);
		double y   = Util.uniform(min, max);
        Vect   vect  = new Vect(x, y);
        _Vect  _vect = new _Vect(x, y);

        assertEquals(
			"vect.toString()",
			"Vect(x:" + x + ", y:" + y + ")",
			vect.toString()
		);

        assertEquals(
			"_vect.toString()",
			"_Vect(x:" + x + ", y:" + y + ")",
			_vect.toString()
		);
    }

    @Test public void testAdd() {
        assertEquals(
			"new Vect(0, -1) equals Vect.unit.add(new Vect(-1, -1))",
			new Vect(0, -1),
			Vect.unit.add(new Vect(-1, -1))
		);

		for(int count = steps; count > 0; count--) {
			_testAdd();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testAdd() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.add(v2) equals _v1.add(_v2)",
			v1.add(v2),
			new Vect(_v1.add(_v2))
		);
    }

    @Test public void testSub() {
        assertEquals(
			"new Vect(2, 1) equals Vect.unit.sub(new Vect(-1, -1))",
			new Vect(2, 1),
			Vect.unit.sub(new Vect(-1, -1))
		);

        assertEquals(
			"new Vect(-2, -1) equals new Vect(-1, -1).sub(Vect.unit)",
			new Vect(-2, -1),
			new Vect(-1, -1).sub(Vect.unit)
		);

		for(int count = steps; count > 0; count--) {
			_testSub();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testSub() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.sub(v2) equals _v1.sub(_v2)",
			v1.sub(v2),
			new Vect(_v1.sub(_v2))
		);

        assertEquals(
			"v2.sub(v1) equals _v2.sub(_v1)",
			v2.sub(v1),
			new Vect(_v2.sub(_v1))
		);
    }

    @Test public void testDiv() {
        assertEquals(
			"new Vect(-1.5, 1.0/3.0) equals new Vect(3, 1).div(new Vect(-2, 3))",
			new Vect(-1.5, 1.0/3.0),
			new Vect(3, 1).div(new Vect(-2, 3))
		);

        assertEquals(
			"new Vect(-2.0/3.0, 3) equals new Vect(-2, 3).div(new Vect(3, 1))",
			new Vect(-2.0/3.0, 3),
			new Vect(-2, 3).div(new Vect(3, 1))
		);

		for(int count = steps; count > 0; count--) {
			_testDiv();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testDiv() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.div(v2) equals _v1.div(_v2)",
			v1.div(v2),
			new Vect(_v1.div(_v2))
		);

        assertEquals(
			"v2.div(v1) equals _v2.div(_v1)",
			v2.div(v1),
			new Vect(_v2.div(_v1))
		);
    }

    @Test public void testMultVect() {
        assertEquals(
			"new Vect(-1, 0) equals Vect.unit.mult(new Vect(-1, -1))",
			new Vect(-1, 0),
			Vect.unit.mult(new Vect(-1, -1))
		);

		for(int count = steps; count > 0; count--) {
			_testMultVect();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testMultVect() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.mult(v2) equals _v1.mult(_v2)",
			v1.mult(v2),
			new Vect(_v1.mult(_v2))
		);
    }

    @Test public void testMultDouble() {
        assertEquals(
			"new Vect(-100, 0) equals Vect.unit.mult(-100)",
			new Vect(-100, 0),
			Vect.unit.mult(-100)
		);

		for(int count = steps; count > 0; count--) {
			_testMultDouble();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testMultDouble() {
        double s   = Util.uniform(min, max);
        Vect   vect  = Vect.random(min, max, min, max);
        _Vect  _vect = new _Vect(vect);

        assertEquals(
			"vect.mult(double) equals _vect.mult(double)",
			vect.mult(s),
			new Vect(_vect.mult(s))
		);
    }

    @Test public void testNeg() {
        assertEquals(
			"new Vect(-1, 1) equals new Vect(1, -1).neg()",
			new Vect(-1, 1),
			new Vect(1, -1).neg()
		);

		for(int count = steps; count > 0; count--) {
			_testNeg();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testNeg() {
        Vect  vect  = Vect.random(min, max, min, max);
        _Vect _vect = new _Vect(vect);

        assertEquals(
			"vect.neg() equals _vect.neg()",
			vect.neg(),
			new Vect(_vect.neg())
		);
    }

    @Test public void testCross() {
        assertEquals(
			"2-6 equals new Vect(1, 2).cross(new Vect(3, 2))",
			2-6,
			new Vect(1, 2).cross(new Vect(3, 2)),
			0.0
		);

		for(int count = steps; count > 0; count--) {
			_testCross();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testCross() {
        Vect   v1  = Vect.random(min, max, min, max);
        Vect   v2  = Vect.random(min, max, min, max);
        _Vect  _v1 = new _Vect(v1);
        _Vect  _v2 = new _Vect(v2);

        assertEquals(
			"v1.cross(v2) equals _v1.cross(_v2)",
			v1.cross(v2),
			_v1.cross(_v2),
			0.0
		);
    }

    @Test public void testDot() {
        assertEquals(
			"3+4 equals new Vect(1, 2).dot(new Vect(3, 2))",
			3+4,
			new Vect(1, 2).dot(new Vect(3, 2)),
			0.0
		);

		for(int count = steps; count > 0; count--) {
			_testDot();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testDot() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.dot(v2) equals _v1.dot(_v2)",
			v1.dot(v2),
			_v1.dot(_v2),
			0.0
		);
    }

    @Test public void testClamp() {
        assertEquals(
			"50 equals Vect.unit.mult(100).clamp(50).length()",
			50,
			Vect.unit.mult(100).clamp(50).length(),
			10*Math.ulp((double) 50)
		);

		for(int count = steps; count > 0; count--) {
			_testClamp();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testClamp() {
        double mag    = Util.uniform(0, max);
        double mag_lt = Util.uniform(0, mag);
        double mag_ge = Util.uniform(mag, max);
        Vect   v1     = Vect.unit.mult(mag);
        Vect   v2     = v1.clamp(mag_lt);
        Vect   v3     = v1.clamp(mag_ge);
        _Vect  _v1    = new _Vect(v1);
        _Vect  _v2    = _v1.clamp(mag_lt);
        _Vect  _v3    = _v1.clamp(mag_ge);

        assertEquals(
			"v2.length() equals _v2.length()",
			v2.length(),
			new Vect(_v2).length(),
			10*Math.ulp(mag)
		);

        assertEquals(
			"v3.length() equals _v3.length()",
			v3.length(),
			new Vect(_v3).length(),
			10*Math.ulp(mag)
		);

        assertTrue(
			"v2.length() <= mag",
			v2.length() <= mag_lt + 10*Math.ulp(mag)
		);

        assertEquals(
			"v3.length() equals mag",
			v3.length(),
			mag,
			10*Math.ulp(mag)
		);
    }

    @Test public void testToAngle() {
        assertEquals(
			"Math.PI/2 equals new Vect(0, 1).toAngle()",
			Math.PI/2,
			new _Vect(0, 1).toAngle(),
			10*Math.ulp(Math.PI)
		);

        assertEquals(
			"-Math.PI equals Vect.unit.neg().toAngle()",
			-Math.PI,
			_Vect.unit.neg().toAngle(),
			10*Math.ulp(Math.PI)
		);

        assertEquals(
			"-Math.PI/2 equals new Vect(0, -1).toAngle()",
			-Math.PI/2,
			new _Vect(0, -1).toAngle(),
			10*Math.ulp(Math.PI)
		);

		for(int count = steps; count > 0; count--) {
			_testToAngle();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testToAngle() {
        Vect  v1  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);

        assertEquals(
			"v1.toAngle() equals _v1.toAngle()",
			v1.toAngle(),
			_v1.toAngle(),
			10*Math.ulp(Math.PI)
		);

        assertTrue(
			"-Math.PI <= v1.toAngle() < Math.PI",
			-Math.PI <= v1.toAngle() && v1.toAngle() < Math.PI
		);
    }

    @Test public void testNormalize() {
        Vect vect = new Vect(100, 0).normalize();

        assertEquals(
			"Vect.unit.toAngle() equals new vect.toAngle()",
			Vect.unit.toAngle(),
			vect.toAngle(),
			10*Math.ulp(Math.PI)
		);

        assertEquals(
			"1 equals new vect.length()",
			1,
			vect.length(),
			10*Math.ulp(1)
		);

		for(int count = steps; count > 0; count--) {
			_testNormalize();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testNormalize() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = v1.normalize();
        _Vect _v2 = new _Vect(v1).normalize();

        assertEquals(
			"1 equals v2.length()",
			1,
			v2.length(),
			10*Math.ulp(1)
		);

        assertEquals(
			"v1.toAngle() equals v2.toAngle()",
			v1.toAngle(),
			v2.toAngle(),
			10*Math.ulp(Math.PI)
		);

        assertEquals(
			"v2.toAngle() equals _v2.toAngle()",
			v2.toAngle(),
			_v2.toAngle(),
			10*Math.ulp(Math.PI)
		);
    }

    @Test public void testRotate() {
        Vect north = new Vect(0,  1);

        assertEquals(
			"Vect.unit.rotate(north).toAngle() equals north.toAngle()",
			Vect.unit.rotate(north).toAngle(),
			north.toAngle(),
			10*Math.ulp(Math.PI)
		);

        Vect sw = new Vect(-1, -1);

        assertEquals(
			"Vect.unit.rotate(sw).toAngle() equals sw.toAngle()",
			Vect.unit.rotate(sw).toAngle(),
			sw.toAngle(),
			10*Math.ulp(Math.PI)
		);

        Vect se = new Vect(1, -1);

        assertEquals(
			"north.rotate(sw).toAngle() equals se.toAngle()",
			north.rotate(sw).toAngle(),
			se.toAngle(),
			10*Math.ulp(Math.PI)
		);

		for(int count = steps; count > 0; count--) {
			_testRotate();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testRotate() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        Vect  v3  = v1.rotate(v2);
        _Vect _v3 = new _Vect(v1).rotate(new _Vect(v2));

        assertEquals(
			"v3.toAngle() equals _v3.toAngle()",
			v3.toAngle(),
			_v3.toAngle(),
			10*Math.ulp(Math.PI)
		);
    }

    @Test public void testUnrotate() {
        Vect north = new Vect(0,  1);
        Vect south = new Vect(0,  -1);

        assertEquals(
			"Vect.unit.unrotate(north).toAngle() equals south.toAngle()",
			Vect.unit.unrotate(north).toAngle(),
			south.toAngle(),
			10*Math.ulp(Math.PI)
		);

        Vect sw = new Vect(-1, -1);
        Vect nw = new Vect(-1, 1);

        assertEquals(
			"Vect.unit.unrotate(sw).toAngle() equals nw.toAngle()",
			Vect.unit.unrotate(sw).toAngle(),
			nw.toAngle(),
			10*Math.ulp(Math.PI)
		);

        assertEquals(
			"north.unrotate(sw).toAngle() equals sw.toAngle()",
			north.unrotate(sw).toAngle(),
			sw.toAngle(),
			10*Math.ulp(Math.PI)
		);

		for(int count = steps; count > 0; count--) {
			_testUnrotate();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testUnrotate() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        Vect  v3  = v1.unrotate(v2);
        _Vect _v3 = new _Vect(v1).unrotate(new _Vect(v2));

        assertEquals(
			"v3.toAngle() equals _v3.toAngle()",
			v3.toAngle(),
			_v3.toAngle(),
			10*Math.ulp(Math.PI)
		);
    }

    @Test public void testDist() {
		for(int count = steps; count > 0; count--) {
			_testDist();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testDist() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.dist(v2) equals new _v1.dist(_v2)",
        	v1.dist(v2),
        	_v1.dist(_v2),
			10*Math.ulp(v1.dist(v2))
		);
    }

    @Test public void testDistSq() {
		for(int count = steps; count > 0; count--) {
			_testDistSq();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testDistSq() {
        Vect  v1  = Vect.random(min, max, min, max);
        Vect  v2  = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.distSq(v2) equals new _v1.distSq(_v2)",
        	v1.distSq(v2),
        	_v1.distSq(_v2),
			10*Math.ulp(v1.distSq(v2))
		);
    }

    @Test public void testNear() {
		for(int count = steps; count > 0; count--) {
			_testNear();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testNear() {
        Vect   v1      = Vect.random(min, max, min, max);
        Vect   v2      = Vect.random(min, max, min, max);
		double dist    = v1.dist(v2);
		double dist_lt = Util.uniform(0, dist);
		double dist_ge = Util.uniform(dist, 3*max);
        _Vect _v1      = new _Vect(v1);
        _Vect _v2      = new _Vect(v2);

        assertEquals(
			"v1.near(v2, dist_lt) equals new _v1.near(_v2, dist_lt)",
        	v1.near(v2, dist_lt),
        	_v1.near(_v2, dist_lt)
		);

        assertEquals(
			"v1.near(v2, dist_ge) equals new _v1.near(_v2, dist_ge)",
        	v1.near(v2, dist_ge),
        	_v1.near(_v2, dist_ge)
		);

        assertFalse(
			"v1.near(v2, dist_lt)",
        	v1.near(v2, dist_lt)
		);

        assertTrue(
			"v1.near(v2, dist_ge)",
        	v1.near(v2, dist_ge)
		);
    }

    @Test public void testPerp() {
		for(int count = steps; count > 0; count--) {
			_testPerp();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testPerp() {
        Vect   v1 = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);

        assertEquals(
			"v1.perp() equals new Vect(_v1.perp())",
        	v1.perp(),
        	new Vect(_v1.perp())
		);
    }

    @Test public void testRPerp() {
		for(int count = steps; count > 0; count--) {
			_testRPerp();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testRPerp() {
        Vect   v1 = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);

        assertEquals(
			"v1.rperp() equals new Vect(_v1.rperp())",
        	v1.rperp(),
        	new Vect(_v1.rperp())
		);
    }

    @Test public void testProject() {
		for(int count = steps; count > 0; count--) {
			_testProject();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testProject() {
        Vect   v1 = Vect.random(min, max, min, max);
        Vect   v2 = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        assertEquals(
			"v1.project(v2) equals new Vect(_v1.project(_v2))",
        	v1.project(v2),
        	new Vect(_v1.project(_v2))
		);
    }

    @Test public void testLerp() {
		for(int count = steps; count > 0; count--) {
			_testLerp();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testLerp() {
		double step = Util.uniform(0, max);
        Vect   v1   = Vect.random(min, max, min, max);
        Vect   v2   = Vect.random(min, max, min, max);
        _Vect _v1   = new _Vect(v1);
        _Vect _v2   = new _Vect(v2);

        assertEquals(
			"v1.lerp(v2, step) equals new Vect(_v1.lerp(_v2, step))",
        	v1.lerp(v2, step),
        	new Vect(_v1.lerp(_v2, step))
		);
    }

    @Test public void testLerpConst() {
		for(int count = steps; count > 0; count--) {
			_testLerpConst();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testLerpConst() {
		double step = Util.uniform(0, max);
        Vect   v1   = Vect.random(min, max, min, max);
        Vect   v2   = Vect.random(min, max, min, max);
        _Vect _v1   = new _Vect(v1);
        _Vect _v2   = new _Vect(v2);

        assertTrue(
			"v1.lerpConst(v2, step).almost(new Vect(_v1.lerpConst(_v2, step)), 10*Math.ulp(max*step))",
        	v1.lerpConst(v2, step).almost(new Vect(_v1.lerpConst(_v2, step)), 10*Math.ulp(max*step))
		);
    }

    @Test public void testSLerp() {
		for(int count = steps; count > 0; count--) {
			_testSLerp();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testSLerp() {
		double step = Util.uniform(0, max);
        Vect   v1 = Vect.random(min, max, min, max);
        Vect   v2 = Vect.random(min, max, min, max);
        _Vect _v1 = new _Vect(v1);
        _Vect _v2 = new _Vect(v2);

        Vect  slerp  = v1.slerp(v2, step);
		_Vect _slerp = _v1.slerp(_v2, step);

       	double ratioX = 1.0 - (slerp.x / _slerp.getX());
       	double ratioY = 1.0 - (slerp.y / _slerp.getY());

        assertTrue(
			"1.0 - (v1.slerp(v2, step).x / new Vect(_v1.slerp(_v2, step).getX()) <= 0.001",
        	ratioX <= 0.001
		);

        assertTrue(
			"1.0 - (v1.slerp(v2, step).y / new Vect(_v1.slerp(_v2, step).getY()) <= 0.001",
        	ratioY <= 0.001
		);
    }

    @Test public void testSLerpConst() {
		for(int count = steps; count > 0; count--) {
			_testSLerpConst();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testSLerpConst() {
		double step = Util.uniform(0, max);
        Vect   v1   = Vect.random(min, max, min, max);
        Vect   v2   = Vect.random(min, max, min, max);
        _Vect _v1   = new _Vect(v1);
        _Vect _v2   = new _Vect(v2);

       	System.err.println("slerpConst: " + v1.slerpConst(v2, step));
		System.err.println("slerpConst: " + new Vect(_v1.slerpConst(_v2, step)));
		System.err.println("slerpConst: " + 0.00001);

        assertTrue(
			"v1.slerpConst(v2, step).almost(new Vect(_v1.slerpConst(_v2, step)), 0.00001)",
        	v1.slerpConst(v2, step).almost(new Vect(_v1.slerpConst(_v2, step)), 0.0001)
		);
    }
}

