package com.example.karakoram.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karakoram.R;
import com.example.karakoram.activity.ComplaintActivity;
import com.example.karakoram.resource.Category;
import com.example.karakoram.resource.Complaint;
import com.example.karakoram.resource.Event;
import com.example.karakoram.activity.EventDescription;
import com.example.karakoram.resource.MaintComplaint;
import com.example.karakoram.resource.MessComplaint;
import com.example.karakoram.resource.Status;

import java.util.ArrayList;
import java.util.Date;

import static com.example.karakoram.R.drawable.green_status;
import static com.example.karakoram.R.drawable.orange_status;
import static com.example.karakoram.R.drawable.red_status;
import static com.example.karakoram.R.drawable.red_status_1;
import static com.example.karakoram.R.drawable.user_info_save_button;


public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.myViewHolder> {

    Context mcontext;
    ArrayList<Complaint> complaints;
    ArrayList<String> key;

    public ComplaintAdapter(Context mcontext, ArrayList<Complaint> complaints, ArrayList<String> key) {
        /* Here, we initialize the ArrayAdapter's internal storage for the context and the list.
         * the second argument is used when the ArrayAdapter is populating a single TextView.
         * Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
         * going to use this second argument, so it can be any value. Here, we used 0.
         */
        this.mcontext=mcontext;
        this.complaints = complaints;
        this.key=key;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v=LayoutInflater.from(mcontext).inflate(R.layout.complaint_listview,parent,false);
        final myViewHolder vHolder=new myViewHolder(v);

//        vHolder.event_list_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int i=vHolder.getAdapterPosition();
//                Date dateTime = event1.get(i).getDateTime();
//                String time = String.format("%02d", dateTime.getHours()) + " : " + String.format("%02d", dateTime.getMinutes());
//                String date = (dateTime.getYear() + 1900) + "-" + String.format("%02d",dateTime.getMonth() + 1) + "-" + String.format("%02d",dateTime.getDate());
//                Intent intent = new Intent(mcontext, EventDescription.class);
//                intent.putExtra("title", event1.get(i).getTitle());
//                intent.putExtra("description", event1.get(i).getDescription());
//                intent.putExtra("time", time);
//                intent.putExtra("date", date);
//                intent.putExtra("key", key.get(i));
//                mcontext.startActivity(intent);
//            }
//        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Complaint complaint = getItem(position);
        if (complaint != null) {
            holder.mStatus.setText(complaint.getStatus().toString());
            holder.mName.setText(complaint.getUserName());

            if(complaint.getStatus().equals(Status.Fired))
                holder.mStatusButton.setImageDrawable(mcontext.getResources().getDrawable(red_status));
            else if(complaint.getStatus().equals(Status.Processing))
                holder.mStatusButton.setImageDrawable(mcontext.getResources().getDrawable(orange_status));
            else
                holder.mStatusButton.setImageDrawable(mcontext.getResources().getDrawable(green_status));

            if(complaint.getCategory().equals(Category.Maintenance))
                holder.mArea.setText(((MaintComplaint)complaint).getComplaintArea().toString());
            else if(complaint.getCategory().equals(Category.Mess))
                holder.mArea.setText(((MessComplaint)complaint).getComplaintArea().toString());
            else
                holder.mArea.setText(complaint.getDescription());

            Date lastUpdate = complaint.getTimestamp();
            int num=lastUpdate.getHours();
            String str="AM";
            if(num>12){num=num-12; str="PM";}
            @SuppressLint("DefaultLocale") String time = String.format("%02d", num) + " : " + String.format("%02d", lastUpdate.getMinutes()) + " " + str;
            @SuppressLint("DefaultLocale") String date = (lastUpdate.getYear() + 1900) + "-" + String.format("%02d",lastUpdate.getMonth() + 1) + "-" + String.format("%02d",lastUpdate.getDate());

            holder.mTime.setText(time);
        }
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }


    public static class myViewHolder extends RecyclerView.ViewHolder{


        TextView mArea;
        TextView mStatus;
        TextView mName;
        TextView mTime;
        ImageButton mStatusButton;
        LinearLayout complaint_list_view;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            mStatus = itemView.findViewById(R.id.tv_status);
            mTime = itemView.findViewById(R.id.tv_last_update);
            mName = itemView.findViewById(R.id.tv_complain_user_name);
            mArea = itemView.findViewById(R.id.tv_complaint_area);
            mStatusButton = itemView.findViewById(R.id.status_imageButton);
            complaint_list_view=(LinearLayout)itemView.findViewById(R.id.complaint_item_id);
        }
    }

    public Complaint getItem(int position) {
        return complaints.get(position);
    }
}
