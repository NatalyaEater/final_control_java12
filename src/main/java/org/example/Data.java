package org.example;

import java.io.*;
import java.util.*;

public class Data {
    private final List<Animals> animals;
    private static final String FILE_PATH = "database.txt";

    public Data() {
        animals = new ArrayList<>();
        loadDatabase();
    }

    public void addAnimal(Animals animal) {
        animals.add(animal);
        saveDatabase();
    }

    public void displayAnimalCommands(String name) {
        for (Animals animal : animals) {
            if (animal.getName().equals(name)) {
                animal.displayCommands();
                return;
            }
        }
        System.out.println("Животное " + name + " не найдено");
    }



    public void teachNewCommand(String name, String command) {
        for (Animals animal : animals) {
            if (animal.getName().equals(name)) {
                String[] commands = command.split(",");
                for (int i = 0; i < commands.length; i++) {
                    String trimmedCommand = commands[i].trim();
                    commands[i] = trimmedCommand;
                }
                animal.teachNewCommand(command);
                saveDatabase();
                System.out.println("Команда добавлена ");
                return;
            }
        }
        System.out.println("Животное " + name + " не найдено");
    }


    private void loadDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String className = data[0];
                    String name = data[1];
                    String skills = String.join(",", Arrays.copyOfRange(data, 2, data.length));

                    Animals animal;
                    switch (className) {
                        case "Dog" -> animal = new Dogs(name, skills);
                        case "Cat" -> animal = new Cats(name, skills);
                        case "Hamster" -> animal = new Hamsters(name, skills);
                        case "Donkey" -> animal = new Donkeys(name, skills);
                        case "Horse" -> animal = new Horses(name, skills);
                        default -> {
                            System.out.println("Неизвестный класс животного: " + className);
                            continue;
                        }
                    }
                    animals.add(animal);
                } else {
                    System.out.println("Некорректные данные в файле: " + line);
                }
            }
            System.out.println("База данных успешно загружена.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении базы данных: " + e.getMessage());
        }
    }


    public void displayAllAnimals() {
        try {
            File file = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String animalData = fileScanner.nextLine();
                System.out.println(animalData);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл с данными не найден.");
        }
    }

    private void saveDatabase() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Animals animal : animals) {
                String className = animal.getClass().getSimpleName();
                String name = animal.getName();
                String command = animal.getCommand().replaceAll(",\\s+", ",");

                String line = className + "," + name + "," + command;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("База данных сохранена.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

}
