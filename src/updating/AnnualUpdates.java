package updating;

import common.Constants;
import fileio.ChildrenInputData;
import fileio.ChildrenUpdatesInputData;
import reading.Child;
import reading.Children;
import reading.Gift;
import reading.Gifts;

import java.util.ArrayList;
import java.util.List;

public class AnnualUpdates {

    private Children children;
    private Gifts gifts;


    public AnnualUpdates(final Children c, final Gifts g) {
        children = c;
        gifts = g;
    }

    /**
     * calculates the budget each kid gets
     */
    public Children calculateKidBudget(final Children children, final double santaBudget) {

        double niceScoreSum = 0;


        for (int i = 0; i < children.getChildren().size(); i++) {
            Child child = children.getChildren().get(i);
            niceScoreSum = niceScoreSum + child.getAverageScore();
        }

        double budgetUnit = santaBudget / niceScoreSum;

        for (int i = 0; i < children.getChildren().size(); i++) {

            double averageScore = children.getChildren().get(i).getAverageScore();
            double assignedBudget = averageScore * budgetUnit;
            children.getChildren().get(i).setAssignedBudget(assignedBudget);
        }

        return children;
    }

    /**
     * gives gifts to the children
     */
    public Children giveChildrenGifts(final Children children, final Gifts gifts) {

        for (int i = 0; i < children.getChildren().size(); i++) {

            double remainingBudget = children.getChildren().get(i).getAssignedBudget();
            List<Gift> giftsList = new ArrayList<>();
            List<String> giftsPreferences = children.getChildren().get(i).getGiftsPreferences();
            for (int j = 0; j < giftsPreferences.size(); j++) {
                String giftPreference = giftsPreferences.get(j);
                double mostExpensive = Constants.BIGFOFFNUMBER;
                Gift cheapestGift = null; //placeholder, needs to be initialized

                //search for the cheapest gift
                for (int k = 0; k < gifts.getGifts().size(); k++) {
                    double giftPrice = gifts.getGifts().get(k).getPrice();
                    String giftCategory = gifts.getGifts().get(k).getCategory();
                    if (remainingBudget >= giftPrice && giftPreference.compareTo(giftCategory) == 0
                            && giftPrice < mostExpensive) {
                        mostExpensive = giftPrice;
                        cheapestGift = gifts.getGifts().get(k);
                    }

                }
                if (cheapestGift != null) {
                    giftsList.add(cheapestGift);
                    remainingBudget = remainingBudget - cheapestGift.getPrice();
                }

            }

            children.getChildren().get(i).setReceivedGifts(giftsList);
        }

        return children;
    }

    /**
     * increments the children's age
     */
    public Children growChildren(final Children children) {
        for (int i = 0; i < children.getChildren().size(); i++) {
            int newAge = children.getChildren().get(i).getAge() + 1;
            children.getChildren().get(i).setAge(newAge);
        }

        return children;
    }

    /**
     * removes kids over 18 from the list
     */
    public Children removeYoungAdults(final Children children) {

        for (int i = 0; i < children.getChildren().size(); i++) {
            if (children.getChildren().get(i).getAge() > Constants.TEENLIMIT) {
                children.getChildren().remove(i);
                children.getChildren().trimToSize();
                i = i - 1;
            }
        }
        return children;
    }

    /**
     * updates children
     */
    public Children updateChildren(final Children children,
                                   final ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        for (int i = 0; i < children.getChildren().size(); i++) {
            Child child = children.getChildren().get(i);
            for (int j = 0; j < childrenUpdates.size(); j++) {

                ChildrenUpdatesInputData childUpdates = childrenUpdates.get(j);
                if (child.getId() == childUpdates.getId()) {
                    //update their scores

                    if (childUpdates.getNiceScore() >= 0) {
                        child.getNiceScoreHistory().add(childUpdates.getNiceScore());
                    }
                    //now for the gift preferences

                    //delete duplicates
                    for (int k = 0; k < child.getGiftsPreferences().size(); k++) {
                        for (int l = 0; l < childUpdates.getGiftsPreferences().size(); l++) {
                            if (child.getGiftsPreferences().get(k).compareTo(
                                    childUpdates.getGiftsPreferences().get(l)) == 0) {
                                child.getGiftsPreferences().remove(k);
                                k = k - 1;
                                l = childUpdates.getGiftsPreferences().size();
                            }
                        }
                    }

                    //add the gifts
                    for (int k = childUpdates.getGiftsPreferences().size() - 1; k >= 0; k--) {
                        child.getGiftsPreferences().add(0,
                                childUpdates.getGiftsPreferences().get(k));
                    }

                    //because of test12, apparently in the gift preferences updates
                    //there can be duplicates, thanks guys, very fun
                    for (int k = 0; k < child.getGiftsPreferences().size(); k++) {
                        for (int l = 0; l < child.getGiftsPreferences().size(); l++) {
                            if (child.getGiftsPreferences().get(k).compareTo(
                                    child.getGiftsPreferences().get(l)) == 0 && k != l) {
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

    /**
     * calculates the average score of children
     */
    public Children calculateAverageScore(final Children children) {
        for (int i = 0; i < children.getChildren().size(); i++) {
            double averageScore = 0;
            Child child = children.getChildren().get(i);
            if (child.getAge() < Constants.BABYLIMIT) {
                child.setAverageScore(Constants.BABYDEFAULTSCORE);
            }
            if (Constants.BABYLIMIT <= child.getAge() && child.getAge() < Constants.KIDLIMIT) {
                for (int j = 0; j < child.getNiceScoreHistory().size(); j++) {
                    averageScore = averageScore + child.getNiceScoreHistory().get(j);
                }
                averageScore = averageScore / child.getNiceScoreHistory().size();
                child.setAverageScore(averageScore);
            }

            if (Constants.KIDLIMIT <= child.getAge()) {
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

    /**
     * adds children to the children list
     */
    public Children addChildren(final Children children,
                                final ArrayList<ChildrenInputData> newChildren) {

        for (int j = 0; j < newChildren.size(); j++) {
            ChildrenInputData newChild = newChildren.get(j);
            int ok = 0;
            for (int i = 0; i < children.getChildren().size(); i++) {
                if (newChild.getId() < children.getChildren().get(i).getId()) {
                    children.getChildren().add(i, new Child(newChild.getId(),
                            newChild.getLastName(), newChild.getFirstName(), newChild.getCity(),
                            newChild.getAge(), newChild.getGiftsPreferences(),
                            newChild.getAverageScore(), newChild.getNiceScoreHistory(),
                            newChild.getAssignedBudget(), newChild.getReceivedGifts()));
                    ok = 1;
                    i = children.getChildren().size();
                }
            }
            if (ok == 0) {
                children.getChildren().add(new Child(newChild.getId(), newChild.getLastName(),
                        newChild.getFirstName(), newChild.getCity(), newChild.getAge(),
                        newChild.getGiftsPreferences(), newChild.getAverageScore(),
                        newChild.getNiceScoreHistory(), newChild.getAssignedBudget(),
                        newChild.getReceivedGifts()));
            }
        }

        return children;
    }

}
