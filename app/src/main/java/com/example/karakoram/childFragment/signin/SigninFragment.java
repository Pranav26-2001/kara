package com.example.karakoram.childFragment.signin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.karakoram.FirebaseQuery;
import com.example.karakoram.R;
import com.example.karakoram.resource.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import static android.content.Context.MODE_PRIVATE;

public class SigninFragment extends Fragment {

    View view;
    private EditText mPassword, mEntryNoEdit;

    public SigninFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signin, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEntryNoEdit = view.findViewById(R.id.et_login_entry_number);
        mPassword = view.findViewById(R.id.et_login_password);
        setButtons();
    }

    private void setButtons() {
        Button submit = view.findViewById(R.id.button_sign_in);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String password = mPassword.getText().toString();
                String entryNumber = mEntryNoEdit.getText().toString();
                Query query = FirebaseQuery.getUserByEntryNumber(entryNumber);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
                        if (iterator.hasNext()) {
                            DataSnapshot dataSnapshot = iterator.next();
                            User user = dataSnapshot.getValue(User.class);
                            if (password.equals(user.getPassword())) {
                                String key = dataSnapshot.getKey();
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(User.SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userId", key);
                                editor.putString("userName", user.getName());
                                editor.putString("entryNumber", user.getEntryNumber());
                                editor.putString("room", user.getRoom());
                                editor.putString("wing", user.getWing());
                                editor.putString("type", user.getType().toString());
                                editor.apply();
                                Toast.makeText(getActivity().getApplicationContext(), "logged in as " + user.getName(), Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            } else
                                Toast.makeText(getActivity().getApplicationContext(), "incorrect password", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getActivity().getApplicationContext(), "user does not exist", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        TextView forgot = view.findViewById(R.id.tv_login_forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "error: contact developers", Toast.LENGTH_SHORT).show();
            }
        });
    }


}