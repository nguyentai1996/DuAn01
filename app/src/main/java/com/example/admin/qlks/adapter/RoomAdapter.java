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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.admin.qlks.R;
import com.example.admin.qlks.room.AddRoomActivity;
import com.example.admin.qlks.database.RoomDAO;
import com.example.admin.qlks.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends BaseAdapter implements Filterable {
    List<Sach> arrSaches;
    List<Sach> arrSortSaches;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    RoomDAO roomDAO;

    public RoomAdapter(Activity context, List<Sach> arraySaches) {
        super();
        this.context = context;
        this.arrSaches = arraySaches;
        this.arrSortSaches = arraySaches;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        roomDAO = new RoomDAO(context);
    }

    @Override
    public int getCount() {
        return arrSaches.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSaches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtBookCode;
        TextView txtPrice;
        TextView txtStt;
        TextView txtLoai;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_book, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtBookCode = (TextView) convertView.findViewById(R.id.tvMa);
            holder.txtLoai = (TextView) convertView.findViewById(R.id.tvLoai);
            holder.txtStt = (TextView) convertView.findViewById(R.id.tvStt);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.tvSoLuong);


            holder.imgEdit = convertView.findViewById(R.id.ivEdit);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddRoomActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MASACH", arrSaches.get(position).getMaSach());

                    b.putString("TENSACH", arrSaches.get(position).getMaTheLoai());
                    b.putString("TACGIA", arrSaches.get(position).getTenSach());

                    b.putString("GIABIA", String.valueOf(arrSaches.get(position).getGiaBia()));

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
                    builder.setMessage("BẠN MUỐN XÓA ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            roomDAO.deleteSachByID(arrSaches.get(position).getMaSach());
                            arrSaches.remove(position);
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
        Sach _entry = (Sach) arrSaches.get(position);
        holder.txtBookCode.setText("Số Phòng: " + _entry.getMaSach());
        holder.txtLoai.setText("Loại Phòng: " + _entry.getMaTheLoai());
        holder.txtStt.setText("Trạng Thái: " + _entry.getTenSach());
        holder.txtPrice.setText("Giá: " + _entry.getGiaBia());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetBook(List<Sach> items) {
        this.arrSaches = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrSaches = arrSortSaches;
    }

    public Filter getFilter() {
        if (sachFilter == null)
            sachFilter = new CustomFilter();
        return sachFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortSaches;
                results.count = arrSortSaches.size();
            } else {
                List<Sach> lsSaches = new ArrayList<Sach>();
                for (Sach p : arrSaches) {
                    if
                            (p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsSaches.add(p);
                }
                results.values = lsSaches;
                results.count = lsSaches.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrSaches = (List<Sach>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
