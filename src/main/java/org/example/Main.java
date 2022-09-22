package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import static org.example.TextGenerator.generateText;

public class Main {
    static AtomicInteger goodNicknamesNumWithThreeLetters = new AtomicInteger(0);
    static AtomicInteger goodNicknamesNumWithFourthLetters = new AtomicInteger(0);
    static AtomicInteger goodNicknamesNumWithFiveLetters = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread firstThread = new Thread(() -> {
            for (String text: texts) {
                analyze(text, TextAnalyzer::allCharactersSimilar);
            }
        });

        Thread secondThread = new Thread(() -> {
            for (String text: texts) {
                analyze(text, TextAnalyzer::isPalindrome);
            }
        });

        Thread thirdThread = new Thread(() -> {
            for (String text: texts) {
                analyze(text, TextAnalyzer::isIncrementedLetters);
            }
        });

        firstThread.start();
        secondThread.start();
        thirdThread.start();

        while (firstThread.isAlive() || secondThread.isAlive() || thirdThread.isAlive()) {

        }
        System.out.println("Красивых слов с длиной 3: " + goodNicknamesNumWithThreeLetters);
        System.out.println("Красивых слов с длиной 4: " + goodNicknamesNumWithFourthLetters);
        System.out.println("Красивых слов с длиной 5: " + goodNicknamesNumWithFiveLetters);
    }

    private static void analyze(String text, Analyzer analyzer) {
        switch (text.length()) {
            case 3 -> {
                if (analyzer.operation(text)) {
                    goodNicknamesNumWithThreeLetters.incrementAndGet();
                }
            }
            case 4 -> {
                if (analyzer.operation(text)) {
                    goodNicknamesNumWithFourthLetters.incrementAndGet();
                }
            }
            case 5 -> {
                if (analyzer.operation(text)) {
                    goodNicknamesNumWithFiveLetters.incrementAndGet();
                }
            }
        }
    }
}