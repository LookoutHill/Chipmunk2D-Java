package ChipmunkJava.Collisions;

import org.junit.Test;
import static org.junit.Assert.*;

public class CollisionTypeTest extends CollisionType {
    @Test public void testAny() {
		assertEquals("CollisionType.get(\"*\").name equals \"*\"", CollisionType.get("*").name, "*");
    }

    @Test public void testAdd() {
		assertEquals("CollisionType.add(\"testAdd\").name equals \"testAdd\"", CollisionType.add("testAdd").name, "testAdd");
    }

    @Test public void testGet() {
		CollisionType.add("testGet");
		assertEquals("CollisionType.get(\"testGet\").name equals \"testGet\"", CollisionType.get("testGet").name, "testGet");
    }
}

