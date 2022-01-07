package fileio;

import java.util.ArrayList;

/**
 * The class contains information about input
 * <p>
 * DO NOT MODIFY
 */
public final class Input {

    private final double numberOfYearsData;

    private final double santaBudgetData;

    private final ArrayList<ChildrenInputData> childrenData;

    private final ArrayList<GiftsInputData> giftsData;

    private final ArrayList<ChangesInputData> changesData;

    /**
     * List of movies
     */

    public Input() {
        this.numberOfYearsData = 0;
        this.santaBudgetData = 0;
        this.childrenData = null;
        this.giftsData = null;
        this.changesData = null;
    }

    public Input(final double numberOfYears, final double santaBudget,
                 final ArrayList<ChildrenInputData> children,
                 final ArrayList<GiftsInputData> gifts,
                 final ArrayList<ChangesInputData> changes) {
        this.numberOfYearsData = numberOfYears;
        this.santaBudgetData = santaBudget;
        this.childrenData = children;
        this.giftsData = gifts;
        this.changesData = changes;
    }

    public double getNumberOfYears() {
        return numberOfYearsData;
    }

    public double getSantaBudget() {
        return santaBudgetData;
    }

    public ArrayList<ChildrenInputData> getChildren() {
        return childrenData;
    }

    public ArrayList<GiftsInputData> getGifts() {
        return giftsData;
    }

    public ArrayList<ChangesInputData> getChanges() {
        return changesData;
    }
}
