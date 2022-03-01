package com.unicode.app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.unicode.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgetPasswordActivity extends AppCompatActivity {

    private TextInputLayout mobileNo_input;
    private Button next_btn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mobileNo_input = findViewById(R.id.mobileNoInputBoxForgotPassword);
        next_btn = findViewById(R.id.nextForgotPasswordButton);
        progressBar = findViewById(R.id.forgetPasswordProgressBar);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobileNo = mobileNo_input.getEditText().getText().toString().trim();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUser = databaseReference.orderByChild("mobileNo").equalTo(mobileNo);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.getValue() != null) {

                            progressBar.setVisibility(View.VISIBLE);
                            mobileNo_input.setError(null);
                            mobileNo_input.setErrorEnabled(false);

                            //get all the values
                            String mobileNo = mobileNo_input.getEditText().getText().toString().trim();

                            Intent intent = new Intent(getApplicationContext(), VerifyMobileNo.class);
                            intent.putExtra("mobileNo", mobileNo);
                            intent.putExtra("whatToDo", "updatePassword");
                            startActivity(intent);
                            finish();
                            progressBar.setVisibility(View.GONE);
                        }
                        else {
                            Toast.makeText(ForgetPasswordActivity.this, "No Such User Exist", Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }

    private void validateRegistration() {


    }
}