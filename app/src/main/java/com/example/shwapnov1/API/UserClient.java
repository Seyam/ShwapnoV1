package com.example.shwapnov1.API;

import com.example.shwapnov1.Model.Post;
import com.example.shwapnov1.Model.SensorData;
import com.example.shwapnov1.Model.User;
import com.example.shwapnov1.Model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {
    @POST("edit")
    Call<User> sendPost(@Body User user);

    @FormUrlEncoded
    @POST("edit")
    Call<UserResponse>  sendPostAsFormUrlEncoded(
            @Field("device_no") String device_no,
            @Field("temperature") String temperature
    );

    @POST("login")
    Call<UserResponse> sendPostAsBasicAuthentication(@Header("Authorization") String authHeader);

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("sensors")
    Call<List<SensorData>> getSensorData();
}
