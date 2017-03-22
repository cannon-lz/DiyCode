package com.zly.diycode.http;

import android.support.v4.util.ArrayMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class ApiConfig {

    private static class ApiConfigInstanceHolder {

        private static final ApiConfig INSTANCE = new ApiConfig();
    }

    private Retrofit mRetrofit;
    private ArrayMap<Class<?>, Object> mApis = new ArrayMap<>();

    private ApiConfig() {
        Retrofit.Builder builder = new Retrofit.Builder();
        mRetrofit = builder.baseUrl("https://diycode.cc/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getDefaultHttpClient()).build();
    }

    public static ApiConfig getInstance() {
        return ApiConfigInstanceHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> T getApi(Class<T> clazz) {
        Object api = mApis.get(clazz);
        if (api == null) {
            api = mRetrofit.create(clazz);
            mApis.put(clazz, api);
        }
        return (T) api;
    }

    private OkHttpClient getDefaultHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }
}
