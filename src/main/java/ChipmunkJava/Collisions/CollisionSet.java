package ChipmunkJava.Collisions;

import ChipmunkJava.*;
import ChipmunkJava.Handlers.*;

import java.util.Map;
import java.util.Hashtable;

public class CollisionSet {
	private native static void init();

	static {
		System.loadLibrary("chipmunkJni");
		init();
	}

	private final long cpCollisionHandlerPtr;

	protected CollisionSet() {
		cpCollisionHandlerPtr = 0;

		String className = this.getClass().getName();
		if(className == "ChipmunkJava.Collisions.CollisionSetTest") return;

		throw new RuntimeException("This constructor allows testing classes to access non-public members. It cannot be called from the class: " + className);
	}

	private static Map<Long,   CollisionSet> _collisionSetsByPtr = new Hashtable<Long,   CollisionSet>();
	private static Map<String, CollisionSet> _collisionSetsByKey = new Hashtable<String, CollisionSet>();

	private native static long _new(Space space, CollisionType ctypeA, CollisionType ctypeB);

	private CollisionSet(Space space, CollisionType ctypeA, CollisionType ctypeB) {
		cpCollisionHandlerPtr = _new(space, ctypeA, ctypeB);
		_collisionSetsByPtr.put(cpCollisionHandlerPtr, this);

		String key = getKey(space, ctypeA, ctypeB);
		_collisionSetsByKey.put(key, this);
	}

	private CollisionSet(Space space, String _ctypeA, String _ctypeB) {
		CollisionType ctypeA = CollisionType.get(_ctypeA);
		CollisionType ctypeB = CollisionType.get(_ctypeB);

		cpCollisionHandlerPtr = _new(space, ctypeA, ctypeB);
		_collisionSetsByPtr.put(cpCollisionHandlerPtr, this);

		String key = getKey(space, ctypeA, ctypeB);
		_collisionSetsByKey.put(key, this);
	}

	public static CollisionSet get(Space space, CollisionType ctypeA, CollisionType ctypeB) {
		String       key  = getKey(space, ctypeA, ctypeB);
		CollisionSet cset = _collisionSetsByKey.getOrDefault(key, null);
		if(cset != null) return cset;
		else             return new CollisionSet(space, ctypeA, ctypeB);
	}

	public static CollisionSet get(Space space, String _ctypeA, String _ctypeB) {
		CollisionType ctypeA = CollisionType.get(_ctypeA);
		CollisionType ctypeB = CollisionType.get(_ctypeB);

		String       key  = getKey(space, ctypeA, ctypeB);
		CollisionSet cset = _collisionSetsByKey.getOrDefault(key, null);
		if(cset != null) return cset;
		else             return new CollisionSet(space, ctypeA, ctypeB);
	}

	public static CollisionSet get(long cpCollisionHandlerPtr) {
		CollisionSet cset = _collisionSetsByPtr.getOrDefault(cpCollisionHandlerPtr, null);
		if(cset != null) return cset;

		throw new IllegalArgumentException("Invalid cpCollisionHandlerPtr used to resolve CollisionSet: " + cpCollisionHandlerPtr);
	}

	private static String getKey(Space space, CollisionType ctypeA, CollisionType ctypeB) {
		return space.toString() + ctypeA.name + ctypeB.name;
	}

	private static String getKey(Space space, String _ctypeA, String _ctypeB) {
		CollisionType ctypeA = CollisionType.get(_ctypeA);
		CollisionType ctypeB = CollisionType.get(_ctypeB);

		return space.toString() + ctypeA.name + ctypeB.name;
	}

	private BeginCollisionHandler     beginCollisionHandler     = null;
	private PreSolveCollisionHandler  preSolveCollisionHandler  = null;
	private PostSolveCollisionHandler postSolveCollisionHandler = null;
	private SeparateCollisionHandler  separateCollisionHandler  = null;

	private static boolean _invokeBeginCollisionHandler(long cpArbiterPtr, long cpSpacePtr, long cpCollisionHandlerPtr) {
		CollisionSet cset    = get(cpCollisionHandlerPtr);
		Arbiter      arbiter = new Arbiter(cpArbiterPtr);
		Space        space   = Space.get(cpSpacePtr);
		return cset.beginCollisionHandler.invoke(arbiter, space);
	}

	private static boolean _invokePreSolveCollisionHandler(long cpArbiterPtr, long cpSpacePtr, long cpCollisionHandlerPtr) {
		CollisionSet cset    = get(cpCollisionHandlerPtr);
		Arbiter      arbiter = new Arbiter(cpArbiterPtr);
		Space        space   = Space.get(cpSpacePtr);
		return cset.preSolveCollisionHandler.invoke(arbiter, space);
	}

	private static void _invokePostSolveCollisionHandler(long cpArbiterPtr, long cpSpacePtr, long cpCollisionHandlerPtr) {
		CollisionSet cset    = get(cpCollisionHandlerPtr);
		Arbiter      arbiter = new Arbiter(cpArbiterPtr);
		Space        space   = Space.get(cpSpacePtr);
		cset.postSolveCollisionHandler.invoke(arbiter, space);
	}

