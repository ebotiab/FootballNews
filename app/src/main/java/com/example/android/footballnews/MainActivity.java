package com.example.android.footballnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout screen = findViewById(R.id.touch);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FootballNewActivity}
                Intent menuIntent = new Intent(MainActivity.this, FootballNewActivity.class);

                // Start the new activity
                startActivity(menuIntent);
            }
        });

        // Create a click listener in the wall title
        ConstraintLayout touchScreen = findViewById(R.id.touch);
        touchScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FootballNewActivity}
                Intent menuIntent = new Intent(MainActivity.this, FootballNewActivity.class);

                // Start the new activity
                startActivity(menuIntent);
            }
        });

        // Create the same click listener also in the init touch message
        TextView touchText = findViewById(R.id.init_text);
        touchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FootballNewActivity}
                Intent menuIntent = new Intent(MainActivity.this, FootballNewActivity.class);

                // Start the new activity
                startActivity(menuIntent);
            }
        });

    }
}