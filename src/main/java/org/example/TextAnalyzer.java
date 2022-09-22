package org.example;

public class TextAnalyzer {
    public static boolean allCharactersSimilar(String text) {
        char[] textMas = text.toCharArray();
        for (int i = 1; i < textMas.length; i++) {
            if (textMas[i - 1] != textMas[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String text) {
        if (allCharactersSimilar(text)) {
            return false;
        }
        return text.equalsIgnoreCase(new StringBuilder(text).reverse().toString());
    }

    public static boolean isIncrementedLetters(String text) {
        char[] textMas = text.toCharArray();
        for (int i = 1; i < textMas.length; i++) {
            if (textMas[i] < textMas[i - 1]) {
                return false;
            }
        }
        if (allCharactersSimilar(text)) {
            return false;
        }
        return true;
    }
}
