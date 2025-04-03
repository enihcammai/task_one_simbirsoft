package com.simbirsoft.taskone.utils;

import java.util.Random;

public class CustomerUtils {

    private final static char FIRST_LETTER = 'a';
    private final static int POST_CODE_LENGTH = 10;

    public static String generatePostCode() {
        Random random = new Random();

        return String.format("%0" + POST_CODE_LENGTH + "d", random.nextInt((int) Math.pow(10, POST_CODE_LENGTH)));
    }

    public static String generateFirstName(String postCode) {
        StringBuilder firstName = new StringBuilder();

        for (int i = 0; i < postCode.length(); i += 2) {
            int num = Integer.parseInt(postCode.substring(i, i + 2));
            char letter = (char) (FIRST_LETTER + (num % 26));
            firstName.append(letter);
        }

        return firstName.substring(0,1).toUpperCase() + firstName.substring(1);
    }

    public static String generateLastName(String postCode) {
        return generateFirstName(new StringBuilder(postCode).reverse().toString()); // Обратная генерация, используя First Name
    }
}
