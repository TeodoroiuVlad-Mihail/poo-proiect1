package fileio;

import java.util.List;

/**
 * The class contains information about input
 * <p>
 * DO NOT MODIFY
 */
public final class Input {

    private final double numberOfYearsData;

    private final double santaBudgetData;

    private final List<ChildrenInputData> childrenData;

    private final List<GiftsInputData> giftsData;

    private final List<ChangesInputData> changesData;
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
                 final List<ChildrenInputData> children,
                 final List<GiftsInputData> gifts,
                 final List<ChangesInputData> changes) {
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

    public List<ChildrenInputData> getChildren() {
        return childrenData;
    }

    public List<GiftsInputData> getGifts() {
        return giftsData;
    }

    public List<ChangesInputData> getChanges() {
        return changesData;
    }
}
