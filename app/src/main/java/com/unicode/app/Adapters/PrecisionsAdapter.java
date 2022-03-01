package com.unicode.app.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicode.app.Models.SelectedPrecisionsModel;
import com.unicode.app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PrecisionsAdapter extends RecyclerView.Adapter<PrecisionsAdapter.PrecisionsViewHolder> {

    Context context; //context will receive the class
    //public static String[] selectedPrecisions;
    public static ArrayList<SelectedPrecisionsModel> precisionsArrayList;

    //public PrecisionsAdapter(Context context, String[] selectedPrecisions) {
    public PrecisionsAdapter(Context context, ArrayList<SelectedPrecisionsModel> precisionsArrayList) {
        this.context = context;
        this.precisionsArrayList = precisionsArrayList;
    }

    @NonNull
    @Override
    public PrecisionsAdapter.PrecisionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //this will convert our sample layout to java file through layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_precision, parent, false);
        return new PrecisionsViewHolder(view);
        //sending whole converted view to PrecisionsViewHolder
    }

    //th,is function will bind data with views from sample layout
    @Override
    public void onBindViewHolder(@NonNull PrecisionsViewHolder holder, int position) {
        holder.textInputLayout.setHint(precisionsArrayList.get(position).getPrecisionName());

    }

    @Override
    public int getItemCount() {
        return precisionsArrayList.size();
    }

    //this class will hold the items/data for sample layout
    protected class PrecisionsViewHolder extends RecyclerView.ViewHolder {

        protected TextInputLayout textInputLayout;
        protected EditText editText;

        //itemView has received the converted view
        public PrecisionsViewHolder(View itemView){
            super(itemView);
            textInputLayout = itemView.findViewById(R.id.precisionTextInputLayout);
            editText = itemView.findViewById(R.id.precisionEditText);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    precisionsArrayList.get(getAdapterPosition()).setPrecisionValue(editText.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {


                }
            });

        }
    }


}
