package com.sergsnmail.lesson3;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        /**
         * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
         * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
         * Посчитать сколько раз встречается каждое слово.
         */
        String[] words = new String[]{"кошка", "собака", "воробей", "собака", "слон", "собака", "кошка", "слон",
                                      "кошка", "собака", "слон", "собака", "слон", "кракен", "собака", "воробей"};

        Map<String, Integer> wordSet = new TreeMap<>();
        for (String word : words) {
            wordSet.put(word, wordSet.getOrDefault(word, 0) + 1);
        }

        for (String key : wordSet.keySet()) {
            System.out.printf("Слово \'%s\' встречается %s раз\n", key, wordSet.get(key));
        }


        /**
         * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
         * В этот телефонный справочник с помощью метода add() можно добавлять записи.
         * С помощью метода get() искать номер телефона по фамилии.
         * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
         * тогда при запросе такой фамилии должны выводиться все телефоны.
         */
        System.out.println();
        Phonebook phonebook = new Phonebook();

        phonebook.add("Иванов", "+7(999)999-99-99");

        phonebook.add("Петров", "+7(888)888-88-88");
        phonebook.add("Петров", "+7(666)666-66-66");

        phonebook.add("Сидоров", "88-88-88");
        phonebook.add("Сидоров", "55-55-55");
        phonebook.add("Сидоров", "777-77-77");
        /**
         * Добавляем дубликат
         */
        phonebook.add("Сидоров", "777-77-77");

        String name = "";
        System.out.println(String.format("Имя: %s\nТелефоны: %s\n", name, phonebook.get(name)));
        name = "Иванов";
        System.out.println(String.format("Имя: %s\nТелефоны: %s\n", name, phonebook.get(name)));
        name = "Петров";
        System.out.println(String.format("Имя: %s\nТелефоны: %s\n", name, phonebook.get(name)));
        name = "Сидоров";
        System.out.println(String.format("Имя: %s\nТелефоны: %s\n", name, phonebook.get(name)));
    }
}
