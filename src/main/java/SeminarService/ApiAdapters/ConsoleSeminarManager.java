package SeminarService.ApiAdapters;

import SeminarService.Controllers.SeminarController;
import SeminarService.Controllers.SeminarControllerException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ConsoleSeminarManager extends SeminarManager {
    SeminarController seminar;
    @Override
    public void startListening(ArrayList<String> persons) throws SeminarControllerException {
        System.out.println("Привет!, Это Seminar Picker!");
        Scanner scanner = new Scanner(System.in);
        if (persons.isEmpty()) {
            initPersons(scanner, persons);
        }
        seminar = new SeminarController(persons);
        printHelp();
        while (true) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                continue;
            }
            switch (line) {
                case "/r":
                    while (true) {
                        if (seminar.allChecked()) {
                            System.out.println("Некого оценивать!");
                            break;
                        }
                        String person = seminar.getRandomPerson();
                        if (seminar.wasAsked(person)) {
                            continue;
                        }
                        System.out.println("Отвечает " + person);
                        System.out.println("Присутствует ли на паре? y/n");
                        String is_here;
                        while (true) {
                            is_here = scanner.next().toLowerCase();
                             if (is_here.equals("y") || is_here.equals("n") || is_here.equals("/q")) {
                                 break;
                             }
                        }
                        if (is_here.equals("y")) {
                            System.out.print("Оценка за ответ: ");
                            Double grade = scanner.nextDouble();
                            seminar.setNewGrade(person, grade);
                            seminar.setVisited(person, true);
                            break;
                        } else if (is_here.equals("n")) {
                            System.out.println("Выбираем заново...");
                            seminar.setVisited(person, false);
                        } else {
                            break;
                        }
                    }
                    break;
                case "/l":
                    System.out.println("------------------------------");
                    Map<String, Double> results = seminar.getResult();
                    for (var person:
                         results.keySet()) {
                        System.out.println(person + ": " + results.get(person));
                    }
                    System.out.println("------------------------------");
                    break;
                case "/h":
                    printHelp();
                    break;
                case "/q":
                    System.out.println("Закрываем программу   ...");
                    return;
                default:
                    System.out.println("Такой команды нет!");
            }
        }
    }

    private void initPersons(Scanner scanner, ArrayList<String> persons) {
        System.out.print("Количество учеников: ");
        int n;
        while (true) {
            try {
                n = scanner.nextInt();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Ошибка! Введи число");
            }
        }
        scanner.nextLine();
        System.out.println("Сами ученики: ");
        for (int i = 0; i < n; ++i) {
            System.out.print((i + 1) + ": ");
            persons.add(scanner.nextLine());
        }
    }

    @Override
    protected void printHelp () {
        System.out.println("===============================");
        System.out.println("/h - Справочник по командам");
        System.out.println("/r - Выбрать счастливчика для ответа на оценку");
        System.out.println("/l - Список ответивших с оценками");
        System.out.println("/q - Выйти из приложения");
        System.out.println("===============================");
    }
}