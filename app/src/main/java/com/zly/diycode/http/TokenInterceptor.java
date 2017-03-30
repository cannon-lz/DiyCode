package com.zly.diycode.http;

import android.text.TextUtils;
import android.util.Log;

import com.zly.diycode.common.UserManager;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by zhangluya on 2017/3/30.
 */

class TokenInterceptor implements Interceptor {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();
        String accessToken = UserManager.getInstance().getToken();
        if (TextUtils.isEmpty(accessToken)) {
            return chain.proceed(request);
        }
        if (body != null) {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            String requestBody = buffer.readString(UTF_8);
            requestBody += "&access_token=" + accessToken;
            Log.i("TokenInterceptor", String.format("request body %s", requestBody));
            return chain.proceed(request.newBuilder().method(request.method(), RequestBody.create(MEDIA_TYPE, requestBody)).build());
        } else if (TextUtils.equals(request.method(), "GET")) {
            HttpUrl httpUrl = request.url().newBuilder().addQueryParameter("access_token", accessToken).build();
            return chain.proceed(request.newBuilder().url(httpUrl).build());
        }
        return chain.proceed(request);
    }
}
