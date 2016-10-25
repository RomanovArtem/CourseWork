package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Artem on 03.10.2016.
 */
public class Meals extends Activity {


    private static final String LOG_TAG = "myServerApp";
    public static final String APP_PREFERENCES = "mysettings";
    //Map <String, Boolean> hashmap = new HashMap<String, Boolean>();
    static ArrayList<String> selectedProducts = new ArrayList();
    JSONObject object = new JSONObject();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals);
    }








    void loadSelectionFruit()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE);
        for (int i = 0; i < 11; i++)
        {
            String fru = "f";
            int j = (i + 1);
            String str = Integer.toString(j);
            fru = fru.concat(str);
            boolean selection = sharedPreferences.getBoolean(fru, false);
            if (selection == true)
            {
                selectedProducts.add(fru);
                //hashmap.put(fru, selection);
            }
        }
        // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
    void loadSelectionVegetables()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE);
        for (int i = 0; i < 15; i++)
        {
            String veg = "v";
            int j = (i + 13);
            String str = Integer.toString(j);
            veg = veg.concat(str);
            boolean selection = sharedPreferences.getBoolean(veg, false);
            if (selection == true)
            {
                selectedProducts.add(veg);
                //hashmap.put(veg, selection);
            }
        }

        /*for (String selectedProduct : selectedProducts) {
             System.out.println(selectedProduct);
        }*/


    }

    public static String Convert()
    {
        String line = new String();
        line = "";
        for (String selectedProduct : selectedProducts) {
            line = line + selectedProduct + " ";
        }
        System.out.println("line: " + line);
        return line;
    }




    public void onClick(View view) {
        /* создаем объект для работы с сервером*/
        loadSelectionFruit();
        loadSelectionVegetables();
        Intent intent = new Intent(this, SelectedDishes.class);
        Client client=new Client();

        Toast toast = Toast.makeText(getApplicationContext(), "Подключаемся к серверу!", Toast.LENGTH_SHORT);
        toast.show();
        client.start();

        while (true) {
            if (client.stroka != "") {
                String data = client.stroka;
                System.out.println(client.stroka);
                intent.putExtra("name", data);
                //intent.putExtra("name", data.toString());
                break;
            }

        }
        startActivity(intent);
        ////System.out.println("sadadasd" + stroka);

    }
}