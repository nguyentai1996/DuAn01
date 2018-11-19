package com.example.admin.qlks.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.admin.qlks.model.Room;
import com.example.admin.qlks.model.Type;
import com.example.admin.qlks.R;
import com.example.admin.qlks.roomtype.RoomType_Activity;
import com.example.admin.qlks.database.RoomDAO;
import com.example.admin.qlks.database.TypeDAO;

import java.util.ArrayList;
import java.util.List;

public class AddRoomActivity extends AppCompatActivity {
    RoomDAO roomDAO;
    TypeDAO theLoaiDAO;
    Spinner spnTheLoai;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<Type> listTheLoai = new ArrayList<>();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_title_activity);

        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        imageView=findViewById(R.id.outthembook);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getTheLoai();
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNXB);
        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText) findViewById(R.id.edSoLuong);
        //
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddRoomActivity.this, spnTheLoai.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSach.setText(b.getString("MASACH"));
        String maTheLoai = b.getString("MATHELOAI");
        edTenSach.setText(b.getString("TENSACH"));
        edNXB.setText(b.getString("NXB"));
        edTacGia.setText(b.getString("TACGIA"));
        edGiaBia.setText(b.getString("GIABIA"));
        edSoLuong.setText(b.getString("SOLUONG"));
        spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
    }
    }

    public void showSpinner(View view) {
        roomDAO = new RoomDAO(AddRoomActivity.this);
        roomDAO.getAllSach();
        startActivity(new Intent(AddRoomActivity.this, RoomType_Activity.class));
    }

    public void getTheLoai() {
//        theLoaiDAO = new TypeDAO(AddRoomActivity.this);
//        listTheLoai = theLoaiDAO.getAllTheLoai();
//        ArrayAdapter<Type> dataAdapter = new ArrayAdapter<Type>(this,
//                android.R.layout.simple_spinner_item, listTheLoai);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnTheLoai.setAdapter(dataAdapter);

        List<String> listTheLoai = new ArrayList<>();
        listTheLoai.add("CNTT");
        listTheLoai.add("KHÁCH SẠN");
        listTheLoai.add("NHÀ HÀNG");
        listTheLoai.add("ĐỒ HỌA");
        listTheLoai.add("WEB");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listTheLoai);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTheLoai.setAdapter(adapter);
    }

    public void addBook(View view) {
            roomDAO = new RoomDAO(AddRoomActivity.this);
            Room room = new
                    Room(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(),
                    edTacGia.getText().toString(), edNXB.getText().toString(),

                    Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText
                    ().toString()));
            try {
                if (roomDAO.inserSach(room) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return 0;
    }
}
