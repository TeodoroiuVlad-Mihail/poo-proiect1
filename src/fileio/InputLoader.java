package fileio;

import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class reads and parses the data from the tests
 * <p>
 * DO NOT MODIFY
 */
public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * The method reads the database
     * @return an Input object
     */
    public Input readInitialData() {
        JSONParser jsonParser = new JSONParser();
        double numberOfYears = 0;
        double santaBudget = 0;
        List<ChildrenInputData> children = new ArrayList<>();
        List<GiftsInputData> gifts = new ArrayList<>();
        List<ChangesInputData> changes = new ArrayList<>();

        try {
            // Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIALDATA);

            numberOfYears = (int) (long) jsonObject.get(Constants.NUMBEROFYEARS);

            santaBudget = (int) (long) jsonObject.get(Constants.SANTABUDGET);

            JSONArray jsonChildren = (JSONArray) initialData.get(Constants.CHILDREN);

            if (jsonChildren != null) {
                for (Object jsonChild: jsonChildren) {
                    children.add(new ChildrenInputData(
                            (int) ((long) ((JSONObject) jsonChild).get(Constants.ID)),
                            (String) ((JSONObject) jsonChild).get(Constants.LASTNAME),
                            (String) ((JSONObject) jsonChild).get(Constants.FIRSTNAME),
                            (int) ((long) ((JSONObject) jsonChild).get(Constants.AGE)),
                            (String) ((JSONObject) jsonChild).get(Constants.CITY),
                            (int) ((long) ((JSONObject) jsonChild).get(Constants.NICESCORE)),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild)
                                    .get(Constants.GIFTSPREFERENCES))
                    ));
                }
            } else {
                System.out.println("NU EXISTA COPII");
            }

            if (jsonChildren == null) {
                children = null;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, children, gifts, changes);
    }

    /**
     * The method reads the actions from input file
     * @param jsonObject
     * @param size
     * @return A list of actions
     */
    /*public List<ActionInputData> readActions(final JSONObject jsonObject, final int size) {

        List<ActionInputData> actions = new ArrayList<>();
        JSONArray jsonActions = (JSONArray)
                jsonObject.get(Constants.ACTIONS);

        if (jsonActions != null) {
            for (Object jsonIterator : jsonActions) {
                String actionType = (String) ((JSONObject) jsonIterator)
                        .get(Constants.ACTION_TYPE);
                double grade = 0;
                int season = 0;

                if (((JSONObject) jsonIterator).get(Constants.SEASON) != null) {
                    season = Integer.parseInt(((JSONObject) jsonIterator)
                            .get(Constants.SEASON).toString());
                }

                if (((JSONObject) jsonIterator).get(Constants.GRADE) != null) {
                    grade = Double.parseDouble(((JSONObject) jsonIterator).get(Constants.GRADE)
                            .toString());
                }

                String genre = null;
                String year = null;
                JSONArray awards = null;
                JSONArray words = null;

                int number;

                if (((JSONObject) jsonIterator).get(Constants.NUMBER) != null) {
                    number = Integer.parseInt(((JSONObject) jsonIterator)
                            .get(Constants.NUMBER).toString());
                } else {
                    number = size;
                }

                if (((JSONObject) jsonIterator).get(Constants.FILTERS) != null) {
                    genre = (String) ((JSONObject) ((JSONObject) jsonIterator)
                            .get(Constants.FILTERS))
                            .get(Constants.GENRE);

                    year = (String) ((JSONObject) ((JSONObject) jsonIterator)
                            .get(Constants.FILTERS))
                            .get(Constants.YEAR);

                    awards = (JSONArray) ((JSONObject) ((JSONObject) jsonIterator)
                            .get(Constants.FILTERS))
                            .get(Constants.AWARDS);

                    words = (JSONArray) ((JSONObject) ((JSONObject) jsonIterator)
                            .get(Constants.FILTERS))
                            .get(Constants.WORDS);
                }

                switch (actionType) {

                    case Constants.COMMAND -> actions.add(new ActionInputData(
                            Integer.parseInt(((JSONObject) jsonIterator).get(Constants.ID)
                                    .toString()),
                            actionType,
                            (String) ((JSONObject) jsonIterator).get(Constants.TYPE),
                            (String) ((JSONObject) jsonIterator).get(Constants.USER),
                            (String) ((JSONObject) jsonIterator).get(Constants.TITLE),
                            grade,
                            season
                    ));
                    case Constants.QUERY -> actions.add(new ActionInputData(
                            Integer.parseInt(((JSONObject) jsonIterator).get(Constants.ID)
                                    .toString()),
                            actionType,
                            (String) ((JSONObject) jsonIterator).get(Constants.OBJECT),
                            genre,
                            (String) ((JSONObject) jsonIterator).get(Constants.SORT),
                            (String) ((JSONObject) jsonIterator).get(Constants.CRITERIA),
                            year,
                            number,
                            Utils.convertJSONArray(words),
                            Utils.convertJSONArray(awards)
                    ));
                    case Constants.RECOMMENDATION -> actions.add(new ActionInputData(
                            Integer.parseInt(((JSONObject) jsonIterator).get(Constants.ID)
                                    .toString()),
                            actionType,
                            (String) ((JSONObject) jsonIterator).get(Constants.TYPE),
                            (String) ((JSONObject) jsonIterator).get(Constants.USERNAME),
                            (String) ((JSONObject) jsonIterator).get(Constants.GENRE)
                    ));
                    default -> {
                    }
                }
            }
        } else {
            System.out.println("NU EXISTA COMENZI");
            actions = null;
        }

        return actions;
    }*/
}
