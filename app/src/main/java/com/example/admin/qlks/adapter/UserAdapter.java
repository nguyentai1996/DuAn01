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


import com.example.admin.qlks.EditUser;
import com.example.admin.qlks.R;
import com.example.admin.qlks.database.UserDAO;
import com.example.admin.qlks.model.User;


import java.util.List;

public class UserAdapter extends BaseAdapter {
    List<User> list;
    public Activity context;
    public LayoutInflater inflater;
    UserDAO userDAO;

    public UserAdapter(Activity context, List<User> list) {
        super();
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userDAO = new UserDAO(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item, null);
            holder.img = convertView.findViewById(R.id.img);
            holder.txtName = (TextView) convertView.findViewById(R.id.tvName);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.imgEdit = convertView.findViewById(R.id.ivEdit);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditUser.class);
                    Bundle b = new Bundle();
                    b.putString("USERNAME", list.get(position).getUserName());
                    b.putString("PHONE", list.get(position).getPhone());
                    b.putString("NAME", list.get(position).getName());
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Message");
                    builder.setMessage("Do you want delete this item ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDAO.deleteUserByID(list.get(position).getUserName());
                            list.remove(position);
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
        User _entry = list.get(position);
        if (position % 3 == 0) {
            holder.img.setImageResource(R.drawable.emone);
        } else if (position % 3 == 1) {
            holder.img.setImageResource(R.drawable.emone1);
        } else {
            holder.img.setImageResource(R.drawable.emone);
        }
        holder.txtName.setText(_entry.getName());
        holder.txtPhone.setText(_entry.getPhone());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<User> items) {
        this.list = items;
        notifyDataSetChanged();
    }
}
