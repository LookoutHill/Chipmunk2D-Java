package ChipmunkJava;

import ChipmunkJava.Collisions.*;
import ChipmunkJava.Constraints.*;
import ChipmunkJava.Debug.*;
import ChipmunkJava.Handlers.*;
import ChipmunkJava.Shapes.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class SpaceTest extends Space {
    @Test public void testNewSpace() {
        Space space = new Space();
        assertTrue("new Space() instanceof Space", space instanceof Space);

		Space.remove(space);
		System.gc(); // Run the garbage collector.
    }

    @Test public void testStepping() {
        Space space = new Space();
		System.err.println("MATT: testStepping(): before: " + space.getStepCount());
		space.step(1.0/1200.0);
		System.err.println("MATT: testStepping(): after:  " + space.getStepCount());

		Space.remove(space);
		System.gc(); // Run the garbage collector.
    }

    @Test public void testAddContainsRemoveBody() {
        Space space = new Space();

		Body  body  = new Body(18, 36);

		assertFalse("space.containsBody(body) == false", space.containsBody(body));

		try {
        	space.addBody(body);
		} catch(Exception err) {
        	fail("space.addBody() threw the following error: \n" + err);
		}

		assertTrue("space.containsBody(body) == true", space.containsBody(body));

		try {
        	space.removeBody(body);
		} catch(Exception err) {
        	fail("space.removeBody() threw the following error: \n" + err);
		}

		assertFalse("space.containsBody(body) == false", space.containsBody(body));

		Space.remove(space);
		System.gc(); // Run the garbage collector.
    }

    @Test public void testAddContainsRemoveShape() {
        Space       space  = new Space();

        Body        body   = new Body(1.8, 18);
		double      radius = 1;
		Vect        offset = new Vect(-36, 5.4);
        CircleShape circle = new CircleShape(body, radius, offset);

		assertFalse("space.containsShape(circle) == false", space.containsShape(circle));

		try {
        	space.addBody(body);
        	space.addShape(circle);
		} catch(Exception err) {
        	fail("space.addShape() threw the following error: \n" + err);
		}

		assertTrue("space.containsShape(circle) == true", space.containsShape(circle));

		try {
        	space.removeShape(circle);
		} catch(Exception err) {
        	fail("space.removeShape() threw the following error: \n" + err);
		}

		assertFalse("space.containsShape(circle) == false", space.containsShape(circle));

		Space.remove(space);
		System.gc(); // Run the garbage collector.
    }

    @Test public void testShapeQuery() {
        Space space = new Space();

		double radius = 30;
		Vect   offset = new Vect(0, 0);

        Body        body1     = new Body(1.8, 18);
		Vect        position1 = new Vect(100, 100);
		body1.setPosition(position1);
        CircleShape circle1 = new CircleShape(body1, radius, offset);
        space.addBody(body1);
        space.addShape(circle1);

		ShapeQueryInfo[] list0 = space.shapeQuery(circle1);
        assertTrue("shapeQuery() with 1 shapes returns an empty list.", list0.length == 0);

        Body        body2     = new Body(1.8, 18);
		Vect        position2 = new Vect(100, 100);
		body2.setPosition(position2);
        CircleShape circle2 = new CircleShape(body2, radius, offset);
        space.addBody(body2);
        space.addShape(circle2);

		ShapeQueryInfo[] list1 = space.shapeQuery(circle1);
        assertTrue("shapeQuery() with 2 overlapped shapes returns a list with 1 element.", list1.length == 1);
        assertTrue("shapeQuery(shape1) returns a list with shape2 in it.", list1[0].getShape().equals(circle2));

		Space.remove(space);
		System.gc(); // Run the garbage collector.
    }
}

/*
	addConstraint(Constraint constraint);
	containsConstraint(Constraint constraint);
	removeConstraint(Constraint constraint);

	BBQuery(BB bb, ShapeFilter filter);
	pointQuery(Vect point, double radius, ShapeFilter filter);
	pointQueryNearest(Vect point, double radius, ShapeFilter filter);
	segmentQuery(Vect pointA, Vect pointB, double radius, ShapeFilter filter);
	segmentQueryFirst(Vect pointA, Vect pointB, double radius, ShapeFilter filter);

	getStaticBody();

	setCollisionBias(double value);
	getCollisionBias();

	setCollisionPersistence(long value);
	getCollisionPersistence();

	setCollisionSlop(double value);
	getCollisionSlop();

	setDamping(double value);
	getDamping();

	setGravity(Vect vect);
	getGravity();

	setIdleSpeedThreshold(double value);
	getIdleSpeedThreshold();

	setIterations(int value);
	getIterations();

	setSleepTimeThreshold(double value);
	getSleepTimeThreshold();
*/

/*
	MATT
	isLocked();

	reindexShape(Shape shape);
	reindexShapesForBody(Body body);
	reindexStatic();

	useSpatialHash(double size, int count);
	debugDraw(SpaceDebugDrawOptions options);
*/
