package com.example.admin.qlks.roomtype;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.admin.qlks.model.Type;
import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.TypeAdapter;
import com.example.admin.qlks.database.TypeDAO;

import java.util.ArrayList;
import java.util.List;

public class ListTypeActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    public static List<Type> dsTheLoai = new ArrayList<>();
    ListView lvTheLoai;
    TypeAdapter adapter = null;
    TypeDAO theLoaiDAO;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.exit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        floatingActionButton = findViewById(R.id.floatAddTheLoai);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListTypeActivity.this, RoomType_Activity.class));
            }
        });
        lvTheLoai = findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TypeDAO(ListTypeActivity.this);
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter = new TypeAdapter(this, dsTheLoai);
        lvTheLoai.setAdapter(adapter);
//        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ListTypeActivity.this, EditType.class);
//                Bundle b = new Bundle();
//                b.putString("ID", dsTheLoai.get(position).getMaTheLoai());
//                b.putString("NAME", dsTheLoai.get(position).getTenTheLoai());
//                b.putString("MOTA", dsTheLoai.get(position).getMoTa());
//                b.putString("VITRI", String.valueOf(dsTheLoai.get(position).getViTri()));
//                intent.putExtras(b);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter.changeDatasetTheloai(dsTheLoai);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn thông tin");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ctx_edit:
                Intent intent1 = new Intent(ListTypeActivity.this, RoomType_Activity.class);
                startActivity(intent1);
                return (true);
            case R.id.menu_ctx_del:
                Intent intent2 = new Intent(ListTypeActivity.this, RoomType_Activity.class);
                startActivity(intent2);
                return (true);
        }
        return super.onContextItemSelected(item);
    }
}
