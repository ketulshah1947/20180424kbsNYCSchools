package com.vzw.hss.myverizon.a20180424kbsnycschools.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vzw.hss.myverizon.a20180424kbsnycschools.R;

import java.util.ArrayList;

/**
 * Created by c0shak8 on 4/25/2018.
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    private ArrayList<String> schoolList;

    public class SchoolViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public SchoolViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
        }
    }

    public SchoolAdapter(ArrayList<String> schoolList) {
        this.schoolList = schoolList;
    }

    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false);
        return new SchoolViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SchoolViewHolder holder, int position) {
        String name = schoolList.get(position);
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

}
