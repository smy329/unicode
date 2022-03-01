package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.unicode.app.R;

public class Gender extends AppCompatActivity {

    LinearLayout male_cv, female_cv;
    private String dressSegment, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        male_cv = findViewById(R.id.genderMaleLL);
        female_cv = findViewById(R.id.genderFemaleLL);

        dressSegment = getIntent().getStringExtra("dress_segment");

        male_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Gender.this, UniformMaleDressType.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", "Male");
                startActivity(intent);
            }
        });

        female_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Gender.this, UniformFemaleDressType.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", "Female");
                startActivity(intent);
            }
        });
    }
}