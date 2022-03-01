package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unicode.app.Database.SharedPrefer;
import com.unicode.app.Models.UserModel;
import com.unicode.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeliveryAddressActivity extends AppCompatActivity {

    TextInputLayout flatHoldingRoadNo_input, postOffice_input, policeStation_input, district_input;
    Button signUp_btn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);

        flatHoldingRoadNo_input = findViewById(R.id.flatHoldingRoadNoInputBox);
        postOffice_input = findViewById(R.id.postOfficeInputBox);
        policeStation_input = findViewById(R.id.policeStationInputBox);
        district_input = findViewById(R.id.districtInputBox);
        signUp_btn = findViewById(R.id.signupButton);




        // for getting database instance, here "users" indicating the node
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = getIntent().getStringExtra("fullName");
                String password = getIntent().getStringExtra("password");
                String mobileNo = getIntent().getStringExtra("mobileNo");
                String flatHoldingRoadNo = flatHoldingRoadNo_input.getEditText().getText().toString().trim();
                String postOffice = postOffice_input.getEditText().getText().toString().trim();
                String policeStation = policeStation_input.getEditText().getText().toString().trim();
                String district = district_input.getEditText().getText().toString().trim();
                String deliveryAddress = flatHoldingRoadNo + ", " + postOffice + ", " + policeStation +  ", " + district;

                UserModel userModel = new UserModel(fullName, mobileNo, password, deliveryAddress);
                databaseReference.child(mobileNo).setValue(userModel);

                //Using Shared Preferences
                SharedPrefer sharedPrefer = new SharedPrefer(DeliveryAddressActivity.this);
                sharedPrefer.setFlatHoldingRoadNo(flatHoldingRoadNo);
                sharedPrefer.setPostOffice(postOffice);
                sharedPrefer.setPoliceStation(policeStation);
                sharedPrefer.setDistrict(district);
                sharedPrefer.setDeliveryAddress(deliveryAddress);

                Intent intent = new Intent(DeliveryAddressActivity.this, DressSegmentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}