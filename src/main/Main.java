package main;

import checker.Checker;

import common.Constants;

import fileio.ChildrenInputData;
import fileio.ChildrenUpdatesInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import reading.Changes;
import reading.Children;
import reading.Gifts;

import writing.Write;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     *
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException, ParseException {

        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        if (outputDirectory.listFiles() != null) {
            for (File file : outputDirectory.listFiles()) {
                if (!file.delete()) {
                    System.out.println("nu s-a sters");
                }
            }
        }

        //done here to avoid the "Unexpected character () at position 0." error
        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            String filepath = Constants.OUTPUT_PATH
                    + i
                    + Constants.FILE_EXTENSION;
            File out = new File(filepath);
            out.createNewFile();
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String inPath = file.getAbsolutePath();
            String outPath = Constants.OUTPUT_PATH
                    + file.getName().replaceAll("[^0-9]+", "")
                    + Constants.FILE_EXTENSION;
            action(inPath, outPath);
        }



        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readInput();

        Writer fileWriter = new Writer(filePath2);
        JSONObject objectResult = new JSONObject();

        double numberOfYears = input.getNumberOfYears();
        double santaBudget = input.getSantaBudget();

        Children children = new Children(input.getChildren());
        Gifts santaGiftsList = new Gifts(input.getGifts());
        Changes changesList = new Changes(input.getChanges());

        Write write = new Write(children);
        JSONArray arrayResult = new JSONArray();
        JSONObject object = null;

        //Calculate stuff and print for the year 0

        Client client = new Client(children, santaBudget, santaGiftsList);

        client.executeAction("RemoveYoungAdults");
        client.executeAction("CalculateAverageScore");
        client.executeAction("CalculateChildrenBudget");
        client.executeAction("GiveChildrenGifts");
        object = write.returnChildren();
        arrayResult.add(arrayResult.size(), object);

        //Now Iterate for the rest of the years
        for (int i = 1; i <= numberOfYears; i++) {
            santaBudget = changesList.getChanges().get(i - 1).getNewSantaBudget();

            ArrayList<ChildrenInputData> newChildren =
                    changesList.getChanges().get(i - 1).getNewChildren();

            ArrayList<ChildrenUpdatesInputData> childrenUpdates =
                    changesList.getChanges().get(i - 1).getChildrenUpdates();

            client = new Client(children, santaBudget, santaGiftsList,
                    newChildren, childrenUpdates);

            client.executeAction("GrowChildren");
            client.executeAction("AddChildren");
            client.executeAction("RemoveYoungAdults");
            client.executeAction("UpdateChildren");
            client.executeAction("CalculateAverageScore");
            client.executeAction("CalculateChildrenBudget");
            client.executeAction("GiveChildrenGifts");
            object = write.returnChildren();
            arrayResult.add(arrayResult.size(), object);
        }

        objectResult.put("annualChildren", arrayResult);

        fileWriter.closeJSON(objectResult);
    }
}
