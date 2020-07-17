package com.example.karakoram.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.karakoram.R;
import com.example.karakoram.firebase.FirebaseQuery;
import com.example.karakoram.resource.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Intent intent = this.getIntent();
        final String title = intent.getStringExtra("title");
        Query query = FirebaseQuery.getEvent(title);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Event event = snapshot.getChildren().iterator().next().getValue(Event.class);
                assert event != null;
                ((TextView)findViewById(R.id.description)).setText(event.getDescription());
                ((TextView)findViewById(R.id.title)).setText(title);
                String date = (event.getDateTime().getYear() + 1900) + "-" + String.format("%02d",event.getDateTime().getMonth() + 1) + "-" + String.format("%02d",event.getDateTime().getDate());
                String time = String.format("%02d",event.getDateTime().getHours()) + " : " + String.format("%02d",event.getDateTime().getMinutes());
                ((TextView)findViewById(R.id.date_output)).setText(date);
                ((TextView)findViewById(R.id.time_output)).setText(time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase error","Something went wrong");
            }
        });
    }
}