package com.unicode.app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.unicode.app.Activities.DressSegmentActivity;
import com.unicode.app.Adapters.PrecisionsAdapter;
import com.unicode.app.Database.SharedPrefer;
import com.unicode.app.Models.PrecisedOrderModel;
import com.unicode.app.Models.SelectedPrecisionsModel;
import com.unicode.app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PrecisionsFragment extends Fragment {

    private String dressType, precisionDataJson, gender, dressSegment,  orderTimestamp12, orderTimestamp24, orderTimestampAsId;
    private int clothIcon;
    private String[] precisions, selectedPrecisions;
    public ArrayList<SelectedPrecisionsModel> precisionsArrayList;

    DatabaseReference databaseReference;

    public PrecisionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_precisions, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.precisionsRecyclerView);
        Button placeOrderPrecisions_btn = view.findViewById(R.id.placeOrderPrecisionsButton);
        precisions = getResources().getStringArray(R.array.precision_list);

        Bundle bundle = getActivity().getIntent().getExtras();
        dressType= bundle.getString("dress_type");
        dressSegment = bundle.getString("dress_segment");
        gender = bundle.getString("gender");
        clothIcon = bundle.getInt("icon_cloth");


        checkDressType();

        //sending data to adapter
        PrecisionsAdapter precisionsAdapter = new PrecisionsAdapter(getContext(), precisionsArrayList);

        recyclerView.setAdapter(precisionsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        databaseReference = FirebaseDatabase.getInstance().getReference("orders/precised");

        placeOrderPrecisions_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String values = "";
//                orderConfirmation();

                Intent intent = new Intent(getContext(), DressSegmentActivity.class);
                intent.putExtra("icon_cloth", clothIcon);
                intent.putExtra("dress_segment", dressSegment);
                intent.putExtra("gender", gender);
                intent.putExtra("dress_type", dressType);

                Gson gson = new Gson();
                precisionDataJson = gson.toJson(PrecisionsAdapter.precisionsArrayList);

                intent.putExtra("precision_data", precisionDataJson);
                startActivity(intent);

                collectTimestamp();
                storeToFirebase();

            }
        });
        return view;
    }

    private void checkDressType() {
        if (dressType.equals("Shirt")){
            selectedPrecisions = new String[] {precisions[0], precisions[1], precisions[2], precisions[3], precisions[4], precisions[5], precisions[6], precisions[7]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("Pant")){
            selectedPrecisions = new String[] {precisions[0], precisions[8], precisions[9], precisions[10], precisions[11]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("T-Shirt")){
            selectedPrecisions = new String[] {precisions[0], precisions[1], precisions[2], precisions[3], precisions[4], precisions[5], precisions[6], precisions[7]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("Trouser")){
            selectedPrecisions = new String[] {precisions[0], precisions[8], precisions[9], precisions[10], precisions[11]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("Blazer")){
            selectedPrecisions = new String[] {precisions[0], precisions[1], precisions[2], precisions[3], precisions[4], precisions[5], precisions[6], precisions[7]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("Apron")){
            selectedPrecisions = new String[] {precisions[0], precisions[1], precisions[2], precisions[3], precisions[4], precisions[5], precisions[6], precisions[7]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("Tunic")){
            selectedPrecisions = new String[] {precisions[0], precisions[8], precisions[9]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("Skirt")){
            selectedPrecisions = new String[] {precisions[0], precisions[8], precisions[9]};
            precisionsArrayList = populateList();
        }
        else if (dressType.equals("Selwar Kameez")){
            selectedPrecisions = new String[] {precisions[0], precisions[1], precisions[2], precisions[3], precisions[4], precisions[5], precisions[6], precisions[7], precisions[8]};
            precisionsArrayList = populateList();
        }
        else {
            //selectedPrecisions = new String[] {precisions[2], precisions[4], precisions[6], precisions[7]};
            Toast.makeText(getContext(), "Invalid type of dress found. Please try again later.", Toast.LENGTH_SHORT).show();
        }
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
        String mobileNo = new SharedPrefer(getContext()).getMobileNo();
        String fullName = new SharedPrefer(getContext()).getFullName();

        PrecisedOrderModel precisedOrderModel = new PrecisedOrderModel( orderTimestampAsId, dressSegment, gender, dressType, mobileNo, fullName, orderTimestamp12, precisionDataJson);
        databaseReference.child(orderTimestamp24).setValue(precisedOrderModel);
        Toast.makeText(getContext(), "Order placed in firebase", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<SelectedPrecisionsModel> populateList(){

        ArrayList<SelectedPrecisionsModel> list = new ArrayList<>();

        for(int i = 0; i < selectedPrecisions.length; i++){
            SelectedPrecisionsModel selectedPrecisionsModel = new SelectedPrecisionsModel();
            selectedPrecisionsModel.setPrecisionValue("0");
            selectedPrecisionsModel.setPrecisionName(selectedPrecisions[i]);
            list.add(selectedPrecisionsModel);
        }

        return list;
    }

    //    private void orderConfirmation() {
//
//        AlertDialog.Builder orderConfirmationDialog = new AlertDialog.Builder(getActivity());
//        orderConfirmationDialog.setTitle("This is title");
//        orderConfirmationDialog.setIcon(R.drawable.ic_apron);
//        orderConfirmationDialog.setMessage("This is message");
//        orderConfirmationDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getContext(), "You clicked yes", Toast.LENGTH_SHORT).show();
//            }
//        });
//        orderConfirmationDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getContext(), "You have clicked no", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        AlertDialog alertDialog = orderConfirmationDialog.create();
//        alertDialog.show();
//    }

}