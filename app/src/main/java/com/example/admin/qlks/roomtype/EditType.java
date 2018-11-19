package com.example.admin.qlks.roomtype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.admin.qlks.R;
import com.example.admin.qlks.database.TypeDAO;

public class EditType extends AppCompatActivity {
    EditText edMaTheloai, edtenTheLoai,edVitri,edMOta;
    TypeDAO theLoaiDAO;
    String id, name, mota, vitri;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
        edMaTheloai = findViewById(R.id.edMaTheloai);
        edVitri = findViewById(R.id.edVitri);
        edMOta = findViewById(R.id.edMota);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edtenTheLoai = findViewById(R.id.edtenTheloai);
        theLoaiDAO = new TypeDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        id = b.getString("ID");
        name = b.getString("NAME");
        mota = b.getString("MOTA");
        vitri = b.getString("VITRI");
        edtenTheLoai.setText(name);
        edVitri.setText(vitri);
    }

    public void updateTheloai(View view) {
        if (theLoaiDAO.updateTheLoai(id, edtenTheLoai.getText().toString(), mota, String.valueOf(Integer.parseInt(edVitri.getText().toString()))) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void HuyTheloai(View view) {
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
