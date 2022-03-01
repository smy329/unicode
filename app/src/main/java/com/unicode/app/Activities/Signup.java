package com.unicode.app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unicode.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    TextInputLayout fullName_input, mobileNo_input, password_input;
    TextView loginHere_text;
    Button next_btn;
    RelativeLayout progressbar_rl;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName_input = findViewById(R.id.fullNameInputBoxSignup);
        mobileNo_input = findViewById(R.id.mobileNoInputBoxSignup);
        password_input = findViewById(R.id.passwordInputBoxSignup);
        loginHere_text = findViewById(R.id.loginTextView);
        next_btn = findViewById(R.id.nextButtonSignup);
        progressbar_rl = findViewById(R.id.progressBarRLSignup);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                if(!validationName() | !validationMobileNo() | !validationPassword() ){
                    return;
                }

                String mobileNo = mobileNo_input.getEditText().getText().toString().trim();
                String password = password_input.getEditText().getText().toString().trim();
                String fullName = fullName_input.getEditText().getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(), VerifyMobileNo.class);
                intent.putExtra("mobileNo", mobileNo);
                intent.putExtra("fullName", fullName);
                intent.putExtra("password", password);
                intent.putExtra("whatToDo", "signUp");
                startActivity(intent);

                //validateRegistration();
            }
        });

        loginHere_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private boolean validationName(){
        String fullName = fullName_input.getEditText().getText().toString().trim();

        if(fullName.isEmpty()){
            fullName_input.setError("Field cannot be empty");
            return false;
        }
        else{
            fullName_input.setError(null);
            return true;
        }
    }

    private boolean validationMobileNo(){
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

    private boolean validationPassword(){
        String password = password_input.getEditText().getText().toString().trim();

        if(password.isEmpty()){
            password_input.setError("Field cannot be empty");

            return false;
        }
        else if (password.length() < 6){
            password_input.setError("Password must be atleast 6 character");

            return false;
        }
        else{
            password_input.setError(null);
            return true;
        }
    }

    private void validateRegistration(){

        progressbar_rl.setVisibility(View.VISIBLE);

        String mobileNo = mobileNo_input.getEditText().getText().toString().trim();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = databaseReference.orderByChild("mobileNo").equalTo(mobileNo);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.getValue() != null){
                        Toast.makeText(Signup.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //get all the values
                        String fullName = fullName_input.getEditText().getText().toString().trim();
//                        String mobileNo = mobileNo_input.getEditText().getText().toString().trim();
                        String password = password_input.getEditText().getText().toString().trim();

                        Intent intent = new Intent(getApplicationContext(), VerifyMobileNo.class);
                        intent.putExtra("mobileNo", mobileNo);
                        intent.putExtra("fullName", fullName);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        progressbar_rl.setVisibility(View.GONE);


    }
}