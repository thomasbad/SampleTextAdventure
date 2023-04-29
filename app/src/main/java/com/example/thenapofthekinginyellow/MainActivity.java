package com.example.thenapofthekinginyellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_Play, btn_loadGame, btn_Help, btn_About;
    boolean loadStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //StartGame();
        //LoadGame();
        GoAbout();
        GoHelp();
    }

    protected void StartGame() {
        btn_Play = findViewById(R.id.btn_Play);
        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loadStatus = false;
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }

//    protected void LoadGame() {
//        btn_Play = findViewById(R.id.btn_Play);
//        btn_Play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //loadStatus = true;
//                startActivity(new Intent(MainActivity.this, GameActivity.class));
//            }
//        });
//    }

    protected void GoAbout() {
        btn_Play = findViewById(R.id.btn_About);
        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    protected void GoHelp() {
        btn_Play = findViewById(R.id.btn_Help);
        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HelpActivity.class));
            }
        });
    }

}

