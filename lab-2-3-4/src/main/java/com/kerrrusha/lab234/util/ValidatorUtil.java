package com.kerrrusha.lab234.util;

import java.util.Optional;

public class ValidatorUtil {

	public static Optional<String> checkIfFieldIsNull(Object field, final String errorMessage) {
		return field == null ? Optional.of(errorMessage) : Optional.empty();
	}
}
