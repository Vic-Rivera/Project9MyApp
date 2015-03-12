package com.example.vic.project9myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddExercises extends Activity {

    private TextView workoutExerciseTitleEditText;
    private EditText exerciseNameEditText;
    private EditText numberOfSetsEditText;
    private Spinner restTimeSpinner;

    private String titleInput;
    int restTime;
    private int restTimeInMili;
    WorkoutsListDB dbhandler;
    List<WorkoutsList> workoutsListObject = new ArrayList<WorkoutsList>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercises);

        workoutExerciseTitleEditText = (TextView) findViewById(R.id.workoutExerciseTitleEditText);
        exerciseNameEditText = (EditText) findViewById(R.id.exerciseNameEditText);
        numberOfSetsEditText = (EditText) findViewById(R.id.numberSetsEditText);
        restTimeSpinner = (Spinner) findViewById(R.id.restTimeSpinner);
        dbhandler = new WorkoutsListDB(this);

        setTitleText();

        addItemsToSetTimeSpinner();
        addListenerToSetTimeSpinner();

        //TODO Database Entry method
        addWorkoutPart();

    }



    public void setTitleText(){
        //Grabs intent data from AddWorkouts Intent
        Bundle workoutTitle = getIntent().getExtras();
        titleInput = workoutTitle.getString("titleInfo");

        //Set Text for title
        workoutExerciseTitleEditText.setText(titleInput);
    }

    public void addItemsToSetTimeSpinner(){
        ArrayAdapter<CharSequence> restTimeSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.rest_times_mins,android.R.layout.simple_spinner_item);
            restTimeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                restTimeSpinner.setAdapter(restTimeSpinnerAdapter);
    }

    public void addListenerToSetTimeSpinner(){
        restTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String restTimeSelected = parent.getItemAtPosition(position).toString();
                if(restTimeSelected.equalsIgnoreCase("30 sec")){
                    restTime = 30000;
                }else if(restTimeSelected.equalsIgnoreCase("1 min")){
                    restTime = 60000;
                }else if(restTimeSelected.equalsIgnoreCase("2 min")){
                    restTime = 120000;
                }else if(restTimeSelected.equalsIgnoreCase("3 min")){
                    restTime = 180000;
                }else{
                    restTime = 1;
                }
                //Testing Toasts
                //Toast.makeText(getBaseContext(),restTimeSelected,Toast.LENGTH_LONG).show();
                //Toast.makeText(getBaseContext(),Integer.toString(restTime),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

    }


    public void addWorkExec(){



    }

    public void addWorkoutPart(){
        WorkoutsList workoutsList = new WorkoutsList(dbhandler.getWorkoutCount(), workoutExerciseTitleEditText.getText().toString());
        dbhandler.addWorkout(workoutsList);
        workoutsListObject.add(workoutsList);
        Toast.makeText(getBaseContext(), workoutExerciseTitleEditText.getText().toString() + "has been created. Add some exercises.", Toast.LENGTH_LONG).show();
        int workoutCount = dbhandler.getWorkoutCount();
        Toast.makeText(getApplicationContext(), Integer.toString(workoutCount) + " workouts in db", Toast.LENGTH_LONG).show();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercises, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.menu_main) {
            Intent i = new Intent(this, MainMenuActivity.class);
            startActivity(i);
            return true;
        }else if (id == R.id.menu_exit){
            //for menu exit button
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
