package com.unicode.app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unicode.app.Activities.DressSegmentActivity;
import com.unicode.app.Database.SharedPrefer;
import com.unicode.app.Models.BulkOrderModel;
import com.unicode.app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SizesFragment extends Fragment {

    private TextView sCounter_tv, mCounter_tv, lCounter_tv, xlCounter_tv, xxlCounter_tv, xxxlCounter_tv;
    private ImageView sIncreaser_iv, mIncreaser_iv, lIncreaser_iv, xlIncreaser_iv, xxlIncreaser_iv, xxxlIncreaser_iv,
                sDecreaser_iv, mDecreaser_iv, lDecreaser_iv, xlDecreaser_iv, xxlDecreaser_iv, xxxlDecreaser_iv;
    private Button placeOrderSizes_btn;

    private DatabaseReference databaseReference; //variable for database instance

    private int[] counter = {0, 0, 0, 0, 0, 0};
    private String dressType, gender, dressSegment, orderTimestamp12, orderTimestamp24, orderTimestampAsId;


    public SizesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sizes, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        dressType= bundle.getString("dress_type");
        dressSegment = bundle.getString("dress_segment");
        gender = bundle.getString("gender");

        sCounter_tv = view.findViewById(R.id.sCounterTextView);
        sIncreaser_iv = view.findViewById(R.id.sIncreaserImageView);
        sDecreaser_iv = view.findViewById(R.id.sDecreaserImageView);

        mCounter_tv = view.findViewById(R.id.mCounterTextView);
        mIncreaser_iv = view.findViewById(R.id.mIncreaserImageView);
        mDecreaser_iv = view.findViewById(R.id.mDecreaserImageView);

        lCounter_tv = view.findViewById(R.id.lCounterTextView);
        lIncreaser_iv = view.findViewById(R.id.lIncreaserImageView);
        lDecreaser_iv = view.findViewById(R.id.lDecreaserImageView);

        xlCounter_tv = view.findViewById(R.id.xlCounterTextView);
        xlIncreaser_iv = view.findViewById(R.id.xlIncreaserImageView);
        xlDecreaser_iv = view.findViewById(R.id.xlDecreaserImageView);

        xxlCounter_tv = view.findViewById(R.id.xxlCounterTextView);
        xxlIncreaser_iv = view.findViewById(R.id.xxlIncreaserImageView);
        xxlDecreaser_iv = view.findViewById(R.id.xxlDecreaserImageView);

        xxxlCounter_tv = view.findViewById(R.id.xxxlCounterTextView);
        xxxlIncreaser_iv = view.findViewById(R.id.xxxlIncreaserImageView);
        xxxlDecreaser_iv = view.findViewById(R.id.xxxlDecreaserImageView);

        placeOrderSizes_btn = view.findViewById(R.id.placeOrderSizesButton);

        databaseReference = FirebaseDatabase.getInstance().getReference("orders/bulk");



        sIncreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[0]++;
                sCounter_tv.setText(counter[0]+"");
            }
        });

        sDecreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[0]--;
                sCounter_tv.setText(counter[0]+"");
            }
        });

        mIncreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[1]++;
                mCounter_tv.setText(counter[1]+"");
            }
        });

        mDecreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[1]--;
                mCounter_tv.setText(counter[1]+"");
            }
        });

        lIncreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[2]++;
                lCounter_tv.setText(counter[2]+"");
            }
        });

        lDecreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[2]--;
                lCounter_tv.setText(counter[2]+"");
            }
        });

        xlIncreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[3]++;
                xlCounter_tv.setText(counter[3]+"");
            }
        });

        xlDecreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[3]--;
                xlCounter_tv.setText(counter[3]+"");
            }
        });

        xxlIncreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[4]++;
                xxlCounter_tv.setText(counter[4]+"");
            }
        });

        xxlDecreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[4]--;
                xxlCounter_tv.setText(counter[4]+"");
            }
        });

        xxxlIncreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[5]++;
                xxxlCounter_tv.setText(counter[5]+"");
            }
        });

        xxxlDecreaser_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[5]--;
                xxxlCounter_tv.setText(counter[5]+"");
            }
        });

        placeOrderSizes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collectTimestamp();
                storeToFirebase();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void collectTimestamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat24 = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss", Locale.getDefault());
        SimpleDateFormat simpleDateFormat12 = new SimpleDateFormat("dd-MM-yyyy KK:mm:ssaaa", Locale.getDefault());
        SimpleDateFormat simpleDateFormatAsId = new SimpleDateFormat("ddMMyyyykkmmss", Locale.getDefault());

        orderTimestamp12 = simpleDateFormat12.format(calendar.getTime()).toString().trim();
        orderTimestamp24 = simpleDateFormat24.format(calendar.getTime()).toString().trim();
        orderTimestampAsId = simpleDateFormatAsId.format(calendar.getTime()).toString().trim();
    }

    private void storeToFirebase() {
        String sSize = sCounter_tv.getText().toString().trim();
        String mSize = mCounter_tv.getText().toString().trim();
        String lSize = lCounter_tv.getText().toString().trim();
        String xlSize = xlCounter_tv.getText().toString().trim();
        String xxlSize = xxlCounter_tv.getText().toString().trim();
        String xxxlSize = xxxlCounter_tv.getText().toString().trim();

        String mobileNo = new SharedPrefer(getContext()).getMobileNo();
        String fullName = new SharedPrefer(getContext()).getFullName();
        //String bulkOrderKey = databaseReference.push().getKey();

        BulkOrderModel bulkOrderModel = new BulkOrderModel(orderTimestampAsId, dressSegment, gender, dressType, mobileNo, fullName, sSize, mSize, lSize, xlSize, xxlSize, xxxlSize, orderTimestamp12);

        databaseReference.child(orderTimestamp24).setValue(bulkOrderModel);
        Toast.makeText(getContext(), "Your order has been placed successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), DressSegmentActivity.class));
    }

}