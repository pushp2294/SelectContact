package com.example.selectcontact;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static ApiClient apiClient;
  //  public static  String BASE_URL="http://sms53.hakimisolution.com";
//    private Long cacheSize = (long) (10 * 1024 * 1024);
    private static Context context;
    public ApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        Interceptor interceptor1=new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + token)
//                        .build();
//                return chain.proceed(request);
//            }
//        };
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(4, TimeUnit.MINUTES)
                .writeTimeout(4, TimeUnit.MINUTES)
                .connectTimeout(4, TimeUnit.MINUTES)
       //         .addInterceptor(interceptor1)
                .build();
//                .cache(cache)
/**Handle Null Values*/
//        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .excludeFieldsWithoutExposeAnnotation()
//                .serializeNulls()
//                .create();
        Gson gson1 = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson1))
                .client(client)
                .build();
    }

    public static synchronized ApiClient getInstance(Context context){
        ApiClient.context=context;
        if(apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }
}
