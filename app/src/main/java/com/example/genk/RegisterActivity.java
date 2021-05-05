package com.example.genk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btn_register);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_reg_email);
        etPassword = findViewById(R.id.et_reg_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
                    String message = "All inputs  required";
                    Toast.makeText(com.example.genk.RegisterActivity.this,message,Toast.LENGTH_LONG).show();

                }else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setName(etName.getText().toString());
                    registerRequest.setEmail(etEmail.getText().toString());
                    registerRequest.setPasswords(etPassword.getText().toString());
                    registerUser(registerRequest);
                }
            }
        });
    }

    public  void  registerUser(RegisterRequest registerRequest){


        Call<RegisterResponse> registerResponseCall = ApiCilent.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()){
                    String message = "Successful";
                    Toast.makeText(com.example.genk.RegisterActivity.this,message,Toast.LENGTH_LONG).show();

                    startActivity(new Intent(com.example.genk.RegisterActivity.this,LoginActivity.class));
                    finish();
                }else {
                    String message = "An error occured please try again";
                    Toast.makeText(com.example.genk.RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(com.example.genk.RegisterActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
}