package ChipmunkJava.Shapes;

import ChipmunkJava.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class SegmentShapeTest extends SegmentShape {
    @Test public void testNewSegmentShapeBodyVectVectDouble() {
        Body         body1     = new Body(1.8, 18);
		Vect         p1        = new Vect(-2, 1);
		Vect         p2        = new Vect(3, -5.4);
		double       thickness = 1;
        SegmentShape line1     = new SegmentShape(body1, p1, p2, thickness);
        assertTrue("new SegmentShape(Body, Vect, Vect, double) instanceof SegmentShape", line1 instanceof SegmentShape);
        assertTrue("new SegmentShape(Body, Vect, Vect, double) instanceof Shape", line1 instanceof Shape);
    }
}

