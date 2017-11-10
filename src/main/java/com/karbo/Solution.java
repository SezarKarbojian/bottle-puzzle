package com.karbo;

import java.util.List;

public class Solution {

    private List<Step> steps;
    private boolean isCorrect = false;

    public Solution(List<Step> steps, boolean isCorrect) {
        this.steps = steps;
        this.isCorrect = isCorrect;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
