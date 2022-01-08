package commands;

/**
 * Supported commands
 */
public enum CommandType {
    CALCULATE_KID_BUDGET("calculateKidBudget"),
    GIVE_CHILDREN_GIFTS("giveChildrenGifts"),
    REMOVE_YOUNG_ADULTS("removeYoungAdults"),
    GROW_CHILDREN("growChildren");

    public final String text;

    CommandType(String text) {
        this.text = text;
    }

    public static CommandType fromString(String text) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.text.equalsIgnoreCase(text)) {
                return commandType;
            }
        }
        return null;
    }
}
