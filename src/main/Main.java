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
import updating.AnnualUpdates;
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

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = Constants.OUTPUT_PATH
                    + file.getName().replaceAll("[^0-9]+", "")
                    + Constants.FILE_EXTENSION;
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }


        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException, ParseException {
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

        //Calculate stuff and print for the year 0
        AnnualUpdates annualUpdates = new AnnualUpdates(children, santaGiftsList);


        JSONObject object = null;
        annualUpdates.removeYoungAdults(children);
        annualUpdates.calculateAverageScore(children);
        annualUpdates.calculateKidBudget(children, santaBudget);
        annualUpdates.giveChildrenGifts(children, santaGiftsList);
        object = write.returnChildren();
        arrayResult.add(arrayResult.size(), object);

        for (int i = 1; i <= numberOfYears; i++) {
            santaBudget = changesList.getChanges().get(i - 1).getNewSantaBudget();

            annualUpdates.growChildren(children);

            ArrayList<ChildrenInputData> newChildren =
                    changesList.getChanges().get(i - 1).getNewChildren();
            annualUpdates.addChildren(children, newChildren);

            annualUpdates.removeYoungAdults(children);

            ArrayList<ChildrenUpdatesInputData> childrenUpdates =
                    changesList.getChanges().get(i - 1).getChildrenUpdates();
            annualUpdates.updateChildren(children, childrenUpdates);

            annualUpdates.calculateAverageScore(children);
            annualUpdates.calculateKidBudget(children, santaBudget);
            annualUpdates.giveChildrenGifts(children, santaGiftsList);
            object = write.returnChildren();
            arrayResult.add(arrayResult.size(), object);
        }

        objectResult.put("annualChildren", arrayResult);

        fileWriter.closeJSON(objectResult);
    }
}
