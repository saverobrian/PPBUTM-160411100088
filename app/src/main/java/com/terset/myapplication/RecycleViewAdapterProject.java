package com.terset.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewAdapterProject extends RecyclerView.Adapter<RecycleViewAdapterProject.ViewHolder>{
    private ArrayList<String> mlist_id, mlist_nama, mlist_harga, mlist_detail;
    private Context mContext;

    DatabaseHelperProject myDB;

    public RecycleViewAdapterProject(Context context, ArrayList<String> mlist_id, ArrayList<String> mlist_nama, ArrayList<String> mlist_harga, ArrayList<String> mlist_detail){
        this.mlist_id = mlist_id;
        this.mlist_nama = mlist_nama;
        this.mlist_harga = mlist_harga;
        this.mlist_detail = mlist_detail;
        mContext = context;

        myDB = new DatabaseHelperProject(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_project_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.projectItem_nama.setText(mlist_nama.get(i));
        viewHolder.projectItem_harga.setText(mlist_harga.get(i));
        viewHolder.projectItem_detail.setText(mlist_detail.get(i));

        final Intent intent = new Intent(mContext, ActivityProject_insert.class);
        intent.putExtra("mode", "update");
        intent.putExtra("id", mlist_id.get(i));
        viewHolder.projectItem_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).startActivityForResult(intent, 1);
                ((Activity) mContext).finish();
            }
        });
        final Intent intentHome = new Intent(mContext, ActivityProject.class);
        viewHolder.projectItem_ButtonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteDataBuild(mlist_id.get(i));
                Toast.makeText(((Activity) mContext),"Click", Toast.LENGTH_SHORT).show();
                ((Activity) mContext).startActivity(intentHome);
                ((Activity) mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist_nama.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout projectItem_layout;
        public TextView projectItem_nama, projectItem_harga, projectItem_detail;
        public Button projectItem_ButtonDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectItem_layout = itemView.findViewById(R.id.projectItem_layout);
            projectItem_nama = itemView.findViewById(R.id.projectItem_nama);
            projectItem_harga = itemView.findViewById(R.id.projectItem_harga);
            projectItem_detail = itemView.findViewById(R.id.projectItem_detail);
            projectItem_ButtonDel = itemView.findViewById(R.id.projectItem_ButtonDel);
        }
    }
}
