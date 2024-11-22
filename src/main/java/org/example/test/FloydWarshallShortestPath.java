package org.example.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FloydWarshallShortestPath {
    private static final int MAX_PATH = 200000;
    private final Scanner scanner;
    private final Map<String, Integer> cityIndex = new HashMap<>();

    public FloydWarshallShortestPath() {
        this.scanner = new Scanner(System.in);
    }

    // Calculate transportation cost for all input cities
    public void calculateTransportationCost() {
        try (scanner) {
            int numberOfTests = scanner.nextInt();
            if (numberOfTests < 1 || numberOfTests > 10) {
                throw new RuntimeException("Input number of tests must be from 1 to 10");
            }

            for (int test = 0; test < numberOfTests; test++) {
                scanner.nextLine();
                int numberOfCities = scanner.nextInt();
                scanner.nextLine();

                int[][] citiesCrossing = fillCitiesCrossing(numberOfCities);
                fillCitiesCrossing(numberOfCities, citiesCrossing);

                findShortestPaths(numberOfCities, citiesCrossing);
                findPathCost(scanner.nextInt(), citiesCrossing);
            }
        }
    }

    /* Fill citiesCrossing array by zero cost city for itself
     * and max cost value between cities by default
     */
    private int[][] fillCitiesCrossing(int numberOfCities) {
        if (numberOfCities < 1 || numberOfCities > 10000) {
            throw new RuntimeException("Input number of cities must be from 1 to 10000");
        }

        int[][] citiesCrossing = new int[numberOfCities + 1][numberOfCities + 1];
        for (int i = 1; i <= numberOfCities; i++) {
            Arrays.fill(citiesCrossing[i], MAX_PATH);
            citiesCrossing[i][i] = 0;
        }
        return citiesCrossing;
    }

    // Fill citiesCrossing array by cities and its neighbors
    private void fillCitiesCrossing(int numberOfCities, int[][] citiesCrossing) {
        for (int i = 1; i <= numberOfCities; i++) {
            cityIndex.put(isValidCityName(scanner.nextLine()), i);

            int neighborsNumber = scanner.nextInt();
            scanner.nextLine();

            //  Fill in the path cost for each neighbor
            for (int j = 0; j < neighborsNumber; j++) {
                int neighborIndex = scanner.nextInt();
                int inputCost = scanner.nextInt();
                citiesCrossing[i][neighborIndex] = inputCost > 0 ? inputCost : MAX_PATH;
                scanner.nextLine();
            }
        }
    }

    // Calculate minimum path cost for all neighbors by Floyd Warshall algorithm
    private void findShortestPaths(int numberOfCities, int[][] citiesCrossing) {
        for (int k = 1; k <= numberOfCities; k++) {
            for (int i = 1; i <= numberOfCities; i++) {
                for (int j = 1; j <= numberOfCities; j++) {
                    if (citiesCrossing[i][k] != MAX_PATH && citiesCrossing[k][j] != MAX_PATH) {
                        citiesCrossing[i][j] = Math.min(citiesCrossing[i][j], citiesCrossing[i][k] + citiesCrossing[k][j]);
                    }
                }
            }
        }
    }

    // Find minimum path cost for a pair of neighbors
    private void findPathCost(int numberOfPaths, int[][] citiesCrossing) {
        scanner.nextLine();
        for (int query = 0; query < numberOfPaths; query++) {
            int startCityIndex = cityIndex.get(checkCity(scanner.next()));
            int endCityIndex = cityIndex.get(checkCity(scanner.next()));
            scanner.nextLine();

            int result = citiesCrossing[startCityIndex][endCityIndex];
            System.out.println(result == MAX_PATH ? "No path" : result);
        }
        System.out.println(System.lineSeparator());
    }

    // Check that input city containing characters a-z and no longer than 10 characters
    public String isValidCityName(String city) {
        if (city == null || !city.matches("[a-z]{1,10}")) {
            throw new RuntimeException("Input city must containing characters a-z"
                    + " and be no longer than 10 characters");
        }
        return city;
    }

    // Check that input city is in our map
    private String checkCity(String city) {
        if (!cityIndex.containsKey(city)) {
            throw new IllegalArgumentException("There is no city " + city);
        }
        return city;
    }
}
