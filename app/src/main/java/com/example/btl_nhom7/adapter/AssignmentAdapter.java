package com.example.btl_nhom7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_nhom7.R;
import com.example.btl_nhom7.model.Assignment;

import java.util.ArrayList;


public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>{
    private Context mContext;
    private ArrayList<Assignment> assignments;

    public AssignmentAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(ArrayList<Assignment> list){
        this.assignments = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent,false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        if(assignment==null){
            return;
        }
        holder.txtSTT.setText(String.valueOf(position+1));
        holder.txtDate.setText(assignment.getDay());
        holder.txtStartTime.setText(assignment.getStartTime());
        holder.txtEndTime.setText(assignment.getEndTime());
        holder.txtRoom.setText(assignment.getIdRoom());
    }
    @Override
    public int getItemCount() {
        if(assignments!=null){
            return  assignments.size();
        }
        return 0;
    }

    public class AssignmentViewHolder extends  RecyclerView.ViewHolder{
        private TextView txtSTT;
        private TextView txtDate;
        private TextView txtStartTime;
        private TextView txtEndTime;
        private TextView txtRoom;
        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSTT = itemView.findViewById(R.id.txtasstt);
            txtDate = itemView.findViewById(R.id.txtasDate);
            txtStartTime = itemView.findViewById(R.id.txtasStartTime);
            txtEndTime = itemView.findViewById(R.id.txtasEndTime);
            txtRoom = itemView.findViewById(R.id.txtasRoom);
        }
    }
}