package commands;

import common.Constants;
import reading.Children;
import reading.Gift;
import reading.Gifts;

import java.util.ArrayList;
import java.util.List;

/**
 * gives gifts to the children
 */

public class GiveChildrenGifts implements AnnualUpdateCommand {
    private Children children;
    private Gifts gifts;

    public GiveChildrenGifts(final Children children, final Gifts gifts) {
        this.children = children;
        this.gifts = gifts;
    }

    /**
     * calculates the budget each kid gets
     */
    public void execute() {

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

    }
}
