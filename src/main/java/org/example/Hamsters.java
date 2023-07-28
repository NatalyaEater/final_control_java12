package org.example;

public class Hamsters extends Animals {
    public Hamsters(String name, String skills) {
        super(name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Список команд хомяков" + getName() + ": " + getCommand());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedCommand = getCommand() + "," + command;
        setCommand(updatedCommand);
        System.out.println("Хомяк " + getName() + "обучен команде " + command);
    }
}