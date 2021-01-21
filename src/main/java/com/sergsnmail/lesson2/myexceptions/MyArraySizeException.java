package com.sergsnmail.lesson2.myexceptions;

public class MyArraySizeException extends RuntimeException {
    private int size;

    public MyArraySizeException(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("Array size must be %sx%s", size, size);
    }
}
