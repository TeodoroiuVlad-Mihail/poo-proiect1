package commands;

import fileio.ChildrenUpdatesInputData;
import builder.Child;
import reading.Children;

import java.util.ArrayList;

public class UpdateChildren implements AnnualUpdateCommand {
    private Children children;
    private ArrayList<ChildrenUpdatesInputData> childrenUpdates;

    public UpdateChildren(final Children children,
                          final ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        this.children = children;
        this.childrenUpdates = childrenUpdates;
    }

    /**
     * updates children
     */
    public void execute() {
        for (int i = 0; i < children.getChildren().size(); i++) {
            Child child = children.getChildren().get(i);
            for (int j = 0; j < childrenUpdates.size(); j++) {

                ChildrenUpdatesInputData childUpdates = childrenUpdates.get(j);
                if (child.getId() == childUpdates.getId()) {
                    //update their scores

                    if (childUpdates.getNiceScore() >= 0) {
                        child.getNiceScoreHistory().add(childUpdates.getNiceScore());
                    }
                    //now for the gift preferences

                    //delete duplicates
                    for (int k = 0; k < child.getGiftsPreferences().size(); k++) {
                        for (int l = 0; l < childUpdates.getGiftsPreferences().size(); l++) {
                            if (child.getGiftsPreferences().get(k).compareTo(
                                    childUpdates.getGiftsPreferences().get(l)) == 0) {
                                child.getGiftsPreferences().remove(k);
                                k = k - 1;
                                l = childUpdates.getGiftsPreferences().size();
                            }
                        }
                    }

                    //add the gifts
                    for (int k = childUpdates.getGiftsPreferences().size() - 1; k >= 0; k--) {
                        child.getGiftsPreferences().add(0,
                                childUpdates.getGiftsPreferences().get(k));
                    }

                    //because of test12, apparently in the gift preferences updates
                    //there can be duplicates, thanks guys, very fun
                    for (int k = 0; k < child.getGiftsPreferences().size(); k++) {
                        for (int l = 0; l < child.getGiftsPreferences().size(); l++) {
                            if (child.getGiftsPreferences().get(k).compareTo(
                                    child.getGiftsPreferences().get(l)) == 0 && k != l) {
                                child.getGiftsPreferences().remove(l);
                                l = l - 1;
                            }
                        }
                    }


                }

            }
        }
    }
}
