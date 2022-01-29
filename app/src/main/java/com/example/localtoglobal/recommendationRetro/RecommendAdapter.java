package com.example.localtoglobal.recommendationRetro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localtoglobal.R;
import com.example.localtoglobal.productRetro.ProductDto;

import java.util.List;

public class RecommendAdapter extends  RecyclerView.Adapter<RecommendAdapter.ViewHolder>{
    private final List<ProductDto> productDtoList;
    private final RecommendDataInterface recommendDataInterface;

    public RecommendAdapter(List<ProductDto> productDtoList, RecommendDataInterface recommendDataInterface) {
        this.productDtoList = productDtoList;
        this.recommendDataInterface = recommendDataInterface;
    }

    @NonNull
    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommendation_item, parent, false);
        return new RecommendAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull RecommendAdapter.ViewHolder holder, int position) {
        ProductDto productDto = productDtoList.get(position);
        holder.name.setText(productDto.getName()+"");
        holder.description.setText(productDto.getDescription()+"");
        holder.rootView.setOnClickListener(view -> {
            recommendDataInterface.onUserClick(productDto);
        });
    }


    @Override
    public int getItemCount() {
        return productDtoList.size();
    }

    public interface RecommendDataInterface
    {
        void onUserClick(ProductDto productDto);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private final View rootView;



        public ViewHolder(View view) {
            super(view);
            rootView = view;
            name = view.findViewById(R.id.name1);
            description = view.findViewById(R.id.description1);

        }

    }
}
