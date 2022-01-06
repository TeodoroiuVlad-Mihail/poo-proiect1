package main;

import checker.Checker;
import common.Constants;
import fileio.ChangesInputData;
import fileio.Writer;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

//added by me
//import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import reading.Child;
import reading.Children;
import reading.Gift;
import reading.Gifts;
import writing.Write;
//import fileio.Writer;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

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
            String filepath = Constants.OUTPUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                System.out.println(filepath + ":");
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
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readInitialData();

        Writer fileWriter = new Writer(filePath2);
        JSONObject objectResult = new JSONObject();

        double numberOfYears = input.getNumberOfYears();
        double santaBudget = input.getSantaBudget();

        Children children = new Children(input.getChildren());
        Gifts santaGiftsList = new Gifts(input.getGifts());
        //Changes changesList =  new Changes(input.getChanges());

        Write write = new Write(children);

        System.out.println("Number of years: " + numberOfYears);
        System.out.println("Santa Budget: " + santaBudget);
        System.out.println();

        System.out.println("Children:");
        for(int i = 0; i < children.children.size(); i++){
            Child child = children.children.get(i);
            System.out.println(child.toString());
        }

        System.out.println();

        System.out.println();
        System.out.println("Gifts:");
        for(int i = 0; i < santaGiftsList.gifts.size(); i++){
            Gift gift = santaGiftsList.gifts.get(i);
            System.out.println(gift.toString());
        }
        System.out.println();
        System.out.println();

        JSONArray arrayResult = new JSONArray();

        //Calculate stuff and print for the year 0
        JSONObject object = null;
        object = write.returnChildren();
        arrayResult.add(arrayResult.size(), object);

        for(int i = 1; i <=numberOfYears ; i++){
            object = write.returnChildren();
            arrayResult.add(arrayResult.size(), object);
        }

        objectResult.put("annualChildren", arrayResult);

        fileWriter.closeJSON(objectResult);
    }
}
