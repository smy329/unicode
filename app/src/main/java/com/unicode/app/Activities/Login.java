package com.unicode.app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unicode.app.Database.SharedPrefer;
import com.unicode.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView signup_tv, forgetPassword_tv;
    Button login_btn;
    TextInputLayout mobileNo_input, password_input;
    RelativeLayout progressbar_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup_tv = findViewById(R.id.signupTextView);
        login_btn = findViewById(R.id.loginButton);
        mobileNo_input = findViewById(R.id.mobileNoInputBoxLogin);
        password_input = findViewById(R.id.passwordInputBoxLogin);
        forgetPassword_tv = findViewById(R.id.forgetPasswordTextViewLogin);
        progressbar_rl = findViewById(R.id.progressBarRLLogin);

        //checking shared preference data, if available bypass login
        String fullNameInSharedPreference = new SharedPrefer(Login.this).getFullName();
        String mobileNoInSharedPreference = new SharedPrefer(Login.this).getMobileNo();
        String passwordInSharedPreference = new SharedPrefer(Login.this).getPassword();

        if (fullNameInSharedPreference !=null && mobileNoInSharedPreference != null && passwordInSharedPreference != null){
            startActivity(new Intent(Login.this, DressSegmentActivity.class));
//            startActivity(new Intent(Login.this, UserProfileActivity.class));

        }


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressbar_rl.setVisibility(View.VISIBLE);

                if(!validateMobileNo() | !validatePassword()){
                    progressbar_rl.setVisibility(View.GONE);
                    return;
                }
                else {

                    String enteredMobileNo = mobileNo_input.getEditText().getText().toString().trim();
                    String enteredPassword = password_input.getEditText().getText().toString().trim();

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                    Query checkUser = databaseReference.orderByChild("mobileNo").equalTo(enteredMobileNo);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()){

                                String passwordFromDB = snapshot.child(enteredMobileNo).child("password").getValue(String.class);

                                if (passwordFromDB.equals(enteredPassword)){



                                    password_input.setError(null);
                                    password_input.setErrorEnabled(false);

                                    String nameFromDB = snapshot.child(enteredMobileNo).child("fullName").getValue(String.class);
                                    String mobileNoFromDB = snapshot.child(enteredMobileNo).child("mobileNo").getValue(String.class);

                                    //Using Shared Preferences
                                    SharedPrefer sharedPrefer = new SharedPrefer(Login.this);
                                    sharedPrefer.setFullName(nameFromDB);
                                    sharedPrefer.setMobileNo(mobileNoFromDB);
                                    sharedPrefer.setPassword(passwordFromDB);

                                    Intent intent = new Intent(Login.this, DressSegmentActivity.class);
//                                    Intent intent = new Intent(Login.this, UserProfile.class);
//                                    intent.putExtra("fullName", nameFromDB);
//                                    intent.putExtra("mobileNo", mobileNoFromDB);
                                    startActivity(intent);
                                    finish();

//                                    loadToast.success();
                                    //progressDialog.dismiss();

                                    progressbar_rl.setVisibility(View.GONE);

                                }
                                else{
                                    password_input.setError("Wrong Password");
                                    password_input.requestFocus();
                                    progressbar_rl.setVisibility(View.GONE);
                                }
                            }
                            else{
                                mobileNo_input.setError("No such Mobile No. exist");
                                mobileNo_input.requestFocus();
                                progressbar_rl.setVisibility(View.GONE);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    progressbar_rl.setVisibility(View.GONE);
                }
            }
        });

        signup_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick (View view){
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        forgetPassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateMobileNo(){
        String mobileNo = mobileNo_input.getEditText().getText().toString().trim();

        if(mobileNo.isEmpty()){
            mobileNo_input.setError("Field cannot be empty");
            return false;
        }
        else if (!(mobileNo.length() == 11 || mobileNo.length() == 14 || mobileNo.length() == 13 )){
            mobileNo_input.setError("Invalid mobile no.");
            return false;
        }
        else{
            mobileNo_input.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = password_input.getEditText().getText().toString().trim();

        if(password.isEmpty()){
            password_input.setError("Field cannot be empty");
            return false;
        }
        else if (password.length() < 6){
            password_input.setError("Password must be at least 6 character");
            return false;
        }
        else{
            password_input.setError(null);
            return true;
        }
    }
}