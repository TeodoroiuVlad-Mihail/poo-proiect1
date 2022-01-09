package commands;

import common.Constants;
import builder.Child;
import reading.Children;

public class CalculateAverageScore implements AnnualUpdateCommand {
    private Children children;


    public CalculateAverageScore(final Children children) {
        this.children = children;
    }

    /**
     * calculates the average score of children
     */
    public void execute() {
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
    }
}
