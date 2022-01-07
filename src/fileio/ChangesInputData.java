package fileio;

import reading.Child;
import reading.ChildUpdates;
import reading.ChildrenUpdates;
import reading.Gift;

import java.util.ArrayList;

public class ChangesInputData {

    private double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<ChildrenInputData> newChildren;
    private ArrayList<ChildrenUpdatesInputData> childrenUpdates;

    public ChangesInputData(double newSantaBudget, ArrayList<Gift> newGifts, ArrayList<ChildrenInputData> newChildren, ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(int newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<ChildrenInputData> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(ArrayList<ChildrenInputData> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<ChildrenUpdatesInputData> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
