package fileio;

import reading.Child;
import reading.Gift;

import java.util.ArrayList;

public class Change {

    private double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<Child> childrenUpdates;

    public Change(double newSantaBudget, ArrayList<Gift> newGifts, ArrayList<Child> newChildren, ArrayList<Child> childrenUpdates) {
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

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }
    public void setNewChildren(ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<Child> getChildrenUpdates() {
        return childrenUpdates;
    }
    public void setChildrenUpdates(ArrayList<Child> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
