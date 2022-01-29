package com.example.localtoglobal.userRetro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.localtoglobal.orderHistory.OrderHistory;
import com.example.localtoglobal.R;


public class MyProfileFragment extends Fragment  {
    public MyProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.localtoglobal", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","name_default");
        String email = sharedPreferences.getString("email","email_default");
        String  phoneNo = sharedPreferences.getString("phone","Phone_default");
        String address = sharedPreferences.getString("address","address_default");
        final EditText editName = view.findViewById(R.id.enter_name_user);
        final EditText editEmail = view.findViewById(R.id.enter_email_user);
        final EditText editPhoneNo = view.findViewById(R.id.enter_phone_user);
        final EditText editAddress = view.findViewById(R.id.enter_address_user);
        editName.setText(name);
        editEmail.setText(email);
        editPhoneNo.setText(phoneNo);
        editAddress.setText(address);
        view.findViewById(R.id.order_history).setOnClickListener( view1-> {
            Intent i = new Intent(getContext(), OrderHistory.class);
            startActivity(i);

        });
    }
}