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
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Artem on 13.11.2016.
 */
public class Recipe extends AppCompatActivity {
    public static final String APP_PREFERENCES = "myselect";
    TextView textViewContent;
    TextView textViewTitle;
    Client2 client2 = new Client2();
    Intent intent;
    DBHelper dbHelper;
    String id;
    ImageButton imageButton;
    boolean flag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        textViewContent = (TextView)findViewById(R.id.content);
        textViewTitle = (TextView)findViewById(R.id.title);
        id = getIntent().getStringExtra("id");
        String recipe = getIntent().getStringExtra("recipe");
        String title = getIntent().getStringExtra("title");

        textViewContent.setText(recipe);
        textViewTitle.setText(title);

        dbHelper = new DBHelper(this);
        loadSelection();
    }


   public void ClickImageButton(View view) {
    // класс SQLiteDatabase предназначен для управления БД SQLite
       SQLiteDatabase database = dbHelper.getWritableDatabase(); // открыть и вернуть экземпляр базы данных для работы
       // если БД нет - то в вызывает onCreate, если версия БД изменилась - onCreate
       ContentValues contentValues = new ContentValues(); // для добавления новых строк в таблицу


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
           saveSelection();

       }
       else {
            contentValues.put(DBHelper.KEY_NAME, textViewTitle.getText().toString());
            contentValues.put(DBHelper.KEY_RECIPE, textViewContent.getText().toString());

           database.insert(DBHelper.TABLE_NAME, null, contentValues);
           Toast toast = Toast.makeText(getApplicationContext(),
                   "Вы добавили рецепт в избранное!",
                   Toast.LENGTH_SHORT);
           toast.setGravity(Gravity.CENTER, 0, 0);
           toast.show();

           imageButton.setImageResource(R.drawable.ystar);
           flag = false;
           saveSelection();
       }
       dbHelper.close(); // закрываем соединение с БД
   }



    void saveSelection()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
        //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        SharedPreferences.Editor ed = sharedPreferences.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
        if (flag == false) {
            ed.putBoolean(id, false); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
            ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
        }
        else {
            ed.putBoolean(id, true); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
            ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
        }

    }
        // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show(); // выводит ссобщ, что данные сохранены


    void loadSelection() {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        boolean select = sharedPreferences.getBoolean(id, false);
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        if (select == false) {
            imageButton.setImageResource(R.drawable.ystar);
            flag = false;
        }
        else {
            imageButton.setImageResource(R.drawable.nstar);
            flag = true;
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
