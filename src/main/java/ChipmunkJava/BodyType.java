package ChipmunkJava;

import java.util.*;

public class BodyType {
	private native static void init();
	private native static int cpBodyTypeGetDynamic();
	private native static int cpBodyTypeGetKinematic();
	private native static int cpBodyTypeGetStatic();

	public static final BodyType DYNAMIC;
	public static final BodyType KINEMATIC;
	public static final BodyType STATIC;

	static {
		System.loadLibrary("chipmunkJni");
		init();

		DYNAMIC   = new BodyType(cpBodyTypeGetDynamic(),   "DYNAMIC");
		KINEMATIC = new BodyType(cpBodyTypeGetKinematic(), "KINEMATIC");
		STATIC    = new BodyType(cpBodyTypeGetStatic(),    "STATIC");
	}

	private final int    cpBodyType;
	private final String name;

	public static BodyType get(int cpBodyType) {
		if     (cpBodyType == DYNAMIC.cpBodyType)   return DYNAMIC;
		else if(cpBodyType == STATIC.cpBodyType)    return STATIC;
		else if(cpBodyType == KINEMATIC.cpBodyType) return KINEMATIC;

		throw new IllegalArgumentException("Illegal value for cpBodyType: " + cpBodyType);
	}

	private BodyType(int _cpBodyType, String _name) {
		cpBodyType = _cpBodyType;
		name       = _name;
	}

	@Override
	public String toString() {
		return name;
	}

	private static final List<BodyType> _btypes = Arrays.asList(BodyType.DYNAMIC, BodyType.KINEMATIC, BodyType.STATIC);

	public static BodyType random() {
		int selected = Util.randint(0, 2); // _btypes.size()-1;
		return _btypes.get(selected);
	}
}

