package fileio;

public class ChangesInputData {

    private double newSantaBudget;
    private int newGifts;
    private int newChildren;
    private int childrenUpdates;

    public ChangesInputData(int newSantaBudget, int newGifts, int newChildren, int childrenUpdates) {
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

    public int getNewGifts() {
        return newGifts;
    }
    public void setNewGifts(int newGifts) {
        this.newGifts = newGifts;
    }

    public int getNewChildren() {
        return newChildren;
    }
    public void setNewChildren(int newChildren) {
        this.newChildren = newChildren;
    }

    public int getChildrenUpdates() {
        return childrenUpdates;
    }
    public void setChildrenUpdates(int childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
