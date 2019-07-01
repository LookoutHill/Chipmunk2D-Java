package ChipmunkJava.Shapes;

import ChipmunkJava.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class PolyShapeTest extends PolyShape {
    @Test public void testNewPolyShapeBodyIntVectArrayDouble() {
        Body   body1   = new Body(1.8, 18);
		double radius1 = 1.8;

		Vect[] verts1 = new Vect[3];
		verts1[0] = new Vect(-1, -1);
		verts1[1] = new Vect( 0,  1);
		verts1[2] = new Vect( 1, -1);

        PolyShape poly1 = new PolyShape(body1, verts1, radius1);
        assertTrue("new PolyShape(Body, Vect[], double) instanceof PolyShape", poly1 instanceof PolyShape);
        assertTrue("new PolyShape(Body, Vect[], double) instanceof Shape", poly1 instanceof Shape);
    }
}

