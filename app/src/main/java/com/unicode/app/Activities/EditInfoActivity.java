package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.unicode.app.Database.SharedPrefer;
import com.unicode.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditInfoActivity extends AppCompatActivity {
    TextInputLayout flatHoldingRoadNo_input, postOffice_input, policeStation_input, district_input;
    Button updateInfo_btn;
    //String deliveryAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        flatHoldingRoadNo_input = findViewById(R.id.flatHoldingRoadNoEditInfoInputBox);
        postOffice_input = findViewById(R.id.postOfficeEditInfoInputBox);
        policeStation_input = findViewById(R.id.policeStationEditInfoInputBox);
        district_input = findViewById(R.id.districtEditInfoInputBox);
        updateInfo_btn = findViewById(R.id.updateInfoButtonUserProfile);

        SharedPrefer sharedPrefer = new SharedPrefer(EditInfoActivity.this);
        flatHoldingRoadNo_input.getEditText().setText(sharedPrefer.getFlatHoldingRoadNo());
        postOffice_input.getEditText().setText(sharedPrefer.getPostOffice());
        policeStation_input.getEditText().setText(sharedPrefer.getPoliceStation());
        district_input.getEditText().setText(sharedPrefer.getDistrict());


        updateInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updateFlatHoldingRoadNo = flatHoldingRoadNo_input.getEditText().getText().toString().trim();
                String updatePostOffice = postOffice_input.getEditText().getText().toString().trim();
                String updatePoliceStation = policeStation_input.getEditText().getText().toString().trim();
                String updateDistrict = district_input.getEditText().getText().toString().trim();
                String updateDeliveryAddress = updateFlatHoldingRoadNo + ", " + updatePostOffice + ", " + updatePoliceStation +  ", " + updateDistrict;

                SharedPrefer sharedPrefer = new SharedPrefer(EditInfoActivity.this);
                String mobileNo = sharedPrefer.getMobileNo();
                sharedPrefer.setFlatHoldingRoadNo(updateFlatHoldingRoadNo);
                sharedPrefer.setPostOffice(updatePostOffice);
                sharedPrefer.setPoliceStation(updatePoliceStation);
                sharedPrefer.setDistrict(updateDistrict);
                sharedPrefer.setDeliveryAddress(updateDeliveryAddress);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                databaseReference.child(mobileNo).child("deliveryAddress").setValue(updateDeliveryAddress);

                Toast.makeText(EditInfoActivity.this, "Your Profile Has Been Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditInfoActivity.this, UserProfileActivity.class));
            }
        });
    }
}