package com.example.localtoglobal.recommendationRetro;

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

import com.example.localtoglobal.R;
import com.example.localtoglobal.homeSlider.SliderAdapter;
import com.example.localtoglobal.homeSlider.SliderModel;
import com.example.localtoglobal.productDetails.ProductDetails;
import com.example.localtoglobal.productRetro.ProductDto;
import com.example.localtoglobal.retrofit.MainBuilder;
import com.example.localtoglobal.retrofit.MainInterface;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements RecommendAdapter.RecommendDataInterface{

    String url1 = "https://www.dqchannels.com/wp-content/uploads/2020/06/made-india-icon-set-product-labels-republic-emblem-144281195-800x420.jpg";
    String url2 = "https://www.nydailynews.com/resizer/UpBkgnm55p4x-w62IvQ2LMEPpB4=/800x476/top/arc-anglerfish-arc2-prod-tronc.s3.amazonaws.com/public/PTHIHN5WMZEFDFLF5YRQCOQVMY.jpg";
    String url3 = "https://thelogicalindian.com/h-upload/2021/01/14/188943-toyweb-1.jpg";
    String url4 = "https://indianetzone.files.wordpress.com/2019/02/indian-traditional-forgotten-games-644x362.jpg";
    String url5 = "https://previews.123rf.com/images/snehit/snehit0812/snehit081200020/4001334-de-l-artisanat-de-l-inde.jpg";


    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<SliderModel> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = view.findViewById(R.id.slider);
        sliderDataArrayList.add(new SliderModel(url1));
        sliderDataArrayList.add(new SliderModel(url2));
        sliderDataArrayList.add(new SliderModel(url3));
        sliderDataArrayList.add(new SliderModel(url4));
        sliderDataArrayList.add(new SliderModel(url5));
        SliderAdapter adapter = new SliderAdapter(view.getContext(), sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        Retrofit retrofit = MainBuilder.getInstance();
        Call<List<ProductDto>> productDto= retrofit.create(MainInterface.class).getProductRecommendation();
        productDto.enqueue(new Callback<List<ProductDto>>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, Response<List<ProductDto>> response) {
                RecyclerView recyclerView = getView().findViewById(R.id.home_recommend);
               recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
              recyclerView.setAdapter(new RecommendAdapter(response.body(), HomeFragment.this));
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {

            }
        });

    }
    @Override
    public void onUserClick(ProductDto productDto) {
        Intent i = new Intent(getContext(), ProductDetails.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("product", productDto);
        i.putExtras(bundle);
        startActivity(i);

    }

}