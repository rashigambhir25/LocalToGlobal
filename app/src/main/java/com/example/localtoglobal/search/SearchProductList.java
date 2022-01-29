package com.example.localtoglobal.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.R;
import com.example.localtoglobal.productDetails.ProductDetails;
import com.example.localtoglobal.productRetro.ProductAdapter;
import com.example.localtoglobal.productRetro.ProductDto;
import com.example.localtoglobal.retrofit.MainInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchProductList extends AppCompatActivity implements ProductAdapter.ProductDataInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        String query= getIntent().getStringExtra("query");
        Retrofit retrofit= MainBuilder.getInstance();
        Call<List<ProductDto>> productDtos=retrofit.create(MainInterface.class).getSearchResult(query);
        productDtos.enqueue(new Callback<List<ProductDto>>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, Response<List<ProductDto>> response) {
                RecyclerView recyclerView = findViewById(R.id.products_recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new ProductAdapter(response.body(), SearchProductList.this));
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {

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
}