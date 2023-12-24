package com.example.addiswonderer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.addiswonderer.databinding.ActivityForgotBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ForgotActivity extends AppCompatActivity {

    ActivityForgotBinding binding;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Create your account");
        progressDialog.setMessage("Please wait");



        binding.forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.forgotEmail.getText().toString();

                progressDialog.dismiss();

                if (email.isEmpty()){
                    binding.forgotEmail.setError("Enter email address");
                }else {

                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(ForgotActivity.this,"please check your Email",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(ForgotActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });


        binding.backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
            }
        });
    }
}