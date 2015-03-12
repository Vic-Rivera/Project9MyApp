package com.example.vic.project9myapp;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;



public class WorkoutsListDB extends SQLiteOpenHelper {

    //constants
    public static final String DB_NAME = "workoutslist.db";
    public static final int DB_VERSION = 1;

    //-----------------------------------------------------------------

    //workoutlist constants
    public static final String WORKOUTS_TABLE = "workouts";

    public static final String WORKOUT_ID = "_id";
    public static final int WORKOUT_ID_COL = 0;

    public static final String WORKOUT_NAME = "workout_name";
    public static final int WORKOUT_NAME_COL = 1;

    //-----------------------------------------------------------------

    //exercise table constants
    public static final String EXERCISE_TABLE = "exercises";

    public static final String EXERCISE_ID = "_id";
    public static final int EXERCISE_ID_COL = 0;

    public static final String WORKOUT_LIST_ID = "workout_id";
    public static final int WORKOUT_LIST_ID_COL = 1;

    public static final String EXERCISE_NAME = "exercise_name";
    public static final int EXERCISE_NAME_COL = 2;

    public static final String NUMBER_OF_SETS = "number_of_sets";
    public static final int NUMBER_OF_SETS_COL = 3;

    public static final String REST_PERIOD = "rest_period";
    public static  final int REST_PERIOD_COL = 4;

    //-----------------------------------------------------------------

    public WorkoutsListDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_WORKOUTS_TABLE);
        db.execSQL(CREATE_EXERCISE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_WORKOUTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_EXERCISE_TABLE);

    }


    //----------------CRUD----------OPERATIONS-------------------------------------------

    //CREATE Workout
    public void addWorkout(WorkoutsList workout){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues workoutValues = new ContentValues();
        workoutValues.put(WORKOUT_NAME, workout.getWorkoutName());

        db.insert(WORKOUTS_TABLE, null, workoutValues);
        //db.close();
    }

    //CREATE Exercise
    public void addExercise(Exercise exercise){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues exerciseValues = new ContentValues();
        exerciseValues.put(WORKOUT_LIST_ID, exercise.getWorkoutId());
        exerciseValues.put(EXERCISE_NAME, exercise.getExerciseName());
        exerciseValues.put(NUMBER_OF_SETS, exercise.getNumberOfSets());
        exerciseValues.put(REST_PERIOD, exercise.getRestPeriod());
        db.insert(EXERCISE_TABLE, null, exerciseValues);
        db.close();
    }

    //SELECT Workout
    public WorkoutsList getWorkout(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(WORKOUTS_TABLE, new String[] {WORKOUT_ID, WORKOUT_NAME}, WORKOUT_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        WorkoutsList workoutsList = new WorkoutsList(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        db.close();
        cursor.close();
        return workoutsList;
    }

    //SELECT Exercise
    public Exercise getExercise(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(EXERCISE_TABLE, new String[] {EXERCISE_ID, WORKOUT_LIST_ID, EXERCISE_NAME, NUMBER_OF_SETS, REST_PERIOD}, EXERCISE_ID + "=?", new String[] {String.valueOf(id)}, null, null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Exercise exercise = new Exercise((Integer.parseInt(cursor.getString(0))), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
        return exercise;
    }

    //Count total workouts in Table
    public int getWorkoutCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + WORKOUTS_TABLE, null);
        int count = cursor.getCount();
        //db.close();
        cursor.close();

        return count;
    }

    public int getExerciseCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EXERCISE_TABLE, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    //DELETE Workout
    public void deleteWorkout(WorkoutsList workoutsList){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(WORKOUTS_TABLE, WORKOUT_ID + "=?", new String[] {String.valueOf(workoutsList.getId())});
        db.close();
    }

    //UPDATE
    //TODO

    //SELECT ALL Workouts
    public List<WorkoutsList> getAllWorkouts(){
        List<WorkoutsList> workoutsLists = new ArrayList<WorkoutsList>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + WORKOUTS_TABLE, null);

        if (cursor.moveToFirst()){
            do{
                WorkoutsList workoutsList = new WorkoutsList(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                workoutsLists.add(workoutsList);
            }
            while (cursor.moveToNext());
        }
        return workoutsLists;
    }

    public List<Exercise> getAllExercises(String workoutId){
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EXERCISE_TABLE + " WHERE " + WORKOUT_LIST_ID + "=" + workoutId, null);

        if (cursor.moveToFirst()){
            do{
                Exercise exercise = new Exercise((Integer.parseInt(cursor.getString(0))), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)));
                exerciseList.add(exercise);
            }
            while(cursor.moveToNext());
        }
        return exerciseList;
    }



    //CREATE Table statements

    public static final String CREATE_WORKOUTS_TABLE =
            "CREATE TABLE " + WORKOUTS_TABLE + "(" +
            WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            WORKOUT_NAME + " TEXT NOT NULL UNIQUE)";

    public static final String CREATE_EXERCISE_TABLE =
            "CREATE TABLE " + EXERCISE_TABLE + " (" +
            EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            WORKOUT_LIST_ID + " TEXT NOT NULL, " +
            EXERCISE_NAME + " TEXT, " +
            NUMBER_OF_SETS + " INTEGER, " +
            REST_PERIOD + " INTEGER);";





}
