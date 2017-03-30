package com.zly.diycode.http;

import com.zly.diycode.data.Mapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class RetrofitCallback<T, R> implements Callback<T> {

    private com.zly.diycode.data.Callback<R> mCallback;

    public RetrofitCallback(com.zly.diycode.data.Callback<R> callback) {
        this.mCallback = callback;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            T body = response.body();
            if (body instanceof List) {
                List list = (List) body;
                List resp = new ArrayList<>();
                for (Object obj : list) {
                    if (obj instanceof Mapper) {
                        Mapper mapper = (Mapper) obj;
                        resp.add(mapper.map());
                    }
                }
                mCallback.onSuccess((R) resp);
            }

            if (body instanceof Mapper) {
                mCallback.onSuccess((R) ((Mapper) body).map());
            }

        } else {
            mCallback.onError(response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        mCallback.onError(t.getMessage());
    }
}
