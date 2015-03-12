package com.example.vic.project9myapp;


public class Exercise {

    private int exerciseId;
    private String workoutId;
    private String exerciseName;
    private int numberOfSets;
    private int restPeriod;

    public Exercise() {}

    public Exercise(int exerciseId, String workoutId, String exerciseName, int numberOfSets, int restPeriod){
        this.exerciseId = exerciseId;
        this.workoutId = workoutId;
        this.exerciseName = exerciseName;
        this. numberOfSets = numberOfSets;
        this.restPeriod = restPeriod;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public int getRestPeriod() {
        return restPeriod;
    }

    public void setRestPeriod(int restPeriod) {
        this.restPeriod = restPeriod;
    }
}
