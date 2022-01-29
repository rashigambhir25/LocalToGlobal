package com.example.localtoglobal.productRetro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localtoglobal.R;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<ProductDto> productDtoList;
    private final ProductDataInterface productDataInterface;

    public ProductAdapter(List<ProductDto> productDtoList, ProductDataInterface productDataInterface) {
        this.productDtoList = productDtoList;
        this.productDataInterface = productDataInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_data, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDto productDto = productDtoList.get(position);
        holder.name.setText(productDto.getName()+"");
        holder.rootView.setOnClickListener((view -> {
            productDataInterface.onUserClick(productDto,view,holder.getAdapterPosition());
        }));

    }

    @Override
    public int getItemCount() {
        return productDtoList.size();
    }
    public interface ProductDataInterface{
        void onUserClick(ProductDto productDto, View view, int position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final View rootView;


        public ViewHolder(View view) {
            super(view);
            rootView = view;
            name = view.findViewById(R.id.name4);
        }

    }
}
