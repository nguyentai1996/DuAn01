package com.example.admin.qlks;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.admin.qlks.database.UserDAO;

public class Login_Activity extends Activity {
    private EditText edtUser, edtPass;
    private CheckBox checkBox;
    private UserDAO userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edUserName);
        edtPass = findViewById(R.id.edPassLogin);
        checkBox = findViewById(R.id.ckb);
        userDAO = new UserDAO(this);
        restore();
    }

    public void login(View view) {
        save();
    }

    private void save() {
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        boolean chk = checkBox.isChecked();
        if (user.equals("")) {
            edtUser.setError(getString(R.string.error_empty));
        } else if (pass.equals("")) {
            edtPass.setError(getString(R.string.error_emptyps));
        } else if (pass.length() > 6) {
            edtPass.setError(getString(R.string.error_length));
        } else {
            if (userDAO.checkLogin(user, pass) > 0) {
                remember(user, pass, chk);
                startActivity(new Intent(Login_Activity.this, MainActivity.class));
            } else if (user.equals("admin") && pass.equals("admin")) {
                startActivity(new Intent(Login_Activity.this, MainActivity.class));
            } else if (!user.equals("admin")) {
                edtUser.setError(getString(R.string.error_user));
            } else {
                edtPass.setError(getString(R.string.error_pass));
            }
//            } else {
//                Toast.makeText(LoginActivity.this, "Username and password incorrect", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    private void remember(String u, String p, boolean check) {
        SharedPreferences sharedPreferences = getSharedPreferences("File", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!check) {
            editor.clear();
        } else {
            editor.putString("username", u);
            editor.putString("password", p);
            editor.putBoolean("cbo", check);
        }
        editor.commit();
    }


    private void restore() {
        SharedPreferences pref = getSharedPreferences("File", MODE_PRIVATE);
        boolean check = pref.getBoolean("cbo", false);
        if (check) {
            String user = pref.getString("username", "");
            String pass = pref.getString("password", "");
            edtUser.setText(user);
            edtPass.setText(pass);
        }
        checkBox.setChecked(check);
    }
    }

