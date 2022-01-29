package com.example.localtoglobal.productDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localtoglobal.R;
import com.example.localtoglobal.cartRetro.CartItemDto;
import com.example.localtoglobal.cartRetro.CartListAdapter;
import com.example.localtoglobal.productRetro.ProductDto;

import java.util.List;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder>{

    private final List<CartItemDto> cartItemDtoList;
    private final CartItemInterface cartItemInterface;

    public ProductDetailsAdapter(List<CartItemDto> cartItemDtoList, CartItemInterface cartItemInterface) {
        this.cartItemDtoList = cartItemDtoList;
        this.cartItemInterface = cartItemInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItemDto cartItemDto = cartItemDtoList.get(position);
        holder.name.setText(cartItemDto.getName()+"");
        holder.price.setText(cartItemDto.getPrice()+"");
        holder.rootView.setOnClickListener((view -> {
            cartItemInterface.onUserClick(cartItemDto);
        }));

    }

    @Override
    public int getItemCount() {
        return cartItemDtoList.size();
    }
    public interface CartItemInterface{
        void onUserClick(CartItemDto cartItemDto);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final View rootView;

        public ViewHolder(View view) {
            super(view);
            rootView =view;
            name = view.findViewById(R.id.product_id);
            price = view.findViewById(R.id.price_product);
        }

    }
}
