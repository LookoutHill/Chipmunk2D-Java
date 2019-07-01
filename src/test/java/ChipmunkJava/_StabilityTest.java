package ChipmunkJava;

import ChipmunkJava.Collisions.*;
import ChipmunkJava.Constraints.*;
import ChipmunkJava.Debug.*;
import ChipmunkJava.Handlers.*;
import ChipmunkJava.Shapes.*;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class _StabilityTest extends Space {
	private final int        max_steps  = 300;
	private final int        skip_steps = 1000;
	private final double     interval   = 1.0/1200.0;

	private final int        width      = 800;
	private final int        height     = 450;

	private final double     elasticity = 0.999999;
	private final double     friction   = 0;

	private final double     angle      = Util.uniform(-720, 720);
	private final Color      color      = Color.random();
	private final Vect       offset     = new Vect(0, 0);
	private final double     radius     = Util.uniform(10, 25);

	private       SpaceDebugDrawOptions options;
	private       Space                 space;
	private       List<Body>            balls    = new ArrayList<Body>();

    @Test public void test() {
		try {
			System.err.println("test()");

			space   = new Space();
			options = new NullSpaceDebugDrawOptions(); // MATT

			createFence();

			int num_balls = 30;
			balls = new ArrayList<Body>();
			for(int count = 0; count < num_balls; count++) {
				createBall();
/*
				System.err.println("ball" + count + ":");
				System.err.println(balls.get(count).getPosition());
				System.err.println(balls.get(count).getVelocity());
*/
			}

			System.err.println(space.getStepCount());
			for(int step = 1; step <= max_steps; step++) {
				space.debugDraw(options); // MATT
				space.step(interval);
				if(step % skip_steps == 0)
					System.err.println(space.getStepCount());
			}

				space.debugDraw(options); // MATT
/*
			for(int count = 0; count < balls.size(); count++) {
				System.err.println("ball" + count + ":");
				System.err.println(balls.get(count).getPosition());
				System.err.println(balls.get(count).getVelocity());
			}
*/

			Space.remove(space);
			SpaceDebugDrawOptions.remove(options); // MATT
			System.gc(); // Run the garbage collector.
		} catch (Exception err) {
			System.err.println("MATT: catch(err)");
			System.err.println(err);
			assertTrue("Caught!", false);
		}
	}

	public void createFence() {
		Body  body   = space.getStaticBody();
		Shape left   = new SegmentShape(body, new Vect(0, 0),          new Vect(0, height),     10);
		Shape top    = new SegmentShape(body, new Vect(0, height),     new Vect(width, height), 10);
		Shape right  = new SegmentShape(body, new Vect(width, height), new Vect(width, 0),      10);
		Shape bottom = new SegmentShape(body, new Vect(width, 0),      new Vect(0, 0),          10);

		left.setElasticity(elasticity);
		left.setFriction(friction);

		top.setElasticity(elasticity);
		top.setFriction(friction);

		right.setElasticity(elasticity);
		right.setFriction(friction);

		bottom.setElasticity(elasticity);
		bottom.setFriction(friction);

		space.addShape(left);
		space.addShape(top);
		space.addShape(right);
		space.addShape(bottom);
	}

	public void createBall(Vect position, Vect velocity) {
		Body body = new Body(1, 1);
		body.setAngle(angle);
		if(position == null)
			position = new Vect(Util.uniform(radius+10, width - radius+10), Util.uniform(radius+10, height - radius+10));
		body.setPosition(position);
		if(velocity == null)
			velocity = Vect.unit.forAngle(body.getAngle()).mult(Util.uniform(60, 100));
		body.setVelocity(velocity);
		Shape circle = new CircleShape(body, radius, offset);
		circle.setColor(color);
		circle.setElasticity(elasticity);
		circle.setFriction(friction);

		space.addBody(body);
		space.addShape(circle);

		balls.add(body);
	}

	public void createBall() {
		createBall(null, null);
	}
}

