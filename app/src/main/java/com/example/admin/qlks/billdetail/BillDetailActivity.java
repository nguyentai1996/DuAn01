package com.example.admin.qlks.billdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.qlks.model.Bill;
import com.example.admin.qlks.model.BillDetail;
import com.example.admin.qlks.model.Room;
import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.CartAdapter;
import com.example.admin.qlks.database.BillDetailDAO;
import com.example.admin.qlks.database.RoomDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillDetailActivity extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    BillDetailDAO billDetailDAO;
    RoomDAO roomDAO;
    public List<BillDetail> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    double thanhTien = 0;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        imageView = findViewById(R.id.exit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua);
        lvCart = (ListView) findViewById(R.id.lvCart);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }

    public void ADDHoaDonCHITIET(View view) {
//        billDetailDAO = new BillDetailDAO(BillDetailActivity.this);
        roomDAO = new RoomDAO(BillDetailActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Room room = roomDAO.getSachByID(edMaSach.getText().toString());
                if (room != null) {
                    int pos = checkMaSach(dsHDCT, edMaSach.getText().toString());
                    Bill bill = new Bill(edMaHoaDon.getText().toString(), new Date());
                    BillDetail billDetail = new
                            BillDetail(1, bill, room, Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos >= 0) {
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        billDetail.setSoLuongMua(soluong +
                                Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos, billDetail);
                    } else {
                        dsHDCT.add(billDetail);
                    }
                    adapter.changeDatasetHDCT(dsHDCT);
                } else {
                    Toast.makeText(getApplicationContext(), "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void thanhToanHoaDon(View view) {
        billDetailDAO = new BillDetailDAO(BillDetailActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (BillDetail hd : dsHDCT) {
                billDetailDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getRoom().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaSach(List<BillDetail> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            BillDetail hd = lsHD.get(i);
            if (hd.getRoom().getMaSach().equalsIgnoreCase(maSach)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if
                (edMaSach.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty() ||
                edMaHoaDon.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }
}
