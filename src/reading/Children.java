package reading;

import fileio.ChildrenInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of children
 */

public class Children {
    ArrayList<Child> children = new ArrayList<>();
    public Children(final List<ChildrenInputData> list) {
        for (ChildrenInputData i : list) {
            Child child = new Child(i.getId(), i.getLastName(), i.getFirstName(), i.getAge(),
                    i.getCity(), i.getNiceScore(), i.getGiftsPreferences());
            children.add(child);
        }
    }
}
