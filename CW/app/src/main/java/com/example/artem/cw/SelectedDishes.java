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

import java.io.IOException;

/**
 * Created by Artem on 16.10.2016.
 */
public class SelectedDishes extends AppCompatActivity {
    //String data = getIntent().getExtras().getString("testNameData");
    public static final String APP_PREFERENCES = "mysettings";
    Client2 client2 =new Client2();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_dishes);

        String data = getIntent().getStringExtra("name");
        System.out.println("Что пришло: " + data);
        String[] parts = data.split("/");
        String part1 = parts[0];
        String part2 = parts[1];

        final String[] dish1 = part1.split(";");
        String[] dish2 = part2.split(";");

        int i = 0;
        int margin = 0;
        TextView textView = null;
        RelativeLayout lMain;
        lMain = (RelativeLayout) findViewById(R.id.selected);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        lParams.topMargin = 30;
        margin = lParams.topMargin;

        TextView titleCan = new TextView(this);
        titleCan.setText(getString(R.string.I_can_cook));
        lMain.addView(titleCan, lParams);

        if (part1.length() != 0) {
            for (String aa : dish1) {
                lParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                lParams.topMargin = margin + 130;
                margin = lParams.topMargin;

                textView = new TextView(this);
                textView.setText(dish1[i]);
                lMain.addView(textView, lParams);


                final TextView finalTextView = textView;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SelectedDishes.this, Recipe.class);
                        startActivity(intent);
                    }
                });
                i++;
            }
        }

        lParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        System.out.println("margin" + margin);
        lParams.topMargin = margin + 130;
        margin = lParams.topMargin;

        TextView titleNoCan = new TextView(this);

        titleNoCan.setText(getString(R.string.I_cant_cook));
        lMain.addView(titleNoCan, lParams);

        i = 0;
        if (part2.length() != 0) {
            for (String aa : dish2) {

                lParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                lParams.topMargin = margin + 130;
                margin = lParams.topMargin;

                textView = new TextView(this);
                textView.setText(dish2[i]);
                lMain.addView(textView, lParams);

                final TextView finalTextView = textView;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SelectedDishes.this, Recipe.class);
                        startActivity(intent);
                    }
                });
                i++;
            }
        }

        titleCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Kek");
                client2.start();

                   /* try {
                        client.out.writeUTF("228");
                        client.out.flush();
                        client.recipe = client.in.readUTF();
                        //System.out.println(client.recipe);
                        client.socket.close();
                        System.out.println("Сокет закрыт");
                    } catch (IOException e) {
                        System.err.println("Сокет не закрыт");
                    } */


            }
        });


        titleNoCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Trararar");
            }
        });

        // Инициализируем компонент
      //  TextView textView = (TextView)findViewById(R.id.textView3);
       // textView.setText(data);




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
