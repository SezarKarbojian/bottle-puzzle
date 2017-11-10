package com.karbo;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolutionsGenerator {

    private Bottle smallBottle;
    private Bottle bigBottle;
    private int amountToMeasure;
    private Set<Integer> listOfActions = Stream.of(1, 2, 3, 4, 5, 6, 7).collect(Collectors.toSet());
    private List<Solution> generatedSolutions;

    public SolutionsGenerator(Bottle smallBottle, Bottle bigBottle, int amountToMeasure) {
        this.smallBottle = smallBottle;
        this.bigBottle = bigBottle;
        this.amountToMeasure = Objects.requireNonNull(amountToMeasure);
    }

    public List<Solution> generateSolutions() {
        generatedSolutions = new ArrayList<>();
        tryCombinations(listOfActions, new Stack<>(), listOfActions.size());
        return generatedSolutions;
    }


    private void tryCombinations(Set<Integer> items, Stack<Integer> permutation, int size) {
        if (permutation.size() == size) {
            //trySolution(permutation.toArray(new Integer[0]));
            trySolution(new ArrayList(permutation));
        }
        Integer[] availableItems = items.toArray(new Integer[0]);
        for (Integer i : availableItems) {
            permutation.push(i);
            items.remove(i);
            tryCombinations(items, permutation, size);
            items.add(permutation.pop());
        }
    }


    private void trySolution(List<Integer> actions) {
        //List<Integer> actions = Arrays.asList(a);
        List<Step> steps = new ArrayList<>();
        int nrOfActionsTaken = 0;
        while (nrOfActionsTaken < 6) {
            for (Integer action : actions) {
                if (takeNextActionIfValid(action)) {
                    steps.add(new Step(bigBottle.getAmount(), smallBottle.getAmount()));
                    nrOfActionsTaken++;
                }
                if (bigBottle.getAmount() == amountToMeasure || smallBottle.getAmount() == amountToMeasure) {
                    storeSolution(steps, true);
                    return;
                }
            }
        }
        storeSolution(steps, false);
    }


    private void storeSolution(List<Step> steps, boolean isCorrect) {
        generatedSolutions.add(new Solution(steps, isCorrect));
        bigBottle.setAmount(0);
        smallBottle.setAmount(0);
    }


    /**
     * @param actionNr
     * @return true if action is valid, false if not
     */
    private boolean takeNextActionIfValid(int actionNr) {

        boolean isValidAction = false;
        switch (actionNr) {
            case 1:
                if (!smallBottle.isEmpty()) {
                    smallBottle.setAmount(0);
                    isValidAction = true;
                }
                break;
            case 2:
                if (!bigBottle.isEmpty()) {
                    bigBottle.setAmount(0);
                    isValidAction = true;
                }
                break;
            case 3:
                if (!smallBottle.isFull()) {
                    smallBottle.setAmount(smallBottle.getCapacity());
                    isValidAction = true;
                }
                break;
            case 4:
                if (!bigBottle.isFull()) {
                    bigBottle.setAmount(bigBottle.getCapacity());
                    isValidAction = true;
                }
                break;
            case 5:
                if (!smallBottle.isFull()) {
                    isValidAction = pourFirstBottleIntoSecond(bigBottle, smallBottle);
                }
                break;
            case 6:
                if (!bigBottle.isFull()) {
                    isValidAction = pourFirstBottleIntoSecond(smallBottle, bigBottle);
                }
                break;
            case 7:
                if (!smallBottle.isFull()) {
                    isValidAction = pourFirstBottleIntoSecond(bigBottle, smallBottle);
                }
                break;
        }
        return isValidAction;
    }


    private boolean pourFirstBottleIntoSecond(Bottle firstBottle, Bottle secondBottle) {
        if (firstBottle == secondBottle) {
            throw new IllegalArgumentException("Cant pour bottle into itself!");
        }
        int spaceFreeInSecondBottle = secondBottle.getCapacity() - secondBottle.getAmount();
        int quantityToBePoured =
                spaceFreeInSecondBottle > firstBottle.getAmount() ? firstBottle.getAmount() : spaceFreeInSecondBottle;
        if (quantityToBePoured == 0) {
            return false;
        }
        secondBottle.setAmount(secondBottle.getAmount() + quantityToBePoured);
        firstBottle.setAmount(firstBottle.getAmount() - quantityToBePoured);
        return true;
    }

}
