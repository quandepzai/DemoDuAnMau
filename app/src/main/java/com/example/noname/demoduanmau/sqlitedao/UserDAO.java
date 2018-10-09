package com.example.noname.demoduanmau.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.noname.demoduanmau.Constant;
import com.example.noname.demoduanmau.common.Constants;
import com.example.noname.demoduanmau.database.DataBaseHelper;
import com.example.noname.demoduanmau.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Constant{
    private DataBaseHelper dataBaseHelper;

    public UserDAO(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }
    public void insertUser (User user){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME,user.getUsername());
        contentValues.put(COLUMN_PASSWORD,user.getPassword());
        contentValues.put(COLUMN_NAME,user.getName());
        contentValues.put(COLUMN_PHONE,user.getPhone());

       sqLiteDatabase.insert(TABLE_USER,null,contentValues);

       // if (Constants.isDEBUG) Log.e("insertUer","insertUser : " + id);

        sqLiteDatabase.close();
    }

    public List<User> gettallUser(){
        List<User> userList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        String SELECT_ALL_USERS = "SELECT * FROM " +TABLE_USER;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USERS,null);

        if (cursor.moveToFirst()){
            do {
                String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                User user = new User(user_name, password, name, phone);
                userList.add(user);
            }while (cursor.moveToFirst());

        }
        return userList;

    }


    public User getUserByUsername(String taikhoan) {
        User user = null;
        //xin quyen gi sqlite
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        // query tim kiem user voi username = tham so truyen vao

        // 1: ten bang
        //2 : array cot can lay du lieu
        // 3: ten cot dung de query
        // 4 : gia tri can tim
        // 5,6,7 : null. la cac cau lenh xap xep dieu kien...
        Cursor cursor = sqLiteDatabase.query(TABLE_USER,new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONE}, COLUMN_USERNAME + "=?",
                                                        new String[]{taikhoan},null,null,null);
        // neu cursor co gia tri
        if (cursor != null && cursor.moveToFirst()){
            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            user = new User(user_name, password, name, phone);
        }
        return user;
    }
}