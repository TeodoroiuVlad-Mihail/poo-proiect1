package commands;

import fileio.ChildrenInputData;
import builder.Child;
import reading.Children;

import java.util.ArrayList;

public class AddChildren implements AnnualUpdateCommand {
    private Children children;
    private ArrayList<ChildrenInputData> newChildren;

    public AddChildren(final Children children, final ArrayList<ChildrenInputData> newChildren) {
        this.children = children;
        this.newChildren = newChildren;
    }

    /**
     * adds children to the children list
     */
    public void execute() {
        for (int j = 0; j < newChildren.size(); j++) {
            ChildrenInputData newChild = newChildren.get(j);
            int ok = 0;
            for (int i = 0; i < children.getChildren().size(); i++) {
                if (newChild.getId() < children.getChildren().get(i).getId()) {
                    children.getChildren().add(i, new Child.ChildBuilder(newChild.getId(),
                            newChild.getLastName(), newChild.getFirstName(), newChild.getCity(),
                            newChild.getAge(), newChild.getGiftsPreferences(),
                            newChild.getAverageScore())
                            .niceScoreHistory(newChild.getNiceScoreHistory())
                            .assignedBudget(newChild.getAssignedBudget())
                            .receivedGifts(newChild.getReceivedGifts())
                            .build());
                    ok = 1;
                    i = children.getChildren().size();
                }
            }
            if (ok == 0) {
                children.getChildren().add(new Child.ChildBuilder(newChild.getId(),
                                newChild.getLastName(), newChild.getFirstName(),
                                newChild.getCity(), newChild.getAge(),
                                newChild.getGiftsPreferences(), newChild.getAverageScore())
                                .niceScoreHistory(newChild.getNiceScoreHistory())
                                .assignedBudget(newChild.getAssignedBudget())
                                .receivedGifts(newChild.getReceivedGifts())
                                .build());
            }
        }

    }
}
