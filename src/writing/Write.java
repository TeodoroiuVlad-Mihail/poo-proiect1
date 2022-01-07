package writing;

import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reading.Child;
import reading.Children;

import java.util.ArrayList;
import java.util.Map;

public class Write {
    Children children;


    public Write(final Children c) {
        children = c;
    }

    public JSONObject returnChildren() {

        JSONArray childArray = new JSONArray();

        for (int i = 0; i < children.children.size(); i++) {
            Child child = children.children.get(i);
            Child copyChild = new Child(child.getId(), child.getLastName(), child.getFirstName(), child.getCity(),
                    child.getAge(), new ArrayList<>(child.getGiftsPreferences()), child.getAverageScore(),
                    new ArrayList<>(child.getNiceScoreHistory()), child.getAssignedBudget(), new ArrayList<>(child.getReceivedGifts()));
            childArray.add(copyChild);
        }


        JSONObject annualChildren = new JSONObject();
        annualChildren.put("children", childArray);
        return annualChildren;
    }
}
