package com.example.localtoglobal.retrofit;

import com.example.localtoglobal.cartRetro.CartDto;
import com.example.localtoglobal.cartRetro.CartItemDto;
import com.example.localtoglobal.categoryRetro.CategoryModel;
import com.example.localtoglobal.loginRetro.LoginEntity;
import com.example.localtoglobal.loginRetro.UserEntity;
import com.example.localtoglobal.order.OrderDto;
import com.example.localtoglobal.productRetro.ProductDto;
import com.example.localtoglobal.registerRetro.EnOtpEntity;
import com.example.localtoglobal.registerRetro.MessageReturn;
import com.example.localtoglobal.registerRetro.RegisterEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MainInterface {

    //  CartProductListInterface
    @GET("/cart/{id}")
    Call<CartDto> getOrderByUserId(@Path("id") Long id);

    @POST("/order/addOrder")
    Call<Void> addOrder(@Body OrderDto orderDto);

    @GET("/order/getByUserId/{id}")
    Call<List<OrderDto>> getOrdersById(@Path("id") Long id);

    //  CategoryInterface
    @GET("/category")
    Call<List<CategoryModel>> getCategoryName();

    //    LoginInterface
    @POST("/login/login")
    Call<UserEntity> postLog(@Body LoginEntity loginEntity);

    //    OtpInterface
    @POST("login/verify")
    Call<MessageReturn> postotp(@Body RegisterEntity registerEntity);

    //   ProductDetailInterface
    @POST("/cart/addToCart")
    Call<MessageReturn> addToCart (@Body CartItemDto cartItemDto);

    //   ProductInterface
    @GET("/category/getProductByCategory/{product}")
    Call<List<ProductDto>> getProductName(@Path("product") String category );

    //  RecommendationInterface
    @GET("/category/recommendations")
    Call<List<ProductDto>> getProductRecommendation();

    //  RegisterInterface
    @POST("/login/register/{email}")
    Call<EnOtpEntity> postregister(@Path("email") String email);

    //    SearchInterface
    @GET("/search/manual/{text}")
    Call<List<ProductDto>> getSearchResult(@Path("text") String s);

}
