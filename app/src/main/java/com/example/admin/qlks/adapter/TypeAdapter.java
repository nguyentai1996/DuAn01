package com.example.admin.qlks.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.qlks.model.Type;
import com.example.admin.qlks.R;
import com.example.admin.qlks.roomtype.EditType;
import com.example.admin.qlks.database.TypeDAO;

import java.util.List;

public class TypeAdapter extends BaseAdapter {
    List<Type> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    TypeDAO theLoaiDAO;

    public TypeAdapter(Activity context, List<Type> arrayTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrayTheLoai;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TypeDAO(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_theloai, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtMaTheLoai = (TextView) convertView.findViewById(R.id.tvMaTheLoai);
            holder.txtTenTheLoai = (TextView) convertView.findViewById(R.id.tvTenTheLoai);
            holder.imgEdit = convertView.findViewById(R.id.ivEdit);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditType.class);
                    Bundle b = new Bundle();
                    b.putString("ID", arrTheLoai.get(position).getMaTheLoai());
                    b.putString("NAME", arrTheLoai.get(position).getTenTheLoai());
                    b.putString("MOTA", arrTheLoai.get(position).getMoTa());
                    b.putString("VITRI", String.valueOf(arrTheLoai.get(position).getViTri()));
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Message");
                    builder.setMessage("Do you want delete this item ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(position).getMaTheLoai());
                            arrTheLoai.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Type _entry = (Type) arrTheLoai.get(position);
        holder.img.setImageResource(R.drawable.emone);
        holder.txtMaTheLoai.setText(_entry.getMaTheLoai());
        holder.txtTenTheLoai.setText(_entry.getTenTheLoai());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetTheloai(List<Type> items) {
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }
}

