package org.example;

import java.util.ArrayList;
import java.util.List;

public class StringSplitter {
    public static void main(String[] args) {
        String input = "12abc3def45ghi67";
        List<String> result = new ArrayList<>();

        StringBuilder current = new StringBuilder();
        boolean isDigit = Character.isDigit(input.charAt(0));

        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
                if (!isDigit) {
                    result.add(current.toString());
                    current.setLength(0); // Clear the StringBuilder
                    isDigit = true;
                }
            } else {
                if (isDigit) {
                    result.add(current.toString());
                    current.setLength(0);
                    isDigit = false;
                }
            }
            current.append(ch);
        }

        // Add the last part to the result
        if (current.length() > 0) {
            result.add(current.toString());
        }

        // Print the result
        System.out.println(result);
    }
}
