package ChipmunkJava.Shapes;

import ChipmunkJava.*;

public class Color {
	public final int r;
	public final int g;
	public final int b;
	public final int a;

	public Color(float _r, float _g, float _b, float _a) {
		r = (int) (_r * 255);
		g = (int) (_g * 255);
		b = (int) (_b * 255);
		a = (int) (_a * 255);
	}

	public Color(float _r, float _g, float _b) {
		this(_r, _g, _b, 1.0f);
	}

	public Color(float[] color) {
		r = (int) (color[0] * 255);
		g = (int) (color[1] * 255);
		b = (int) (color[2] * 255);
		a = (int) (color[3] * 255);
	}

	public Color(int _r, int _g, int _b, int _a) {
		r = _r;
		g = _g;
		b = _b;
		a = _a;
	}

	public Color(int _r, int _g, int _b) {
		this(_r, _g, _b, 255);
	}

	public Color(int color) {
		r = ((color >> 16) & 0xff);
		g = ((color >>  8) & 0xff);
		b = ((color      ) & 0xff);
		a = ((color >> 24));
	}

	public Color() {
		r = 0;
		g = 0;
		b = 0;
		a = 0;
	}

	public Color(Color that) {
		r = that.r;
		g = that.g;
		b = that.b;
		a = that.a;
	}

	public Color clone() {
		return new Color(this);
	}

	public float[] toFloatArray() {
		return new float[] { r/255.0f, g/255.0f, b/255.0f, a/255.0f };
	}

	public int toInt() {
		return a*16777216 + r*65536 + g*256 + b;
	}

	public int to_color() {
		return toInt();
	}

	public java.awt.Color toAwt() {
		return new java.awt.Color(r, g, b, a);
	}

	@Override
	public String toString() {
		return "Color(r:" + r + ", g:" + g + ", b:" + b + ", a:" + a + ")";
	}

	public static Color random() {
		return randomRGB();
	}

	public static Color randomRGB() {
		return new Color(Util.randint(0, 255), Util.randint(0, 255), Util.randint(0, 255));
	}

	public static Color randomRGBA() {
		return new Color(Util.randint(0, 255), Util.randint(0, 255), Util.randint(0, 255), Util.randint(0, 255));
	}
}

