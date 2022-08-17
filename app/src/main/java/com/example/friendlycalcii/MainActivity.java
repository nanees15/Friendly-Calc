package com.example.friendlycalcii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    private EditText display;
    Button advanced;
    Button history;
    Intent scientificIntent, historyIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advanced = findViewById(R.id.advanced);
        history = findViewById(R.id.history);
        display = findViewById(R.id.textView);


        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scientificIntent = new Intent(MainActivity.this, ScientificActivity.class);
                startActivity(scientificIntent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyIntent);
            }
        });

        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    private void update(String str){
        String oldStr = display.getText().toString();//store the value in old
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString()))
        {
            display.setText(str);
            display.setSelection(cursorPos + 1);
        }
        else
        {
            display.setText(String.format("%s%s%s",leftStr,str,rightStr));
            display.setSelection(cursorPos + 1);
        }
    }
    public void zero(View view){
        update("0");
    }
    public void one(View view){
        update("1");
    }
    public void two(View view){
        update("2");
    }
    public void three(View view){
        update("3");
    }
    public void four(View view){
        update("4");
    }
    public void five(View view){
        update("5");
    }
    public void six(View view){
        update("6");
    }
    public void seven(View view){
        update("7");
    }
    public void eight(View view){
        update("8");
    }
    public void nine(View view){
        update("9");
    }
    public void clear(View view){
        display.setText("");
    }
    public void praces(View view){
        int courserpos = display.getSelectionStart();
        int text = display.getText().length();
        int openPrace = 0,closePrace = 0;

        for (int i = 0; i < courserpos; i++) {
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPrace += 1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closePrace += 1;
            }
        }

        if(openPrace == closePrace || display.getText().toString().substring(text-1,text).equals("(")){
            update("(");
        }
        if(closePrace < openPrace && !display.getText().toString().substring(text-1,text).equals("(")){
            update(")");
        }
        display.setSelection(courserpos +1);
    }
    public void modules(View view){
        update("^");
    }
    public void divide(View view){
        update("/");
    }
    public void multiply(View view){
        update("x");
    }
    public void subtract(View view){
        update("-");
    }
    public void plus(View view){
        update("+");
    }
    public void equal(View view){
        String userInPut = display.getText().toString();
        userInPut = userInPut.replaceAll("x","*");

        Expression Exp = new Expression(userInPut);
        String Result = String.valueOf(Exp.calculate());

        display.setText(Result);
    }
    public void dot(View view){
        update(".");
    }
    public void PosNeg(View view){
        update("-");
    }

    public void backspace(View view){
        int cursor = display.getSelectionStart();
        int txtlength = display.getText().length();
        if(cursor != 0 && txtlength != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursor-1,cursor,"");
            display.setText(selection);
            display.setSelection(cursor-1);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.formulas:
                intent = new Intent(MainActivity.this, FormulasActivity.class);
                startActivity(intent);
                return true;
            case R.id.show_rules:
                intent = new Intent(MainActivity.this, ShowRulesActivity.class);
                startActivity(intent);
                return true;
            case R.id.add_rules:
                intent = new Intent(MainActivity.this, AddNewRuleActivity.class);
                startActivity(intent);
                return true;
            case R.id.setting:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }
    

}

