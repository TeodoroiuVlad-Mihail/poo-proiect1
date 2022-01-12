package main;

import commands.AnnualUpdateCommand;

public class Invoker {

    /**
     * Executes a given command
     * @param command
     */
    public void execute(final AnnualUpdateCommand command) {
        command.execute();
    }
}
