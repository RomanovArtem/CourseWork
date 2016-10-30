package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


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
        toggleButton = (ToggleButton) findViewById(R.id.seasoning);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Seasoning.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.dairy);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Dairy.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.supplements);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Supplements.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.nuts);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Nuts.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.meat);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Meat.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.refill);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Refill.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.seafood);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Seafood.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.cereals);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Cereals.class);
            startActivity(intent);
        }
        toggleButton = (ToggleButton) findViewById(R.id.greenery);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Greenery.class);
            startActivity(intent);
        }

        toggleButton = (ToggleButton) findViewById(R.id.berries);
        if (toggleButton.isChecked() == true)
        {
            Intent intent = new Intent(MainActivity.this, Berries.class);
            startActivity(intent);
        }

    }
    public void onClick(View view) {
        /* создаем объект для работы с сервером*/
        // loadSelectionFruit();
        //loadSelectionVegetables();
        //Thread ct=new Thread(doInThread);
        //ct.start();
        Intent intent = new Intent(MainActivity.this, Meals.class);
        startActivity(intent);


    }








}
