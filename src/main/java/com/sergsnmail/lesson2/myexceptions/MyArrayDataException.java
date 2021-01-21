package com.sergsnmail.lesson2.myexceptions;

public class MyArrayDataException extends RuntimeException {
    private String value;
    private int i,j;

    public MyArrayDataException(String value, int i, int j) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Wrong data value (%s) in cell[%s][%s] of input array", value, i, j );
    }
}
