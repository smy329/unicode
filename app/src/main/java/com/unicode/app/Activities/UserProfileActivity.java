package com.unicode.app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unicode.app.Database.SharedPrefer;
import com.unicode.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {

    TextView fullname_tv, mobileNo_tv, deliveryAddress_tv;
    ImageView logoutIcon_iv, editInfo_iv;
    DatabaseReference databaseReference;
    Button orderHistory_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullname_tv = findViewById(R.id.fullNameTextViewProfile);
        mobileNo_tv = findViewById(R.id.mobileNoTextViewProfile);
        logoutIcon_iv = findViewById(R.id.logoutIconImageView);
        deliveryAddress_tv = findViewById(R.id.deliveryAddressUserProfileTextView);
        editInfo_iv = findViewById(R.id.editInfoIconUserProfile);
        orderHistory_button = findViewById(R.id.orderHistoryButtonUserProfile);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        SharedPrefer sharedPrefer = new SharedPrefer(UserProfileActivity.this);
        String fullname = sharedPrefer.getFullName();
        String mobileNo = sharedPrefer.getMobileNo();
        String deliveryAddress = sharedPrefer.getDeliveryAddress();

        fullname_tv.setText(fullname);
        mobileNo_tv.setText(mobileNo);
        deliveryAddress_tv.setText(deliveryAddress);

        //retrieveDataFromFirebase();

        logoutIcon_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPrefer sharedPrefer = new SharedPrefer(UserProfileActivity.this);
                sharedPrefer.clear();

                Intent intent = new Intent(UserProfileActivity.this, Login.class);
                startActivity(intent);

            }
        });

        editInfo_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, EditInfoActivity.class);
                startActivity(intent);
            }
        });

        orderHistory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    public void retrieveDataFromFirebase(){

        //getting mobileNo from Shared Preference
        SharedPrefer sharedPrefer = new SharedPrefer(UserProfileActivity.this);
        String mobileNo = sharedPrefer.getMobileNo();

        databaseReference.child(mobileNo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String deliveryAddressFromDB = snapshot.child("deliveryAddress").getValue(String.class);
                deliveryAddress_tv.setText(deliveryAddressFromDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}