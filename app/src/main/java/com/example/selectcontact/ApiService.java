package com.example.selectcontact;

import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    public static  String BASE_URL="http://sms53.hakimisolution.com";
//    public static  String BASE_URL_NEW="http://psms.hakimisolution.com";

    @GET("/api/sendhttp.php")
    Call<String> sendmsg(@Query("authkey") String authkey,@Query("mobiles") String mobiles,@Query("message") String message,@Query("sender") String sender,@Query("route") String route,@Query("country") String country);

    @GET("/api/balance.php")
    Call<String> getNoOfMessage(@Query("authkey") String authkey, @Query("type") String type);
}
