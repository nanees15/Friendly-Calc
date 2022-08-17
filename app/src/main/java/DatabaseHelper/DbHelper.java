package DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static String databaseName = "CalcDatabase";
    SQLiteDatabase CalcDatabase;

    public DbHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table preSavedRules (id integer primary key autoincrement," +"formula text not null, name text)");


        sqLiteDatabase.execSQL("insert into preSavedRules (formula,name)" + "values ('V=IR', 'Ohms Law')");
        sqLiteDatabase.execSQL("insert into preSavedRules (formula,name)" + "values ('V=u + at', 'Velocity')");
        sqLiteDatabase.execSQL("insert into preSavedRules (formula,name)" + "values ('h² = a² + b²', 'Pythagorean Theorem')");
        sqLiteDatabase.execSQL("insert into preSavedRules (formula,name)" + "values ('E = mc²', 'Mass–energy equivalence')");
        sqLiteDatabase.execSQL("insert into preSavedRules (formula,name)" + "values ('c = 2πr ', 'Circumference')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists preSavedRules");
        onCreate(sqLiteDatabase);
    }

    public Cursor fetchPreSaved()
    {
        CalcDatabase = getReadableDatabase();
        String[] rowDetails = {"formula", "name", "id"};
        Cursor cursor = CalcDatabase.query("preSavedRules", rowDetails, null, null, null, null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        CalcDatabase.close();
        return cursor;
    }


}
