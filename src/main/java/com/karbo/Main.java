package com.karbo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        System.setProperty("amountToMeasure", "4");

        String input = Objects.requireNonNull(System.getProperty("amountToMeasure"), "amountToMeasure can't be null");
        int amountToMeasure;
        try {
            amountToMeasure = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("amountToMeasure must be an integer");
            return;
        }
        if (amountToMeasure < 0 || amountToMeasure > 5) {
            throw new IllegalArgumentException("Cant measure less than 0 and more than 5");
        }
        SolutionsGenerator solutionsGenerator = new SolutionsGenerator(new Bottle(Capacity.THREE), new Bottle(Capacity.FIVE), amountToMeasure);
        List<Solution> attemptedSolutions = solutionsGenerator.generateSolutions();
        printBestSolution(attemptedSolutions);
    }


    private static void printBestSolution(List<Solution> attemptedSolutions) {
        System.out.println("Tried " + attemptedSolutions.size() + " solutions");
        System.out.println("Number of correct solutions found: " + attemptedSolutions.stream().filter(Solution::isCorrect).count());
        Solution bestSolution = attemptedSolutions
                .stream()
                .filter(Solution::isCorrect)
                .sorted(Comparator.comparingInt(s1 -> s1.getSteps().size()))
                .findFirst().orElse(new Solution(new ArrayList<>(), false));
        System.out.println("Best solution has " + bestSolution.getSteps().size() + "  steps: ");
        bestSolution.getSteps().forEach(s -> System.out.println(s.toString()));
    }

}