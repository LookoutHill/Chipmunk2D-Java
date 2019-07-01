package ChipmunkJava.Constraints;

import ChipmunkJava.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConstraintTest extends Constraint {
    @Test public void testPinJointFinalize() {
        Body        body1  = new Body(1.8, 18);
        Body        body2  = new Body(3.6, 36);
		Vect        vect1  = new Vect(-36, 5.4);
		Vect        vect2  = new Vect(-18, -7.2);
        PinJoint    joint1 = new PinJoint(body1, body2, vect1, vect2);
		joint1.finalize();
    }
}

