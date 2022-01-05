package main;

import checker.Checker;
import common.Constants;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

//added by me
//import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
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

        double numberOfYears = input.getNumberOfYears();
        double santaBudget = input.getSantaBudget();

        System.out.println("Number of years: " + numberOfYears);
        System.out.println("Santa Budget: " + santaBudget);
        System.out.println();

        for(int i = 0; i <=numberOfYears ; i++){
            //commands for prelucration
        }

        //Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //entry point to implementation



        //arrayResult.add(arrayResult.size(), object);

        //fileWriter.closeJSON(arrayResult);
    }
}
