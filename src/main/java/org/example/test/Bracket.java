package org.example.test;

import java.util.Scanner;

public class Bracket {

    public void bracketCalculate() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Input number of brackets: ");
            int numberBrackets = scanner.nextInt();
            System.out.println("Number of brackets expressions for your input " + numberBrackets
                    + " = " + countBracketExpressions(numberBrackets));
        }
    }

    // Method for calculating number of bracket expressions by Catalan number
    public static int countBracketExpressions(int numberBrackets) {
        if (numberBrackets <= 0) {
            throw new IllegalArgumentException("Number of brackets must be greater than 0");
        }

        int[] catalan = new int[numberBrackets + 1];
        catalan[0] = 1;

        for (int i = 1; i <= numberBrackets; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - 1 - j];
            }
        }
        return catalan[numberBrackets];
    }
}
