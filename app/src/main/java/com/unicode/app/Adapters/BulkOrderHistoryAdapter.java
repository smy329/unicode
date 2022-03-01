package com.unicode.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicode.app.Models.BulkOrderHistoryModel;
import com.unicode.app.R;

import java.util.ArrayList;

public class BulkOrderHistoryAdapter extends RecyclerView.Adapter<BulkOrderHistoryAdapter.BulkOrderHistoryViewHolder> {
    Context context;
    ArrayList <BulkOrderHistoryModel> bulkOrderList;

    public BulkOrderHistoryAdapter(Context context, ArrayList<BulkOrderHistoryModel> bulkOrderList) {
        this.context = context;
        this.bulkOrderList = bulkOrderList;
    }

    @NonNull
    @Override
    public BulkOrderHistoryAdapter.BulkOrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_bulk_order_history, parent, false);
        return new BulkOrderHistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BulkOrderHistoryAdapter.BulkOrderHistoryViewHolder holder, int position) {
        BulkOrderHistoryModel bulkOrderHistoryModel = bulkOrderList.get(position);
        holder.orderId_tv.setText(bulkOrderHistoryModel.getOrderId());
        holder.dressSegment_tv.setText(bulkOrderHistoryModel.getDressSegment());
        holder.gender_tv.setText(bulkOrderHistoryModel.getGender());
        holder.dressType_tv.setText(bulkOrderHistoryModel.getDressType());
        holder.orderTime_tv.setText(bulkOrderHistoryModel.getOrderTimestamp());
        holder.sValue_tv.setText(bulkOrderHistoryModel.getsSize());
        holder.mValue_tv.setText(bulkOrderHistoryModel.getmSize());
        holder.lValue_tv.setText(bulkOrderHistoryModel.getlSize());
        holder.xlValue_tv.setText(bulkOrderHistoryModel.getXlSize());
        holder.xxlValue_tv.setText(bulkOrderHistoryModel.getXxlSize());
        holder.xxxlValue_tv.setText(bulkOrderHistoryModel.getXxxlSize());

    }

    @Override
    public int getItemCount() {
        return bulkOrderList.size();
    }

    public class BulkOrderHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView orderId_tv, dressSegment_tv, gender_tv, dressType_tv, orderTime_tv, sValue_tv, mValue_tv, lValue_tv, xlValue_tv, xxlValue_tv, xxxlValue_tv;

        public BulkOrderHistoryViewHolder(@NonNull View itemView) {

            super(itemView);
            orderId_tv = itemView.findViewById(R.id.orderIdBulkTextViewOrderHistory);
            dressSegment_tv = itemView.findViewById(R.id.dressSegmentBulkTextViewOrderHistory);
            gender_tv = itemView.findViewById(R.id.genderBulkTextViewOrderHistory);
            dressType_tv = itemView.findViewById(R.id.dressTypeBulkTextViewOrderHistory);
            orderTime_tv = itemView.findViewById(R.id.orderTimeBulkTextViewOrderHistory);
            sValue_tv = itemView.findViewById(R.id.sValueBulkOrderHistoryTextView);
            mValue_tv = itemView.findViewById(R.id.mValueBulkOrderHistoryTextView);
            lValue_tv = itemView.findViewById(R.id.lValueBulkOrderHistoryTextView);
            xlValue_tv = itemView.findViewById(R.id.xlValueBulkOrderHistoryTextView);
            xxlValue_tv = itemView.findViewById(R.id.xxlValueBulkOrderHistoryTextView);
            xxxlValue_tv = itemView.findViewById(R.id.xxxlValueBulkOrderHistoryTextView);
        }
    }
}
