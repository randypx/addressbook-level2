package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;


public class Contact {
    public final String value;
    private boolean isPrivate;
    private final String regex;

    public Contact(String value, boolean isPrivate, String regex, String message) throws IllegalValueException{
        this.regex = regex;
        this.isPrivate = isPrivate;
        if (!isValid(value)) {
            throw new IllegalValueException(message);
        }
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    boolean isValid(String test) {
        return test.matches(regex);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
