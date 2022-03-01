package com.unicode.app.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unicode.app.Adapters.BulkOrderHistoryAdapter;
import com.unicode.app.Database.SharedPrefer;
import com.unicode.app.Models.BulkOrderHistoryModel;
import com.unicode.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BulkOrderHistoryFragment extends Fragment {

    private String mobileNo;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    public BulkOrderHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bulk_order_history, container, false);

        recyclerView = view.findViewById(R.id.orderHistoryBulkRecyclerView);

        mobileNo = new SharedPrefer(getContext()).getMobileNo();

        ArrayList<BulkOrderHistoryModel> bulkOrderList = new ArrayList<>();
        BulkOrderHistoryAdapter bulkOrderHistoryAdapter = new BulkOrderHistoryAdapter(getContext(), bulkOrderList );

        databaseReference = FirebaseDatabase.getInstance().getReference("orders/bulk");

        recyclerView.setAdapter(bulkOrderHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference.orderByChild("mobileNo").equalTo(mobileNo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BulkOrderHistoryModel bulkOrderHistoryModel = dataSnapshot.getValue(BulkOrderHistoryModel.class);
                    //0 is added as index for showing latest data first
                    bulkOrderList.add(0, bulkOrderHistoryModel);
                }
                bulkOrderHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

}