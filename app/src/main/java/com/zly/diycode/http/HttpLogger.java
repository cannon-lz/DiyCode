package com.zly.diycode.http;

import android.os.Handler;
import android.os.HandlerThread;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhangluya on 2016/10/17.
 */
class HttpLogger implements HttpLoggingInterceptor.Logger {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final JsonParser PARSER = new JsonParser();
    private Handler mLogHandler;

    HttpLogger() {
        HandlerThread logThread = new HandlerThread("log-thread");
        logThread.start();
        mLogHandler = new Handler(logThread.getLooper());
    }

    @Override
    public void log(final String message) {
        mLogHandler.post(new Runnable() {
            @Override
            public void run() {
                String log = message;
                if (isJsonObj(log)) {
                    JsonElement je = PARSER.parse(log);
                    log = GSON.toJson(je);
                }
                Platform.get().log(Platform.INFO, log, null);
            }
        });
    }

    private boolean isJsonObj(String message) {
        boolean isJson;
        try {
            new JSONObject(message);
            isJson = true;
        } catch (JSONException e) {
            isJson = false;
        }
        return isJson;
    }
}
