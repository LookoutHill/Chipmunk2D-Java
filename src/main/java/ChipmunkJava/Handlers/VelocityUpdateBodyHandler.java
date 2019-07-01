package ChipmunkJava.Handlers;

import ChipmunkJava.*;

public class VelocityUpdateBodyHandler implements UpdateBodyHandler {
	public static final VelocityUpdateBodyHandler Default = new VelocityUpdateBodyHandler();
	
	public void invoke(Vect gravity, double damping, double dt) {
		throw new RuntimeException("The default VelocityUpdateBodyHandler cannot be invoked. Did you mean to subclass VelocityUpdateBodyHandler?");
	}
}

