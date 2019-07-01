package ChipmunkJava.Debug;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

public class NullSpaceDebugDrawOptions extends SpaceDebugDrawOptions {
	public void drawCircle(Vect position, double angle, double radius, Color strokeColor, Color fillColor) {}

	public void drawDot(double size, Vect position, Color strokeColor) {}

	public void drawFatSegment(Vect vect1, Vect vect2, double radius, Color strokeColor, Color fillColor) {}

	public void drawPolygon(Vect[] verts, double radius, Color strokeColor, Color fillColor) {}

	public void drawSegment(Vect vect1, Vect vect2, Color strokeColor) {}
}
