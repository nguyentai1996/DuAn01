package com.example.admin.qlks.toproom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.qlks.R;
import com.example.admin.qlks.database.HoaDonChiTietDAO;


public class RevenueStatisticsActivity extends AppCompatActivity {

    TextView tvNgay, tvThang1, tvNam,tvThang2,tvThang3,tvThang4,tvThang5,tvThang6,tvThang7,tvThang8,tvThang9,tvThang10,tvThang11,tvThang12;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);

        tvThang1 = (TextView) findViewById(R.id.tvThongKeThang1);

        tvThang2 = (TextView) findViewById(R.id.tvThongKeThang2);
        tvThang3 = (TextView) findViewById(R.id.tvThongKeThang3);
        tvThang4 = (TextView) findViewById(R.id.tvThongKeThang4);
        tvThang5 = (TextView) findViewById(R.id.tvThongKeThang5);
        tvThang6 = (TextView) findViewById(R.id.tvThongKeThang6);
        tvThang7 = (TextView) findViewById(R.id.tvThongKeThang7);
        tvThang8 = (TextView) findViewById(R.id.tvThongKeThang8);
        tvThang9 = (TextView) findViewById(R.id.tvThongKeThang9);
        tvThang10 = (TextView) findViewById(R.id.tvThongKeThang10);
        tvThang11 = (TextView) findViewById(R.id.tvThongKeThang11);
        tvThang12 = (TextView) findViewById(R.id.tvThongKeThang12);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);


        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);

        tvThang1.setText("Tháng 1: " + (hoaDonChiTietDAO.getDoanhThuThang1()));
        tvThang2.setText("Tháng 2: " + hoaDonChiTietDAO.getDoanhThuThangHai());
        tvThang3.setText("Tháng 3: " + hoaDonChiTietDAO.getDoanhThuThangBa());
        tvThang4.setText("Tháng 4: " + hoaDonChiTietDAO.getDoanhThuThangTu());
        tvThang5.setText("Tháng 5: " + hoaDonChiTietDAO.getDoanhThuThangNam());
        tvThang6.setText("Tháng 6: " + hoaDonChiTietDAO.getDoanhThuThangSau());
        tvThang7.setText("Tháng 7: " + hoaDonChiTietDAO.getDoanhThuThangBay());
        tvThang8.setText("Tháng 8: " + hoaDonChiTietDAO.getDoanhThuThangTam());
        tvThang9.setText("Tháng 9: " + hoaDonChiTietDAO.getDoanhThuThang9());
        tvThang10.setText("Tháng 10: " + hoaDonChiTietDAO.getDoanhThuThang10());
        tvThang11.setText("Tháng 11: " + hoaDonChiTietDAO.getDoanhThuThang11());
        tvThang12.setText("Tháng 12: " + hoaDonChiTietDAO.getDoanhThuThang12());
        tvNam.setText("Nam 2018: " + hoaDonChiTietDAO.getDoanhThuTheoNam());




    }

    public void exit(View view) {
        finish();
    }
}

