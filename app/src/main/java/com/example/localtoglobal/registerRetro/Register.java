package com.example.localtoglobal.registerRetro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.localtoglobal.R;
import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.retrofit.MainInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Register extends AppCompatActivity {

    private Button signup;
    private EditText etname, etmail,etphone ,etaddress, etpassword , etconfirmpw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_register);
        etname = findViewById(R.id.enter_name);
        etmail = findViewById(R.id.enter_email);
        etphone = findViewById(R.id.enter_phone);
        etaddress = findViewById(R.id.enter_address);
        etpassword = findViewById(R.id.enter_pwd);
        etconfirmpw = findViewById(R.id.con_pwd);
        signup = findViewById(R.id.bt_sign_up);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.localtoglobal",Context.MODE_PRIVATE);
        signup.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", etname.getText().toString());
            editor.putString("email", etmail.getText().toString());
            editor.putString("phone", etphone.getText().toString());
            editor.putString("address", etaddress.getText().toString());
            editor.putString("password", etpassword.getText().toString());
            editor.putString("confirm password", etconfirmpw.getText().toString());
            editor.apply();
            RegisterApi(etmail.getText().toString());
        });
    }

    private void RegisterApi(String mail){

        Retrofit retrofit = MainBuilder.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.localtoglobal",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Call<EnOtpEntity> regcall = retrofit.create(MainInterface.class).postregister(mail);
        regcall.enqueue(new Callback<EnOtpEntity>() {
            @Override
            public void onResponse(Call<EnOtpEntity> call, Response<EnOtpEntity> response) {
                Log.d("customer", "onResponse: "+response.body().getEnOtp());
                editor.putString("enOtp",response.body().getEnOtp());
                editor.apply();
                startActivity(new Intent(getApplicationContext(), RegisterOtp.class));
            }
            @Override
            public void onFailure(Call<EnOtpEntity> call, Throwable t) {
                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}