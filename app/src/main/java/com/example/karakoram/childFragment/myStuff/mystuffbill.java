package com.example.karakoram.childFragment.myStuff;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karakoram.FirebaseQuery;
import com.example.karakoram.R;
import com.example.karakoram.adapter.HostelBillAdapter;
import com.example.karakoram.resource.HostelBill;
import com.example.karakoram.resource.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class mystuffbill extends Fragment {

//   ArrayList<Integer> bill= new ArrayList<>();

    /* Variables */
    private ArrayList<String> key = new ArrayList<>();
    private ArrayList<HostelBill> hostelBill;


    /* Views */
    private View view;
    private RecyclerView listView;
    private FloatingActionButton fab;
    Drawable mdivider;

    /* Adapters */
    private HostelBillAdapter adapter;

    public mystuffbill() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_bill_child, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fab=view.findViewById(R.id.FABchild_bill);
        fab.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(User.SHARED_PREFS, MODE_PRIVATE);

        String userId = sharedPreferences.getString("userId","loggedOut");

        FirebaseQuery.getUserBill(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hostelBill = new ArrayList<>();
                for (DataSnapshot snapshotItem : snapshot.getChildren()) {
                    hostelBill.add(snapshotItem.getValue(HostelBill.class));
                    key.add(snapshotItem.getKey());
                }
                start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase error", "Something went wrong");
            }
        });


    }


    private void start() {
        Log.i("ASDF", String.valueOf(hostelBill));
        adapter = new HostelBillAdapter(getActivity(), hostelBill,key);
        listView = view.findViewById(R.id.billlistView);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mdivider= ContextCompat.getDrawable(view.getContext(),R.drawable.divider);
        //mdivider.setBounds(getParentFragment().getPaddingLeft(),0,16,0);
        DividerItemDecoration itemdecor=new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
        itemdecor.setDrawable(mdivider);
        listView.addItemDecoration(itemdecor);
        listView.setAdapter(adapter);
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HostelBillDescription.class);
                intent.putExtra("key", key.get(i));
                startActivity(intent);
            }
        }); */
    }
}