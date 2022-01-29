package com.example.localtoglobal.cartRetro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.R;
import com.example.localtoglobal.order.OrderDto;
import com.example.localtoglobal.order.OrderItemDto;
import com.example.localtoglobal.recommendationRetro.OrderSuccess;
import com.example.localtoglobal.retrofit.MainInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CartFragment extends Fragment implements CartListAdapter.CartProductInterface{

    public CartFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.localtoglobal", Context.MODE_PRIVATE);
        Long userId = sharedPreferences.getLong("userId",500);
        cartItemsView(userId);
        view.findViewById(R.id.bt_order_now).setOnClickListener( view1-> {
            orderApi(userId);
                Intent i = new Intent(getContext(), OrderSuccess.class);
                startActivity(i);
        });
    }
    public void cartItemsView(Long userId){
        Retrofit retrofit = MainBuilder.getInstance();
        Call<CartDto> cartDto = retrofit.create(MainInterface.class).getOrderByUserId(userId);
        cartDto.enqueue(new Callback<CartDto>() {
            @Override
            public void onResponse(Call<CartDto> call, Response<CartDto> response) {
                RecyclerView recyclerView = getView().findViewById(R.id.cart_recycle_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new CartListAdapter(response.body().getCartItems(), CartFragment.this));
                Long total = response.body().getTotal();
                TextView totalPrice = getView().findViewById(R.id.total_price);
                totalPrice.setText(total+ "");
            }
            @Override
            public void onFailure(Call<CartDto> call, Throwable t) { }
        });
    }
    public void orderApi(Long userId){
        Retrofit retrofit = MainBuilder.getInstance();
        Call<CartDto> cartDtoCall = retrofit.create(MainInterface.class).getOrderByUserId(userId);
        cartDtoCall.enqueue(new Callback<CartDto>() {
            @Override
            public void onResponse(Call<CartDto> call, Response<CartDto> response) {
                List<CartItemDto> cartItemDtoList=response.body().getCartItems();
                List<OrderItemDto> orderItemDtos=new ArrayList<OrderItemDto>();
                for(CartItemDto cartItemDto:cartItemDtoList){
                    OrderItemDto orderItemDto =new OrderItemDto();
                    orderItemDto.setMerchantId(cartItemDto.getMerchantId());
                    orderItemDto.setName(cartItemDto.getName());
                    orderItemDto.setPrice(cartItemDto.getPrice());
                    orderItemDto.setProductId(cartItemDto.getProductId());
                    orderItemDto.setQuantity(cartItemDto.getQuantity());
                    orderItemDtos.add(orderItemDto);
                }
                OrderDto orderDto=new OrderDto();
                orderDto.setOrderItems(orderItemDtos);
                orderDto.setUserId(response.body().getUserId());
                Call<Void> orderDtoCall = retrofit.create(MainInterface.class).addOrder(orderDto);
                orderDtoCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) { }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) { }
                });
            }
            @Override
            public void onFailure(Call<CartDto> call, Throwable t) { }
        });
    }
    @Override
    public void onUserClick(CartItemDto cartItemDto) { }
}