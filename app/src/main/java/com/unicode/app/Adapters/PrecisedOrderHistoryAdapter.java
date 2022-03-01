package com.unicode.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicode.app.Models.PrecisedOrderHistoryModel;
import com.unicode.app.Models.SelectedPrecisionsModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unicode.app.R;

import java.util.ArrayList;
import java.util.List;

public class PrecisedOrderHistoryAdapter extends RecyclerView.Adapter<PrecisedOrderHistoryAdapter.PrecisedOrderHistoryViewHolder> {

    Context context;
    ArrayList<PrecisedOrderHistoryModel> precisedOrderList;
    private static ArrayList<SelectedPrecisionsModel> dataList = new ArrayList<SelectedPrecisionsModel>();

    public PrecisedOrderHistoryAdapter(Context context, ArrayList<PrecisedOrderHistoryModel> precisedOrderList) {
        this.context = context;
        this.precisedOrderList = precisedOrderList;
    }

    @NonNull
    @Override
    public PrecisedOrderHistoryAdapter.PrecisedOrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_precised_order_history,parent,false);
        return new PrecisedOrderHistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PrecisedOrderHistoryAdapter.PrecisedOrderHistoryViewHolder holder, int position) {
        PrecisedOrderHistoryModel precisedOrderHistoryModel = precisedOrderList.get(position);
        holder.orderId_tv.setText(precisedOrderHistoryModel.getOrderId());
        holder.dressSegment_tv.setText(precisedOrderHistoryModel.getDressSegment());
        holder.gender_tv.setText(precisedOrderHistoryModel.getGender());
        holder.dressType_tv.setText(precisedOrderHistoryModel.getDressType());
        holder.orderTime_tv.setText(precisedOrderHistoryModel.getOrderTimestamp());
//        holder.precisionName_tv.setText(precisedOrderHistoryModel.getPrecisionName());
//        holder.precisionValue_tv.setText(precisedOrderHistoryModel.getPrecisionValue());

        String precisionData = precisedOrderHistoryModel.getPrecisionDataJson();

        Gson gson = new Gson();
        TypeToken<List<SelectedPrecisionsModel>> token = new TypeToken<List<SelectedPrecisionsModel>>(){

        };
        dataList = gson.fromJson(precisionData, token.getType());
        String values = "";
        for(int i = 0; i < dataList.size(); i++){

            values += dataList.get(i).getPrecisionName()+System.getProperty("line.separator");

        }
        holder.precisionName_tv.setText(values);

        values = "";
        for(int i = 0; i < dataList.size(); i++){

            values += dataList.get(i).getPrecisionValue() + " Inches" + System.getProperty("line.separator");

        }
        holder.precisionValue_tv.setText(values);

    }

    @Override
    public int getItemCount() {
        return precisedOrderList.size();
    }

    public class PrecisedOrderHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView orderId_tv, dressSegment_tv, gender_tv, dressType_tv, orderTime_tv, precisionName_tv, precisionValue_tv;

        public PrecisedOrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId_tv = itemView.findViewById(R.id.orderIdTextViewOrderHistory);
            dressSegment_tv = itemView.findViewById(R.id.dressSegmentTextViewOrderHistory);
            gender_tv = itemView.findViewById(R.id.genderTextViewOrderHistory);
            dressType_tv = itemView.findViewById(R.id.dressTypeTextViewOrderHistory);
            orderTime_tv = itemView.findViewById(R.id.orderTimeTextViewOrderHistory);
            precisionName_tv = itemView.findViewById(R.id.precisionNameOrderHistoryTextView);
            precisionValue_tv = itemView.findViewById(R.id.precisionValueOrderHistoryTextView);




        }
    }
}
