package reading;

import builder.Child;
import fileio.ChildrenInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of children
 */

public class Children {
    private ArrayList<Child> children = new ArrayList<>();

    public Children(final List<ChildrenInputData> list) {
        for (ChildrenInputData i : list) {
            Child child = new Child.ChildBuilder(i.getId(), i.getLastName(), i.getFirstName(),
                    i.getCity(), i.getAge(), i.getGiftsPreferences(), i.getAverageScore())
                    .niceScoreHistory(i.getNiceScoreHistory())
                    .assignedBudget(i.getAssignedBudget())
                    .receivedGifts(i.getReceivedGifts())
                    .build();
            children.add(child);
        }
    }

    /**
     * getter for the children
     */
    public ArrayList<Child> getChildren() {
        return children;
    }
}
