package bc_correct;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import bc.Easy;

public class EasyCorrectTest {

	@Test
	public void testToBigDecimal() throws Exception {
		assertEquals(BigDecimal.valueOf(3.14), EasyCorrect.toBigDecimal(3.14));
	}

}
