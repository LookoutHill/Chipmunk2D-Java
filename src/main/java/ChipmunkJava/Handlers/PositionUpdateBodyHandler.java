package ChipmunkJava.Handlers;

public class PositionUpdateBodyHandler implements UpdateBodyHandler {
	public static final PositionUpdateBodyHandler Default = new PositionUpdateBodyHandler();
	
	public void invoke(double dt) {
		throw new RuntimeException("The default PositionUpdateBodyHandler cannot be invoked. Did you mean to subclass PositionUpdateBodyHandler?");
	}
}

