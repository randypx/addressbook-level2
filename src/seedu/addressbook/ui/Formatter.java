package seedu.addressbook.ui;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.common.Messages.*;

public class Formatter {

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    public static String formatWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        return format(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                DIVIDER);
    }

    public static String formatGoodbyeMessage() {
        return format(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    public static String formatInitFailedMessage() {
        return format(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    /** Formatss message(s) for the user */
    public static String format(String... message) {
        StringBuilder output = new StringBuilder();
        for (String m : message) {
            String str = LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + "\n";
            output.append(str);
        }
        return output.toString();
    }

    /**
     * Formaats the result of a command execution.
     */
    public static String formatResult(String feedback) {
        return format(feedback, DIVIDER);
    }


    /**
     * Formats a list of persons to the user, formatted as an indexed list.
     */
    public static String formatList(List<? extends ReadOnlyPerson> persons) {
        int displayIndex =  0 +DISPLAYED_INDEX_OFFSET;
        final StringBuilder listFormatter = new StringBuilder();
        for (ReadOnlyPerson person : persons) {
            String personAsText = person.getAsTextHidePrivate();
            String line = String.format(MESSAGE_INDEXED_LIST_ITEM, displayIndex, personAsText) + "\n";
            listFormatter.append(line);
        }
        return format(listFormatter.toString());
    }
}
