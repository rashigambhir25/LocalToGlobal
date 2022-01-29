package com.example.localtoglobal.productDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.R;
import com.example.localtoglobal.cartRetro.CartItemDto;
import com.example.localtoglobal.productRetro.MerchantProductDto;
import com.example.localtoglobal.productRetro.ProductDto;
import com.example.localtoglobal.registerRetro.MessageReturn;
import com.example.localtoglobal.retrofit.MainInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        CartItemDto cartItemDto = new CartItemDto();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.localtoglobal", Context.MODE_PRIVATE);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        ProductDto productDto = (ProductDto) bundle.getSerializable("product");
        List<MerchantProductDto> merchantProductDto = productDto.getMerchantProducts();
        String productId = productDto.getId();
        TextView name = findViewById(R.id.name_product);
        TextView description = findViewById(R.id.description_content);
        name.setText(productDto.getName());
        description.setText(productDto.getDescription());
        List<String> spinnerList = new ArrayList<String>();
        Spinner spinnerMerchantProduct = findViewById(R.id.merchant_list);
        if(merchantProductDto == null)
        {
            return;
        }
        for (int i = 0; i < merchantProductDto.size(); i++) {
            String spinnerDetails = "Merchant: " + merchantProductDto.get(i).getMerchantId() +
                    ",Price:" + merchantProductDto.get(i).getPrice() + ",Stock: " + merchantProductDto.get(i).getStock();
            spinnerList.add(spinnerDetails);
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(ProductDetails.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        spinnerMerchantProduct.setAdapter(stringArrayAdapter);
        Button button = findViewById(R.id.add_to_cart_btn);
        button.setOnClickListener(view -> {
            String s = (String) spinnerMerchantProduct.getSelectedItem();
            String[] splitted = s.split(",");
            String first = splitted[0];
            String second = splitted[1];
            Long price = Long.parseLong(second.substring(6));
            Long quantity = Long.valueOf(1);
            Long merchantId = Long.parseLong(first.substring(10));
            Long userId = sharedPreferences.getLong("userId", 500);
            cartItemDto.setProductId(productId);
            cartItemDto.setMerchantId(merchantId);
            cartItemDto.setUserId(userId);
            cartItemDto.setQuantity(quantity);
            cartItemDto.setPrice(price);
            addCartItem(cartItemDto);
        });
    }

    public void addCartItem(CartItemDto cartItemDto) {
        Retrofit retrofit = MainBuilder.getInstance();
        Call<MessageReturn> stockStatus = retrofit.create(MainInterface.class).addToCart(cartItemDto);
        stockStatus.enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                if (response.body().getStatus() == 200) {
                    Toast.makeText(getApplicationContext(), "Product added to cart!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Out of Stock", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {

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