package onboarding;

import java.util.regex.Pattern;

public class Problem4 {
    public static String solution(String word) {
        String answer = getConversionWord(word);
        return answer;
    }

    static String getConversionWord(String word) {
        int len = word.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char character = word.charAt(i);
            if (isUpperCase(character)) {
                result.append(upperCaseConversion(character));
            }
            else if (isLowerCase(character)) {
                result.append(lowerCaseConversion(character));
            }
            else {
                result.append(character);
            }
        }

        return result.toString();
    }

    static StringBuilder processConversion(StringBuilder result, char character) {
        boolean isAlphabet = isAlphabet(character);
        if (isAlphabet) {

        }
        else if (isAlphabet) {
            result.append(character);
            return result;
        }
    }

    static boolean isAlphabet(char character) {
        String pattern = "[a-zA-z]";
        boolean regex = Pattern.matches(pattern, Character.toString(character));

        return regex;
    }

    static boolean isUpperCase(char character) {
        if (character >= 'A' && character <= 'Z') {
            return true;
        }
        return false;
    }

    static boolean isLowerCase(char character) {
        if (character >= 'a' && character <= 'z') {
            return true;
        }
        return false;
    }

    static Character upperCaseConversion(char character) {
        int move = character - 'A';
        char result =  (char) ('Z' - move);

        return result;
    }

    static Character lowerCaseConversion(char character) {
        int move = character - 'a';
        char result = (char) ('z' - move);

        return result;
    }

    public static void main(String[] args) {
        char ex = '@';
        System.out.println(isAlphabet(ex));
    }
}
