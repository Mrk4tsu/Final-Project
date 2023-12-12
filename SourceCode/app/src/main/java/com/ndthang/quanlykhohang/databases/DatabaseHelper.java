package com.ndthang.quanlykhohang.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quanlyhanghoa";
    private static final int DATABASE_VERSION = 1;

    // Bảng User
    private static final String TABLE_USER = "user";
    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_PASSWORD = "password";
    private static final String USER_COLUMN_USERNAME = "username";

    // Bảng Product
    public static final String TABLE_PRODUCT = "product";
    public static final String PRODUCT_COLUMN_ID = "id";
    public static final String PRODUCT_COLUMN_NAME = "name";
    public static final String PRODUCT_COLUMN_QUANTITY = "quantity";
    public static final String PRODUCT_COLUMN_PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng User
        String createUserTableQuery = "CREATE TABLE " + TABLE_USER + " (" +
                USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COLUMN_NAME + " TEXT, " +
                USER_COLUMN_PASSWORD + " TEXT, " +
                USER_COLUMN_USERNAME + " TEXT)";
        db.execSQL(createUserTableQuery);

        // Tạo bảng Product
        String createProductTableQuery = "CREATE TABLE " + TABLE_PRODUCT + " (" +
                PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_COLUMN_NAME + " TEXT, " +
                PRODUCT_COLUMN_QUANTITY + " INTEGER, " +
                PRODUCT_COLUMN_PRICE + " REAL)";
        db.execSQL(createProductTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);

        // Tạo lại bảng mới
        onCreate(db);
    }
}
