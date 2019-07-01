package ChipmunkJava.Shapes;

import ChipmunkJava.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShapeTest extends Shape {
    @Test public void testCircleFinalize() {
        Body        body1     = new Body(1.8, 18);
		double      radius1   = 1;
		Vect        position1 = new Vect(-36, 5.4);
        CircleShape circle1   = new CircleShape(body1, radius1, position1);
		circle1.finalize();
    }

    @Test public void testPolyFinalize() {
        Body   body1   = new Body(1.8, 18);
		double radius1 = 1.8;

		Vect[] verts1 = new Vect[3];
		verts1[0] = new Vect(-1, -1);
		verts1[1] = new Vect( 0,  1);
		verts1[2] = new Vect( 1, -1);

        PolyShape poly1 = new PolyShape(body1, verts1, radius1);
		poly1.finalize();
    }

    @Test public void testSegmentFinalize() {
        Body         body1     = new Body(1.8, 18);
		Vect         p1        = new Vect(-2, 1);
		Vect         p2        = new Vect(3, -5.4);
		double       thickness = 1;
        SegmentShape line1     = new SegmentShape(body1, p1, p2, thickness);
		line1.finalize();
    }
}

