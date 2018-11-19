package com.example.admin.qlks.roomtype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.admin.qlks.model.Type;
import com.example.admin.qlks.R;
import com.example.admin.qlks.database.TypeDAO;

public class RoomType_Activity extends AppCompatActivity {
    private TypeDAO theLoaiDAO;
    private EditText edma, edten, edvitri, edmota;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booktype_activity);
        edma = findViewById(R.id.idtheloai);
        edten = findViewById(R.id.tentheloai);
        edvitri = findViewById(R.id.vititheloai);
        imageView= findViewById(R.id.outthemtheloai);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edmota = findViewById(R.id.motatheloai);
    }

    public void addTheloai(View view) {
        theLoaiDAO = new TypeDAO(RoomType_Activity.this);
        Type user = new Type(edma.getText().toString(), edten.getText().toString(), edmota.getText().toString(), Integer.parseInt(String.valueOf(edvitri.getText())));
        try {
            if (validateForm() > 0) {
                if (theLoaiDAO.inserTheLoai(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void HuyTheloai(View view) {
        finish();
    }

    private int validateForm() {
        int check = 1;
        if (edma.getText().length() == 0 || edten.getText().length() == 0 || edmota.getText().length() == 0 || edvitri.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}
