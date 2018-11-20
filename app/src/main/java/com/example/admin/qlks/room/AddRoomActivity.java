package com.example.admin.qlks.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.admin.qlks.R;
import com.example.admin.qlks.database.RoomDAO;
import com.example.admin.qlks.model.Sach;

public class AddRoomActivity extends AppCompatActivity {
    RoomDAO roomDAO;

    Spinner spnTheLoai;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        setTitle("THÊM SÁCH");

        imageView=findViewById(R.id.outthembook);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);

        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);

        //

        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
        edMaSach.setText(b.getString("MASACH"));
        edTenSach.setText(b.getString("TENSACH"));
        edTacGia.setText(b.getString("TACGIA"));
        edGiaBia.setText(b.getString("GIABIA"));


    }
    }



//    public void getTheLoai() {
////        theLoaiDAO = new TheloaiDAO(AddRoomActivity.this);
////        listTheLoai = theLoaiDAO.getAllTheLoai();
////        ArrayAdapter<Theloai> dataAdapter = new ArrayAdapter<Theloai>(this,
////                android.R.layout.simple_spinner_item, listTheLoai);
////
////        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spnTheLoai.setAdapter(dataAdapter);
//
//        List<String> listTheLoai = new ArrayList<>();
//        listTheLoai.add("CNTT");
//        listTheLoai.add("KHÁCH SẠN");
//        listTheLoai.add("NHÀ HÀNG");
//        listTheLoai.add("ĐỒ HỌA");
//        listTheLoai.add("WEB");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listTheLoai);
//        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        spnTheLoai.setAdapter(adapter);
//    }

    public void addBook(View view) {
            roomDAO = new RoomDAO(AddRoomActivity.this);
            Sach sach = new
                    Sach(edMaSach.getText().toString(), edTenSach.getText().toString(), edTacGia.getText().toString(), Double.parseDouble(edGiaBia.getText().toString()));
            try {
                if (roomDAO.inserSach(sach) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
    }


}
