package com.example.localtoglobal.recommendationRetro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.localtoglobal.orderHistory.OrderHistory;
import com.example.localtoglobal.R;
import com.example.localtoglobal.productDetails.ProductDetails;
import com.example.localtoglobal.productRetro.ProductDto;
import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.retrofit.MainInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderSuccess extends AppCompatActivity implements RecommendAdapter.RecommendDataInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Retrofit retrofit = MainBuilder.getInstance();
        Call<List<ProductDto>> productDto = retrofit.create(MainInterface.class).getProductRecommendation();
        productDto.enqueue(new Callback<List<ProductDto>>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, Response<List<ProductDto>> response) {
                RecyclerView recyclerView = findViewById(R.id.recommend);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new RecommendAdapter(response.body(), OrderSuccess.this));
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {

            }
//            @Override
//            public void onResponse(Call<List<RecommendationDto>> call, Response<List<RecommendationDto>> response) {
//                RecyclerView recyclerView = findViewById(R.id.recommend);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(new RecommendAdapter(response.body(), OrderSuccess.this));
////               Toast.makeText(getApplicationContext(), "Open", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<RecommendationDto>> call, Throwable t) {
////                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//
//            }

        });

        findViewById(R.id.bt_order_details).setOnClickListener( view1-> {
            Intent i = new Intent(this, OrderHistory.class);
            startActivity(i);

        });
    }


    @Override
    public void onUserClick(ProductDto productDto) {
//        ProductDto productDto=new ProductDto();

        Intent i = new Intent(this, ProductDetails.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("product", productDto);
        i.putExtras(bundle);
        startActivity(i);

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