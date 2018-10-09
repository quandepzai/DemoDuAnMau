package com.example.noname.demoduanmau;

public interface Constant {

    String POSITION = "position";
    String DATA = "data";
    String ON_UPDATE_USER = "on_update_user";

    //table user

    String TABLE_USER = "USER";

    String COLUMN_USERNAME = "Username";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_NAME = "Name";
    String COLUMN_PHONE = "PhoneNumber";

    String CREATE_TABLE_USER = "CREATE_TABLE" + TABLE_USER + "(" +
            COLUMN_USERNAME + " VARCHAR PRIMARY KEY," +
            COLUMN_PASSWORD + " VARCHAR," +
            COLUMN_NAME + " VARCHAR," +
            COLUMN_PHONE + " VARCHAR," +
            ")";

    /* table type book*/


    // CREATE TABLE TypeBook (MaTheLoai CHAR(5) PRIMARY KEY NOT NULL,
    // TypeName NVARCHAR(50) NOT NULL,
    // Description NVARCHAR(255),
    // Position INT
    // )


}
