package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.unicode.app.R;

public class UniformFemaleDressType extends AppCompatActivity {

    private CardView shirt_cv, pant_cv, tshirt_cv, trouser_cv, blazer_cv, apron_cv, skirt_cv, tunic_cv;
    private TextView shirt_tv, pant_tv, tshirt_tv, trouser_tv, blazer_tv, apron_tv, skirt_tv, tunic_tv;
    private String dressType, dressSegment, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniform_female_dress_type);

        //Receiving data
        dressSegment = getIntent().getStringExtra("dress_segment");
        gender = getIntent().getStringExtra("gender");

        shirt_cv = findViewById(R.id.shirtUniformFemaleCardView);
        shirt_tv = findViewById(R.id.shirtUniformFemaleTitleTextView);

        pant_cv = findViewById(R.id.pantUniformFemaleCardView);
        pant_tv = findViewById(R.id.pantUniformFemaleTitleTextView);

        tshirt_cv = findViewById(R.id.tshirtUniformFemaleCardView);
        tshirt_tv = findViewById(R.id.tshirtUniformFemaleTitleTextView);

        trouser_cv = findViewById(R.id.trouserUniformFemaleCardView);
        trouser_tv = findViewById(R.id.trouserUniformFemaleTextView);

        blazer_cv = findViewById(R.id.blazerUniformFemaleCardView);
        blazer_tv = findViewById(R.id.blazerUniformFemaleTextView);

        apron_cv = findViewById(R.id.apronUniformFemaleCardView);
        apron_tv = findViewById(R.id.apronUniformFemaleTextView);

        skirt_cv = findViewById(R.id.skirtUniformFemaleCardView);
        skirt_tv = findViewById(R.id.skirtUniformFemaleTextView);

        tunic_cv = findViewById(R.id.tunicUniformFemaleCardView);
        tunic_tv = findViewById(R.id.tunicUniformFemaleTextView);

        shirt_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dressType = shirt_tv.getText().toString().trim();
                //clothIcon =

                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
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

                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
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
                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
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
                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
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
                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
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
                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_apron);
                startActivity(intent);
            }
        });

        skirt_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dressType = skirt_tv.getText().toString().trim();
                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_skirt);
                startActivity(intent);
            }
        });

        tunic_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dressType = tunic_tv.getText().toString().trim();
                Intent intent = new Intent(UniformFemaleDressType.this, OrderActivity.class);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);
                intent.putExtra("icon_cloth", R.drawable.ic_tunic);
                startActivity(intent);
            }
        });



    }
}