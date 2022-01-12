package writing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import builder.Child;
import reading.Children;

import java.util.ArrayList;

public class Write {
    private Children children;

    public Write(final Children c) {
        children = c;
    }
    /**
     * Transforms a child instance into a json object then puts it in the jsonArray result
     */
    public JSONObject returnChildren() {

        JSONArray childArray = new JSONArray();

        for (int i = 0; i < children.getChildren().size(); i++) {
            Child child = children.getChildren().get(i);
            Child copyChild = new Child.ChildBuilder(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    new ArrayList<>(child.getGiftsPreferences()), child.getAverageScore())
                    .niceScoreHistory(new ArrayList<>(child.getNiceScoreHistory()))
                    .assignedBudget(child.getAssignedBudget())
                    .receivedGifts(new ArrayList<>(child.getReceivedGifts()))
                    .build();
            childArray.add(copyChild);
        }


        JSONObject annualChildren = new JSONObject();
        annualChildren.put("children", childArray);
        return annualChildren;
    }
}
