package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.unicode.app.R;

public class UniformMaleDressType extends AppCompatActivity {
    private CardView shirt_cv, pant_cv, tshirt_cv, trouser_cv, blazer_cv, apron_cv;
    private TextView shirt_tv, pant_tv, tshirt_tv, trouser_tv, blazer_tv, apron_tv;
    private String dressType, dressSegment, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniform_male_dress_type);

        //Receiving data
        dressSegment = getIntent().getStringExtra("dress_segment");
        gender = getIntent().getStringExtra("gender");

        shirt_cv = findViewById(R.id.shirtUniformMaleCardView);
        shirt_tv = findViewById(R.id.shirtUniformMaleTitleTextView);

        pant_cv = findViewById(R.id.pantUniformMaleCardView);
        pant_tv = findViewById(R.id.pantUniformMaleTitleTextView);

        tshirt_cv = findViewById(R.id.tshirtUniformMaleCardView);
        tshirt_tv = findViewById(R.id.tshirtUniformMaleTitleTextView);

        trouser_cv = findViewById(R.id.trouserUniformMaleCardView);
        trouser_tv = findViewById(R.id.trouserUniformMaleTextView);

        blazer_cv = findViewById(R.id.blazerUniformMaleCardView);
        blazer_tv = findViewById(R.id.blazerUniformMaleTextView);

        apron_cv = findViewById(R.id.apronUniformMaleCardView);
        apron_tv = findViewById(R.id.apronUniformMaleTextView);

        shirt_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dressType = shirt_tv.getText().toString().trim();
                //clothIcon =

                Intent intent = new Intent(UniformMaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_shirt);
                startActivity(intent);
            }
        });

        pant_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dressType = pant_tv.getText().toString().trim();

                Intent intent = new Intent(UniformMaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_pant);
                startActivity(intent);
            }
        });

        tshirt_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dressType = tshirt_tv.getText().toString().trim();
                Intent intent = new Intent(UniformMaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_t_shirt);
                startActivity(intent);
            }
        });

        trouser_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dressType = trouser_tv.getText().toString().trim();
                Intent intent = new Intent(UniformMaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_trouser);
                startActivity(intent);
            }
        });

        blazer_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dressType = blazer_tv.getText().toString().trim();
                Intent intent = new Intent(UniformMaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_blazer);
                startActivity(intent);
            }
        });

        apron_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dressType = apron_tv.getText().toString().trim();
                Intent intent = new Intent(UniformMaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_apron);
                startActivity(intent);
            }
        });






    }
}