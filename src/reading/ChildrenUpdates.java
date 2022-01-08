package reading;

import fileio.ChildrenUpdatesInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of children updates
 */

public class ChildrenUpdates {
    private ArrayList<ChildUpdates> childrenUpdates = new ArrayList<>();

    public ChildrenUpdates(final List<ChildrenUpdatesInputData> list) {
        for (ChildrenUpdatesInputData i : list) {
            ChildUpdates child = new ChildUpdates(i.getId(), i.getNiceScore(),
                    i.getGiftsPreferences());
            childrenUpdates.add(child);
        }
    }

    /**
     * getter for the array of children updates :/
     */
    public ArrayList<ChildUpdates> getChildrenUpdates() {
        return childrenUpdates;
    }
}
