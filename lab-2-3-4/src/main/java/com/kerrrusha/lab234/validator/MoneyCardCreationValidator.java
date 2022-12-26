package com.kerrrusha.lab234.validator;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;

public class MoneyCardCreationValidator extends AbstractValidator {

    private static final String INVALID_NAME_LENGTH = "Card name must be at least 3 symbols length.";
    private static final String NAME_CONTAINS_SPACES_ERROR = "Name cannot contain spaces.";
    private static final String NAME_CONTAINS_NON_LETTER_SYMBOLS = "Card name should consist only of english letters.";
    private static final String NAME_IS_NULL = "Something is wrong while submitting money card name to the server. We will definitely fix this, but for now, please try again.";

    private final String moneyAccountName;

    public MoneyCardCreationValidator(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
    }

    @Override
    protected void validate() {
        addPossibleError(checkIfFieldIsNull(moneyAccountName, NAME_IS_NULL));
        addPossibleError(validateLength());
        addPossibleError(validateSpaces());
        addPossibleError(validateNonLetterSymbols());
    }

    private Optional<String> validateLength() {
        return moneyAccountName.length() < 3 ? Optional.of(INVALID_NAME_LENGTH) : Optional.empty();
    }

    private Optional<String> validateSpaces() {
        return moneyAccountName.contains(" ") ? Optional.of(NAME_CONTAINS_SPACES_ERROR) : Optional.empty();
    }

    private Optional<String> validateNonLetterSymbols() {
        return Pattern.compile("[^a-zA-Z ]").matcher(moneyAccountName).find()
                ? Optional.of(NAME_CONTAINS_NON_LETTER_SYMBOLS)
                : Optional.empty();
    }
}
