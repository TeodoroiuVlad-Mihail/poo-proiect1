package reading;

import fileio.ChangesInputData;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of children
 */

public class Changes {
    public ArrayList<Change> changes = new ArrayList<>();

    public Changes(final List<ChangesInputData> list) {
        int j = 0;
        for (ChangesInputData i : list) {
            Change change = new Change(i.getNewSantaBudget(), i.getNewGifts(), i.getNewChildren(), i.getChildrenUpdates());
            changes.add(change);
            j++;
        }
    }

}