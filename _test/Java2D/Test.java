import ChipmunkJava.*;
import ChipmunkJava.Debug.*;
import ChipmunkJava.Shapes.*;

import java.util.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Test {
	private static Timer timer;

	public static void main(String[] args) {
		EventQueue.invokeLater(
				new Runnable() {
						@Override
						public void run() {
							timer = new Timer(4, new Animator());
							timer.start();
						}
				}
		);
	}
}

class Animator implements ActionListener {
	private final int width  = 800;
	private final int height = 450;

	private final Stage  stage;
	private final Window win;

	public Animator() {
		stage = new Stage(width, height);

		win = new Window(width, height, stage);
		win.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		stage.update();
		stage.repaint();
	}
}

class Window extends JFrame {
	public Window(int width, int height, Stage stage) {
		add(stage);

		setResizable(false);
		pack();

		setTitle("Java2D Animation");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Stage extends JPanel {
	private final int        max_steps  = 300000;
	private final int        skip_steps = 1000;
	private final double     interval   = 1.0/1200.0;

	private final int        width;
	private final int        height;

	private final double     elasticity = 0.999999;
	private final double     friction   = 0;

	private final Vect       offset     = new Vect(0, 0);

	private       Java2DSpaceDebugDrawOptions options;
	private       Space                       space;
	private       List<Body>                  balls        = new ArrayList<Body>();
	private       long                        update_count = 0;

	public Stage(int _width, int _height) {
		width  = _width;
		height = _height;

		setPreferredSize(new Dimension(width, height));
		setBackground(java.awt.Color.BLACK);

		space   = new Space();
		options = new Java2DSpaceDebugDrawOptions();

		createFence();

		int num_balls = 30;
		balls = new ArrayList<Body>();
		for(int count = 0; count < num_balls; count++) {
			createBall();
		}
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
		);

		rh.put(
				RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY
		);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHints(rh);

		g2d.setPaint(new GradientPaint(0,0,new java.awt.Color(127, 127, 127),getWidth(),getHeight(),new java.awt.Color(0, 0, 127)));
		g2d.fillRect(0, 0, getWidth(), getHeight());

		options.setCanvas(g2d);
		space.debugDraw(options);

		Toolkit.getDefaultToolkit().sync();
   }

	public void update() {
		for(int count = 20; count > 0; count--) {
			space.step(interval);
		}

		update_count++;
		if(update_count % 60 == 0) {
			System.err.println(update_count + " | " + space.getStepCount());
		}
	}
}

