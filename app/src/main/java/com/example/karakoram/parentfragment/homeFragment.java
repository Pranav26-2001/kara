package com.example.karakoram.parentfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.karakoram.R;
import com.example.karakoram.childFragment.EventResources.Event;
import com.example.karakoram.childFragment.EventResources.EventAdapter;
import com.example.karakoram.childFragment.EventResources.Eventdiscription;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class homeFragment extends Fragment  {

    final ArrayList<Event> event = new ArrayList<Event>();
    View view;
    ListView listView;
    FloatingActionButton fab;


    public homeFragment() {
        // Required empty public constructor
    }








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Event, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);  */

        event.add(new Event("Event 1","Mess meeting","9 PM","..........",R.drawable.download_1));
        event.add(new Event("Event 2","maint meeting","10 PM",".............",R.drawable.download_2));
        event.add(new Event("Event 3","elction meeting","5 PM","................",R.drawable.download_3));
        event.add(new Event("Event 4","sports meeting","9 PM","..........."));
        event.add(new Event("Event 5","general meeting","11 PM","..........."));
        event.add(new Event("Event 6","freshers meeting","8 AM","..........."));
        event.add(new Event("Event 7","Mess meeting","12 PM","..........."));
        event.add(new Event("Event 8","Mess meeting","12 PM",".........."));
        event.add(new Event("Event 9","Mess meeting","12 PM",".............."));

        EventAdapter adapter = new EventAdapter(getActivity(),event);
         listView = (ListView) view.findViewById(R.id.list_event);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent= new Intent(getActivity().getApplicationContext(), Eventdiscription.class);
                intent.putExtra("event_name",event.get(i).getEvent_name());
                intent.putExtra("event_title",event.get(i).getEvent_title());
                intent.putExtra("event_details",event.get(i).getEvent_details());
                intent.putExtra("event_time",event.get(i).getEvent_time());
                if(event.get(i).hasimage()){
                intent.putExtra("event_image",event.get(i).getEvent_image());
                    Log.d("image", "onItemClick: image"+event.get(i).getEvent_image());
                }

                startActivity(intent);
                //showDailog(i);
            }});

        fab=view.findViewById(R.id.FAB_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"add event",Snackbar.LENGTH_LONG).setAction("Action",null).show();


            }
        });


    }




   /* public void showDailog(int i)
    {    Event m=event.get(i);
         FragmentManager manager=getFragmentManager();
         mydialog dial=new mydialog(m.getEvent_name(),m.getEvent_details(),m.getEvent_time());
         dial.show(manager,"mydialog");

    } */


}