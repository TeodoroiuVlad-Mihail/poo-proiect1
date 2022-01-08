package commands;


import reading.Child;
import reading.Children;

public class calculateKidBudget implements AnnualUpdateCommand{
    private Children children;
    private double santaBudget;


    /**
     * calculates the budget each kid gets
     */
    public calculateKidBudget(final Children children, final double santaBudget) {
        this.children = children;
        this.santaBudget = santaBudget;
    }

    public void execute(){
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
    }
}
