package com.example.admin.qlks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.qlks.R;
import com.example.admin.qlks.model.TypeRoom;
import com.example.admin.qlks.room.AddRoomActivity;

import java.util.ArrayList;
import java.util.List;

public class TypeRoomAdapter extends BaseAdapter {

    Context context;
    int mylayout;
    List<TypeRoom> arraylist;

    public TypeRoomAdapter(AddRoomActivity addRoomActivity, int item_typer_oom, ArrayList<TypeRoom> arrayList) {
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(mylayout,null);

        TextView txtN= (TextView) view.findViewById(R.id.txtN);
        TextView txtToado= (TextView) view.findViewById(R.id.txtToado);
        ImageView img=(ImageView) view.findViewById(R.id.img);

        txtN.setText(arraylist.get(i).Ten );
        txtToado.setText(String.valueOf(arraylist.get(i).Toado));
        txtToado.setText(String.valueOf(arraylist.get(i).Toado));
        img.setImageResource(arraylist.get(i).Hinh);
        return view;
    }
}
