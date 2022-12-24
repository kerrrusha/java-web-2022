package com.kerrrusha.lab234.validator;

import com.kerrrusha.lab234.validator.auth.RegisterValidator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;

public abstract class AbstractValidator {

	protected static final String DATABASE_ERROR = "Something is wrong with the server. We will definitely fix this, but for now, please try again.";

	private final Collection<Optional<String>> possibleErrors;

	public AbstractValidator() {
		possibleErrors = new HashSet<>();
	}

	protected abstract void validate();

	public Collection<String> getErrors() {
		clearPossibleErrors();
		validate();
		return getErrorPool();
	}

	protected final Collection<String> getErrorPool() {
		return possibleErrors.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(toCollection(HashSet<String>::new));
	}

	protected final void clearPossibleErrors() {
		possibleErrors.clear();
	}

	protected final void addPossibleError(Optional<String> error) {
		possibleErrors.add(error);
	}

    public boolean onlyErrorAlreadyExists() {
		return possibleErrors.size() == 1 && possibleErrors.toArray()[0].equals(RegisterValidator.PHONE_ALREADY_EXISTS);
    }
}
