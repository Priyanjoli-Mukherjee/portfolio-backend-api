package com.heroku.java.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtils {

    private static String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public static boolean randomBoolean() {
        final Random random = new Random();
        return random.nextBoolean();
    }

    public static int randomInteger(int bound) {
        if (bound <= 0) {
            return bound;
        }
        final Random random = new Random();
        return random.nextInt(bound);
    }

    public static Date randomDate() {
        final Random random = new Random();
        Date date = new Date();
        date.setTime(random.nextLong(date.getTime()));
        return date;
    }

    public static String randomText() {
        Pattern capitalLettersRegex = Pattern.compile("[A-Z]");
        Pattern periodRegex = Pattern.compile("\\.");

        String[] letters = LOREM_IPSUM.split("");

        ArrayList<Integer> capitalLetterIndices = new ArrayList<>();

        for (int i = 0; i < letters.length; i++) {
            Matcher capitalLetterMatcher = capitalLettersRegex.matcher(letters[i]);
            boolean isMatchCapitalLetter = capitalLetterMatcher.matches();

            if (isMatchCapitalLetter) {
                capitalLetterIndices.add(i);
            }
        }

        int k = capitalLetterIndices.get(randomInteger(capitalLetterIndices.size()));

        ArrayList<Integer> periodIndices = new ArrayList<>();

        for (int i = k + 1; i < letters.length; i++) {
            Matcher periodMatcher = periodRegex.matcher(letters[i]);
            boolean isMatchPeriod = periodMatcher.matches();

            if (isMatchPeriod) {
                periodIndices.add(i);
            }
        }

        int l = periodIndices.get(randomInteger(periodIndices.size()));

        return LOREM_IPSUM.substring(k, l);
    }

    public static <T> T randomArrayElement(T[] arr) {
        return arr[randomInteger(arr.length)];
    }

    public static <T> T randomListElement(ArrayList<T> list) {
        return list.get(randomInteger((list.size())));
    }
}
