package bc;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class EasyTest {

	@Test
	public void testTriple() {
		assertEquals(Double.valueOf(3.0), Easy.triple(1.0));
	}

	@Test
	public void testSwitch() throws Exception {
		assertEquals(1, Easy.sfDeadStroreDueToSwitchFallthroughToThrowWRONG(2));
	}

	@Test
	public void testToArray() throws Exception {
		// given
		final Collection<String> fruits = new ArrayList<String>();
		fruits.add("apple");
		fruits.add("banana");
		fruits.add("cherry");
		assertArrayEquals(new String[] { "apple", "banana", "cherry" }, Easy.toStringArray(fruits));
	}

	@Test
	public void testToBigDecimal() throws Exception {
		assertEquals(BigDecimal.valueOf(3.14), Easy.toBigDecimal(3.14));
	}

	@Test
	public void testPlusOne() throws Exception {
		assertEquals(2, Easy.plusOne(BigDecimal.ONE));
	}

}
