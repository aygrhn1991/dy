package com.dy.util;

import java.util.Random;

public class Util {
    public static int getRandomNum(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
