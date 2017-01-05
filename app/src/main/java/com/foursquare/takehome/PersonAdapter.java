package com.foursquare.takehome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.CustomViewHolder> {
    List<Person> personList;
    Context context;

    public PersonAdapter(List<Person> pList, Context c) {
        personList = pList;
        context = c;
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
        if(visitor.getName()==null) {
            holder.vName.setText("No Visitors");
            holder.vName.setTextColor(context.getResources().getColor(R.color.colorGrey));
            holder.vTiming.setTextColor(context.getResources().getColor(R.color.colorGrey));
        }
        else
            holder.vName.setText(visitor.getName());
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        Date arrTime = new Date(visitor.getArriveTime()*1000);
        Date leaveTime = new Date(visitor.getLeaveTime()*1000);
        holder.vTiming.setText(formatDate.format(arrTime)+"-"+formatDate.format(leaveTime));
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
