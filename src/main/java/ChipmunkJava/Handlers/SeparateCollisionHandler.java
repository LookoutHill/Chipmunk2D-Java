package ChipmunkJava.Handlers;

import ChipmunkJava.*;
import ChipmunkJava.Collisions.*;

public class SeparateCollisionHandler implements CollisionHandler {
	public static final SeparateCollisionHandler Default = new SeparateCollisionHandler();
	
	public void invoke(Arbiter arbiter, Space space) {
		throw new RuntimeException("The default SeparateCollisionHandler cannot be invoked. Did you mean to subclass SeparateCollisionHandler?");
	}
}

