package reading;

import java.util.ArrayList;
import java.util.List;

public class Child {
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

    public Child(int id, String lastName, String firstName, String city, int age,
                 List<String> giftsPreferences, double averageScore, List<Double> niceScoreHistory,
                 double assignedBudget, List<Gift> receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = averageScore;
        this.niceScoreHistory = niceScoreHistory;
        this.assignedBudget = assignedBudget;
        this.receivedGifts = receivedGifts;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }
    public void setGiftsPreferences(List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public double getAverageScore() {
        return averageScore;
    }
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }
    public void setNiceScoreHistory(List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }
    public void setAssignedBudget(double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }
    public void setReceivedGifts(List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public String giftsToString(List<String> giftsPreferences) {
        String string = new String();
        string = string.concat("[");
        for(int i = 0; i < giftsPreferences.size(); i++) {
            string = string.concat("\"" + giftsPreferences.get(i) + "\"");
            if (i < giftsPreferences.size() - 1)
                string = string.concat(", ");
        }
        string = string.concat(" ]");

        return string;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"lastName\": \"" + lastName + '\"' +
                ", \"firstName\": \"" + firstName + '\"' +
                ", \"city\": \"" + city + '\"' +
                ", \"age\":" + age +
                ", \"giftsPreferences\":" + giftsToString(giftsPreferences) +
                ", \"averageScore\":" + averageScore +
                ", \"niceScoreHistory\":" + niceScoreHistory +
                ", \"assignedBudget\":" + assignedBudget +
                ", \"receivedGifts\":" + receivedGifts +
                '}';
    }
}
