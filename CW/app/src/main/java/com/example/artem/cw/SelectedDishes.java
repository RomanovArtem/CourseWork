package com.example.artem.cw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.ToggleButton;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Artem on 16.10.2016.
 */
public class SelectedDishes extends AppCompatActivity {
    //String data = getIntent().getExtras().getString("testNameData");
    public static final String APP_PREFERENCES = "mysettings";
    Client2 client2 = new Client2();
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_dishes);

        String titleDish = getIntent().getStringExtra("titleDish");
        String idDish = getIntent().getStringExtra("idDish");
        System.out.println("Что пришло: " + titleDish);
        System.out.println("id которые пришли: " + idDish);
        String[] partsTitle = titleDish.split("/");
        String[] partsId = idDish.split("/");

        String partTitle1 = partsTitle[0];
        String partTitle2 = partsTitle[1];
        String partId1 = partsId[0];
        String partId2 = partsId[1];

        final String[] dish1 = partTitle1.split(";");
        final String[] dish2 = partTitle2.split(";");
        final String[] id1 = partId1.split(";");
        final String[] id2 = partId2.split(";");

        int i = 0;
        int margin = 0;
        TextView textView = null;
        RelativeLayout lMain;
        lMain = (RelativeLayout) findViewById(R.id.selected);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams lParams1 ;
        RelativeLayout.LayoutParams lParamsImageLeft;

        lParams.topMargin = 30;
        margin = 100;
        TextView titleCan = new TextView(this);
        titleCan.setText(getString(R.string.I_can_cook));
        titleCan.setGravity(Gravity.CENTER_HORIZONTAL);
        lMain.addView(titleCan, lParams);

        if (partTitle1.length() != 0) {
            for (String aa : dish1) {
                lParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
                lParams1 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
                lParamsImageLeft = new RelativeLayout.LayoutParams(250, 200 );

                lParams.topMargin = margin;
                lParams1.topMargin = margin;
                lParamsImageLeft.topMargin = margin;
                margin = margin + 235;



                textView = new TextView(this);
               // textView.setText(dish1[i]);
                //textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.radius_selected_dishes_yes);
                //textView.setTextColor(Color.RED);
                lMain.addView(textView, lParams);

                ImageView imageLeft = new ImageView(this);
                imageLeft.setImageResource(R.drawable.cap);
                lMain.addView(imageLeft, lParamsImageLeft);

                lParams1.leftMargin = 200;
                lParams1.rightMargin = 50;
                TextView textView1 = new TextView(this);
                textView1.setText(dish1[i]);
                textView1.setGravity(Gravity.CENTER);
                textView1.setTextColor(Color.RED);
                lMain.addView(textView1, lParams1);


                final TextView finalTextView = textView;
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(SelectedDishes.this, Recipe.class);
                        client2.Client2(id1[finalI]);
                        client2.start();
                        while (true) {
                            if (client2.recipe != "") {
                                intent.putExtra("id", id1[finalI]);
                                intent.putExtra("recipe", client2.recipe);
                                intent.putExtra("title", dish1[finalI]);
                                break;
                            }
                        }
                        startActivity(intent);
                    }
                });
                i++;
            }
        }
        else {
            lParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
            lParams.topMargin = margin + 30;
            margin = lParams.topMargin;



           textView = new TextView(this);
            textView.setText("НИЧЕГО!");
            lMain.addView(textView, lParams);

            /* ToggleButton toggleButton = new ToggleButton(this);
            toggleButton.setBackgroundColor(Color.BLACK);
            lMain.addView(toggleButton, lParams);*/
        }

        lParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        System.out.println("margin" + margin);
        lParams.topMargin = margin + 30;
        margin = margin + 100;

        TextView titleNoCan = new TextView(this);

        titleNoCan.setText(getString(R.string.I_cant_cook));
        titleNoCan.setGravity(Gravity.CENTER_HORIZONTAL);
        lMain.addView(titleNoCan, lParams);
        i = 0;
        if (partTitle2.length() != 0) {
            for (String aa : dish2) {
                lParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
                lParams1 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
                lParamsImageLeft = new RelativeLayout.LayoutParams(250, 200 );

                lParams.topMargin = margin;
                lParams1.topMargin = margin;
                lParamsImageLeft.topMargin = margin;
                margin = margin + 235;



                textView = new TextView(this);
                // textView.setText(dish2[i]);
                //textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.radius_selected_dishes_no);
                //textView.setTextColor(Color.RED);
                lMain.addView(textView, lParams);


                ImageView imageLeft = new ImageView(this);
                imageLeft.setImageResource(R.drawable.cap);
                lMain.addView(imageLeft, lParamsImageLeft);

                lParams1.leftMargin = 200;
                lParams1.rightMargin = 50;
                TextView textView1 = new TextView(this);
                textView1.setText(dish2[i]);
                textView1.setGravity(Gravity.CENTER);
                textView1.setTextColor(Color.RED);
                lMain.addView(textView1, lParams1);

                final TextView finalTextView = textView;
                final int finalI1 = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(SelectedDishes.this, Recipe.class);
                        client2.Client2(id2[finalI1]);
                        client2.start();
                        while (true) {
                            if (client2.recipe != "") {
                                intent.putExtra("id", id2[finalI1]);
                                intent.putExtra("recipe", client2.recipe);
                                intent.putExtra("title", dish2[finalI1]);
                                break;
                            }
                        }
                        startActivity(intent);
                    }
                });
                i++;
            }
        }

       /* titleCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client2.start();
            }
        });


        titleNoCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client2.start();
            }
        }); */
    }
    public void onClick(View view) {
    String str = "0";
            client2.Client2(str);
            client2.start();

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
       // ed.clear();
       // ed.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
