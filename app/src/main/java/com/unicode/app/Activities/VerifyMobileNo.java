package com.unicode.app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.unicode.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.TimeUnit;

public class VerifyMobileNo extends AppCompatActivity {

    private Button verifyOtp_btn;
    private TextInputLayout otp_input;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth; // variable for FirebaseAuth class
    private DatabaseReference databaseReference; //variable for database instance
    private String verificationCodeBySystem, fullNameFromSignup, passwordFromSignup, mobileNoFromSignup, whatToDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mobile_no);

        // below line is for getting instance of our FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();

        verifyOtp_btn = findViewById(R.id.verifyOtpButton);
        otp_input = findViewById(R.id.otpInputBox);
        progressBar = findViewById(R.id.progressBar);

        fullNameFromSignup = getIntent().getStringExtra("fullName");
        passwordFromSignup = getIntent().getStringExtra("password");
        mobileNoFromSignup = getIntent().getStringExtra("mobileNo");
        whatToDo = getIntent().getStringExtra("whatToDo");
        String mobileNoWithCountryCode = "+88" + getIntent().getStringExtra("mobileNo");

        sendVerificationCodeToUser(mobileNoWithCountryCode);

        verifyOtp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validationOTP()){
                    return;
                }
            }
        });
    }
    private boolean validationOTP(){
        String otpEnteredByUser = otp_input.getEditText().getText().toString().trim();

        if (otpEnteredByUser.isEmpty()) {
            otp_input.setError("Please Enter Your OTP");
            return false;

        }
        else if (otpEnteredByUser.length() != 6){
            otp_input.setError("Check Your OTP Carefully");
            return false;
        }
        else {
            // if OTP field is not empty call this method to verify the OTP.
            Toast.makeText(VerifyMobileNo.this, "Verifying OTP...", Toast.LENGTH_SHORT).show();
            verifyCode(otpEnteredByUser);
            return true;
        }
    };

    // this method is used for getting OTP on user's phone number
    private void sendVerificationCodeToUser(String mobileNoWithCountryCode) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobileNoWithCountryCode)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)           // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        // this method is used when OTP is sent from Firebase
        // this method will be useful when otp will be sent to different device
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeBySystem = s;
            Toast.makeText(VerifyMobileNo.this, "OTP has been sent to User", Toast.LENGTH_SHORT).show();
        }

        // this method is called when user receive OTP from Firebase
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                otp_input.getEditText().setText(code);
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(VerifyMobileNo.this, "Code should be filled", Toast.LENGTH_SHORT).show();
                verifyCode(code);
            }
        }
        // this method is called when firebase doesn't sends our OTP code due to any error or issue
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyMobileNo.this, "OTP Verification Failed", Toast.LENGTH_SHORT).show();
        }
    };

    // this method is use to verify code from Firebase.
    private void verifyCode(String codeFromUser) {

        // getting credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeFromUser );
        // after getting credential we are calling sign in method.
        Log.d("test", "verifyCode function calling");
        signInTheUserByCredentials (credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyMobileNo.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent;
                            if (whatToDo.equals("updatePassword")){
                                intent = new Intent(VerifyMobileNo.this, NewPasswordActivity.class);
                                intent.putExtra("mobileNo", mobileNoFromSignup);
                            }

                            else{
                                intent = new Intent(getApplicationContext(), DeliveryAddressActivity.class);
                                //UserModel userModel = new UserModel(fullNameFromSignup, mobileNoFromSignup, passwordFromSignup);
                                //this line will create another node based on Mobile no
                                //databaseReference.child(mobileNoFromSignup).setValue(userModel);

                                intent.putExtra("mobileNo", mobileNoFromSignup);
                                intent.putExtra("fullName", fullNameFromSignup);
                                intent.putExtra("password", passwordFromSignup);
                                Log.d("Test 2", "Going to delivery address");
                            }
                            startActivity(intent);
                            finish();

                        }
                        else{
                            Toast.makeText(VerifyMobileNo.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
            }
        });
    }
}