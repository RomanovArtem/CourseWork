package com.example.artem.cw;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Artem on 13.11.2016.
 */
public class Recipe extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mysettings";
    TextView textViewContent;
    TextView textViewTitle;
    Client2 client2 = new Client2();
    Intent intent;
    DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        textViewContent = (TextView)findViewById(R.id.content);
        textViewTitle = (TextView)findViewById(R.id.title);
        String recipe = getIntent().getStringExtra("recipe");
        String title = getIntent().getStringExtra("title");

        textViewContent.setText(recipe);
        textViewTitle.setText(title);

        dbHelper = new DBHelper(this);
    }


    boolean flag = false;
   public void ClickImageButton(View view) {
    // класс SQLiteDatabase предназначен для управления БД SQLite
       SQLiteDatabase database = dbHelper.getWritableDatabase(); // открыть и вернуть экземпляр базы данных для работы
       // если БД нет - то в вызывает onCreate, если версия БД изменилась - onCreate
       ContentValues contentValues = new ContentValues(); // для добавления новых строк в таблицу

       ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
       if (!flag) {
           database.delete(DBHelper.TABLE_NAME, null, null);
        /*   Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null); //чтенеие всех записей из таблицы
            if (cursor.moveToFirst()) { // перемемещает курсор на первую строку в рез-те запроса // и проверяет есть ли вообще записи в курсоре
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                int recipeIndex = cursor.getColumnIndex(DBHelper.KEY_RECIPE);

                do {
                    Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                    ", name = " + cursor.getString(nameIndex) +
                                    ", recipe = " + cursor.getString(recipeIndex));
                } while (cursor.moveToNext());
            }
           else
            Log.d("mLog", "0 rows");

            cursor.close(); // закрываем для освобождения памяти  */
           imageButton.setImageResource(R.drawable.nstar);
           flag = true;
       }
       else {
            contentValues.put(DBHelper.KEY_NAME, textViewTitle.getText().toString());
            contentValues.put(DBHelper.KEY_RECIPE, textViewContent.getText().toString());

           database.insert(DBHelper.TABLE_NAME, null, contentValues);

           imageButton.setImageResource(R.drawable.ystar);
           flag = false;
       }
       dbHelper.close(); // закрываем соединение с БД
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
