package bc_correct;

import java.math.BigDecimal;
import java.util.Collection;

public class EasyCorrect {

	public static Double triple(Double d) {
		final Object doubleValue = Double.valueOf(d);
		final Double value = (Double) doubleValue;
		return value * 3d;
	}
	
	public static int sfDeadStroreDueToSwitchFallthroughToThrowWRONG(final int input) {
		int result;
		switch (input) {
		case 0:
			System.out.println("   - enter case 0");
			result = 4;
			break;
		case 1:
			System.out.println("   - enter case 1");
			result = 2;
			break;
		case 2:
			System.out.println("   - enter case 2");
			result = 1;
			break;
		default:
			throw new IllegalArgumentException();
		}
		return result;
	}
	
	public static String[] toStringArray(Collection<String> collection) {
		final String[] array = (String[]) collection.toArray(new String[collection.size()]);
		return array;
	}
	
	public static BigDecimal toBigDecimal(Double d) {
		final BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
		return bigDecimal;
	}
	
	public static void vaFormatStringIllegalWRONG() {
		System.out.println(String.format("   - %s > %s", "10", "9"));
	}
	
	public static boolean blueFrog(boolean value) {
		if (value == true) {
			return false;
		} else {
			return true;
		}
	}
}
