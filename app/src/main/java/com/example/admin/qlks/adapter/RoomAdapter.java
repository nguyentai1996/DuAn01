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


import com.example.admin.qlks.model.Room;
import com.example.admin.qlks.R;
import com.example.admin.qlks.room.AddRoomActivity;
import com.example.admin.qlks.database.RoomDAO;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends BaseAdapter implements Filterable {
    List<Room> arrRooms;
    List<Room> arrSortRooms;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    RoomDAO roomDAO;

    public RoomAdapter(Activity context, List<Room> arrayRooms) {
        super();
        this.context = context;
        this.arrRooms = arrayRooms;
        this.arrSortRooms = arrayRooms;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        roomDAO = new RoomDAO(context);
    }

    @Override
    public int getCount() {
        return arrRooms.size();
    }

    @Override
    public Object getItem(int position) {
        return arrRooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtBookCode;
        TextView txtSoLuong;
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
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
            holder.imgEdit = convertView.findViewById(R.id.ivEdit);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddRoomActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MASACH", arrRooms.get(position).getMaSach());
                    b.putString("MATHELOAI", arrRooms.get(position).getMaTheLoai());
                    b.putString("TENSACH", arrRooms.get(position).getTenSach());
                    b.putString("TACGIA", arrRooms.get(position).getTacGia());
                    b.putString("NXB", arrRooms.get(position).getNXB());
                    b.putString("GIABIA", String.valueOf(arrRooms.get(position).getGiaBia()));
                    b.putString("SOLUONG", String.valueOf(arrRooms.get(position).getSoLuong()));
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
                            roomDAO.deleteSachByID(arrRooms.get(position).getMaSach());
                            arrRooms.remove(position);
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
        Room _entry = (Room) arrRooms.get(position);
        holder.txtBookCode.setText("Mã sách: " + _entry.getMaSach());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuong());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetBook(List<Room> items) {
        this.arrRooms = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrRooms = arrSortRooms;
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
                results.values = arrSortRooms;
                results.count = arrSortRooms.size();
            } else {
                List<Room> lsRooms = new ArrayList<Room>();
                for (Room p : arrRooms) {
                    if
                            (p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsRooms.add(p);
                }
                results.values = lsRooms;
                results.count = lsRooms.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrRooms = (List<Room>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
