package com.example.artem.cw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
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
        System.out.println("Что пришло: " + data);
        String[] parts = data.split("/");
        String part1 = parts[0];
        String part2 = parts[1];

        String[] dish1 = part1.split(";");
        String[] dish2 = part2.split(";");




        // Инициализируем компонент
      //  TextView textView = (TextView)findViewById(R.id.textView3);
       // textView.setText(data);


        RelativeLayout lMain;
        lMain = (RelativeLayout) findViewById(R.id.selected);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        lParams.topMargin = 130;

        TextView textView = new TextView(this);
        textView.setText(data);

        lMain.addView(textView, lParams);

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
