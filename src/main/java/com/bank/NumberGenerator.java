package com.bank;

import java.util.Random;

public class NumberGenerator {
    public static long get(){
        return (new Random()).nextLong();
    }

}
