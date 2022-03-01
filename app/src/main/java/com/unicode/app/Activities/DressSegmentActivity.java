package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.unicode.app.R;

public class DressSegmentActivity extends AppCompatActivity {

    CardView uniform_cv, events_cv, tailoring_cv, designing_cv;
    ImageView iconProfile_iv;
    private String businessAreaCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_segment);

        uniform_cv = findViewById(R.id.uniformCardView);
        events_cv = findViewById(R.id.eventsCardView);
        tailoring_cv = findViewById(R.id.tailoringCardView);
        designing_cv = findViewById(R.id.designingCardView);
        iconProfile_iv = findViewById(R.id.profileIconImageView);

        iconProfile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        uniform_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Gender.class);
                intent.putExtra("dress_segment", "Uniform");
                startActivity(intent);
            }
        });

        events_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DressSegmentActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });

        tailoring_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DressSegmentActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        designing_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DressSegmentActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}