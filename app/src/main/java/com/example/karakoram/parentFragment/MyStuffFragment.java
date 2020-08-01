package com.example.karakoram.parentFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.karakoram.R;
import com.example.karakoram.adapter.pageadapter;
import com.example.karakoram.childFragment.bill.billChildFragment;
import com.example.karakoram.childFragment.mess.messUSTFragment;
import com.example.karakoram.resource.Category;
import com.example.karakoram.resource.User;
import com.example.karakoram.upload_complain_Fragment;
import com.google.android.material.tabs.TabLayout;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MyStuffFragment extends Fragment {

    View myfragment;
    ViewPager viewPager;
    TabLayout tabLayout;

    public MyStuffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myfragment= inflater.inflate(R.layout.fragment_bill, container, false);
        viewPager=myfragment.findViewById(R.id.viewpager);
        tabLayout=myfragment.findViewById(R.id.tabs);
        return myfragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setUpViewPager(ViewPager viewPager){
        pageadapter adapter=new pageadapter(getChildFragmentManager());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(User.SHARED_PREFS, MODE_PRIVATE);
        String userid=sharedPreferences.getString("userId","logedOut");
        Log.d(userid, "setUpViewPager: "+userid);
        if(sharedPreferences.getString("type","Student").equals("Admin")) {
            adapter.addFragment(new HomeFragment(userid), "Events");
            adapter.addFragment(new billChildFragment(Category.Cultural), "Bills");
        }
        else{
            adapter.addFragment(new messUSTFragment(userid), "Mess Feedbacks");
            adapter.addFragment(new upload_complain_Fragment(), "Complains");
        }
        viewPager.setAdapter(adapter);
    }
}