package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;



public class MainActivity extends AppCompatActivity {
    private Button mButtonOpen  = null;
    private Client mServer = null;
    private static final String LOG_TAG = "myServerApp";


    ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mButtonOpen = (Button)findViewById(R.id.next);
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
        mServer = new Client();
        /* Открываем соединение. Открытие должно происходить в отдельном потоке от ui */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mServer.openConnection();
                    mServer.sendData("Send text to server".getBytes());
                    mServer.closeConnection();

                    /*
                        устанавливаем активные кнопки для отправки данных
                        и закрытия соедиения. Все данные по обновлению интерфеса должны
                        обрабатывается в Ui потоке, а так как мы сейчас находимся в
                        отдельном потоке, нам необходимо вызвать метод  runOnUiThread()
                    */
                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage());
                    mServer = null;
                }
            }
        }).start();


            /* Закрываем соединение */

    }
}
