package reading;

import java.util.List;

public class ChildUpdates {
    private int id;
    private double niceScore;
    private List<String> giftsPreferences;

    public ChildUpdates(final int id, final double niceScore,
                        final List<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * getter for the id of the child to be updated
     */
    public int getId() {
        return id;
    }

    /**
     * setter for the id of the child to be updated
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * getter for the nice score to be added to the list
     */
    public double getNiceScore() {
        return niceScore;
    }

    /**
     * setter for the nice score to be added to the list
     */
    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * getter for gift preferences
     */
    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * setter for gift preferences
     */
    public void setGiftsPreferences(final List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

}
