package ChipmunkJava.Debug;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

public class TextSpaceDebugDrawOptions extends SpaceDebugDrawOptions {
	public void drawCircle(Vect position, double angle, double radius, Color strokeColor, Color fillColor) {
		String msg =  "drawCircle(";
		       msg += "position:"      + position;
		       msg += ", angle:"       + angle;
		       msg += ", radius:"      + radius;
		       msg += ", strokeColor:" + strokeColor;
		       msg += ", fillColor:"   + fillColor;
		       msg += ")";
		System.err.println(msg);
	}

	public void drawDot(double size, Vect position, Color strokeColor) {
		String msg =  "drawDot(";
		       msg += "size:"          + size;
		       msg += ", position:"    + position;
		       msg += ", strokeColor:" + strokeColor;
		       msg += ")";
		System.err.println(msg);
	}

	public void drawFatSegment(Vect vect1, Vect vect2, double radius, Color strokeColor, Color fillColor) {
		String msg =  "drawFatSegment(";
		       msg += "vect1:"         + vect1;
		       msg += ", vect2:"       + vect2;
		       msg += ", radius:"      + radius;
		       msg += ", strokeColor:" + strokeColor;
		       msg += ", fillColor:"   + fillColor;
		       msg += ")";
		System.err.println(msg);
	}

	public void drawPolygon(Vect[] verts, double radius, Color strokeColor, Color fillColor) {
		String msg =  "drawPolygon(";
		       msg += "verts:"         + verts;
		       msg += ", radius:"      + radius;
		       msg += ", strokeColor:" + strokeColor;
		       msg += ", fillColor:"   + fillColor;
		       msg += ")";
		System.err.println(msg);
	}

	public void drawSegment(Vect vect1, Vect vect2, Color strokeColor) {
		String msg =  "drawSegment(";
		       msg += "vect1:"         + vect1;
		       msg += ", vect2:"       + vect2;
		       msg += ", strokeColor:" + strokeColor;
		       msg += ")";
		System.err.println(msg);
	}
}
