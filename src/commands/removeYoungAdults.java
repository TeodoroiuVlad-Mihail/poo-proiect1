package commands;

import common.Constants;
import reading.Children;

public class removeYoungAdults implements AnnualUpdateCommand{
    private Children children;

    /**
     * removes children over 18 from the list
     */
    public removeYoungAdults(final Children children) {
        this.children = children;
    }

    public void execute(){
        for (int i = 0; i < children.getChildren().size(); i++) {
            if (children.getChildren().get(i).getAge() > Constants.TEENLIMIT) {
                children.getChildren().remove(i);
                children.getChildren().trimToSize();
                i = i - 1;
            }
        }
    }
}
