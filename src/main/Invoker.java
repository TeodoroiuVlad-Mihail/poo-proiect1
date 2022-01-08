package main;

import commands.AnnualUpdateCommand;

public class Invoker {

    /**
     * Executes a given command
     * @param command
     */
    public void execute(AnnualUpdateCommand command) {
        command.execute();
    }
}
