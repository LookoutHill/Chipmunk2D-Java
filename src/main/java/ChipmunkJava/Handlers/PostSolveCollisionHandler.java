package ChipmunkJava.Handlers;

import ChipmunkJava.*;
import ChipmunkJava.Collisions.*;

public class PostSolveCollisionHandler implements CollisionHandler {
	public static final PostSolveCollisionHandler Default = new PostSolveCollisionHandler();
	
	public void invoke(Arbiter arbiter, Space space) {
		throw new RuntimeException("The default PostSolveCollisionHandler cannot be invoked. Did you mean to subclass PostSolveCollisionHandler?");
	}
}

