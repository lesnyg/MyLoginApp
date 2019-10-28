package com.lesnyg.myloginapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterApi {
    String BASE_URL = "http://lesnyg.dothome.co.kr";

    @FormUrlEncoded
    @POST("/Register.php")
    Call<Result> register(@Field("userID") String userID,
                          @Field("userPassword") String userPassword,
                          @Field("userName") String userName,
                          @Field("userAge") int userAge);

    @FormUrlEncoded
    @POST("/Login.php")
    Call<Result> login(@Field("userID") String userID,
                          @Field("userPassword") String userPassword);
}
