package com.example.admin.qlks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.admin.qlks.database.UserDAO;


public class EditUser extends AppCompatActivity {
    EditText editName, editPhone;
    UserDAO userDAO;
    String editusername, editname, editphone;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editName=findViewById(R.id.edName);
        editPhone=findViewById(R.id.edPhone);
        imageView=findViewById(R.id.exit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userDAO=new UserDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        editname=b.getString("NAME");
        editphone=b.getString("PHONE");
        editusername=b.getString("USERNAME");
        editName.setText(editname);
        editPhone.setText(editphone);
    }

    public void updateUser(View view) {
        if(userDAO.updateUser(editusername,editName.getText().toString(),editPhone.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), "Save successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void Huy(View view) {
    }
}
