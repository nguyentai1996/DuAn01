package com.example.admin.qlks.billdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.admin.qlks.model.BillDetail;
import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.CartAdapter;
import com.example.admin.qlks.database.BillDetailDAO;

import java.util.ArrayList;
import java.util.List;

public class BillDetailByIDActivity extends AppCompatActivity {
    ImageView imageView;
    public List<BillDetail> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    BillDetailDAO billDetailDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill_detail);
        imageView = findViewById(R.id.outhoadonDetail);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        billDetailDAO = new BillDetailDAO(BillDetailByIDActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT = billDetailDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}
