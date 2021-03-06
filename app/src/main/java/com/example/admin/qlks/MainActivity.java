
package com.example.admin.qlks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.example.admin.qlks.bill.HoaDonActivity;
import com.example.admin.qlks.bill.ListHoaDonActivity;
import com.example.admin.qlks.billdetail.ListHoaDonChiTietByIDActivity;
import com.example.admin.qlks.room.ListRoomActivity;
import com.example.admin.qlks.toproom.RevenueStatisticsActivity;
import com.example.admin.qlks.adapter.UserActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





    public void sach(View view) {
        startActivity(new Intent(getApplicationContext(), ListRoomActivity.class));
    }

    public void hoadon(View view) {
        startActivity(new Intent(getApplicationContext(), HoaDonActivity.class));
    }




    public void viewthongke(View view) {
        startActivity(new Intent(getApplicationContext(), MainMainActivity.class));
    }

    public void user(View view) {
        startActivity(new Intent(getApplicationContext(), UserActivity.class));
    }

    public void location(View view) {
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }

    public void thongke(View view) {
        Intent intent = new Intent(this,ListHoaDonActivity.class);
        startActivity(intent);
    }
}
