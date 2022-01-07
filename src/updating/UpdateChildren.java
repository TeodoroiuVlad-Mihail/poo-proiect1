package updating;

import fileio.ChildrenInputData;
import fileio.ChildrenUpdatesInputData;
import reading.*;

import java.util.ArrayList;
import java.util.List;

public class UpdateChildren {

    Children children;
    Gifts gifts;


    public UpdateChildren(final Children c, final Gifts g) {
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
            for (int j = 0; j < giftsPreferences.size(); j++) {
                String giftPreference = giftsPreferences.get(j);
                double mostExpensive = 1000000000;
                Gift cheapestGift = null; //placeholder, needs to be initialized

                //search for the cheapest gift
                for (int k = 0; k < gifts.gifts.size(); k++) {
                    double giftPrice = gifts.gifts.get(k).getPrice();
                    String giftCategory = gifts.gifts.get(k).getCategory();
                    if (remainingBudget >= giftPrice && giftPreference.compareTo(giftCategory) == 0 && giftPrice < mostExpensive) {
                        mostExpensive = giftPrice;
                        cheapestGift = gifts.gifts.get(k);
                    }

                }
                if (cheapestGift != null) {
                    giftsList.add(cheapestGift);
                    remainingBudget = remainingBudget - cheapestGift.getPrice();
                }

            }

            children.children.get(i).setReceivedGifts(giftsList);
        }

        return children;
    }

    public Children GrowChildren(Children children) {
        for (int i = 0; i < children.children.size(); i++) {
            int newAge = children.children.get(i).getAge() + 1;
            children.children.get(i).setAge(newAge);
        }

        return children;
    }

    public Children RemoveYoungAdults(Children children) {

        for (int i = 0; i < children.children.size(); i++) {
            if (children.children.get(i).getAge() > 18) {
                children.children.remove(i);
                children.children.trimToSize();
                i = i - 1;
            }
        }
        return children;
    }

    public Children UpdateChildren(Children children, ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        for (int i = 0; i < children.children.size(); i++) {
            Child child = children.children.get(i);
            for (int j = 0; j < childrenUpdates.size(); j++) {

                ChildrenUpdatesInputData childUpdates = childrenUpdates.get(j);
                if (child.getId() == childUpdates.getId()) {
                    //update their scores

                    if (childUpdates.getNiceScore() >= 0)
                        child.getNiceScoreHistory().add(childUpdates.getNiceScore());
                    //now for the gift preferences

                    //delete duplicates
                    for (int k = 0; k < child.getGiftsPreferences().size(); k++) {
                        for (int l = 0; l < childUpdates.getGiftsPreferences().size(); l++) {
                            if (child.getGiftsPreferences().get(k).compareTo(childUpdates.getGiftsPreferences().get(l)) == 0) {
                                child.getGiftsPreferences().remove(k);
                                k = k - 1;
                                l = childUpdates.getGiftsPreferences().size();
                            }
                        }
                    }

                    //add the gifts
                    for (int k = childUpdates.getGiftsPreferences().size() - 1; k >= 0; k--) {
                        child.getGiftsPreferences().add(0, childUpdates.getGiftsPreferences().get(k));
                    }

                    //because of test12, apparently in the gift preferences updates there can be duplicates, thanks guys, very fun
                    for (int k = 0; k < child.getGiftsPreferences().size(); k++) {
                        for (int l = 0; l < child.getGiftsPreferences().size(); l++) {
                            if (child.getGiftsPreferences().get(k).compareTo(child.getGiftsPreferences().get(l)) == 0 && k != l) {
                                child.getGiftsPreferences().remove(l);
                                l = l - 1;
                            }
                        }
                    }


                }

            }
        }

        return children;
    }

    public Children CalculateAverageScore(Children children) {
        for (int i = 0; i < children.children.size(); i++) {
            double averageScore = 0;
            Child child = children.children.get(i);
            if (child.getAge() < 5) {
                child.setAverageScore(10);
            }
            if (5 <= child.getAge() && child.getAge() < 12) {
                for (int j = 0; j < child.getNiceScoreHistory().size(); j++) {
                    averageScore = averageScore + child.getNiceScoreHistory().get(j);
                }
                averageScore = averageScore / child.getNiceScoreHistory().size();
                child.setAverageScore(averageScore);
            }

            if (12 <= child.getAge()) {
                double ponderSum = 0;
                for (int j = 0; j < child.getNiceScoreHistory().size(); j++) {
                    averageScore = averageScore + (j + 1) * child.getNiceScoreHistory().get(j);
                    ponderSum = ponderSum + (j + 1);
                }
                averageScore = averageScore / ponderSum;
                child.setAverageScore(averageScore);
            }

        }
        return children;
    }

    public Children AddChildren(Children children, ArrayList<ChildrenInputData> newChildren) {

        for (int j = 0; j < newChildren.size(); j++) {
            ChildrenInputData newChild = newChildren.get(j);
            int ok = 0;
            for (int i = 0; i < children.children.size(); i++) {
                if (newChild.getId() < children.children.get(i).getId()) {
                    children.children.add(i, new Child(newChild.getId(), newChild.getLastName(), newChild.getFirstName(), newChild.getCity(), newChild.getAge(), newChild.getGiftsPreferences(), newChild.getAverageScore(), newChild.getNiceScoreHistory(), newChild.getAssignedBudget(), newChild.getReceivedGifts()));
                    ok = 1;
                    i = children.children.size();
                }
            }
            if (ok == 0) {
                children.children.add(new Child(newChild.getId(), newChild.getLastName(), newChild.getFirstName(), newChild.getCity(), newChild.getAge(), newChild.getGiftsPreferences(), newChild.getAverageScore(), newChild.getNiceScoreHistory(), newChild.getAssignedBudget(), newChild.getReceivedGifts()));
            }
        }

        return children;
    }

}
