package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Artem on 16.10.2016.
 */
public class SelectedDishes extends AppCompatActivity {
    //String data = getIntent().getExtras().getString("testNameData");
    public static final String APP_PREFERENCES = "mysettings";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_dishes);

        String data = getIntent().getStringExtra("name");
        // Инициализируем компонент
        TextView textView = (TextView)findViewById(R.id.textView3);
        textView.setText(data);

    }

    public void onClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.clear();
        ed.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
