package ChipmunkJava;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class BodyTest extends Body {
	private final int steps = 1000;
	private final int min   = 1;
	private final int max   = steps;

	@Test public void testNewBodyDoubleDouble() {
		for(int count = steps; count > 0; count--) {
			_testNewBodyDoubleDouble();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testNewBodyDoubleDouble() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Body body = new Body(mass, mass);

		assertTrue(
			"new Body(double, double) instanceof Body",
			body instanceof Body
		);

		Body.remove(body);
	}

	@Test public void testNewBodyBodyType() {
		Body body1 = new Body(BodyType.STATIC);

		assertTrue(
			"new Body(BodyType) instanceof Body",
			body1 instanceof Body
		);

		Body body2 = new Body(BodyType.KINEMATIC);

		assertTrue(
			"new Body(BodyType) instanceof Body",
			body2 instanceof Body
		);

		Body.remove(body1);
		Body.remove(body2);
	}

	@Test public void testGetMass() {
		for(int count = steps; count > 0; count--) {
			_testGetMass();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testGetMass() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Body body = new Body(mass, moment);

		assertTrue(
			"body.getMass()",
			body.getMass() == mass
		);

		Body.remove(body);
	}

	@Test public void testGetMoment() {
		for(int count = steps; count > 0; count--) {
			_testGetMoment();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testGetMoment() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Body body = new Body(mass, moment);

		assertTrue(
			"body.getMoment()",
			body.getMoment() == moment
		);

		Body.remove(body);
	}

	@Test public void testNewBodyBody() {
		for(int count = steps; count > 0; count--) {
			_testNewBodyBody();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testNewBodyBody() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Body body1 = new Body(mass, moment);
		Body body2 = new Body(body1);

		assertTrue(
			"new Body(double, double) instanceof Body",
			body2 instanceof Body
		);

		assertTrue(
			"body1.getMass() == body2.getMass()",
			body1.getMass() == body2.getMass()
		);

		assertTrue(
			"body1.getMoment() == body2.getMoment()",
			body1.getMoment() == body2.getMoment()
		);

		Body.remove(body1);
		Body.remove(body2);
	}

	@Test public void testBodyClone() {
		for(int count = steps; count > 0; count--) {
			_testBodyClone();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testBodyClone() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Body body1 = new Body(mass, moment);
		Body body2 = body1.clone();

		assertTrue(
			"body.clone() instanceof Body",
			body2 instanceof Body
		);

		assertTrue(
			"body1.getMass() == body2.getMass()",
			body1.getMass() == body2.getMass()
		);

		assertTrue(
			"body1.getMoment() == body2.getMoment()",
			body1.getMoment() == body2.getMoment()
		);

		Body.remove(body1);
		Body.remove(body2);
	}

	@Test public void testSetAngleGetRotation() {
		for(int count = steps; count > 0; count--) {
			_testSetAngleGetRotation();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetAngleGetRotation() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);
		double angle  = Util.uniform(-720, 720);

		Body body = new Body(mass, moment);
		body.setAngle(angle);

		Vect   actual = body.getRotation();
		Vect expected = new Vect(Math.cos(angle), Math.sin(angle));

		assertEquals(
			"body.getRotation() x-axis",
			actual.x,
			expected.x,
			10*Math.ulp(actual.x)
		);

		assertEquals(
			"body.getRotation() y-axis",
			actual.y,
			expected.y,
			10*Math.ulp(actual.y)
		);
		Body.remove(body);
	}

	@Test public void testSetGetAngle() {
		for(int count = steps; count > 0; count--) {
			_testSetGetAngle();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetAngle() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);
		double angle  = Util.uniform(-720, 720);

		Body body = new Body(mass, moment);
		body.setAngle(angle);

		assertTrue(
			"body.getAngle()",
			body.getAngle() == angle
		);

		Body.remove(body);
	}

	@Test public void testSetGetAngularVelocity() {
		for(int count = steps; count > 0; count--) {
			_testSetGetAngularVelocity();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetAngularVelocity() {
		double mass            = Util.uniform(min, max);
		double moment          = Util.uniform(min, max);
		double angularVelocity = Util.uniform(min, max);

		Body body = new Body(mass, moment);
		body.setAngularVelocity(angularVelocity);

		assertTrue(
			"body.getAngularVelocity()",
			body.getAngularVelocity() == angularVelocity
		);

		Body.remove(body);
	}

	@Test public void testSetGetBodyType() {
		for(int count = steps; count > 0; count--) {
			_testSetGetBodyType();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetBodyType() {
		double   mass   = Util.uniform(min, max);
		double   moment = Util.uniform(min, max);

		Body body = new Body(mass, moment);

		BodyType btype = BodyType.random();
		body.setBodyType(btype);

		assertTrue(
			"body.getBodyType()",
			body.getBodyType() == btype
		);

		Body.remove(body);
	}

	@Test public void testSetGetCenterOfGravity() {
		for(int count = steps; count > 0; count--) {
			_testSetGetCenterOfGravity();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetCenterOfGravity() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Vect cog = Vect.random(min, max, min, max);

		Body body = new Body(mass, moment);
		body.setCenterOfGravity(cog);

		assertTrue(
			"body.getCenterOfGravity()",
			body.getCenterOfGravity().equals(cog)
		);

		Body.remove(body);
	}

	@Test public void testSetGetForce() {
		for(int count = steps; count > 0; count--) {
			_testSetGetForce();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetForce() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Vect force = Vect.random(min, max, min, max);

		Body body = new Body(mass, moment);
		body.setForce(force);

		assertTrue(
			"body.getForce()",
			body.getForce().equals(force)
		);

		Body.remove(body);
	}

	@Test public void testSetGetPosition() {
		for(int count = steps; count > 0; count--) {
			_testSetGetPosition();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetPosition() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Vect   position = Vect.random(min, max, min, max);

		Body body = new Body(mass, moment);
		body.setPosition(position);

		assertTrue(
			"body.getPosition()",
			body.getPosition().equals(position)
		);

		Body.remove(body);
	}

	@Test public void testSetGetTorque() {
		for(int count = steps; count > 0; count--) {
			_testSetGetTorque();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetTorque() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);
		double torque = Util.uniform(min, max);

		Body body = new Body(mass, moment);
		body.setTorque(torque);

		assertTrue(
			"body.getTorque()",
			body.getTorque() == torque
		);

		Body.remove(body);
	}

	@Test public void testSetGetVelocity() {
		for(int count = steps; count > 0; count--) {
			_testSetGetVelocity();
		}
		System.gc(); // Run the garbage collector.
	}

	public void _testSetGetVelocity() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Vect velocity = Vect.random(min, max, min, max);

		Body body = new Body(mass, moment);
		body.setVelocity(velocity);

		assertTrue(
			"body.getVelocity()",
			body.getVelocity().equals(velocity)
		);

		Body.remove(body);
	}

    @Test public void testGetKineticEnergy() {
		for(int count = steps; count > 0; count--) {
			_testGetKineticEnergy();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testGetKineticEnergy() {
		double mass     = Util.uniformWithout(min, max, 0.0);
		double moment   = Util.uniformWithout(min, max, 0.0);
		Vect   velocity = Vect.zero;

		Body body = new Body(mass, moment);

		assertEquals(
			"body.getKineticEnergy() equals 0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		velocity = Vect.randomWithout(min, max, min, max, Vect.zero);
		body.setVelocity(velocity);

		assertTrue(
			"body.getKineticEnergy() != 0",
			body.getKineticEnergy() != 0
		);
    }

    @Test public void testGetSpace() {
		for(int count = steps; count > 0; count--) {
			_testGetSpace();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testGetSpace() {
		double mass   = Util.uniform(min, max);
		double moment = Util.uniform(min, max);

		Body body = new Body(mass, moment);

		try {
			body.getSpace();
		} catch(Exception err) {
        	fail("body.getSpace() threw the following error: \n" + err);
		}
    }

    @Test public void testLocalToWorld() {
		for(int count = steps; count > 0; count--) {
			_testLocalToWorld();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testLocalToWorld() {
		double mass     = Util.uniformWithout(min, max, 0.0);
		double moment   = Util.uniformWithout(min, max, 0.0);
		Vect   position = Vect.random(min, max, min, max);

		Body body = new Body(mass, moment);
		body.setPosition(position);

		assertEquals(
			"body.localToWorld(Vect.zero) equals position",
			body.localToWorld(Vect.zero),
			position
		);
	}

    @Test public void testWorldToLocal() {
		for(int count = steps; count > 0; count--) {
			_testWorldToLocal();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testWorldToLocal() {
		double mass     = Util.uniformWithout(min, max, 0.0);
		double moment   = Util.uniformWithout(min, max, 0.0);
		Vect   position = Vect.random(min, max, min, max);

		Body body = new Body(mass, moment);
		body.setPosition(position);

		assertEquals(
			"body.worldToLocal(position) equals Vect.zero",
			body.worldToLocal(position),
			Vect.zero
		);
	}

    @Test public void testGetVelocityAtLocalPoint() {
		for(int count = steps; count > 0; count--) {
			_testGetVelocityAtLocalPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testGetVelocityAtLocalPoint() {
		double mass            = Util.uniformWithout(min, max, 0.0);
		double moment          = Util.uniformWithout(min, max, 0.0);
		double angularVelocity = 0;
		Vect   velocity        = Vect.zero;

		Body body = new Body(mass, moment);

		assertEquals(
			"body.getVelocityAtLocalPoint(Vect.zero) equals velocity",
			body.getVelocityAtLocalPoint(Vect.zero),
			velocity
		);

		assertEquals(
			"body.getVelocityAtLocalPoint(Vect.unit) equals velocity",
			body.getVelocityAtLocalPoint(Vect.unit),
			velocity
		);

		velocity = Vect.randomWithout(min, max, min, max, Vect.zero);
		body.setVelocity(velocity);

		assertEquals(
			"body.getVelocityAtLocalPoint(Vect.zero) equals velocity",
			body.getVelocityAtLocalPoint(Vect.zero),
			velocity
		);

		assertEquals(
			"body.getVelocityAtLocalPoint(Vect.unit) equals velocity",
			body.getVelocityAtLocalPoint(Vect.unit),
			velocity
		);

		angularVelocity = Util.uniformWithout(min, max, 0.0);
		body.setAngularVelocity(angularVelocity);

		assertEquals(
			"body.getVelocityAtLocalPoint(Vect.zero) equals velocity",
			body.getVelocityAtLocalPoint(Vect.zero),
			velocity
		);

		assertTrue(
			"body.getVelocityAtLocalPoint(Vect.unit) != velocity",
			body.getVelocityAtLocalPoint(Vect.unit) != velocity
		);
    }

    @Test public void testGetVelocityAtWorldPoint() {
		for(int count = steps; count > 0; count--) {
			_testGetVelocityAtWorldPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testGetVelocityAtWorldPoint() {
		double mass            = Util.uniformWithout(min, max, 0.0);
		double moment          = Util.uniformWithout(min, max, 0.0);
		double angularVelocity = 0;
		Vect   position        = Vect.zero;
		Vect   velocity        = Vect.zero;

		Body body = new Body(mass, moment);
		body.setPosition(position);

		assertEquals(
			"body.getVelocityAtWorldPoint(position) equals velocity",
			body.getVelocityAtWorldPoint(position),
			velocity
		);

		assertEquals(
			"body.getVelocityAtWorldPoint(position.add(Vect.unit)) equals velocity",
			body.getVelocityAtWorldPoint(position.add(Vect.unit)),
			velocity
		);

		velocity = Vect.randomWithout(min, max, min, max, Vect.zero);
		body.setVelocity(velocity);

		assertEquals(
			"body.getVelocityAtWorldPoint(position) equals velocity",
			body.getVelocityAtWorldPoint(position),
			velocity
		);

		assertEquals(
			"body.getVelocityAtWorldPoint(position.add(Vect.unit)) equals velocity",
			body.getVelocityAtWorldPoint(position.add(Vect.unit)),
			velocity
		);

		angularVelocity = Util.uniformWithout(min, max, 0.0);
		body.setAngularVelocity(angularVelocity);

		assertEquals(
			"body.getVelocityAtWorldPoint(position) equals velocity",
			body.getVelocityAtWorldPoint(position),
			velocity
		);

		assertTrue(
			"body.getVelocityAtWorldPoint(position.add(Vect.unit)) != velocity",
			body.getVelocityAtWorldPoint(position.add(Vect.unit)) != velocity
		);
    }

    @Test public void testApplyForceAtLocalPoint() {
		for(int count = steps; count > 0; count--) {
			_testApplyForceAtLocalPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testApplyForceAtLocalPoint() {
		double mass   = Util.uniformWithout(min, max, 0.0);
		double moment = Util.uniformWithout(min, max, 0.0);

		Body body = new Body(mass, moment);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		Vect force = Vect.zero;
		body.applyForceAtLocalPoint(force, Vect.zero);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		force = Vect.randomWithout(min, max, min, max, Vect.zero);
		body.applyForceAtLocalPoint(force, Vect.zero);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		Space space = new Space();
		space.addBody(body);
		space.step(1.0);

		assertTrue(
			"body.getKineticEnergy() != 0.0",
			body.getKineticEnergy() != 0.0
		);

		space.removeBody(body);
	}

    @Test public void testApplyForceAtWorldPoint() {
		for(int count = steps; count > 0; count--) {
			_testApplyForceAtWorldPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testApplyForceAtWorldPoint() {
		double mass     = Util.uniformWithout(min, max, 0.0);
		double moment   = Util.uniformWithout(min, max, 0.0);
		Vect   position = Vect.randomWithout(min, max, min, max, Vect.zero);

		Body body = new Body(mass, moment);
		body.setPosition(position);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		Vect force = Vect.zero;
		body.applyForceAtWorldPoint(force, position);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		force = Vect.randomWithout(min, max, min, max, Vect.zero);
		body.applyForceAtWorldPoint(force, position);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		Space space = new Space();
		space.addBody(body);
		space.step(1.0);

		assertTrue(
			"body.getKineticEnergy() != 0.0",
			body.getKineticEnergy() != 0.0
		);

		space.removeBody(body);
	}

    @Test public void testApplyImpulseAtLocalPoint() {
		for(int count = steps; count > 0; count--) {
			_testApplyImpulseAtLocalPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testApplyImpulseAtLocalPoint() {
		double mass   = Util.uniformWithout(min, max, 0.0);
		double moment = Util.uniformWithout(min, max, 0.0);

		Body body = new Body(mass, moment);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		Vect force = Vect.zero;
		body.applyImpulseAtLocalPoint(force, Vect.zero);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		force = Vect.randomWithout(min, max, min, max, Vect.zero);
		body.applyImpulseAtLocalPoint(force, Vect.zero);

		assertTrue(
			"body.getKineticEnergy() != 0.0",
			body.getKineticEnergy() != 0.0
		);
	}

    @Test public void testApplyImpulseAtWorldPoint() {
		for(int count = steps; count > 0; count--) {
			_testApplyImpulseAtWorldPoint();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testApplyImpulseAtWorldPoint() {
		double mass     = Util.uniformWithout(min, max, 0.0);
		double moment   = Util.uniformWithout(min, max, 0.0);
		Vect   position = Vect.randomWithout(min, max, min, max, Vect.zero);

		Body body = new Body(mass, moment);
		body.setPosition(position);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		Vect force = Vect.zero;
		body.applyImpulseAtWorldPoint(force, position);

		assertEquals(
			"body.getKineticEnergy() equals 0.0",
			body.getKineticEnergy(),
			0.0,
			0.0
		);

		force = Vect.randomWithout(min, max, min, max, Vect.zero);
		body.applyImpulseAtWorldPoint(force, position);

		assertTrue(
			"body.getKineticEnergy() != 0.0",
			body.getKineticEnergy() != 0.0
		);
	}

    @Test public void testSleeping() {
		for(int count = steps; count > 0; count--) {
			_testIsSleeping();
		}
		System.gc(); // Run the garbage collector.
	}

    public void _testIsSleeping() {
		double mass     = Util.uniformWithout(min, max, 0.0);
		double moment   = Util.uniformWithout(min, max, 0.0);

		Body body1 = new Body(mass, moment);
		Body body2 = new Body(mass, moment);

		Space space = new Space();
		space.setSleepTimeThreshold(1.0);
		space.addBody(body1);
		space.addBody(body2);

		assertFalse(
			"body1.isSleeping() == false",
			body1.isSleeping()
		);

		body1.sleep();

		assertTrue(
			"body1.isSleeping() == true",
			body1.isSleeping()
		);

		body1.activate();

		assertFalse(
			"body1.isSleeping() == false",
			body1.isSleeping()
		);

		space.removeBody(body1);
	}
}

/*
	MATT
	void sleepWithGroup(Body groupMember);
	void activateStatic(Shape shape);
*/
