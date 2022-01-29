package com.example.localtoglobal.categoryRetro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.localtoglobal.productRetro.ProductList;
import com.example.localtoglobal.R;
import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.retrofit.MainInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryFragment extends Fragment implements CategoryAdapter.CategoryModelInterface {

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = MainBuilder.getInstance();
        Call<List<CategoryModel>> categoryModelCall = retrofit.create(MainInterface.class).getCategoryName();
        categoryModelCall.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                RecyclerView recyclerView = getView().findViewById(R.id.category_recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new CategoryAdapter(response.body(),CategoryFragment.this));
                Toast.makeText(getContext(), "Open", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onUserClick(CategoryModel categoryModel, View v, int position) {
        Intent i = new Intent(getContext(), ProductList.class);
        i.putExtra("categoryName",categoryModel.getName());
        startActivity(i);
    }
}
