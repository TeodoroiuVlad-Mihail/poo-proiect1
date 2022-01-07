package reading;

import fileio.ChildrenUpdatesInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of children updates
 */

public class ChildrenUpdates {
    public ArrayList<ChildUpdates> childrenUpdates = new ArrayList<>();

    public ChildrenUpdates(final List<ChildrenUpdatesInputData> list) {
        for (ChildrenUpdatesInputData i : list) {
            ChildUpdates child = new ChildUpdates(i.getId(), i.getNiceScore(), i.getGiftsPreferences());
            childrenUpdates.add(child);
        }
    }

}
