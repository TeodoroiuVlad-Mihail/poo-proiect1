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


    public Write(final Children c){
        children = c;

    }

    public JSONObject returnChildren() {

        JSONArray childArray = new JSONArray();

        for (int i = 0; i < children.children.size(); i++) {
            Child child = children.children.get(i);
            childArray.add(child.toString());
        }

        JSONObject annualChildren= new JSONObject();
        annualChildren.put("Children", childArray);
        return annualChildren;
    }
}
