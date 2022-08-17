package com.example.friendlycalcii;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import DatabaseHelper.DbHelper;

public class FormulasActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<String> calcAdapter;
    DbHelper calc;
    String name, formula, item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulas);

        list = findViewById(R.id.listView);
        calcAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        list.setAdapter(calcAdapter);

        calc = new DbHelper(getApplicationContext());
        Cursor cursor = calc.fetchPreSaved();

        while(!cursor.isAfterLast())
        {
            formula = cursor.getString(0);
            name = cursor.getString(1);
            item = name + " : " + formula;
            calcAdapter.add(item);

            cursor.moveToNext();
        }
    }
}