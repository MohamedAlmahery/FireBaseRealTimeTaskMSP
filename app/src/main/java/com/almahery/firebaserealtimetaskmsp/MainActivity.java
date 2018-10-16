package com.almahery.firebaserealtimetaskmsp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener {
    private TextView Text;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootRefrence = firebaseDatabase.getReference();
    private DatabaseReference mHeadingReference = mRootRefrence.child("heading");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text = (TextView)findViewById(R.id.textView);

    }

    public void cloud_click(View view) {

        String heading = ("Cloudy");
        mHeadingReference.setValue(heading);

    }

    public void sunny_click(View view) {
        String heading = ("Sunny");
        mHeadingReference.setValue(heading);
    }

    public void cold_click(View view) {
        String heading = ("Cold");
        mHeadingReference.setValue(heading);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        if (dataSnapshot.getValue(String.class)!=null){
            String key = dataSnapshot.getKey();
            if(key.equals("heading")){
                String heading = dataSnapshot.getValue(String.class);
                Text.setText(heading);
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart(){
       super.onStart();
       mHeadingReference.addValueEventListener(this);

    }
}
