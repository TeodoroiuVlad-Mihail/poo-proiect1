package commands;

import reading.Children;

public class GrowChildren implements AnnualUpdateCommand {
    private Children children;


    public GrowChildren(final Children children) {
        this.children = children;
    }

    /**
     * increments the children's age
     */
    public void execute() {
        for (int i = 0; i < children.getChildren().size(); i++) {
            int newAge = children.getChildren().get(i).getAge() + 1;
            children.getChildren().get(i).setAge(newAge);
        }
    }
}
