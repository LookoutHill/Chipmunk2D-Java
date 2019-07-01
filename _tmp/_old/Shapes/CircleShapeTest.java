package ChipmunkJava.Shapes;

import ChipmunkJava.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircleShapeTest extends CircleShape {
    @Test public void testNewCircleShapeBodyDoubleVect() {
        Body        body     = new Body(1.8, 18);
		double      radius   = 1;
		Vect        position = new Vect(-36, 5.4);
        CircleShape circle   = new CircleShape(body, radius, position);
        assertTrue("new CircleShape(Body, double, Vect) instanceof CircleShape", circle instanceof CircleShape);
        assertTrue("new CircleShape(Body, double, Vect) instanceof Shape",       circle instanceof Shape);
        assertTrue("new CircleShape(Body, double, Vect) isn't a cpNullPtr",      circle.cpShapePtr != circle.cpNullPtr);
        assertTrue("Shape._shapes contains new CircleShape(Body, double, Vect)", Shape._shapes.getOrDefault(circle.cpShapePtr, null) != null);
        assertTrue("Shape._shapes.size() == 1", Shape._shapes.size() == 1);
    }
}

