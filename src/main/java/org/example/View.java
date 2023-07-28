package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private final Data database;
    private final Scanner scanner;

    public View(Data database) {
        this.database = database;
        scanner = new Scanner(System.in);
    }

    public void Menu() {
        while (true) {
            try {
                System.out.println("Меню:");
                System.out.println("1. Добавить новое животное");
                System.out.println("2. Показать список всех животных");
                System.out.println("3. Просмотреть список команд животного");
                System.out.println("4. Обучить животное новой команде");
                System.out.println("0. Выход");
                System.out.print("Выберите пункт меню: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addNewAnimal();
                    case 2 -> database.displayAllAnimals();
                    case 3 -> displayAnimalCommands();
                    case 4 -> teachNewCommand();
                    case 0 -> {
                        System.out.println("Программа завершена.");
                        return;
                    }
                    default -> System.out.println("Ошибка.Попробуйте снова");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: неверный формат ввода. Попробуйте снова.");
                scanner.nextLine();
            }
        }
    }


    private void addNewAnimal() {
        System.out.println("Выберите класс животного:");
        System.out.println("1. Кошки");
        System.out.println("2. Собаки");
        System.out.println("3. Хомяки");
        System.out.println("4. Лошади");
        System.out.println("5.Ослы");
        int animalsClass = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите имя животного:");
        String name = scanner.nextLine();
        System.out.println("Введите список команд через запятую:");
        String command = scanner.nextLine();

        Animals animals;
        switch (animalsClass) {
            case 1 -> animals = new Cats(name, command);
            case 2 -> animals = new Dogs(name, command);
            case 3 -> animals = new Hamsters(name, command);
            case 4 -> animals = new Horses(name, command);
            case 5 -> animals = new Donkeys(name, command);
            default -> {
                System.out.println("Неверный выбор класса животного.");
                return;
            }
        }


        database.addAnimal(animals);
        System.out.println("Животное успешно добавлено в базу данных.");
    }

    private void displayAnimalCommands() {
        System.out.println("Введите имя животного:");
        String name = scanner.nextLine();

        database.displayAnimalCommands(name);
    }

    private void teachNewCommand() {
        System.out.println("Введите имя животного:");
        String name = scanner.nextLine();
        System.out.println("Введите новые команды через запятую:");
        String command = scanner.nextLine();

        database.teachNewCommand(name, command);
        System.out.println("Команда успешно добавлена для животного.");
    }
}
