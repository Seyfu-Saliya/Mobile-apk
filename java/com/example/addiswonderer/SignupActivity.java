package com.example.addiswonderer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.addiswonderer.databinding.ActivitySignupBinding;
import com.example.addiswonderer.model.My_Models;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    Button button,button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Creating your account");
        progressDialog.setMessage("Please wait");

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=binding.nameSignup.getText().toString();
                String email=binding.emailSignup.getText().toString();
                String password=binding.passwordSignup.getText().toString();
                String confirmpassword=binding.repassSignup.getText().toString();

                if (name.isEmpty()) {

                    binding.nameSignup.setError("Enter your name");


                } else if (email.isEmpty()) {

                    binding.emailSignup.setError("Enter your email address");

                } else if (password.isEmpty()) {

                    binding.passwordSignup.setError("Enter your password");

                } else if (confirmpassword.isEmpty()) {

                    binding.repassSignup.setError("Confirm your password");

                }else {

                    progressDialog.show();

                    auth.createUserWithEmailAndPassword(binding.emailSignup.getText().toString(),binding.passwordSignup.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()){


                                My_Models models= new My_Models(email,name,password,confirmpassword);


                                String id=task.getResult().getUser().getUid();
                                firebaseFirestore.collection("users").document().set(models).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {


                                        if (task.isSuccessful()){
                                            progressDialog.dismiss();

                                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                            finish();
                                        }else {


                                            progressDialog.dismiss();

                                            Toast.makeText(SignupActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                });
                            }


                        }
                    });

                }
            }
        });





        button_login=(Button) findViewById(R.id.already_have_an_account);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}