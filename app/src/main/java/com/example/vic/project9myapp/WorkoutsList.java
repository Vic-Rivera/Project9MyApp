package com.example.vic.project9myapp;


public class WorkoutsList {

    private int id;
    private String workoutName;

    public WorkoutsList() {}


    //whats this one for?
    public WorkoutsList(String workoutName) {
        this.workoutName = workoutName;
    }

    public WorkoutsList(int id, String workoutName){
        this.id = id;
        this.workoutName = workoutName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    @Override
    public String toString() {
        return workoutName;
    }

}
