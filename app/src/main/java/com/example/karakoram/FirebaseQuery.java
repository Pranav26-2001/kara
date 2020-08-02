package com.example.karakoram;

import android.net.Uri;

import com.example.karakoram.resource.Category;
import com.example.karakoram.resource.Complaint;
import com.example.karakoram.resource.Event;
import com.example.karakoram.resource.HostelBill;
import com.example.karakoram.resource.Menu;
import com.example.karakoram.resource.MessFeedback;
import com.example.karakoram.resource.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseQuery {

    private static DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static FirebaseStorage storage = FirebaseStorage.getInstance();

    public static Query getUserDetail(String id){
        return ref.child("users").child(id);
    }

    public static String addUser(User user){
        String key = ref.child("users").push().getKey();
        ref.child("users").child(key).setValue(user);
        return key;
    }

    public static Query getUserByEntryNumber(String entryNumber){
        return ref.child("users").orderByChild("entryNumber").equalTo(entryNumber);
    }

    public static Query getEvent(String key){
        return ref.child("events").child(key);
    }

    public static StorageReference getEventImageRef(String key){
        return storage.getReference("eventImages/"+key+".png");
    }

    public static Query getAllEvents(){
        return ref.child("events").orderByChild("userId");
    }

    public static Query getUserEvents(String userId){
        return ref.child("events").orderByChild("userId").equalTo(userId);
    }

    public static void addEvent(Event event, Uri imageUri){
        String key = ref.child("events").push().getKey();
        ref.child("events").child(key).setValue(event);
        storage.getReference("/eventImages/"+key+".png").putFile(imageUri);
    }

    public static Query getCategoryBills(Category category){
        return ref.child("hostelBills").orderByChild("category").equalTo(String.valueOf(category));
    }

    public static Query getBill(String key){
        return ref.child("hostelBills").child(key);
    }

    public static StorageReference getBillImageRef(String key){
        return storage.getReference("hostelBillImages/"+key+".png");
    }

    public static void addBill(HostelBill bill, Uri imageUri){
        String key = ref.child("hostelBills").push().getKey();
        ref.child("hostelBills").child(key).setValue(bill);
        storage.getReference("/hostelBillImages/"+key+".png").putFile(imageUri);
    }

    public static void addMessFeedBack(MessFeedback messFeedback){
        String key = ref.child("messFeedback").push().getKey();
        ref.child("messFeedback").child(key).setValue(messFeedback);
    }

    public static Query getAllMessFeedback(){
        return ref.child("messFeedback");
    }

    public Query getUserMessFeedback(String userId){
        return ref.child("messFeedBack").orderByChild("userId").equalTo(userId);
    }

    public static Query getAllMenu(){
        return ref.child("messMenu");
    }

    public static  void updateMenu(Menu menu, int day){
        ref.child("messMenu").child(String.valueOf(day)).setValue(menu);
    }

    public static Query getMaintComplaints(){
        return ref.child("complaints").orderByChild("category").equalTo("Maintenance");
    }

    public static Query getMessComplaints(){
        return ref.child("complaints").orderByChild("category").equalTo("Mess");
    }

    public static Query getOtherComplaints(){
        return ref.child("complaints").orderByChild("category").equalTo("Others");
    }

    public static void addCompliant(Complaint complaint){
        String key = ref.child("complaints").push().getKey();
        ref.child("complaints").child(key).setValue(complaint);
    }

    public static void addCompliant(Complaint complaint, Uri imageUri){
        String key = ref.child("complaints").push().getKey();
        ref.child("complaints").child(key).setValue(complaint);
        storage.getReference("/complaintImages/"+key+".png").putFile(imageUri);
    }

}