package com.example.artem.cw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import static com.example.artem.cw.R.layout.main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onToggleButtonClick (View button){
        Toast.makeText(
                getApplicationContext(),
                Boolean.toString(((ToggleButton) button).isChecked()),
                Toast.LENGTH_SHORT).show();

    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Fruit.class);
        startActivity(intent);
    }
}
