package fileio;

import reading.Gift;

import java.util.ArrayList;

public class ChangesInputData {

    private double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<ChildrenInputData> newChildren;
    private ArrayList<ChildrenUpdatesInputData> childrenUpdates;

    public ChangesInputData(final double newSantaBudget, final ArrayList<Gift> newGifts,
                            final ArrayList<ChildrenInputData> newChildren,
                            final ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    /**
     * getter for the new santa budget of the year
     */
    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * setter for the new santa budget of the year
     */
    public void setNewSantaBudget(final int newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /**
     * setter for new gifts
     */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    /**
     * setter for new gifts
     */
    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    /**
     * setter for new children
     */
    public ArrayList<ChildrenInputData> getNewChildren() {
        return newChildren;
    }

    /**
     * setter for new children
     */
    public void setNewChildren(final ArrayList<ChildrenInputData> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     * getter for children updates
     */
    public ArrayList<ChildrenUpdatesInputData> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     * setter for children updates
     */
    public void setChildrenUpdates(final ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
