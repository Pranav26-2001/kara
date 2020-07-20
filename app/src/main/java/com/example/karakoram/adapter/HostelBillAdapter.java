package com.example.karakoram.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.karakoram.R;
import com.example.karakoram.resource.HostelBill;

import java.util.ArrayList;

public class HostelBillAdapter extends ArrayAdapter<HostelBill> {

    TextView mAmount;
    TextView mDescription;
//    TextView mTime;
    TextView mCategory;

    public HostelBillAdapter(Activity context, ArrayList<HostelBill> word) {

        /* Here, we initialize the ArrayAdapter's internal storage for the context and the list.
         * the second argument is used when the ArrayAdapter is populating a single TextView.
         * Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
         * going to use this second argument, so it can be any value. Here, we used 0.
         */
        super(context, 0, word);
        Log.i("ASDF", "print");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View list = convertView;
        if (list == null) {
            list = LayoutInflater.from(getContext()).inflate(R.layout.hostel_bill_listview, parent, false);
        }

        HostelBill hostelBill = getItem(position);

        mAmount = list.findViewById(R.id.tv_bill_amount);
        mDescription = list.findViewById(R.id.tv_bill_description);
//        mTime = list.findViewById(R.id.tv_bill_time);
        mCategory = list.findViewById(R.id.tv_bill_time);

        if (hostelBill != null) {
            String description = (String) hostelBill.getDescription().subSequence(0, Math.min(15, hostelBill.getDescription().length())) + "...";
//            String time = String.format("%02d", hostelBill.getDateTime().getHours()) + " : " + String.format("%02d", hostelBill.getDateTime().getMinutes());
            mAmount.setText("hostelBill.getAmount()");
            mDescription.setText("description");
//            mTime.setText(time);
            mCategory.setText("hostelBill.getCategory().name()");
        }
        return list;
    }

}
