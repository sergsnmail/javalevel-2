package com.sergsnmail.lesson5;

public class ArraySolver implements Runnable {
    float[] arr;
    int coeff;

    public ArraySolver (float[] arr, int coeff){
        this.arr = arr;
        this.coeff = coeff;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + coeff / 5) * Math.cos(0.2f + coeff / 5) * Math.cos(0.4f + coeff / 2));
            coeff++;
        }
    }
}
