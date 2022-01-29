package com.example.localtoglobal.registerRetro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.localtoglobal.loginRetro.Login;
import com.example.localtoglobal.R;
import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.retrofit.MainInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class RegisterOtp extends AppCompatActivity {

    private EditText otp;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_otp);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        otp = findViewById(R.id.et_otp);
        bt = findViewById(R.id.bt_submit);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.localtoglobal", Context.MODE_PRIVATE);
        bt.setOnClickListener(v -> {
            if (otp.getText().toString().isEmpty()) {
                Toast.makeText(RegisterOtp.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            }

            String enOtp = sharedPreferences.getString("enOtp","enOtp_default");
            String name = sharedPreferences.getString("name","name_default");
            String email = sharedPreferences.getString("email","mail_default");
            String phone = sharedPreferences.getString("phone","phone_default");
            String address = sharedPreferences.getString("address","address_default");
            String password = sharedPreferences.getString("password","password_default");
            String conpassword = sharedPreferences.getString("conpassword","conpassword_default");

            Otpverify(otp.getText().toString(),enOtp, name, email, phone,address,password,conpassword);
        });
    }

    public void Otpverify(String otp ,String enOtp, String name , String email ,String phone,String address,String password,String conpassword ) {

        Retrofit retrofit = MainBuilder.getInstance();
        MainInterface otpInterface = retrofit.create(MainInterface.class);

        RegisterEntity registerEntity = new RegisterEntity(Long.parseLong(otp), enOtp, name, email, Long.parseLong(phone), address, password, conpassword);
        Call<MessageReturn> otpcall = otpInterface.postotp(registerEntity);

        otpcall.enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                if (response.body().getStatus() == 200 || response.body().getStatus() == 201) {
                    Toast.makeText(RegisterOtp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));

                } else {
                    Toast.makeText(RegisterOtp.this, response.body().getStatus().toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {

                Toast.makeText(RegisterOtp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Register.class));

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
