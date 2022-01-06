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
        ArrayList<ChildrenInputData> children = new ArrayList<>();
        ArrayList<GiftsInputData> gifts = new ArrayList<>();
        ArrayList<ChangesInputData> changes = new ArrayList<>();

        try {
            // Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIALDATA);

            numberOfYears = (int) (long) jsonObject.get(Constants.NUMBEROFYEARS);

            santaBudget = (int) (long) jsonObject.get(Constants.SANTABUDGET);

            JSONArray jsonChildren = (JSONArray) initialData.get(Constants.CHILDREN);

            JSONArray jsonGifts = (JSONArray) initialData.get(Constants.SANTAGIFTSLIST);

            if (jsonChildren != null) {
                for (Object jsonChild: jsonChildren) {
                    children.add(new ChildrenInputData(
                            (int) ((long) ((JSONObject) jsonChild).get(Constants.ID)),
                            (String) ((JSONObject) jsonChild).get(Constants.LASTNAME),
                            (String) ((JSONObject) jsonChild).get(Constants.FIRSTNAME),
                            (String) ((JSONObject) jsonChild).get(Constants.CITY),
                            (int) ((long) ((JSONObject) jsonChild).get(Constants.AGE)),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild)
                                    .get(Constants.GIFTSPREFERENCES)),
                            (int) ((long) ((JSONObject) jsonChild).get(Constants.NICESCORE))
                    ));
                }
            } else {
                System.out.println("NU EXISTA COPII");
            }

            if (jsonChildren == null) {
                children = null;
            }

            //now to read the gifts

            if (jsonGifts != null) {
                for (Object jsonGift: jsonGifts) {
                    gifts.add(new GiftsInputData(
                            (String) ((JSONObject) jsonGift).get(Constants.PRODUCTNAME),
                            (double) ((long) ((JSONObject) jsonGift).get(Constants.PRICE)),
                            (String) ((JSONObject) jsonGift).get(Constants.CATEGORY)
                    ));
                }
            } else {
                System.out.println("NU EXISTA CADOURI");
            }

            if (jsonGifts == null) {
                gifts = null;
            }


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, children, gifts, changes);
    }


}
