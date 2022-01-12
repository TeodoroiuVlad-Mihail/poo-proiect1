package fileio;

import reading.Gift;

import java.util.ArrayList;
import java.util.List;

public class ChildrenInputData {
    private int id;
    private String lastName;
    private String firstName;
    private String city;
    private int age;
    private List<String> giftsPreferences;
    private double averageScore;
    private List<Double> niceScoreHistory;
    private double assignedBudget;
    private List<Gift> receivedGifts;

    public ChildrenInputData(final int id, final String lastName, final String firstName,
                             final String city, final int age, final List<String> giftsPreferences,
                             final double niceScore) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = niceScore;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(niceScore);
        this.assignedBudget = 0;
        this.receivedGifts = new ArrayList<>();
    }

    /**
     * getter for id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * getter for last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter for first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter for city
     */
    public String getCity() {
        return city;
    }

    /**
     * setter for city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * getter for age
     */
    public int getAge() {
        return age;
    }

    /**
     * setter for age
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * getter for assigned gift preferences
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

    /**
     * getter for average score
     */
    public double getAverageScore() {
        return averageScore;
    }

    /**
     * setter for average score
     */
    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * getter for assigned budget
     */
    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * setter for score history
     */
    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * getter for assigned budget
     */
    public double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * setter for assigned budget
     */
    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * getter for received gifts
     */
    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * setter for received gifts
     */
    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
