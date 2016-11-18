package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Artem on 13.11.2016.
 */
public class Recipe extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mysettings";
    Client2 client2 = new Client2();
    Intent intent;

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


    boolean flag = false;
   public void ClickImageButton(View view) {
       ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
       if (!flag) {
           imageButton.setImageResource(R.drawable.nstar);
           flag = true;
       }
       else {
           imageButton.setImageResource(R.drawable.ystar);
           flag = false;
       }
   }




    public void onClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        // ed.clear();
        // ed.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
