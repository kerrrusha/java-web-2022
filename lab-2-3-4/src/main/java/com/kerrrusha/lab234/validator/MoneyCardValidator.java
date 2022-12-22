package com.kerrrusha.lab234.validator;

import java.util.Collection;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;

public class MoneyCardValidator extends AbstractValidator {

    private static final String INVALID_NAME_LENGTH = "Card name must be at least 3 symbols length.";
    private static final String NAME_CONTAINS_SPACES_ERROR = "Name cannot contain spaces.";
    private static final String NAME_CONTAINS_NON_LETTER_SYMBOLS = "Card name should consist only of english letters.";
    private static final String NAME_IS_NULL = "Something is wrong while submitting money card name to the server. We will definitely fix this, but for now, please try again.";

    private final String name;

    public MoneyCardValidator(String name) {
        this.name = name;
    }

    @Override
    public Collection<String> getErrors() {
        clearPossibleErrors();
        validate();
        return getErrorPool();
    }

    @Override
    protected void validate() {
        addPossibleError(checkIfFieldIsNull(name, NAME_IS_NULL));
        addPossibleError(validateLength());
        addPossibleError(validateSpaces());
        addPossibleError(validateNonLetterSymbols());
    }

    private Optional<String> validateLength() {
        return name.length() < 3 ? Optional.of(INVALID_NAME_LENGTH) : Optional.empty();
    }

    private Optional<String> validateSpaces() {
        return name.contains(" ") ? Optional.of(NAME_CONTAINS_SPACES_ERROR) : Optional.empty();
    }

    private Optional<String> validateNonLetterSymbols() {
        return Pattern.compile("[^a-zA-Z ]").matcher(name).find()
                ? Optional.of(NAME_CONTAINS_NON_LETTER_SYMBOLS)
                : Optional.empty();
    }
}
