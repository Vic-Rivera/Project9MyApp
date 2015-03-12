package com.example.vic.project9myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectWorkout extends Activity {

    ListView workoutListView;
    List<WorkoutsList> workoutsListViewList = new ArrayList<WorkoutsList>();
    WorkoutsListDB dbHandler;
    Button buttonWorkoutSelect;
    EditText woNameEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_workout);

        buttonWorkoutSelect = (Button) findViewById(R.id.buttonWorkoutSelect);
        woNameEditText = (EditText) findViewById(R.id.woNameEditText);
        workoutListView = (ListView) findViewById(R.id.workoutListView);
        dbHandler = new WorkoutsListDB(this);

        buttonWorkoutSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addWorkoutsToView(woNameEditText.getText().toString());
                populateList();
                Toast.makeText(getApplicationContext(), woNameEditText.getText().toString() + " has been added!", Toast.LENGTH_SHORT).show();
            }
        });

        List<WorkoutsList> workoutsToListView = dbHandler.getAllWorkouts();
        int workoutCount = dbHandler.getWorkoutCount();

        for (int i = 0; i <workoutCount; i ++){
            workoutsListViewList.add(workoutsToListView.get(i));
        }

        if (!workoutsToListView.isEmpty())
            populateList();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_workout, menu);
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

    public void workoutGo(View view){
        Intent i = new Intent(this, WorkoutGo.class);
        startActivity(i);
    }

    private void populateList(){
        ArrayAdapter<WorkoutsList> adapter = new WorkoutListAdapter();
        workoutListView.setAdapter(adapter);
    }

    //add contact
    private void addWorkoutsToView(String workoutName){
        workoutsListViewList.add(new WorkoutsList(workoutName));
    }

    private class WorkoutListAdapter extends ArrayAdapter<WorkoutsList> {
        public WorkoutListAdapter() {
            super (SelectWorkout.this, R.layout.activity_layout_select_workout_listview,workoutsListViewList);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.activity_layout_select_workout_listview, parent, false);

            WorkoutsList currentWorkout = workoutsListViewList.get(position);

            TextView workoutName = (TextView) view.findViewById(R.id.nameListText);
            workoutName.setText(currentWorkout.getWorkoutName());

            return view;
        }
    }

}
