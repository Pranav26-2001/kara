package com.example.karakoram.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karakoram.R;
import com.example.karakoram.FirebaseQuery;
import com.example.karakoram.resource.HostelBill;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Intent intent = this.getIntent();
        String billId = intent.getStringExtra("key");
        Query query = FirebaseQuery.getBill(billId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HostelBill bill = snapshot.getValue(HostelBill.class);
                if(bill==null)
                    Log.d("123hello","null");
                else {
                    ((TextView) findViewById(R.id.amount_output)).setText("Rs "+String.valueOf(bill.getAmount()));
                    ((TextView) findViewById(R.id.category_output)).setText(String.valueOf(bill.getCategory()));
                    ((TextView) findViewById(R.id.description_output)).setText(bill.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase error","Something went wrong");
            }
        });

        final ImageView billImage = findViewById(R.id.bill_image);
        StorageReference ref = FirebaseStorage.getInstance().getReference("hostelBillImages/"+billId+".png");
        long MAXBYTES = 1024*1024;
        ref.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                billImage.setImageBitmap(bitmap);
            }
        });
    }
}