package com.example.admin.qlks.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.admin.qlks.R;
import com.example.admin.qlks.database.HoaDonChiTietDAO;
import com.example.admin.qlks.model.HoaDonChiTiet;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    public CartAdapter(Activity context, List<HoaDonChiTiet> arrayHoaDonChiTiet) {
        super();
        this.context = context;
        this.arrHoaDonChiTiet = arrayHoaDonChiTiet;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonChiTiet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView txtMaSach;
        TextView txtSoLuong;
        TextView txtGiaBia;
        TextView txtThanhTien;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cart, null);
            holder.txtMaSach = (TextView) convertView.findViewById(R.id.tvMaSach);
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
            holder.txtGiaBia = (TextView) convertView.findViewById(R.id.tvGiaBia);
            holder.txtThanhTien = (TextView)
                    convertView.findViewById(R.id.tvThanhTien);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    hoaDonChiTietDAO.deleteHoaDonChiTietByID(String.valueOf(arrHoaDonChiTiet.get(position).getMaHDCT()));
                    arrHoaDonChiTiet.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        HoaDonChiTiet _entry = (HoaDonChiTiet) arrHoaDonChiTiet.get(position);
        holder.txtMaSach.setText("Mã phòng: " + _entry.getSach().getMaSach());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuongMua());
        holder.txtGiaBia.setText("Giá phòng: " + _entry.getSach().getGiaBia() + " usd");
        holder.txtThanhTien.setText("Thành tiền: " + _entry.getSoLuongMua() * _entry.getSach().getGiaBia() + " usd");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetHDCT(List<HoaDonChiTiet> items) {
        this.arrHoaDonChiTiet = items;
        notifyDataSetChanged();
    }
}
