package com.example.admin.qlks.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.admin.qlks.model.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeDAO {
    private SQLiteDatabase db;
    private Databasemanager databasemanager;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri int);";
    public static final String TAG = "TheLoaiDAO";

    public TypeDAO(Context context) {
        databasemanager = new Databasemanager(context);
        db = databasemanager.getWritableDatabase();
    }

    //insert
    public int inserTheLoai(Type theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());
        values.put("vitri", theLoai.getViTri());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //getAllTheLoai
    public List<Type> getAllTheLoai() {
        List<Type> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Type ee = new Type();
            ee.setMaTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setViTri(c.getInt(3));
            dsTheLoai.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }

    //delete
    public int deleteTheLoaiByID(String matheloai) {
        int result = db.delete(TABLE_NAME, "matheloai=?", new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;
    }

    public int updateTheLoai(Type theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());
        values.put("vitri", theLoai.getViTri());
        int result = db.update(TABLE_NAME, values, "matheloai=?", new
                String[]{theLoai.getMaTheLoai()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public int updateTheLoai(String s, String s1, String mota, String s2) {
        ContentValues values = new ContentValues();
        values.put("matheloai", s);
        values.put("tentheloai", s1);
        int result = db.update(TABLE_NAME, values, "matheloai=?", new String[]{s});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
