package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.unicode.app.Adapters.PrecisedOrderHistoryAdapter;
import com.unicode.app.Fragments.BulkOrderHistoryFragment;
import com.unicode.app.Fragments.PrecisedOrderHistoryFragment;
import com.unicode.app.Models.PrecisedOrderHistoryModel;
import com.unicode.app.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    private String mobileNo;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private PrecisedOrderHistoryAdapter precisedOrderHistoryAdapter;
    private ArrayList<PrecisedOrderHistoryModel> precisedOrderList;
    private Spinner orderHistoryTypeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        orderHistoryTypeSpinner = findViewById(R.id.orderHistoryTypeSpinner);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.order_type_list, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderHistoryTypeSpinner.setAdapter(spinnerAdapter);
        orderHistoryTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(String.valueOf(orderHistoryTypeSpinner.getSelectedItem()).equals("Precised Order")) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // replace the FrameLayout with new Fragment
                    fragmentTransaction.replace(R.id.frameLayout, new PrecisedOrderHistoryFragment());
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    fragmentTransaction.commit(); // save the changes
                }
                else if (String.valueOf(orderHistoryTypeSpinner.getSelectedItem()).equals("Bulk Order")) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    // create a FragmentTransaction to begin the transaction and replace the Fragment
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // replace the FrameLayout with new Fragment
                    fragmentTransaction.replace(R.id.frameLayout, new BulkOrderHistoryFragment());
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                    fragmentTransaction.commit(); // save the changes
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}