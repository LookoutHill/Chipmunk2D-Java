package ChipmunkJava.Handlers;

import ChipmunkJava.*;
import ChipmunkJava.Collisions.*;

public class PreSolveCollisionHandler implements CollisionHandler {
	public static final PreSolveCollisionHandler Default = new PreSolveCollisionHandler();
	
	public boolean invoke(Arbiter arbiter, Space space) {
		throw new RuntimeException("The default PreSolveCollisionHandler cannot be invoked. Did you mean to subclass PreSolveCollisionHandler?");
	}
}

