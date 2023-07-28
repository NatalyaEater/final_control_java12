package org.example;

public class Donkeys extends Animals {
    public Donkeys(String name, String skills) {
        super(name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Список команд ослов" + getName() + ": " + getCommand());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedCommand = getCommand() + "," + command;
        setCommand(updatedCommand);
        System.out.println("Осел" + getName() + "обучен команде " + command);
    }
}
