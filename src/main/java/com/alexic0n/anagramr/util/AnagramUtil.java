package com.alexic0n.anagramr.util;

import java.util.Arrays;

public final class AnagramUtil {
    public static String orderAndSanitizeString(String input) {
        char[] chars = input.replaceAll("[^A-Za-z]", "").toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
