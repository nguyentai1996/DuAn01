package com.example.admin.qlks.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.admin.qlks.model.Bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private SQLiteDatabase db;
    private Databasemanager databasemanager;
    public static final String TABLE_NAME = "Bill";
    public static final String SQL_HOA_DON = "CREATE TABLE Bill (mahoadon text primary key, ngaymua date);";
    public static final String TAG = "BillDAO";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public BillDAO(Context context) {
        databasemanager = new Databasemanager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int inserHoaDon(Bill hd) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", sdf.format(hd.getNgayMua()));
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }     //getAll

    public List<Bill> getAllHoaDon() throws ParseException {
        List<Bill> dsBill = new ArrayList<>();
        Cursor c;
        c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Bill ee = new Bill();
            ee.setMaHoaDon(c.getString(0));
            ee.setNgayMua(sdf.parse(c.getString(1)));
            dsBill.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsBill;
    }

    public int updateHoaDon(Bill hd) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", hd.getNgayMua().toString());
        int result = db.update(TABLE_NAME, values, "mahoadon=?", new String[]{hd.getMaHoaDon()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteHoaDonByID(String mahoadon) {
        int result = db.delete(TABLE_NAME, "mahoadon=?", new String[]{mahoadon});
        if (result == 0) return -1;
        return 1;
    }
}
