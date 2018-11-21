package com.example.admin.qlks.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.TypeRoomAdapter;
import com.example.admin.qlks.database.RoomDAO;
import com.example.admin.qlks.model.Sach;
import com.example.admin.qlks.model.TypeRoom;

import java.util.ArrayList;

public class AddRoomActivity extends AppCompatActivity {
    RoomDAO roomDAO;

    Spinner spnType;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        setTitle("THÊM SÁCH");

        imageView = findViewById(R.id.outthembook);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
//        spnType = (Spinner) findViewById(R.id.spn);

        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);

        //

//        ArrayList<TypeRoom> arrayList = new ArrayList<TypeRoom>();
//
//        arrayList.add(new TypeRoom(R.drawable.hotel, "Single Room", 100 - 110));
//        arrayList.add(new TypeRoom(R.drawable.ashoka, "Double Room", 200 - 210));
//        arrayList.add(new TypeRoom(R.drawable.hotel, "Triple Room", 300 - 310));
//        arrayList.add(new TypeRoom(R.drawable.ashoka, "King Room", 400 - 410));
//
//        TypeRoomAdapter typeRoomAdapter = new TypeRoomAdapter(this, R.layout.item_typer_oom, arrayList);
//        spnType.setAdapter(typeRoomAdapter);

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


    public void addBook(View view) {
        roomDAO = new RoomDAO(AddRoomActivity.this);
        Sach sach = new
                Sach(edMaSach.getText().toString(), edTenSach.getText().toString(), edTacGia.getText().toString(), Double.parseDouble(edGiaBia.getText().toString())
        );
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
