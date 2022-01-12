package commands;

/**
 * Supported commands
 */
public enum CommandType {
    ADD_CHILDREN("AddChildren"),
    CALCULATE_AVERAGE_SCORE("CalculateAverageScore"),
    CALCULATE_CHILDREN_BUDGET("CalculateChildrenBudget"),
    GIVE_CHILDREN_GIFTS("GiveChildrenGifts"),
    GROW_CHILDREN("GrowChildren"),
    REMOVE_YOUNG_ADULTS("RemoveYoungAdults"),
    UPDATE_CHILDREN("UpdateChildren");

    private final String text;

    CommandType(final String text) {
        this.text = text;
    }

    /**
     * returns a string into a command type through its name
     */
    public static CommandType fromString(final String text) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.text.equalsIgnoreCase(text)) {
                return commandType;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }
}
