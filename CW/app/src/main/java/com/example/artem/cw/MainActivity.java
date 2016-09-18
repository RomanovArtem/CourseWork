package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;



public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    public void onToggleButtonClick (View view)
    {
        toggleButton = (ToggleButton) findViewById(R.id.fruit);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Fruit.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.vegetables);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Vegetables.class);
            startActivity(intent);
        }

    }


    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Vegetables.class);
        startActivity(intent);
    }
}
