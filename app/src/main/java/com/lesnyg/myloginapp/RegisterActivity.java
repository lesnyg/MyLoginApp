package com.lesnyg.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_id;
    private EditText et_pass;
    private EditText et_name;
    private EditText et_age;
    private Button btn_register;
    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        btn_register = findViewById(R.id.btn_register);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RegisterApi registerApi = retrofit.create(RegisterApi.class);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID  = et_id.getText().toString();
                String userPass  = et_pass.getText().toString();
                String userName  = et_name.getText().toString();
                int userAge  = Integer.parseInt(et_age.getText().toString());

                registerApi.register(userID,userPass,userName,userAge).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Result result = response.body();
                        boolean success = result.isSuccess();
                        if(success){
                            Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                        }else{
                            Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }
        });

        btn_login = findViewById(R.id.btn_signin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}
