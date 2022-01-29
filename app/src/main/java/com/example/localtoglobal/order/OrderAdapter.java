package com.example.localtoglobal.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localtoglobal.R;


import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private final List<OrderDto> orderDtoList;
    private final OrderDataInterface orderDataInterface;

    public OrderAdapter(List<OrderDto> orderDtoList, OrderDataInterface orderDataInterface) {
        this.orderDtoList = orderDtoList;
        this.orderDataInterface = orderDataInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDto orderDto = orderDtoList.get(position);
        holder.name.setText(orderDto.getDate()+"");
        holder.price.setText(orderDto.getTotal()+"");
        holder.quantity.setText(orderDto.getOrderItems().size()+"");

        holder.rootView.setOnClickListener((view -> {
            orderDataInterface.onUserClick(orderDto);
        }));
    }


    @Override
    public int getItemCount() {
        return orderDtoList.size();
    }
    public interface OrderDataInterface
    {
        void onUserClick(OrderDto orderDto);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final TextView quantity;
        private final View rootView;



        public ViewHolder(View view) {
            super(view);
            rootView = view;
            name = view.findViewById(R.id.name2);
            price = view.findViewById(R.id.price2);
            quantity = view.findViewById(R.id.quantity2);
        }

    }
}