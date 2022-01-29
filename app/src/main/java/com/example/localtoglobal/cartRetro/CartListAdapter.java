package com.example.localtoglobal.cartRetro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localtoglobal.R;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private final List<CartItemDto> cartItemDtoList;
    private final CartProductInterface cartProductInterface;

    public CartListAdapter(List<CartItemDto> cartItemDtoList, CartProductInterface cartProductInterface) {
        this.cartItemDtoList = cartItemDtoList;
        this.cartProductInterface = cartProductInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItemDto cartItemDto = cartItemDtoList.get(position);
        holder.name.setText(cartItemDto.getName()+"");
        holder.price.setText(cartItemDto.getPrice()+"");
        holder.quantity.setText(cartItemDto.getQuantity()+"");
        holder.rootView.setOnClickListener((view -> {
            cartProductInterface.onUserClick(cartItemDto);
        }));
    }


    @Override
    public int getItemCount() {
        return cartItemDtoList.size();
    }
    public interface CartProductInterface
    {
        void onUserClick(CartItemDto cartItemDto);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final TextView quantity;
        private final View rootView;


        public ViewHolder(View view) {
            super(view);
            rootView =view;
            quantity = view.findViewById(R.id.quantity);
            name = view.findViewById(R.id.product_id);
            price = view.findViewById(R.id.price_product);
        }

    }
}
