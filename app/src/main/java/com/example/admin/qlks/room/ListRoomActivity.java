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


import com.example.admin.qlks.model.Room;
import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.RoomAdapter;
import com.example.admin.qlks.database.RoomDAO;

import java.util.ArrayList;
import java.util.List;

public class ListRoomActivity extends AppCompatActivity {
    public static List<Room> dsRooms = new ArrayList<>();
//    public static List<Room> dsRooms = new ArrayList<>();
    ListView lvBook;
    RoomAdapter adapter = null;
    RoomDAO roomDAO;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        lvBook = (ListView) findViewById(R.id.lvBook);
        toolbar=findViewById(R.id.toolbarbook);
        setSupportActionBar(toolbar);
        imageView=findViewById(R.id.outbook);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton=findViewById(R.id.floatAddSach);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListRoomActivity.this,AddRoomActivity.class));
            }
        });
        roomDAO = new RoomDAO(ListRoomActivity.this);
        dsRooms = roomDAO.getAllSach();
        adapter = new RoomAdapter(this, dsRooms);
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
        dsRooms.clear();
        dsRooms = RoomDAO.getAllSach();
        adapter.changeDatasetBook(dsRooms);
    }
}
