package com.example.artem.cw;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Artem on 21.11.2016.
 */
public class Favourites extends AppCompatActivity {
    public static final String APP_PREFERENCES = "myselect1";
    Intent intent;
    TextView textView;
    int idIndex;
    int nameIndex;
    int recipeIndex;

    DBHelper dbHelper;
    SQLiteDatabase database;
    int margin = 130;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        SelectRecipes();



}

    public void SelectRecipes() {

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase(); // открыть и вернуть экземпляр базы данных для работы
        // если БД нет - то в вызывает onCreate, если версия БД изменилась - onCreate
        ContentValues contentValues = new ContentValues(); // для добавления новых строк в таблицу

        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null); //чтенеие всех записей из таблицы
        if (cursor.moveToFirst()) { // перемемещает курсор на первую строку в рез-те запроса // и проверяет есть ли вообще записи в курсоре
            idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            recipeIndex = cursor.getColumnIndex(DBHelper.KEY_RECIPE);

            do {
                CreateTextView(cursor.getString(nameIndex), cursor.getString(idIndex), cursor.getString(recipeIndex));
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", recipe = " + cursor.getString(recipeIndex));
            } while (cursor.moveToNext());
        }
        else
            Log.d("mLog", "0 rows");

        cursor.close(); // закрываем для освобождения памяти
    }

    public void CreateTextView(final String title, final String id, final String recipe) {
        textView = null;
        RelativeLayout lMain;
        lMain = (RelativeLayout) findViewById(R.id.fav);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lParams.topMargin = margin;
        margin = margin + 130;
        lParams.leftMargin = 15;

        final TextView titleCan = new TextView(this);
        titleCan.setText(title);
        lMain.addView(titleCan, lParams);



        titleCan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               /* database = dbHelper.getWritableDatabase(); // открыть и вернуть экземпляр базы данных для работы
                // если БД нет - то в вызывает onCreate, если версия БД изменилась - onCreate
                ContentValues contentValues = new ContentValues(); // для добавления новых строк в таблицу
                String[] selectionArgs = {titleCan.getText().toString()};
                Cursor cursor = database.query(DBHelper.TABLE_NAME, null, DBHelper.KEY_NAME, selectionArgs, null, null, null); //чтенеие всех записей из таблицы
                if (cursor.moveToFirst()) { // перемемещает курсор на первую строку в рез-те запроса // и проверяет есть ли вообще записи в курсоре
                   idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                   nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                   recipeIndex = cursor.getColumnIndex(DBHelper.KEY_RECIPE);
                }
                else
                    Log.d("mLog", "0 rows");

                cursor.close(); // закрываем для освобождения памяти  */


                System.out.println(titleCan.getText().toString());
                intent = new Intent(Favourites.this, Recipe.class);
                intent = new Intent(Favourites.this, Recipe.class);
                intent.putExtra("id", id);
                intent.putExtra("recipe", recipe);
                intent.putExtra("title", title);

                startActivity(intent);
            }


        });

    }

    public void ClickImageButton(View view) {
        database.delete(DBHelper.TABLE_NAME, null, null);

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.clear();
        ed.commit();

        Toast toast = Toast.makeText(getApplicationContext(),
                "Вы удалили все рецепты из избранных!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
