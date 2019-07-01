package ChipmunkJava;

import org.junit.Test;
import static org.junit.Assert.*;

public class BBTest extends BB {
	private final int steps = 1000;
	private final int min   = -steps;
	private final int max   = steps;

    @Test public void testNewBBDoubleDoubleDoubleDouble() {
		for(int count = steps; count > 0; count--) {
			_testNewBBDoubleDoubleDoubleDouble();
			_testNew_BBDoubleDoubleDoubleDouble();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testNewBBDoubleDoubleDoubleDouble() {
		double l = Util.uniform(min, max);
		double b = Util.uniform(min, max);
		double r = Util.uniform(min, max);
		double t = Util.uniform(min, max);

        BB bb = new BB(l, b, r, t);

        assertTrue(
			"new BB(double, double, double, double)",
			bb instanceof BB
		);
    }

    public void _testNew_BBDoubleDoubleDoubleDouble() {
		double l = Util.uniform(min, max);
		double b = Util.uniform(min, max);
		double r = Util.uniform(min, max);
		double t = Util.uniform(min, max);

        _BB bb = new _BB(l, b, r, t);

        assertTrue(
			"new _BB(double, double, double, double)",
			bb instanceof _BB
		);
    }

    @Test public void testBBRandom() {
		for(int count = steps; count > 0; count--) {
			_testBBRandom();
			_test_BBRandom();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testBBRandom() {
        BB bb = BB.random(min, max, min, max, max, max);

        assertTrue(
			"new " + bb.toString(),
			bb instanceof BB
		);
    }

    public void _test_BBRandom() {
        _BB bb = _BB.random(min, max, min, max, max, max);

        assertTrue(
		    "new " + bb.toString(),
			bb instanceof _BB
		);
    }

    @Test public void testBBClone() {
		for(int count = steps; count > 0; count--) {
			_testBBClone();
			_test_BBClone();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testBBClone() {
        BB bb1 = BB.random(min, max, min, max, max, max);
        BB bb2 = bb1.clone();
        BB bb3 = new BB(bb1);

       	assertTrue(
			"bb.clone() instanceof BB",
			bb2 instanceof BB
		);

       	assertTrue(
			"new BB(bb) instanceof BB",
			bb3 instanceof BB
		);
    }

    public void _test_BBClone() {
        _BB bb1 = _BB.random(min, max, min, max, max, max);
        _BB bb2 = bb1.clone();
        _BB bb3 = new _BB(bb1);

       	assertTrue(
			"_bb.clone() instanceof _BB",
			bb2 instanceof _BB
		);

       	assertTrue(
			"new _BB(_bb) instanceof _BB",
			bb3 instanceof _BB
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
        BB bb1 = BB.random(min, max, min, max, max, max);
        BB bb2 = new BB(bb1.l + 1, bb1.b + 1, bb1.r + 1, bb1.t + 1);
        BB bb3 = bb1.clone();

        assertFalse(
			"bb1.equals(bb2)",
			bb1.equals(bb2)
		);

        assertFalse(
			"bb2.equals(bb1)",
			bb2.equals(bb1)
		);

        assertTrue(
			"bb1.equals(bb3)",
			bb1.equals(bb3)
		);

        assertTrue(
			"bb3.equals(bb1)",
			bb3.equals(bb1)
		);

        assertFalse(
			"bb2.equals(bb3)",
			bb2.equals(bb3)
		);

        assertFalse(
			"bb3.equals(bb2)",
			bb3.equals(bb2)
		);
    }

    public void _test_Equals() {
        _BB bb1 = _BB.random(min, max, min, max, max, max);
        _BB bb2 = new _BB(bb1.getL() + 1, bb1.getB() + 1, bb1.getR() + 1, bb1.getT() + 1);
        _BB bb3 = bb1.clone();

        assertFalse(
			"_bb1.equals(_bb2)",
			bb1.equals(bb2)
		);

        assertFalse(
			"_bb2.equals(_bb1)",
			bb2.equals(bb1)
		);

        assertTrue(
			"_bb1.equals(_bb3)",
			bb1.equals(bb3)
		);

        assertTrue(
			"_bb3.equals(_bb1)",
			bb3.equals(bb1)
		);

        assertFalse(
			"_bb2.equals(_bb3)",
			bb2.equals(bb3)
		);

        assertFalse(
			"_bb3.equals(_bb2)",
			bb3.equals(bb2)
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
        BB   bb1 = BB.random(min, max, min, max, max, max);
        BB   bb2 = new BB(bb1.l + 1, bb1.b + 1, bb1.r + 1, bb1.t + 1);
        BB   bb3 = bb1.clone();
		double t  = 10*Math.ulp(max);

        assertFalse(
			"bb1.almost(bb2, t)",
			bb1.almost(bb2, t)
		);

        assertFalse(
			"bb2.almost(bb1, t)",
			bb2.almost(bb1, t)
		);

        assertTrue(
			"bb1.almost(bb3, t)",
			bb1.almost(bb3, t)
		);

        assertTrue(
			"bb3.almost(bb1, t)",
			bb3.almost(bb1, t)
		);

        assertFalse(
			"bb2.almost(bb3, t)",
			bb2.almost(bb3, t)
		);

        assertFalse(
			"bb3.almost(bb2, t)",
			bb3.almost(bb2, t)
		);
    }

    public void _test_Almost() {
        _BB  bb1 = _BB.random(min, max, min, max, max, max);
        _BB  bb2 = new _BB(bb1.getL() + 1, bb1.getB() + 1, bb1.getR() + 1, bb1.getT() + 1);
        _BB  bb3 = bb1.clone();
		double t  = 10*Math.ulp(max);

        assertFalse(
			"_bb1.almost(_bb2, t)",
			bb1.almost(bb2, t)
		);

        assertFalse(
			"_bb2.almost(_bb1, t)",
			bb2.almost(bb1, t)
		);

        assertTrue(
			"_bb1.almost(_bb3, t)",
			bb1.almost(bb3, t)
		);

        assertTrue(
			"_bb3.almost(_bb1, t)",
			bb3.almost(bb1, t)
		);

        assertFalse(
			"_bb2.almost(_bb3, t)",
			bb2.almost(bb3, t)
		);

        assertFalse(
			"_bb3.almost(_bb2, t)",
			bb3.almost(bb2, t)
		);
    }

    @Test public void testBB_BBConversion() {
		for(int count = steps; count > 0; count--) {
			_testBB_BBConversion();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testBB_BBConversion() {
        _BB _bb1 = _BB.random(min, max, min, max, max, max);
        BB  bb2  = new BB(_bb1);
        _BB _bb3 = new _BB(bb2);

        assertTrue(
			"_bb1.equals(bb2)",
			_bb1.getL() == bb2.l && _bb1.getB() == bb2.b && _bb1.getR() == bb2.r && _bb1.getT() == bb2.t
		);

        assertTrue(
			"_bb1.equals(_bb3)",
			_bb1.equals(_bb3)
		);

        assertTrue(
			"_bb3.equals(_bb1)",
			_bb3.equals(_bb1)
		);

        assertTrue(
			"bb2.equals(_bb3)",
			bb2.l == _bb3.getL() && bb2.b == _bb3.getB() && bb2.r == _bb3.getR() && bb2.t == _bb3.getT()
		);
	}

    @Test public void testToString() {
		double l    = Util.uniform(min, max);
		double b    = Util.uniform(min, max);
		double r    = Util.uniform(min, max);
		double t    = Util.uniform(min, max);
        BB     bb1  = new BB(l, b, r, t);
        _BB    _bb1 = new _BB(l, b, r, t);

        assertEquals(
			"bb.toString()",
			"BB(l:" + l + ", b:" + b + ", r:" + r + ", t:" + t + ")",
			bb1.toString()
		);

        assertEquals(
			"_bb.toString()",
			"_BB(l:" + l + ", b:" + b + ", r:" + r + ", t:" + t + ")",
			_bb1.toString()
		);
    }

    @Test public void testArea() {
		for(int count = steps; count > 0; count--) {
			_testArea();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testArea() {
        BB  bb1  = BB.random(min, max, min, max, max, max);
        _BB _bb1 = new _BB(bb1);

        assertEquals(
			"BB.area() equals _BB.area()",
			bb1.area(),
			_bb1.area(),
			10*Math.ulp(bb1.area())
		);
    }

    @Test public void testCenter() {
		for(int count = steps; count > 0; count--) {
			_testCenter();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testCenter() {
        BB  bb  = BB.random(min, max, min, max, max, max);
        _BB _bb = new _BB(bb);

        assertEquals(
			"bb.center() equals new BB(_bb.center())",
        	bb.center(),
        	new Vect(_bb.center())
		);
    }

    @Test public void testContainsBB() {
		for(int count = steps; count > 0; count--) {
			_testContainsBB();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testContainsBB() {
        BB   bb1 = BB.random(min, max, min, max, max, max);
        BB   bb2 = new BB(bb1.l + 1, bb1.b + 1, bb1.r - 1, bb1.t - 1);
        BB   bb3 = new BB(bb1.l - 1, bb1.b - 1, bb1.r + 1, bb1.t + 1);

        _BB _bb1 = new _BB(bb1);
        _BB _bb2 = new _BB(_bb1.getL() + 1, _bb1.getB() + 1, _bb1.getR() - 1, _bb1.getT() - 1);
        _BB _bb3 = new _BB(_bb1.getL() - 1, _bb1.getB() - 1, _bb1.getR() + 1, _bb1.getT() + 1);

        assertTrue(
			"bb1.containsBB(bb2)",
        	bb1.containsBB(bb2)
		);

        assertFalse(
			"bb1.containsBB(bb3)",
        	bb1.containsBB(bb3)
		);

        assertTrue(
			"_bb1.containsBB(_bb2)",
        	_bb1.containsBB(_bb2)
		);

        assertFalse(
			"_bb1.containsBB(_bb3)",
        	_bb1.containsBB(_bb3)
		);
    }

    @Test public void testContainsPoint() {
		for(int count = steps; count > 0; count--) {
			_testContainsPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testContainsPoint() {
        BB bb = BB.random(min, max, min, max, max, max);
		double minX;
		double maxX;
		if(Util.randbool()) {
			minX = min;
			maxX = bb.l;
		} else {
			minX = bb.r + Math.ulp(bb.r);
			maxX = max;
		}
		double minY;
		double maxY;
		if(Util.randbool()) {
			minY = min;
			maxY = bb.b;
		} else {
			minY = bb.t + Math.ulp(bb.t);
			maxY = max;
		}
        Vect p1 = new Vect(Util.uniform(minX, maxX), Util.uniform(minY, maxY));
        Vect p2 = new Vect(Util.uniform(bb.l, bb.r), Util.uniform(bb.b, bb.t));

        _BB   _bb = new _BB(bb);
        _Vect _p1  = new _Vect(p1);
        _Vect _p2  = new _Vect(p2);

        assertFalse(
			"bb.containsPoint(p1)",
        	bb.containsPoint(p1)
		);

        assertTrue(
			"bb.containsPoint(p2)",
        	bb.containsPoint(p2)
		);

        assertFalse(
			"_bb.containsPoint(_p1)",
        	_bb.containsPoint(_p1)
		);

        assertTrue(
			"_bb.containsPoint(_p2)",
        	_bb.containsPoint(_p2)
		);
    }

    @Test public void testIntersects() {
		for(int count = steps; count > 0; count--) {
			_testIntersects();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testIntersects() {
        BB  bb1  = BB.random(min, max, min, max, max, max);
        BB  bb2  = BB.random(min, max, min, max, max, max);
        _BB _bb1 = new _BB(bb1);
        _BB _bb2 = new _BB(bb2);

        assertEquals(
			"bb1.intersects(bb2) equals _bb1.intersects(_bb2)",
			bb1.intersects(bb2),
			_bb1.intersects(_bb2)
		);
    }

    @Test public void testIntersectsSegment() {
		for(int count = steps; count > 0; count--) {
			_testIntersectsSegment();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testIntersectsSegment() {
        BB    bb1  = BB.random(min, max, min, max, max, max);
		Vect  p1   = Vect.random(min, max, min, max);
		Vect  p2   = Vect.random(min, max, min, max);
        _BB   _bb1 = new _BB(bb1);
        _Vect _p1  = new _Vect(p1);
        _Vect _p2  = new _Vect(p2);

        assertEquals(
			"bb1.intersectsSegment(p1, p2) equals _bb1.intersectsSegment(_p1, _p2)",
			bb1.intersectsSegment(p1, p2),
			_bb1.intersectsSegment(_p1, _p2)
		);
    }

    @Test public void testMergedArea() {
		for(int count = steps; count > 0; count--) {
			_testMergedArea();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testMergedArea() {
        BB  bb1  = BB.random(min, max, min, max, max, max);
        BB  bb2  = BB.random(min, max, min, max, max, max);
        _BB _bb1 = new _BB(bb1);
        _BB _bb2 = new _BB(bb2);

        assertEquals(
			"bb1.mergedArea(bb2) equals _bb1.mergedArea(_bb2)",
			bb1.mergedArea(bb2),
			_bb1.mergedArea(_bb2),
			10*Math.ulp(bb1.mergedArea(bb2))
		);
    }

    @Test public void testSegmentQuery() {
		for(int count = steps; count > 0; count--) {
			_testSegmentQuery();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testSegmentQuery() {
        BB    bb1  = BB.random(min, max, min, max, max, max);
		Vect  p1   = Vect.random(min, max, min, max);
		Vect  p2   = Vect.random(min, max, min, max);
        _BB   _bb1 = new _BB(bb1);
        _Vect _p1  = new _Vect(p1);
        _Vect _p2  = new _Vect(p2);

        assertEquals(
			"bb1.segmentQuery(p1, p2) equals _bb1.segmentQuery(_p1, _p2)",
			bb1.segmentQuery(p1, p2),
			_bb1.segmentQuery(_p1, _p2),
			10*Math.ulp(bb1.segmentQuery(p1, p2))
		);
    }

    @Test public void testClampVect() {
		for(int count = steps; count > 0; count--) {
			_testClampVect();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testClampVect() {
        BB    bb  = BB.random(min, max, min, max, max, max);
		Vect  p    = Vect.random(min, max, min, max);
        _BB   _bb = new _BB(bb);
        _Vect _p   = new _Vect(p);

        assertTrue(
			"bb.clampVect(p).almost(new Vect(_bb.clampVect(_p)), 10*Math.ulp(max))",
			bb.clampVect(p).almost(new Vect(_bb.clampVect(_p)), 10*Math.ulp(max))
		);
    }

    @Test public void testWrapVect() {
		for(int count = steps; count > 0; count--) {
			_testWrapVect();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testWrapVect() {
        BB    bb  = BB.random(min, max, min, max, max, max);
		Vect  p    = Vect.random(min, max, min, max);
        _BB   _bb = new _BB(bb);
        _Vect _p   = new _Vect(p);

        assertTrue(
			"bb.wrapVect(p).almost(new Vect(_bb.wrapVect(_p)), 10*Math.ulp(max))",
			bb.wrapVect(p).almost(new Vect(_bb.wrapVect(_p)), 10*Math.ulp(max))
		);
    }

    @Test public void testExpand() {
		for(int count = steps; count > 0; count--) {
			_testExpand();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testExpand() {
        BB    bb  = BB.random(min, max, min, max, max, max);
		Vect  p    = Vect.random(min, max, min, max);
        _BB   _bb = new _BB(bb);
        _Vect _p   = new _Vect(p);

        assertTrue(
			"bb.expand(p).almost(new BB(_bb.expand(_p)), 10*Math.ulp(max))",
			bb.expand(p).almost(new BB(_bb.expand(_p)), 10*Math.ulp(max))
		);
    }

    @Test public void testOffset() {
		for(int count = steps; count > 0; count--) {
			_testOffset();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testOffset() {
        BB    bb  = BB.random(min, max, min, max, max, max);
		Vect  p    = Vect.random(min, max, min, max);
        _BB   _bb = new _BB(bb);
        _Vect _p   = new _Vect(p);

        assertTrue(
			"bb.offset(p).almost(new BB(_bb.offset(_p)), 10*Math.ulp(max))",
			bb.offset(p).almost(new BB(_bb.offset(_p)), 10*Math.ulp(max))
		);
    }

    @Test public void testMerge() {
		for(int count = steps; count > 0; count--) {
			_testMerge();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testMerge() {
        BB  bb1  = BB.random(min, max, min, max, max, max);
        BB  bb2  = BB.random(min, max, min, max, max, max);
        _BB _bb1 = new _BB(bb1);
        _BB _bb2 = new _BB(bb2);

        assertTrue(
			"bb1.merge(bb2).almost(new BB(_bb1.merge(_bb2)), 10*Math.ulp(max))",
			bb1.merge(bb2).almost(new BB(_bb1.merge(_bb2)), 10*Math.ulp(max))
		);
    }
}

