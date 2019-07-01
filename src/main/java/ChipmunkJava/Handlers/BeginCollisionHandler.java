package ChipmunkJava.Handlers;

import ChipmunkJava.*;
import ChipmunkJava.Collisions.*;

public class BeginCollisionHandler implements CollisionHandler {
	public static final BeginCollisionHandler Default = new BeginCollisionHandler();
	
	public boolean invoke(Arbiter arbiter, Space space) {
		throw new RuntimeException("The default BeginCollisionHandler cannot be invoked. Did you mean to subclass BeginCollisionHandler?");
	}
}

