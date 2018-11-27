package com.example.alex.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void firstChapter (View view){
        Intent firstChapter = new Intent(this , FirstChapter.class);
        startActivity(firstChapter);
    }
    public void criminal(View view){
        Intent criminal = new Intent(this , CrimeListActivity.class);
        startActivity(criminal);
    }
}
