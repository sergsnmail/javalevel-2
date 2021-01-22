package com.sergsnmail.lesson3;

import java.util.TreeSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;

public class Phonebook {

    /**
     * Упорядоченое множество для хранения пары ключ-значение
     * ключ - фамилия
     * значение - упорядоченое множество уникальных значений номеров телефонов
     */
    private final Map<String, Set<String>> repo;

    public Phonebook() {
        repo = new TreeMap<>();
    }

    public void add(String name, String phone) {
        if (name.equals("") || phone.equals("")) {
            throw new IllegalArgumentException("Arguments \'name\' and \'phone\', has not be empty value");
        }

        Set<String> phoneList = repo.getOrDefault(name, new TreeSet<>());
        phoneList.add(phone);
        repo.put(name, phoneList);
    }

    public Set<String> get(String name) {
        return repo.get(name);
    }
}
