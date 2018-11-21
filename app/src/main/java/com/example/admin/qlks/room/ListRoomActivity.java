package com.example.admin.qlks.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.RoomAdapter;
import com.example.admin.qlks.database.RoomDAO;
import com.example.admin.qlks.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListRoomActivity extends AppCompatActivity {
    public static List<Sach> dsSaches = new ArrayList<>();
//    public statistical List<Sach> dsSaches = new ArrayList<>();
    ListView lvBook;
    RoomAdapter adapter = null;
    RoomDAO roomDAO;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        lvBook = (ListView) findViewById(R.id.lvBook);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton=findViewById(R.id.floatAddSach);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListRoomActivity.this,AddRoomActivity.class));
            }
        });
        roomDAO = new RoomDAO(ListRoomActivity.this);
        dsSaches = roomDAO.getAllSach();
        adapter = new RoomAdapter(this, dsSaches);
;
        lvBook.setAdapter(adapter);

        lvBook.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearchBook);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        dsSaches.clear();
        dsSaches = RoomDAO.getAllSach();
        adapter.changeDatasetBook(dsSaches);
    }

    public void addBook(View view) {
    }

    public void exit(View view) {
        finish();
    }
}
