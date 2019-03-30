package com.terset.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private ArrayList<Boolean> mlist_checkbox;
    private ArrayList<String> mlist_judul, mlist_jumlah, mlist_sajian;
    private Context mContext;

    private OnItemClick mCallback;

    public RecycleViewAdapter(Context context, ArrayList<Boolean> list_checkbox, ArrayList<String> list_judul, ArrayList<String> list_jumlah, ArrayList<String> list_sajian, OnItemClick OnItemClick){
        mlist_checkbox = list_checkbox;
        mlist_judul = list_judul;
        mlist_jumlah = list_jumlah;
        mlist_sajian = list_sajian;
        mContext = context;

        mCallback = OnItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity2_menu_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.act2_menu_item_checkbox.setChecked(mlist_checkbox.get(i));
        viewHolder.act2_menu_item_judul.setText(mlist_judul.get(i));
        viewHolder.act2_menu_item_jumlah.setText(mlist_jumlah.get(i));
        viewHolder.act2_menu_item_sajian.setText(mlist_sajian.get(i));


        final Intent intent = new Intent(mContext, Activity4_1.class);
        intent.putExtra("index", i);
        intent.putExtra("judul", mlist_judul.get(i));
        intent.putExtra("jumlah", mlist_jumlah.get(i));
        intent.putExtra("sajian", mlist_sajian.get(i));

        viewHolder.act2_menu_item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.act2_menu_item_checkbox.isChecked()){
                    //Toast.makeText(mContext, "onclick : Checked", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).startActivityForResult(intent, 1);
                    //mCallback.onClick(i, true, "", "");
                }
                else{
                    Toast.makeText(mContext, "onclick : Unchecked", Toast.LENGTH_SHORT).show();
                    mCallback.onClick(i, false, "0", "-");
                }
            }
        });
        viewHolder.act2_menu_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).startActivityForResult(intent, 1);
                //mCallback.onClick(i, true, "", "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist_judul.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout act2_menu_item;
        public CheckBox act2_menu_item_checkbox;
        public TextView act2_menu_item_judul, act2_menu_item_jumlah, act2_menu_item_sajian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            act2_menu_item = itemView.findViewById(R.id.act2_menu_item);
            act2_menu_item_checkbox = itemView.findViewById(R.id.act2_menu_item_checkbox);
            act2_menu_item_judul = itemView.findViewById(R.id.act2_menu_item_judul);
            act2_menu_item_jumlah = itemView.findViewById(R.id.act2_menu_item_jumlah);
            act2_menu_item_sajian = itemView.findViewById(R.id.act2_menu_item_sajian);
        }
    }
}











