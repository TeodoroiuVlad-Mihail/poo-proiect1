package updating;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reading.Child;
import reading.Children;
import reading.Gift;
import reading.Gifts;

import java.util.ArrayList;
import java.util.List;

public class UpdateChildren {

    Children children;
    Gifts gifts;


    public UpdateChildren(final Children c, final Gifts g){
        children = c;
        gifts = g;
    }

    public Children CalculateKidBudget(Children children, double santaBudget) {

        double niceScoreSum = 0;


        for (int i = 0; i < children.children.size(); i++) {
            Child child = children.children.get(i);
            niceScoreSum = niceScoreSum + child.getAverageScore();
        }

        double budgetUnit = santaBudget / niceScoreSum;

        for (int i = 0; i < children.children.size(); i++) {

            double averageScore = children.children.get(i).getAverageScore();
            double assignedBudget = averageScore * budgetUnit;
            children.children.get(i).setAssignedBudget(assignedBudget);
        }

        return children;
    }

    public Children GiveChildrenGifts(Children children, Gifts gifts) {

        for (int i = 0; i < children.children.size(); i++) {

            double remainingBudget = children.children.get(i).getAssignedBudget();
            List<Gift> giftsList = new ArrayList<>();
            List<String> giftsPreferences = children.children.get(i).getGiftsPreferences();
            for(int j = 0; j < giftsPreferences.size(); j++){
                String giftPreference = giftsPreferences.get(j);
                for(int k = 0; k < gifts.gifts.size(); k++){
                    double giftPrice =  gifts.gifts.get(k).getPrice();
                    String giftCategory = gifts.gifts.get(k).getCategory();
                    if(remainingBudget >= giftPrice && giftPreference.compareTo(giftCategory) == 0){
                        remainingBudget = remainingBudget - giftPrice;
                        giftsList.add(gifts.gifts.get(k));
                    }
                }
            }

            children.children.get(i).setReceivedGifts(giftsList);
        }

        return children;
    }

    public Children GrowChildren(Children children) {
        for (int i = 0; i < children.children.size(); i++) {
            int newAge = children.children.get(i).getAge()+ 1;
            children.children.get(i).setAge(newAge);
        }

        return children;
    }

    public Children RemoveYoungAdults(Children children) {

        for (int i = 0; i < children.children.size(); i++) {
            if(children.children.get(i).getAge() > 18)
                children.children.remove(i);
        }
        return children;
    }

}
