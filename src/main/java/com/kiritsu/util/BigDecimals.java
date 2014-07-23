package com.kiritsu.util;

import java.math.BigDecimal;

public class BigDecimals {

	public static final boolean isNegative(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO) < 0;
	}

	public static final boolean isPositiveOrZero(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO) >= 0;
	}

	public static final boolean isPositive(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO) > 0;
	}
}
