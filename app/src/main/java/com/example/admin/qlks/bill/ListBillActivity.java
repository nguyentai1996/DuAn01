package com.example.admin.qlks.bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.admin.qlks.billdetail.BillDetailByIDActivity;
import com.example.admin.qlks.model.Bill;
import com.example.admin.qlks.R;
import com.example.admin.qlks.adapter.BillAdapter;
import com.example.admin.qlks.database.BillDAO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListBillActivity extends AppCompatActivity {
    private ImageView imageView;
    public List<Bill> dsBill = new ArrayList<>();
    ListView lvHoaDon;
    BillAdapter adapter = null;
    BillDAO billDAO;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        imageView = findViewById(R.id.outbill);
        floatingActionButton = findViewById(R.id.floatAddHoadon);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListBillActivity.this, BillActivity.class);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("HOÁ ĐƠN");
        lvHoaDon = (ListView) findViewById(R.id.lvHoaDon);
        billDAO = new BillDAO(ListBillActivity.this);
        try {
            dsBill = billDAO.getAllHoaDon();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
        adapter = new BillAdapter(this, dsBill);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Bill bill = (Bill) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListBillActivity.this, BillDetailByIDActivity.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", bill.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        // TextFilter
        lvHoaDon.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearch);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before[" + before + "] - Count[" + count + "]");
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
        dsBill.clear();
        try {
            dsBill = billDAO.getAllHoaDon();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter.changeDataset(dsBill);
    }
}
