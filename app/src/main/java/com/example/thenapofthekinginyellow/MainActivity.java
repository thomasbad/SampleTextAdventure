package com.example.thenapofthekinginyellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_Play, btn_Help, btn_About;
    boolean loadStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartGame();
        GoAbout();
        GoHelp();
    }

    protected void StartGame() {
        btn_Play = findViewById(R.id.btn_Play);
        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }


    protected void GoAbout() {
        btn_About = findViewById(R.id.btn_About);
        btn_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    protected void GoHelp() {
        btn_Help = findViewById(R.id.btn_Help);
        btn_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HelpActivity.class));
            }
        });
    }

}