	private static void _invokeSeparateCollisionHandler(long cpArbiterPtr, long cpSpacePtr, long cpCollisionHandlerPtr) {
		CollisionSet cset    = get(cpCollisionHandlerPtr);
		Arbiter      arbiter = new Arbiter(cpArbiterPtr);
		Space        space   = Space.get(cpSpacePtr);
		cset.separateCollisionHandler.invoke(arbiter, space);
	}

	public static void setCollisionHandler(Space space, CollisionType ctypeA, CollisionType ctypeB, CollisionHandler handler) {
		CollisionSet cset = get(space, ctypeA, ctypeB);
		cset.setCollisionHandler(handler);
	}

	public static void setCollisionHandler(Space space, String _ctypeA, String _ctypeB, CollisionHandler handler) {
		CollisionType ctypeA = CollisionType.get(_ctypeA);
		CollisionType ctypeB = CollisionType.get(_ctypeB);

		CollisionSet cset = get(space, ctypeA, ctypeB);
		cset.setCollisionHandler(handler);
	}

	public static void resetCollisionHandler(Space space, CollisionType ctypeA, CollisionType ctypeB, CollisionHandler handler) {
		CollisionSet cset = get(space, ctypeA, ctypeB);
		cset.resetCollisionHandler(handler);
	}

	public static void resetCollisionHandler(Space space, String _ctypeA, String _ctypeB, CollisionHandler handler) {
		CollisionType ctypeA = CollisionType.get(_ctypeA);
		CollisionType ctypeB = CollisionType.get(_ctypeB);

		CollisionSet cset = get(space, ctypeA, ctypeB);
		cset.resetCollisionHandler(handler);
	}

	public void setCollisionHandler(CollisionHandler handler) {
		if     (handler instanceof BeginCollisionHandler)     setBeginCollisionHandler(    (BeginCollisionHandler)     handler);
		else if(handler instanceof PreSolveCollisionHandler)  setPreSolveCollisionHandler( (PreSolveCollisionHandler)  handler);
		else if(handler instanceof PostSolveCollisionHandler) setPostSolveCollisionHandler((PostSolveCollisionHandler) handler);
		else if(handler instanceof SeparateCollisionHandler)  setSeparateCollisionHandler( (SeparateCollisionHandler)  handler);
		else                                                  throw new IllegalArgumentException("Unknown handler type: " + handler.getClass().getName());
	}

	public void resetCollisionHandler(CollisionHandler handler) {
		if     (handler instanceof BeginCollisionHandler)     resetBeginCollisionHandler(    (BeginCollisionHandler)     handler);
		else if(handler instanceof PreSolveCollisionHandler)  resetPreSolveCollisionHandler( (PreSolveCollisionHandler)  handler);
		else if(handler instanceof PostSolveCollisionHandler) resetPostSolveCollisionHandler((PostSolveCollisionHandler) handler);
		else if(handler instanceof SeparateCollisionHandler)  resetSeparateCollisionHandler( (SeparateCollisionHandler)  handler);
		else                                                  throw new IllegalArgumentException("Unknown handler type: " + handler.getClass().getName());
	}

	private native void _disableBeginCollisionHandler();
	private native void _enableBeginCollisionHandler();
	private native void _disablePreSolveCollisionHandler();
	private native void _enablePreSolveCollisionHandler();
	private native void _disablePostSolveCollisionHandler();
	private native void _enablePostSolveCollisionHandler();
	private native void _disableSeparateCollisionHandler();
	private native void _enableSeparateCollisionHandler();

	private void setBeginCollisionHandler(BeginCollisionHandler handler) {
		beginCollisionHandler = handler;
		_enableBeginCollisionHandler();
	}

	private void resetBeginCollisionHandler(BeginCollisionHandler handler) {
		beginCollisionHandler = null;
		_disableBeginCollisionHandler();
	}

	private void setPreSolveCollisionHandler(PreSolveCollisionHandler handler) {
		preSolveCollisionHandler = handler;
		_enablePreSolveCollisionHandler();
	}

	private void resetPreSolveCollisionHandler(PreSolveCollisionHandler handler) {
		preSolveCollisionHandler = null;
		_disablePreSolveCollisionHandler();
	}

	private void setPostSolveCollisionHandler(PostSolveCollisionHandler handler) {
		postSolveCollisionHandler = handler;
		_enablePostSolveCollisionHandler();
	}

	private void resetPostSolveCollisionHandler(PostSolveCollisionHandler handler) {
		postSolveCollisionHandler = null;
		_disablePostSolveCollisionHandler();
	}

	private void setSeparateCollisionHandler(SeparateCollisionHandler handler) {
		separateCollisionHandler = handler;
		_enableSeparateCollisionHandler();
	}

	private void resetSeparateCollisionHandler(SeparateCollisionHandler handler) {
		separateCollisionHandler = null;
		_disableSeparateCollisionHandler();
	}
}

