package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unicode.app.Models.SelectedPrecisionsModel;
import com.unicode.app.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationActivity extends AppCompatActivity {

    private TextView dressType_tv,desc_tv, precisionName_tv, precisionvalue_tv;
    private ImageView clothIcon_iv;
    private Button orderConfirmation_btn;
    String dressType;
    int clothIcon;
    String precisionList;
    private static ArrayList<SelectedPrecisionsModel> dataList = new ArrayList<SelectedPrecisionsModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        dressType_tv = findViewById(R.id.clothTitleTextViewOrderConfirmation);
        desc_tv = findViewById(R.id.descTextViewOrderConfirmation);
        clothIcon_iv = findViewById(R.id.imageViewOrderConfirmation);
        precisionName_tv = findViewById(R.id.precisionNameOrderConfirmationTextView);
        precisionvalue_tv = findViewById(R.id.precisionValueOrderConfirmationTextView);
        orderConfirmation_btn = findViewById(R.id.orderConfirmButton);

        try {
            dressType = getIntent().getStringExtra("dress_type");
            Bundle bundle = getIntent().getExtras();
            clothIcon = bundle.getInt("icon_cloth");
            clothIcon_iv.setImageResource(clothIcon);

            precisionList = getIntent().getStringExtra("precision_data");

            Gson gson = new Gson();
            TypeToken<List<SelectedPrecisionsModel>> token = new TypeToken<List<SelectedPrecisionsModel>>(){

            };
            dataList = gson.fromJson(precisionList, token.getType());
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        dressType_tv.setText(dressType);
        clothIcon_iv.setImageResource(clothIcon);

        String values = "";
        for(int i = 0; i < dataList.size(); i++){

            values += dataList.get(i).getPrecisionName() +System.getProperty("line.separator")+System.getProperty("line.separator");

        }

        precisionName_tv.setText(values);

        values = "";
        for(int i = 0; i < dataList.size(); i++){

            values +=  dataList.get(i).getPrecisionValue()+ " Inches" +System.getProperty("line.separator")+System.getProperty("line.separator");

        }

        precisionvalue_tv.setText(values);

        orderConfirmation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderConfirmationActivity.this, "Order placed successfully. We will contact with you very soon", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OrderConfirmationActivity.this, DressSegmentActivity.class);
                startActivity(intent);
            }
        });

    }

}