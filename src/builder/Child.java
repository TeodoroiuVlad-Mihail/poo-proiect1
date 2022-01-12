package builder;

import reading.Gift;

import java.util.List;

public class Child {
    //mandatory (the ones we are given when initializing a child)
    private int id;
    private String lastName;
    private String firstName;
    private String city;
    private int age;
    private List<String> giftsPreferences;
    private double averageScore;
    //optional (the ones not mandatory for child "creation")
    private List<Double> niceScoreHistory;
    private double assignedBudget;
    private List<Gift> receivedGifts;

    public Child(final ChildBuilder childBuilder) {
        this.id = childBuilder.id;
        this.lastName = childBuilder.lastName;
        this.firstName = childBuilder.firstName;
        this.city = childBuilder.city;
        this.age = childBuilder.age;
        this.giftsPreferences = childBuilder.giftsPreferences;
        this.averageScore = childBuilder.averageScore;
        this.niceScoreHistory = childBuilder.niceScoreHistory;
        this.assignedBudget = childBuilder.assignedBudget;
        this.receivedGifts = childBuilder.receivedGifts;
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

    /**
     * to also format the preferences properly
     */
    public String giftsToString(final List<String> pGiftsPreferences) {
        String string = new String();
        string = string.concat("[");
        for (int i = 0; i < pGiftsPreferences.size(); i++) {
            string = string.concat("\"" + pGiftsPreferences.get(i) + "\"");
            if (i < pGiftsPreferences.size() - 1) {
                string = string.concat(", ");
            }
        }
        string = string.concat(" ]");

        return string;
    }

    /**
     * toString for children, modified to fit output
     */
    @Override
    public String toString() {
        return "{"
                + "\"id\":" + id
                + ", \"lastName\": \"" + lastName + '\"'
                + ", \"firstName\": \"" + firstName + '\"'
                + ", \"city\": \"" + city + '\"'
                + ", \"age\":" + age
                + ", \"giftsPreferences\":" + giftsToString(giftsPreferences)
                + ", \"averageScore\":" + averageScore
                + ", \"niceScoreHistory\":" + niceScoreHistory
                + ", \"assignedBudget\":" + assignedBudget
                + ", \"receivedGifts\":" + receivedGifts
                + '}';
    }

    public static class ChildBuilder {
        //mandatory (the ones we are given when initializing a child)
        private int id;
        private String lastName;
        private String firstName;
        private String city;
        private int age;
        private List<String> giftsPreferences;
        private double averageScore;
        //optional (the ones not mandatory for child "creation")
        private List<Double> niceScoreHistory = null;
        private double assignedBudget = 0;
        private List<Gift> receivedGifts = null;

        public ChildBuilder(final int id, final String lastName, final String firstName,
                            final String city, final int age, final List<String> giftsPreferences,
                            final double averageScore) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.city = city;
            this.age = age;
            this.giftsPreferences = giftsPreferences;
            this.averageScore = averageScore;
        }

        /**
         * adds the nice score history
         */
        public ChildBuilder niceScoreHistory(final List<Double> pNiceScoreHistory) {
            this.niceScoreHistory = pNiceScoreHistory;
            return this;
        }

        /**
         * adds the assigned budget
         */
        public ChildBuilder assignedBudget(final double pAssignedBudget) {
            this.assignedBudget = pAssignedBudget;
            return this;
        }

        /**
         * adds the received gifts list
         */
        public ChildBuilder receivedGifts(final List<Gift> pReceivedGifts) {
            this.receivedGifts = pReceivedGifts;
            return this;
        }

        /**
         * the method that "builds" the children
         */
        public Child build() {
            return new Child(this);
        }
    }
}
