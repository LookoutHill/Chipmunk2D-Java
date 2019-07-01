package ChipmunkJava;

public abstract class Util {
	public static double uniform(double min, double max) {
		double range = max - min;
		return Math.random() * range + min;
	}

	public static double uniformWithout(double min, double max, double not) {
		double value = uniform(min, max);
		while(value == not)
			value = uniform(min, max);
		return value;
	}

	public static int randint(int min, int max) {
		return (int) uniform(min, max+1);
	}

	public static double randintWithout(int min, int max, int not) {
		int value = randint(min, max);
		while(value == not)
			value = randint(min, max);
		return value;
	}

	public static boolean randbool() {
		double choice = Math.random() * 2;
		if(choice >= 1) return true;
		else            return false;
	}

	public static double littleLess(double value) {
		return value - Math.ulp(value);
	}

	public static double littleMore(double value) {
		return value + Math.ulp(value);
	}

	public static double clamp(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}
}
