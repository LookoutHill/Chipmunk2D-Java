package ChipmunkJava.Debug;

import ChipmunkJava.*;
import ChipmunkJava.Shapes.*;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Java2DSpaceDebugDrawOptions extends SpaceDebugDrawOptions {
	private Graphics2D canvas;

	public void setCanvas(Graphics2D _canvas) {
		canvas = _canvas;
	}

	public void drawCircle(Vect position, double angle, double radius, Color strokeColor, Color fillColor) {
		Vect direction = position.forAngle(angle).mult(radius).add(position);
		int  d         = (int) (2*radius);
		int  px        = (int) position.x;
		int  py        = (int) position.y;
		int  pxr       = (int) (px - radius);
		int  pyr       = (int) (py - radius);
		int  dx        = (int) direction.x;
		int  dy        = (int) direction.y;

		canvas.setPaint(fillColor.toAwt());
		canvas.fillOval(pxr, pyr, d, d);
		canvas.setPaint(strokeColor.toAwt());
		canvas.drawOval(pxr, pyr, d, d);
		canvas.drawLine(px, py, dx, dy);
	}

	public void drawDot(double size, Vect position, Color strokeColor) {
		canvas.setPaint(strokeColor.toAwt());
		canvas.fillOval((int) (position.x - size/2), (int) (position.y - size/2), (int) size, (int) size);
	}

	public void drawFatSegment(Vect vect1, Vect vect2, double radius, Color strokeColor, Color fillColor) {
		canvas.setPaint(strokeColor.toAwt());
		canvas.setStroke(new BasicStroke((int) (2*radius), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		canvas.drawLine((int) vect1.x, (int) vect1.y, (int) vect2.x, (int) vect2.y);
	}

	public void drawPolygon(Vect[] verts, double radius, Color strokeColor, Color fillColor) {
		Polygon p = new Polygon();
		for(Vect vert : verts)
			p.addPoint((int) vert.x, (int) vert.y);

		canvas.setPaint(fillColor.toAwt());
		canvas.fillPolygon(p);
		canvas.setPaint(strokeColor.toAwt());
		canvas.drawPolygon(p);
	}

	public void drawSegment(Vect vect1, Vect vect2, Color strokeColor) {
		canvas.setPaint(strokeColor.toAwt());
		canvas.drawLine((int) vect1.x, (int) vect1.y, (int) vect2.x, (int) vect2.y);
	}
}
