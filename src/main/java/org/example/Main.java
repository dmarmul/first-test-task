package org.example;

import java.math.BigInteger;
import java.util.stream.IntStream;
import org.example.test.Bracket;
import org.example.test.FloydWarshallShortestPath;

public class Main {
    public static void main(String[] args) {
        Bracket bracket = new Bracket();
        bracket.bracketCalculate();

//        FloydWarshallShortestPath floydWarshallShortestPath = new FloydWarshallShortestPath();
//        floydWarshallShortestPath.calculateTransportationCost();

//        System.out.println(getSumDigitsInNumber(100));
    }

    public static int getSumDigitsInNumber(int number) {
        // Numbers greater than 50000 will be difficult to calculate
        if (number < 1 || number > 50000) {
            return 0;
        }

        // Find sum of digits of BigInteger factorial
        return IntStream.rangeClosed(2, number)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .get()
                .toString()
                .chars()
                .map(Character::getNumericValue)
                .sum();
    }
}
