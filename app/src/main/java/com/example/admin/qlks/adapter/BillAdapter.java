package com.example.admin.qlks.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.qlks.model.Bill;
import com.example.admin.qlks.R;
import com.example.admin.qlks.database.BillDAO;
import com.example.admin.qlks.database.BillDetailDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends BaseAdapter implements Filterable {
    List<Bill> arrBill;
    List<Bill> arrSortBill;
    private Filter hoaDonFilter;
    public Activity context;
    public LayoutInflater inflater;
    BillDAO hoadonDAO;
    BillDetailDAO billDetailDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public BillAdapter(Activity context, List<Bill> arrayBill) {
        super();
        this.context = context;
        this.arrBill = arrayBill;
        this.arrSortBill = arrayBill;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoadonDAO = new BillDAO(context);
        billDetailDAO = new BillDetailDAO(context);
    }

    @Override
    public int getCount() {
        return arrBill.size();
    }

    @Override
    public Object getItem(int position) {
        return arrBill.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaHoaDon;
        TextView txtNgayMua;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_hoadon, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtMaHoaDon = (TextView) convertView.findViewById(R.id.tvMaHoaDon);
            holder.txtNgayMua = (TextView) convertView.findViewById(R.id.tvNgayMua);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if
                            (billDetailDAO.checkHoaDon(arrBill.get(position).getMaHoaDon())) {
                        Toast.makeText(context, "Bạn phải xoá hoá đơn chi tiết trước khi xoá hoá đơn này", Toast.LENGTH_LONG).show();
                    } else {

                        hoadonDAO.deleteHoaDonByID(arrBill.get(position).getMaHoaDon());
                        arrBill.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Bill _entry = (Bill) arrBill.get(position);
        holder.txtMaHoaDon.setText(_entry.getMaHoaDon());
        holder.txtNgayMua.setText(sdf.format(_entry.getNgayMua()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Bill> items) {
        this.arrBill = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrBill = arrSortBill;
    }

    public Filter getFilter() {
        if (hoaDonFilter == null)
            hoaDonFilter = new CustomFilter();
        return hoaDonFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortBill;
                results.count = arrSortBill.size();
            } else {
                List<Bill> lsBill = new ArrayList<Bill>();
                for (Bill p : arrBill) {
                    if
                            (p.getMaHoaDon().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsBill.add(p);
                }
                results.values = lsBill;
                results.count = lsBill.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrBill = (List<Bill>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
