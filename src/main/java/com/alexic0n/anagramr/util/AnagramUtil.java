package com.alexic0n.anagramr.util;

import java.util.Arrays;

public final class AnagramUtil {
    public static String orderString(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
