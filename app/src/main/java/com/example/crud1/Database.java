package com.example.crud1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "employee.db";
    public static final String TABLE_NAME = "tbl_employee";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertData(Employee employee) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("firstname", employee.getFirstname());
            contentValues.put("lastname", employee.getLastname());
            contentValues.put("address", employee.getAddress());
            long result = db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return result != -1; // Return true if the insertion was successful, or false otherwise
        } catch (Exception e) {
            return false;
        }
    }

    public List<Employee> DisplayAll() {
        try {
            List<Employee> employees = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            if (cursor.moveToFirst()) {
                do {
                    Employee employee = new Employee();
                    employee.setId(cursor.getInt(0));
                    employee.setFirstname(cursor.getString(1));
                    employee.setLastname(cursor.getString(2));
                    employee.setAddress(cursor.getString(3));
                    employees.add(employee);
                } while (cursor.moveToNext());
            }

            cursor.close();
            sqLiteDatabase.close();
            return employees;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean Delete(int id) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            int row = db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(id)});
            db.close();
            return row > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Update(Employee employee) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("firstname", employee.getFirstname());
            contentValues.put("lastname", employee.getLastname());
            contentValues.put("address", employee.getAddress());
            int rowsAffected = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{String.valueOf(employee.getId())});
            db.close();
            return rowsAffected > 0; // Return true if at least one row was updated, or false otherwise
        } catch (Exception e) {
            return false;
        }
    }


    }
