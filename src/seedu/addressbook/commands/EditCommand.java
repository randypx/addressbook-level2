package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Edits the person identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS [t/tag]...\n"
            + "Example: " + COMMAND_WORD
            + " 1 John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Person edited: %1$s";

    private int targetIndex;
    private final Person toEdit;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(int index, String name,
                       String phone, boolean isPhonePrivate,
                       String email, boolean isEmailPrivate,
                       String address, boolean isAddressPrivate,
                       Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toEdit = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
        targetIndex = index - DISPLAYED_INDEX_OFFSET;
    }

    @Override
    public CommandResult execute() {
        try {
            addressBook.editPerson(targetIndex, toEdit);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, toEdit));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

}
