package com.example.admin.qlks.toproom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.qlks.model.Room;
import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.RoomAdapter;
import com.example.admin.qlks.database.RoomDAO;

import java.util.ArrayList;
import java.util.List;

public class PersonBookingActivity extends AppCompatActivity {
    ImageView imageView;
    public static List<Room> dsRooms = new ArrayList<>();
    ListView lvBook;
    RoomAdapter adapter = null;
    RoomDAO roomDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luot_sach_ban_chay);
        imageView = findViewById(R.id.outtop10);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvBook = (ListView) findViewById(R.id.lvBookTop);
        edThang = (EditText) findViewById(R.id.edThang);
    }

    public void VIEW_SACH_TOP_10(View view) {
        if (Integer.parseInt(edThang.getText().toString()) > 13 ||
                Integer.parseInt(edThang.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
        } else {
            roomDAO = new RoomDAO(PersonBookingActivity.this);
            dsRooms = roomDAO.getSachTop10(edThang.getText().toString());
            adapter = new RoomAdapter(this, dsRooms);
            lvBook.setAdapter(adapter);
        }
    }
}
