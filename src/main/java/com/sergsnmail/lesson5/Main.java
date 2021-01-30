package com.sergsnmail.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        arrayMethod1();
        arrayMethod2();
        arrayMethod3(10);
    }

    private static void arrayMethod1() {
        System.out.println("\nMethod-1 begin. (Single Thread)");
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.printf("Result: arr[0]=%s arr[4999999]=%s, arr[9999999]=%s\n", arr[0], arr[4999999], arr[9999999]);
        System.out.printf("Total time: %s\n", System.currentTimeMillis() - a);
    }

    /**
     * Решение задачи при помощи 2-х потоков
     **/
    private static void arrayMethod2() {
        System.out.println("\nMethod-2 begin. (2 Thread)");
        float[] arr = new float[size];

        Arrays.fill(arr, 1);

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long startTime = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread solver1 = new Thread(new ArraySolver(a1, 0));
        solver1.start();
        Thread solver2 = new Thread(new ArraySolver(a2, h));
        solver2.start();
        System.out.printf("Start time: %s\n", System.currentTimeMillis() - startTime);

        long calcTime = System.currentTimeMillis();
        while (true) {
            if (!solver1.isAlive() && !solver2.isAlive()) {
                break;
            }
        }
        System.out.printf("Calc time: %s\n", System.currentTimeMillis() - calcTime);

        long joinTime = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.printf("Join time: %s\n", System.currentTimeMillis() - joinTime);

        System.out.printf("Result: arr[0]=%s arr[4999999]=%s, arr[9999999]=%s\n", arr[0], arr[4999999], arr[9999999]);
        System.out.printf("Total time: %s\n", System.currentTimeMillis() - startTime);
    }


    /**
     * Решение задачи при помощи произвольного количества (countThread) потоков
     **/
    private static void arrayMethod3(int countThread) {
        System.out.printf("\nMethod-3 begin. (%s Thread)\n", countThread);
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        int h = size / countThread;
        int append = size % countThread;
        Thread[] solvers = new Thread[countThread];
        List<float[]> arrList = new ArrayList<>();
        int srcPos = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < countThread; i++) {
            long startThreadTime = System.currentTimeMillis();
            float[] newArr = new float[h + append];
            System.arraycopy(arr, srcPos, newArr, 0, h + append);
            solvers[i] = new Thread(new ArraySolver(newArr, srcPos));
            solvers[i].start();
            System.out.printf("   %s Thread start time: %s\n", i + 1, System.currentTimeMillis() - startThreadTime);
            arrList.add(i, newArr);
            srcPos += h + append;
            append = 0;
        }
        System.out.printf("Start time: %s\n", System.currentTimeMillis() - startTime);

        long calcTime = System.currentTimeMillis();
        boolean wait = true;
        while (wait) {
            boolean isAlive = false;
            for (Thread solver : solvers) {
                isAlive |= solver.isAlive();
            }
            wait = isAlive;
        }
        System.out.printf("Calc time: %s\n", System.currentTimeMillis() - calcTime);

        long joinTime = System.currentTimeMillis();
        int destPos = 0;
        for (int i = 0; i < countThread; i++) {
            System.arraycopy(arrList.get(i), 0, arr, destPos, arrList.get(i).length);
            destPos += arrList.get(i).length;
        }
        System.out.printf("Join time: %s\n", System.currentTimeMillis() - joinTime);
        System.out.printf("Result: arr[0]=%s arr[4999999]=%s, arr[9999999]=%s\n", arr[0], arr[4999999], arr[9999999]);
        System.out.printf("Total time: %s\n", System.currentTimeMillis() - startTime);
    }

}
