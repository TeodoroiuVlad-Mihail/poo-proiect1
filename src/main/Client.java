package main;

import commands.*;
import fileio.ChildrenInputData;
import fileio.ChildrenUpdatesInputData;
import reading.Children;
import reading.Gifts;

import java.util.ArrayList;

/**
 * Receives commands in clear text from the user and transforms them in AnnualUpdateCommand objects. It uses the Invoker to
 * execute the given commands.
 */
public class Client {

    private Invoker invoker;
    private Children children;
    private double santaBudget;
    private Gifts gifts;
    private ArrayList<ChildrenInputData> newChildren;
    private ArrayList<ChildrenUpdatesInputData> childrenUpdates;

    Client(final Children children, final double santaBudget, final Gifts gifts) {
        invoker = new Invoker();
        this.children = children;
        this.santaBudget = santaBudget;
        this.gifts = gifts;
        this.newChildren = null;
        this.childrenUpdates = null;

    }

    //This is iirc overloading, something about using the same name for constructor but
    //with different arguments
    Client(final Children children, final double santaBudget, final Gifts gifts,
           final ArrayList<ChildrenInputData> newChildren,
           final ArrayList<ChildrenUpdatesInputData> childrenUpdates) {
        invoker = new Invoker();
        this.children = children;
        this.santaBudget = santaBudget;
        this.gifts = gifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }


    public void executeAction(String commandName) {
        AnnualUpdateCommand command;
        try {
            CommandType commandType = CommandType.fromString(commandName);
            command = getCommand(commandType);
            if (command == null) {
                throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException ex) {
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
            case GIVE_CHILDREN_GIFTS: {
                return new giveChildrenGifts(children, gifts);
            }
            case REMOVE_YOUNG_ADULTS: {
                return new removeYoungAdults(children);
            }
            case GROW_CHILDREN: {
                return new growChildren(children);
            }
        }
        return null;
    }
}
