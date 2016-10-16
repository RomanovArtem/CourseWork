package com.example.artem.cw;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Artem on 16.10.2016.
 */
public class SelectedDishes extends AppCompatActivity {
    //String data = getIntent().getExtras().getString("testNameData");


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_dishes);

        String data = getIntent().getStringExtra("name");
        // Инициализируем компонент
        TextView textView = (TextView)findViewById(R.id.textView3);
        textView.setText(data);

    }



}
