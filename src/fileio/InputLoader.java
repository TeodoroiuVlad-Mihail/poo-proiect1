package fileio;

import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import reading.Gift;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
     *
     * @return an Input object
     */
    public Input readInput() {
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
                for (Object jsonChild : jsonChildren) {
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
                for (Object jsonGift : jsonGifts) {
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

            //now for the changes
            JSONArray jsonChanges = (JSONArray) jsonObject.get("annualChanges");
            if (jsonChanges != null) {
                for (Object jsonChange : jsonChanges) {

                    JSONArray jsonNewChildren =
                            ((JSONArray) ((JSONObject) jsonChange).get("newChildren"));
                    ArrayList<ChildrenInputData> newChildren = new ArrayList<>();
                    if (jsonNewChildren != null) {
                        for (Object jsonNewChild : jsonNewChildren) {
                            newChildren.add(new ChildrenInputData(
                                    (int) ((long) ((JSONObject) jsonNewChild).get(Constants.ID)),
                                    (String) ((JSONObject) jsonNewChild).get(Constants.LASTNAME),
                                    (String) ((JSONObject) jsonNewChild).get(Constants.FIRSTNAME),
                                    (String) ((JSONObject) jsonNewChild).get(Constants.CITY),
                                    (int) ((long) ((JSONObject) jsonNewChild).get(Constants.AGE)),
                                    Utils.convertJSONArray((JSONArray) ((JSONObject) jsonNewChild)
                                            .get(Constants.GIFTSPREFERENCES)),
                                    (int) ((long)
                                            ((JSONObject) jsonNewChild).get(Constants.NICESCORE))
                            ));
                        }
                    }
                    if (jsonNewChildren == null) {
                        newChildren = null;
                    }

                    //Construct the Children updates
                    JSONArray jsonChildrenUpdates =
                            ((JSONArray) ((JSONObject) jsonChange).get("childrenUpdates"));
                    ArrayList<ChildrenUpdatesInputData> childrenUpdates = new ArrayList<>();
                    if (jsonChildrenUpdates != null) {
                        for (Object jsonChildrenUpdate : jsonChildrenUpdates) {
                            double niceScore;
                            if (((JSONObject) jsonChildrenUpdate).get("niceScore") != null) {
                                niceScore = (double) ((long)
                                        ((JSONObject) jsonChildrenUpdate).get("niceScore"));
                            } else {
                                niceScore = -1;
                            }
                            childrenUpdates.add(new ChildrenUpdatesInputData(
                                    (int) ((long) ((JSONObject) jsonChildrenUpdate).get("id")),
                                    niceScore,
                                    Utils.convertJSONArray((JSONArray)
                                            ((JSONObject) jsonChildrenUpdate)
                                            .get(Constants.GIFTSPREFERENCES))
                            ));
                        }
                    }

                    if (jsonChildrenUpdates == null) {
                        childrenUpdates = null;
                    }

                    changes.add(new ChangesInputData(
                            (double) ((long) ((JSONObject) jsonChange).get("newSantaBudget")),
                            (ArrayList<Gift>) ((JSONArray) ((JSONObject) jsonChange)
                                    .get("newGifts")),
                            newChildren,
                            childrenUpdates
                    ));
                }
            } else {
                System.out.println("NU EXISTA SCHIMBARI");
            }
            if (jsonChanges == null) {
                changes = null;
            }


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, children, gifts, changes);
    }

}
