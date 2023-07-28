package org.example;

public class Horses extends Animals {
    public Horses(String name, String skills) {
        super(name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Список команд лошадей" + getName() + ": " + getCommand());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedCommand = getCommand() + "," + command;
        setCommand(updatedCommand);
        System.out.println("Лошадь " + getName() + "обучена команде " + command);
    }
}