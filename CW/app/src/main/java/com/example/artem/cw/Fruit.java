package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.artem.cw.MainActivity;
import com.example.artem.cw.R;


/**
 * Created by Artem on 05.09.2016.
 */

public class Fruit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit);
    }

    public void onClick(View view) {
        Intent intent = new Intent(Fruit.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
