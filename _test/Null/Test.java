import ChipmunkJava.*;
import ChipmunkJava.Collisions.*;
import ChipmunkJava.Constraints.*;
import ChipmunkJava.Debug.*;
import ChipmunkJava.Handlers.*;
import ChipmunkJava.Shapes.*;

import java.util.*;


public class Test {
	private final int        max_steps  = 300000;
	private final int        skip_steps = 1000;
	private final double     interval   = 1.0/1200.0;

	private final int        width      = 800;
	private final int        height     = 450;

	private final double     elasticity = 0.999999;
	private final double     friction   = 0;

	private final Vect       offset     = new Vect(0, 0);

	private       SpaceDebugDrawOptions options;
	private       Space                 space;
	private       List<Body>            balls        = new ArrayList<Body>();

	public static void main(String[] args) {
		new Test().test();
	}

    public void test() {
		System.err.println("test()");

		space   = new Space();
		options = new NullSpaceDebugDrawOptions();

		createFence();

		int num_balls = 30;
		balls = new ArrayList<Body>();
		for(int count = 0; count < num_balls; count++) {
			createBall();
		}

		System.err.println("0 | " + space.getStepCount());
		for(int step = 1; step <= max_steps; step++) {
			space.debugDraw(options);
			space.step(interval);
			if(step % skip_steps == 0)
				System.err.println(step + " | " + space.getStepCount());
		}

		space.debugDraw(options);
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
		double angle  = Util.uniform(-720, 720);
		double radius = Util.uniform(10, 25);
		Color  color  = Color.random();

		if(position == null)
			position = new Vect(Util.uniform(radius+10, width - radius+10), Util.uniform(radius+10, height - radius+10));
		if(velocity == null)
			velocity = Vect.unit.forAngle(angle).mult(Util.uniform(60, 100));
		System.err.println("velocity: " + velocity);

		Body body = new Body(1, 1);
		body.setAngle(angle);
		body.setPosition(position);
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

