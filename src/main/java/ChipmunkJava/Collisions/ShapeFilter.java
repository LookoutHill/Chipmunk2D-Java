package ChipmunkJava.Collisions;

public class ShapeFilter {
	private native static void init();
	private native static long getNoCollisionGroup();
	private native static int  getAllCollisionCategories();

	private static final long noCollisionGroup;
	private static final int  allCollisionCategories;

	static {
		System.loadLibrary("chipmunkJni");
		init();

		noCollisionGroup       = getNoCollisionGroup();
		allCollisionCategories = getAllCollisionCategories();

		if(allCollisionCategories != CollisionType.get("*").cpCollisionType) throw new RuntimeException("Chipmunk2D Java requires that Chipmunk2D define CP_ALL_CATEGORIES == CP_WILDCARD_COLLISION_TYPE and they aren't.");
	}

	public static final ShapeFilter filterNothing = new ShapeFilter(noCollisionGroup, allCollisionCategories, allCollisionCategories);

	public final long group;
	public final int  category;
	public final int  mask;

	public ShapeFilter(long _group, int _category, int _mask) {
		if(_group == CollisionType.get("*").cpCollisionType) _group = noCollisionGroup;

		group    = _group;
		category = _category;
		mask     = _mask;
	}

	public ShapeFilter(CollisionType group, CollisionType category, CollisionType mask) {
		this(group.cpCollisionType, (int) category.cpCollisionType, (int) mask.cpCollisionType);
	}

	public ShapeFilter(int _category, int _mask) {
		this(noCollisionGroup, _category, _mask);
	}

	public ShapeFilter(CollisionType category, CollisionType mask) {
		this(noCollisionGroup, (int) category.cpCollisionType, (int) mask.cpCollisionType);
	}

	public ShapeFilter(long[] values) {
		group    =       values[0];
		category = (int) values[1];
		mask     = (int) values[2];
	}

	public long[] toArray() {
		long[] array = { group, category, mask };
		return array;
	}

	@Override
	public String toString() {
		return "ShapeFilter(group:" + group + ", category:" + category + ", mask:" + mask + ")";
	}
}

