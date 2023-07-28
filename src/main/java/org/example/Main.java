package org.example;

public class Main {
    public static void main(String[] args) {
        Data database = new Data();
        View menu = new View(database);
        menu.Menu();
    }
}