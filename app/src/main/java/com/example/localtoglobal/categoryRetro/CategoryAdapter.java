package com.example.localtoglobal.categoryRetro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localtoglobal.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModelList;
    private CategoryModelInterface categoryModelInterface;

    public CategoryAdapter(List<CategoryModel> categoryModelList, CategoryModelInterface categoryModelInterface) {
        this.categoryModelList = categoryModelList;
        this.categoryModelInterface = categoryModelInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel categoryModel = categoryModelList.get(position);
        holder.name.setText(categoryModel.getName()+"");
        holder.rootView.setOnClickListener((view -> {
            categoryModelInterface.onUserClick(categoryModel,view, holder.getAdapterPosition());
        }));
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public interface CategoryModelInterface {
        void onUserClick(CategoryModel categoryModel,View v,int position);

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final View rootView;

        public ViewHolder(View view) {
            super(view);
            rootView =view;
            name = view.findViewById(R.id.name3);
        }

    }


}
