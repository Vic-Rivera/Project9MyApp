package com.example.vic.project9myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;


public class AddWorkouts extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workouts);



    }

    public void createWorkout(View view) {

        final EditText title = (EditText) findViewById(R.id.workoutTitleEditText);
        String titleInput = title.getText().toString();
        Context context = AddWorkouts.this;

        if(TextUtils.isEmpty(titleInput)){
            Toast.makeText(context, "Title Field is Empty", Toast.LENGTH_LONG).show();

        }else{
            Intent i = new Intent(this, AddExercises.class);
            i.putExtra("titleInfo", titleInput);
            startActivity(i);
        }



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
