package org.example;

public abstract class Animals {
    private String name;
    private String command;

    public Animals(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String updatedCommand) {
        this.command = updatedCommand;
    }

    public abstract void displayCommands();

    public abstract void teachNewCommand(String command);
}
