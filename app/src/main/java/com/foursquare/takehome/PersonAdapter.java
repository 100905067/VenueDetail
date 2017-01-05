package com.foursquare.takehome;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.CustomViewHolder> {
    List<Person> personList;

    public PersonAdapter(List<Person> pList) {
        personList = pList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cell, null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Person visitor = personList.get(position);
        holder.vName.setText(visitor.getName());
        holder.vTiming.setText(visitor.getArriveTime()+"-"+visitor.getLeaveTime());
    }

    @Override
    public int getItemCount() {

        if (personList.isEmpty())
            return 0;
        return personList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView vName, vTiming;

        public CustomViewHolder(View itemView) {
            super(itemView);
            vName = (TextView) itemView.findViewById(R.id.visitorName);
            vTiming = (TextView) itemView.findViewById(R.id.visitorTiming);
        }
    }
}
