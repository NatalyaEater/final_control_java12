package org.example;

public class Cats extends Animals {
    public Cats(String name, String skills) {
        super(name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Список команд котов" + getName() + ": " + getCommand());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedCommand = getCommand() + "," + command;
        setCommand(updatedCommand);
        System.out.println("Кот " + getName() + "обучен команде " + command);
    }
}
