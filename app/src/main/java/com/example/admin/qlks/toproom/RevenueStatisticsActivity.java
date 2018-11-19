package com.example.admin.qlks.toproom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.qlks.R;
import com.example.admin.qlks.database.BillDetailDAO;


public class RevenueStatisticsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvNgay, tvThang, tvNam;
    BillDetailDAO billDetailDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        imageView = findViewById(R.id.outthongke);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        billDetailDAO = new BillDetailDAO(this);
        tvNgay.setText("Hôm nay: " + billDetailDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này: " + billDetailDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này: " + billDetailDAO.getDoanhThuTheoNam());
    }
}
