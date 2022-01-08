package commands;

import common.Constants;
import reading.Children;

public class growChildren implements AnnualUpdateCommand {
    private Children children;

    /**
     * increments the children's age
     */
    public growChildren(final Children children) {
        this.children = children;
    }

    public void execute() {
        for (int i = 0; i < children.getChildren().size(); i++) {
            int newAge = children.getChildren().get(i).getAge() + 1;
            children.getChildren().get(i).setAge(newAge);
        }
    }
}