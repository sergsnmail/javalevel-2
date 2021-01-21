package com.sergsnmail.lesson2;

import com.sergsnmail.lesson2.myexceptions.MyArrayDataException;
import com.sergsnmail.lesson2.myexceptions.MyArraySizeException;

public class Main {

    private static final int ARRAY_SIZE = 4;

    public static void main(String[] args) {

        /**
         *  Передаем методу хороший массив
         */
        System.out.println("Передаем методу хороший массив:");
        System.out.println(String.format("Результат %s\n", testMethod(createCorrectArray())));

        /**
         *  Передаем методу массив неправильного размера
         */
        System.out.println("Передаем методу массив неправильного размера:");
        System.out.println(String.format("Результат %s\n", testMethod(createBadSizeArray())));

        /**
         *  Передаем методу массив с полохими данными
         */
        System.out.println("Передаем методу массив с полохими данными:");
        System.out.println(String.format("Результат %s\n", testMethod(createBadContentArray())));

    }

    private static int testMethod(String[][] arr) {
        int result = 0;
        try {
            result = calcArray(arr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("ERROR: " + e);
        }
        return result;
    }


    private static int calcArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length > ARRAY_SIZE || array.length < 1) {
            throw new MyArraySizeException(ARRAY_SIZE);
        }

        for (String[] subArr : array) {
            if (subArr.length > ARRAY_SIZE || array.length < 1) {
                throw new MyArraySizeException(ARRAY_SIZE);
            }
        }

        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(array[i][j], i, j);
                }
            }
        }
        return result;
    }

    private static String[][] createCorrectArray() {
        return new String[][]{{"1", "2", "3", "4"},
                              {"5", "6", "7", "8"},
                              {"9", "9", "10", "11"},
                              {"12", "13", "14", "15"}};
    }

    private static String[][] createBadContentArray() {
        return new String[][]{{"1", "2", "3", "4"},
                              {"5", "6", "7", "8"},
                              {"9", "digit-1", "10", "11"},
                              {"12", "13", "14", "15"}};
    }

    private static String[][] createBadSizeArray() {
        return new String[][]{{"1", "2", "3", "4"},
                              {"5", "6", "7", "8"},
                              {"9", "9", "10", "11"},
                              {"12", "13", "14", "15"},
                              {"1", "2", "3", "4", "5"},};
    }
}
