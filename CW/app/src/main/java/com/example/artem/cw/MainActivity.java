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


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "myServerApp";
    public static final String APP_PREFERENCES = "mysettings";
    //Map <String, Boolean> hashmap = new HashMap<String, Boolean>();
    ArrayList<String> selectedProducts = new ArrayList();



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

    }
        public void onClick(View view) {
        /* создаем объект для работы с сервером*/
            Thread ct=new Thread(doInThread);
            //Thread ct1=new Thread
           // ct.start();
            loadSelectionFruit();
            loadSelectionVegetables();

    }


    void loadSelectionFruit()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE);
        for (int i = 0; i < 11; i++)
        {
            String fru = "f";
            String str = Integer.toString(i);
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
            String str = Integer.toString(i);
            veg = veg.concat(str);
            boolean selection = sharedPreferences.getBoolean(veg, false);
            if (selection == true)
            {
                selectedProducts.add(veg);
                //hashmap.put(veg, selection);
            }
        }

        for (String selectedProduct : selectedProducts) {
             System.out.println(selectedProduct);
        }
        /*for (Map.Entry entry : hashmap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }*/
    }






    private Runnable doInThread = new Runnable() {
        Socket socket;
        public void run() {

           // int serverPort = 31007; // здесь обязательно нужно указать порт к которому привязывается сервер.
            //String address = "192.168.0.102"; // это IP-адрес компьютера, где исполняется наша серверная программа.
            // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

            try {
                InetSocketAddress inetAddress = new InetSocketAddress("192.168.0.101", 31010); // создаем объект который отображает вышеописанный IP-адрес.
                System.out.println("Сокет с адресом : " + inetAddress);
                socket = new Socket(inetAddress.getAddress(), 31010); // создаем сокет используя IP-адрес и порт сервера.
                System.out.println("Сокет создан.");

                // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();

                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);

                // Создаем поток для чтения с клавиатуры.
                //BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                String line = "12334";
                System.out.println();

                //while (true) {
                    //line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                    System.out.println("Посылаем данные серверу...");
                    out.writeUTF(line); // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                    System.out.println("Сервер прислал: : " + line);
                    System.out.println();
               // }
            } catch (Exception x) {
                x.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
