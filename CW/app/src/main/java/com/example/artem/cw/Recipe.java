package com.example.artem.cw;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Artem on 13.11.2016.
 */
public class Recipe extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        TextView textView = (TextView)findViewById(R.id.content);
        TextView a = (TextView)findViewById(R.id.title);
        String recipe = getIntent().getStringExtra("recipe");
        String title = getIntent().getStringExtra("title");

        textView.setText(recipe);
        a.setText(title);
    }
}
