package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.unicode.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPasswordActivity extends AppCompatActivity {

    TextInputLayout newPassword_input, confirmPassword_input;
    Button updatePassword_btn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        newPassword_input = findViewById(R.id.passwordInputBoxNewPassword);
        confirmPassword_input = findViewById(R.id.confirmPasswordInputBoxNewPassword);
        updatePassword_btn = findViewById(R.id.updateNewPasswordButton);
        
        updatePassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validationNewPassword() | !validationConfirmPassword()){
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);

                String newPassword = newPassword_input.getEditText().getText().toString().trim();
                String mobileNo = getIntent().getStringExtra("mobileNo");

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                databaseReference.child(mobileNo).child("password").setValue(newPassword);

                Intent intent = new Intent(NewPasswordActivity.this, PasswordUpdateSuccessActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private boolean validationNewPassword(){
        String newPassword = newPassword_input.getEditText().getText().toString().trim();

        if(newPassword.isEmpty()){
            newPassword_input.setError("Field cannot be empty");

            return false;
        }
        else if (newPassword.length() < 6){
            newPassword_input.setError("Password must be at least 6 character");

            return false;
        }
        else{
            newPassword_input.setError(null);
            return true;
        }
    }

    private boolean validationConfirmPassword(){

        String newPassword = newPassword_input.getEditText().getText().toString().trim();
        String confirmPassword = confirmPassword_input.getEditText().getText().toString().trim();

        if(confirmPassword.equals(newPassword)){
            confirmPassword_input.setError(null);
            return true;
        }

        else{
            confirmPassword_input.setError("Password did not matched");
            return false;
        }
    }
}