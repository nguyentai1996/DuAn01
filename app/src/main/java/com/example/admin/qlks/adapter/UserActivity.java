package com.example.admin.qlks.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.qlks.EditUser;
import com.example.admin.qlks.model.User;
import com.example.admin.qlks.R;
import com.example.admin.qlks.database.UserDAO;

import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ListView listView;
    private EditText edUser, edPass, edName, edPhone;
    private UserDAO userDAO;
    UserAdapter adapter;
    private List<User> list;
    private TextView textView;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listView = findViewById(R.id.lv);
        userDAO = new UserDAO(UserActivity.this);
        list = userDAO.getAllUser();
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new UserAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserActivity.this, EditUser.class);
                Bundle b = new Bundle();
                b.putString("USERNAME", list.get(position).getUserName());
                b.putString("PHONE", list.get(position).getPhone());
                b.putString("NAME", list.get(position).getName());
                intent.putExtras(b);
                startActivity(intent);
                return false;
            }
        });


    }

    public void them(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
        builder.setTitle("Add User");
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View viewDialog = inflater.inflate(R.layout.dialog, null);
        builder.setView(viewDialog);
        edName = viewDialog.findViewById(R.id.Name);
        edPass = viewDialog.findViewById(R.id.Pass);
        edPhone = viewDialog.findViewById(R.id.Phone);
        edUser = viewDialog.findViewById(R.id.User);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userDAO = new UserDAO(UserActivity.this);
                User user = new User(edUser.getText().toString(), edPass.getText().toString(), edName.getText().toString(), edPhone.getText().toString());
                try {
                    if (validateForm() > 0) {
                        if (userDAO.inserUser(user) > 0) {
                            Toast.makeText(UserActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list = userDAO.getAllUser();
                            adapter.changeDataset(userDAO.getAllUser());
                        } else {
                            Toast.makeText(UserActivity.this, "Add error", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    private int validateForm() {
        int check = 1;
        if (edUser.getText().length() == 0 || edName.getText().length() == 0 || edPass.getText().length() == 0 || edPhone.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "You must enter full information ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }


    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list = userDAO.getAllUser();
        adapter.changeDataset(userDAO.getAllUser());
    }


    public void exit(View view) {
        finish();
    }
}


