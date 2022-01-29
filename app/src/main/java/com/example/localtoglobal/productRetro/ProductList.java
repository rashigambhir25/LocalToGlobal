package com.example.localtoglobal.productRetro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.example.localtoglobal.R;
import com.example.localtoglobal.productDetails.ProductDetails;
import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.retrofit.MainInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductList extends AppCompatActivity implements ProductAdapter.ProductDataInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String categoryName = getIntent().getStringExtra("categoryName");

        Retrofit retrofit = MainBuilder.getInstance();
        Call<List<ProductDto>> productDtoCall = retrofit.create(MainInterface.class).getProductName(categoryName);
        productDtoCall.enqueue(new Callback<List<ProductDto>>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, Response<List<ProductDto>> response) {
                RecyclerView recyclerView = findViewById(R.id.products_recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new ProductAdapter(response.body(), ProductList.this));
//               Toast.makeText(getApplicationContext(), "Open", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

   }
    @Override
    public void onUserClick(ProductDto productDto, View view, int position) {
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