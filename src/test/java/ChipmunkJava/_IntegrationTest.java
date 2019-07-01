package ChipmunkJava;

import ChipmunkJava.Collisions.*;
import ChipmunkJava.Constraints.*;
import ChipmunkJava.Debug.*;
import ChipmunkJava.Handlers.*;
import ChipmunkJava.Shapes.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class _IntegrationTest extends Space {
    @Test public void testStep() {
		System.err.println("testStep()");

        Space space = new Space();

		TextSpaceDebugDrawOptions options = new TextSpaceDebugDrawOptions();

        Body body     = new Body(1.8, 18);
		Vect position = new Vect(36, -18);
		body.setPosition(position);
		Vect velocity = new Vect(60, 60);
		body.setVelocity(velocity);
        assertTrue("body.getPosition().equals(position)", body.getPosition().equals(position));

		double      radius = 1;
		Vect        offset = new Vect(-36, 5.4);
        CircleShape circle = new CircleShape(body, radius, offset);

        space.addBody(body);
        space.addShape(circle);
		space.debugDraw(options);
		for(int count = 10; count > 0; count--) {
			space.debugDraw(options);
        	space.step(1.0/60.0);
		}

        Vect position2 = body.getPosition();
        Vect position3 = new Vect(46, -8);
        assertTrue("position2.equals(position3)", position2.equals(position3));

		Space.remove(space);
		Body.remove(body);
		Shape.remove(circle);
		SpaceDebugDrawOptions.remove(options);
		System.gc(); // Run the garbage collector.
    }

	class PrintOnBegin extends BeginCollisionHandler {
		public boolean invoke(Arbiter arbiter, Space space) {
			System.err.println("MATT: Begin: BAM!");
			return true;
		}
	}

	class PrintOnPreSolve extends PreSolveCollisionHandler {
		public boolean invoke(Arbiter arbiter, Space space) {
			System.err.println("MATT: PreSolve: BAM!");
			return true;
		}
	}

	class PrintOnPostSolve extends PostSolveCollisionHandler {
		public void invoke(Arbiter arbiter, Space space) {
			System.err.println("MATT: PostSolve: BAM!");
		}
	}

	class PrintOnSeparate extends SeparateCollisionHandler {
		public void invoke(Arbiter arbiter, Space space) {
			System.err.println("MATT: Separate: BAM!");
		}
	}

    @Test public void testCollisionHandlers() {
		System.err.println("testCollisionHandlers()");

        Space space    = new Space();

		TextSpaceDebugDrawOptions options = new TextSpaceDebugDrawOptions();

		space.setCollisionHandler("*", "*", new PrintOnBegin());
		space.setCollisionHandler("*", "*", new PrintOnPreSolve());
		space.setCollisionHandler("*", "*", new PrintOnPostSolve());
		space.setCollisionHandler("*", "*", new PrintOnSeparate());

        Body body1     = new Body(1.8, 18);
		Vect position1 = new Vect(36, -18);
		body1.setPosition(position1);
		Vect velocity1 = new Vect(60, 60);
		body1.setVelocity(velocity1);
        assertTrue("body1.getPosition().equals(position1)", body1.getPosition().equals(position1));

		double      radius1 = 5;
		Vect        offset1 = new Vect(0, 0);
        CircleShape circle1 = new CircleShape(body1, radius1, offset1);
		circle1.setElasticity(1);

        space.addBody(body1);
        space.addShape(circle1);

        Body body2     = new Body(1.8, 18);
		Vect position2 = new Vect(51, -18);
		body2.setPosition(position2);
		Vect velocity2 = new Vect(-60, 60);
		body2.setVelocity(velocity2);
        assertTrue("body2.getPosition().equals(position2)", body2.getPosition().equals(position2));

		double      radius2 = 5;
		Vect        offset2 = new Vect(0, 0);
        CircleShape circle2 = new CircleShape(body2, radius2, offset2);
		circle2.setElasticity(1);

        space.addBody(body2);
        space.addShape(circle2);
		space.debugDraw(options);
		for(int count = 100; count > 0; count--) {
			space.debugDraw(options);
        	space.step(1.0/60.0);
		}

		Space.remove(space);
		Body.remove(body1);
		Body.remove(body2);
		Shape.remove(circle1);
		Shape.remove(circle2);
		SpaceDebugDrawOptions.remove(options);
		System.gc(); // Run the garbage collector.
    }
}
