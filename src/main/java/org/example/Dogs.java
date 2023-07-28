package org.example;

public class Dogs extends Animals {
    public Dogs(String name, String skills) {
        super(name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Список команд собак" + getName() + ": " + getCommand());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedCommand = getCommand() + "," + command;
        setCommand(updatedCommand);
        System.out.println("Собака " + getName() + "обучена команде " + command);
    }
}
