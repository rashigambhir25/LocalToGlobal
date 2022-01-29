package com.example.localtoglobal.orderHistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.R;
import com.example.localtoglobal.order.OrderAdapter;
import com.example.localtoglobal.order.OrderDto;
import com.example.localtoglobal.retrofit.MainInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderHistory extends AppCompatActivity implements OrderAdapter.OrderDataInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.localtoglobal", Context.MODE_PRIVATE);
        Long userId = sharedPreferences.getLong("userId",500);
        Retrofit retrofit = MainBuilder.getInstance();
        Call<List<OrderDto>> orderDtos = retrofit.create(MainInterface.class).getOrdersById(userId);
        orderDtos.enqueue(new Callback<List<OrderDto>>() {
            @Override
            public void onResponse(Call<List<OrderDto>> call, Response<List<OrderDto>> response) {
                RecyclerView recyclerView = findViewById(R.id.recycle_view2);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new OrderAdapter(response.body(), OrderHistory.this));

            }

            @Override
            public void onFailure(Call<List<OrderDto>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onUserClick(OrderDto orderDto) {

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