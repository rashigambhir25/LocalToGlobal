package com.example.localtoglobal.loginRetro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.search.Dashboard;
import com.example.localtoglobal.R;
import com.example.localtoglobal.registerRetro.Register;
import com.example.localtoglobal.retrofit.MainInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private EditText email,pwd;
    private Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_add_email);
        pwd = findViewById(R.id.et_add_pwd);
        signin = findViewById(R.id.bt_sign_in);

        signin.setOnClickListener(view -> {
            if (email.getText().toString().isEmpty() && pwd.getText().toString().isEmpty()) {
                Toast.makeText(Login.this, "Please enter details", Toast.LENGTH_SHORT).show();
                return;
            }
            loginAPI(email.getText().toString(), pwd.getText().toString());
        });

        findViewById(R.id.bt_new_user).setOnClickListener(view -> {
            startActivity(new Intent(this, Register.class));
        });
        findViewById(R.id.bt_skip).setOnClickListener(view -> {
            Intent i = new Intent(this, Dashboard.class);
            startActivity(i);
        });

    }

    public void loginAPI(String mail,String pwd){
        Retrofit retrofit = MainBuilder.getInstance();
//        LoginInterface loginInterface = retrofit.create(LoginInterface.class);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.localtoglobal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        LoginEntity loginEntity = new LoginEntity(mail,pwd);
        Call<UserEntity> loginEntityCall = retrofit.create(MainInterface.class).postLog(loginEntity);
        loginEntityCall.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.body().getStatus() == 200 || response.body().getStatus() == 201) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    editor.putLong("userId",response.body().getId());
                    editor.apply();
                    Long userId = sharedPreferences.getLong("userId",200);
                    Intent i = new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(i);}
                else{
                    Toast.makeText(Login.this, "Login failure", Toast.LENGTH_SHORT).show();

                }

            }
            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();


            }
        });

    }
}
