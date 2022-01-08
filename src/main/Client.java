package main;

import commands.AnnualUpdateCommand;
import commands.CommandType;
import commands.calculateKidBudget;
import reading.Children;

/**
 * Receives commands in clear text from the user and transforms them in AnnualUpdateCommand objects. It uses the Invoker to
 * execute the given commands.
 */
public class Client {

    private Invoker invoker;
    private Children children;
    private double santaBudget;


    Client(final Children children, double santaBudget) {
        invoker = new Invoker();
        this.children = children;
        this.santaBudget = santaBudget;
    }



    public void executeAction(String commandName) {
        AnnualUpdateCommand command;
        try {
            CommandType commandType = CommandType.fromString(commandName);
            command = getCommand(commandType);
            if (command == null) {
                throw new IllegalArgumentException();
            }

        } catch(IllegalArgumentException ex) {
            System.out.println("Invalid command: " + commandName);
            System.out.println("Available commands:");
            for (CommandType type : CommandType.values()) {
                System.out.println("\t- " + type.text);
            }
            return;
        }


        invoker.execute(command);

    }

    private AnnualUpdateCommand getCommand(CommandType type) throws IllegalArgumentException {
        switch (type) {
            case CALCULATE_KID_BUDGET: {
                return new calculateKidBudget(children, santaBudget);
            }
        }
        return null;
    }
}
