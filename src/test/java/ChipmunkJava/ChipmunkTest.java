package ChipmunkJava;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChipmunkTest extends Chipmunk {
	private final int steps = 10;
	private final int min   = 1;
	private final int max   = steps;

    @Test public void testAreaForCircle() {
		for(int count = steps; count > 0; count--) {
    		_testAreaForCircle();
		}
	}

    public void _testAreaForCircle() {
    	double radiusA  = Util.uniform(0, max);
    	double radiusB  = Util.uniform(0, max);
		double expected = Math.PI * Math.abs(radiusA*radiusA - radiusB*radiusB);
		double actual   = Chipmunk.areaForCircle(radiusA, radiusB);
        assertEquals("areaForCircle()", actual, expected, 10*Math.ulp(actual));
    }

    @Test public void testAreaForPoly() {
		for(int count = steps; count > 0; count--) {
    		_testAreaForPoly();
		}
	}

    public void _testAreaForPoly() {
		Vect   position = Vect.random(min, max, min, max);
		Vect   stretch  = Vect.random(min, max, min, max);
    	Vect[] vertices = { position, position.add(Vect.unit.mult(stretch)), position.add(new Vect(0.0, 1.0).mult(stretch)), position };
		double expected = 0.5 * vertices[1].sub(vertices[0]).x * vertices[2].sub(vertices[0]).y;
		double actual   = Chipmunk.areaForPoly(vertices, 0.0);
        assertEquals("areaForPoly()", expected, actual, 100*Math.ulp(actual));
    }

    @Test public void testAreaForSegment() {
		for(int count = steps; count > 0; count--) {
    		_testAreaForSegment();
		}
	}

    public void _testAreaForSegment() {
		Vect   pointA   = Vect.random(min, max, min, max);
		Vect   pointB   = Vect.random(min, max, min, max);
		double radius   = Util.uniform(0, max);
		double expected = radius * (Math.PI*radius + 2.0 * pointA.dist(pointB));
		double actual   = Chipmunk.areaForSegment(pointA, pointB, radius);
        assertEquals("areaForSegment()", expected, actual, 10*Math.ulp(actual));
    }

    @Test public void testMomentForCircle() {
		for(int count = steps; count > 0; count--) {
    		_testMomentForCircle();
		}
	}

    public void _testMomentForCircle() {
		double mass     = Util.uniform(min, max);
    	double radiusA  = Util.uniform(0, max);
    	double radiusB  = Util.uniform(0, max);
		Vect   offset   = Vect.random(min, max, min, max);
		double expected = mass * (0.5 * (radiusA*radiusA + radiusB*radiusB) + offset.lengthSq());
		double actual   = Chipmunk.momentForCircle(mass, radiusA, radiusB, offset);
        assertEquals("momentForCircle()", expected, actual, 10*Math.ulp(actual));
    }

    @Test public void testMomentForBox() {
		for(int count = steps; count > 0; count--) {
    		_testMomentForBox();
		}
	}

    public void _testMomentForBox() {
		double mass     = Util.uniform(min, max);
		double width    = Util.uniform(min, max);
		double height   = Util.uniform(min, max);
		double expected = mass * (width*width + height*height) / 12.0;
		double actual   = Chipmunk.momentForBox(mass, width, height);
        assertEquals("momentForBox()", expected, actual, 100*Math.ulp(actual));
    }

/*
	MATT
    @Test public void testMomentForPoly() {
		for(int count = steps; count > 0; count--) {
    		_testMomentForPoly();
		}
	}

    public void _testMomentForPoly() {
		double mass     = Util.uniform(min, max);
		Vect   position = Vect.random(min, max, min, max);
		Vect   stretch  = Vect.random(min, max, min, max);
    	Vect[] vertices = { position, position.add(Vect.unit.mult(stretch)), position.add(new Vect(0.0, 1.0).mult(stretch)), position };
		double expected = 0.5 * vertices[1].sub(vertices[0]).x * vertices[2].sub(vertices[0]).y;
		double actual   = Chipmunk.momentForPoly(mass, vertices, offset, radius);
        assertEquals("momentForPoly()", expected, actual, 10*Math.ulp(actual));
    }
*/

    @Test public void testMomentForSegment() {
		for(int count = steps; count > 0; count--) {
    		_testMomentForSegment();
		}
	}

    public void _testMomentForSegment() {
		double mass     = Util.uniform(min, max);
		Vect   pointA   = Vect.random(min, max, min, max);
		Vect   pointB   = Vect.random(min, max, min, max);
		double radius   = Util.uniform(min, max);
		Vect   offset   = pointA.lerp(pointB, 0.5);
		double length   = pointA.dist(pointB) + 2.0*radius;
		double expected = mass * ((length*length + 4.0*radius*radius)/12.0 + offset.lengthSq());
		double actual   = Chipmunk.momentForSegment(mass, pointA, pointB, radius);
        assertEquals("momentForSegment()", expected, actual, 10*Math.ulp(actual));
    }
}

