package com.example.admin.qlks.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.admin.qlks.model.BillDetail;
import com.example.admin.qlks.R;
import com.example.admin.qlks.database.BillDetailDAO;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<BillDetail> arrBillDetail;
    public Activity context;
    public LayoutInflater inflater;
    BillDetailDAO billDetailDAO;

    public CartAdapter(Activity context, List<BillDetail> arrayBillDetail) {
        super();
        this.context = context;
        this.arrBillDetail = arrayBillDetail;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        billDetailDAO = new BillDetailDAO(context);
    }

    @Override
    public int getCount() {
        return arrBillDetail.size();
    }

    @Override
    public Object getItem(int position) {
        return arrBillDetail.get(position);
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

                    billDetailDAO.deleteHoaDonChiTietByID(String.valueOf(arrBillDetail.get(position).getMaHDCT()));
                    arrBillDetail.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        BillDetail _entry = (BillDetail) arrBillDetail.get(position);
        holder.txtMaSach.setText("Mã sách: " + _entry.getRoom().getMaSach());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuongMua());
        holder.txtGiaBia.setText("Giá bìa: " + _entry.getRoom().getGiaBia() + " vnd");
        holder.txtThanhTien.setText("Thành tiền: " + _entry.getSoLuongMua() * _entry.getRoom().getGiaBia() + " vnd");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetHDCT(List<BillDetail> items) {
        this.arrBillDetail = items;
        notifyDataSetChanged();
    }
}
