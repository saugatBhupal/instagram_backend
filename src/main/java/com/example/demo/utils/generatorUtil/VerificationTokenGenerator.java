package com.example.demo.utils.generatorUtil;

import java.util.Random;

public class VerificationTokenGenerator {
    public static String generate() {
        int leftLimit = 48; 
        int rightLimit = 122; 
        int targetStringLength = 8; 
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || (i >= 65 && i <= 90) || (i >= 97 && i <= 122)))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}