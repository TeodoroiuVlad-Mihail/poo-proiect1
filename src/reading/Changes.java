package reading;

import fileio.Change;
import fileio.ChangesInputData;
import fileio.ChildrenInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of children
 */

public class Changes {
    public ArrayList<Change> changes = new ArrayList<>();
    public Changes(final List<ChangesInputData> list) {
        for (ChangesInputData i : list) {
            Change change = new Change(i.getNewSantaBudget(), i.getNewGifts(), i.getNewChildren(), i.getChildrenUpdates());
            changes.add(change);
        }
    }

}