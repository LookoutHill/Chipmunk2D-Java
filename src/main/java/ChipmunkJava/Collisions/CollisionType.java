package ChipmunkJava.Collisions;

import java.util.Map;
import java.util.Hashtable;

public class CollisionType {
	private native static void init();
	private native static long cpCollisionTypeGetWildcard();

	private static long counter = 1;

	private static Map<String,  CollisionType> ctypesByName = new Hashtable<String,  CollisionType>();
	private static Map<Long,    CollisionType> ctypesByType = new Hashtable<Long,    CollisionType>();

	static {
		System.loadLibrary("chipmunkJni");
		init();

		String name     = "*";
		long   wildcard = cpCollisionTypeGetWildcard();
		new CollisionType(name, wildcard);
	}

	public final String name;
	public final long   cpCollisionType;

	protected CollisionType() {
		name            = this.getClass().getName();
		cpCollisionType = -2;

		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Collisions.CollisionTypeTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private CollisionType(String _name, long _cpCollisionType) {
		name            = _name;
		cpCollisionType = _cpCollisionType;
		ctypesByName.put(name,    this);
		ctypesByType.put(counter, this);
	}

	private CollisionType(String name) {
		this(name, counter);
		counter++;
	}

	public static CollisionType add(String name) {
		CollisionType ctype = ctypesByName.getOrDefault(name, null);
		if(ctype != null) throw new IllegalArgumentException("Failed to add CollisionType. CollisionType already exists: '" + name + "'");

		return new CollisionType(name);
	}

	public static CollisionType get(String name) {
		return ctypesByName.get(name);
	}

	public static CollisionType get(long ctype) {
		return ctypesByType.get(ctype);
	}

	public static CollisionType put(String name) {
		CollisionType ctype = ctypesByName.getOrDefault(name, null);
		if(ctype != null) return ctype;

		return new CollisionType(name);
	}

	public static long getMask(String ... names) {
		long mask = 0;
		for(String name : names) {
			mask |= get(name).cpCollisionType;
		}
		return mask;
	}

	public static long getMask(long ... ctypes) {
		long mask = 0;
		for(long ctype : ctypes) {
			mask |= get(ctype).cpCollisionType;
		}
		return mask;
	}

	public String toString() {
		return "CTYPE_" + name + "(" + cpCollisionType + ")";
	}
}

