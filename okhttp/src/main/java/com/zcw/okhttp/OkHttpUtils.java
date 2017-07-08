package com.zcw.okhttp;

import com.zcw.okhttp.callback.MyCallback;
import com.zcw.okhttp.utils.Platform;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 朱城委 on 2017/6/22.<br><br>
 */

public class OkHttpUtils {
    private volatile static OkHttpUtils instance;

    private Platform platform;

    private OkHttpClient okHttpClient;

    public static OkHttpUtils getInstance() {
        if(instance == null) {
            synchronized (OkHttpUtils.class) {
                if(instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    private OkHttpUtils() {
        okHttpClient = new OkHttpClient();
        platform = Platform.getInstance();
    }

    public void getString(String url, MyCallback callback) {
        Request request = new Request.Builder().url(url).build();
        execute(request, callback);
    }

    public void execute(final Request request, final MyCallback callback) {
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailResultCallback(call, e, callback, Constants.NETWORK_REQUEST_EXCEPTION);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    int code = response.code();
                    if(response.isSuccessful()) {
                        Object o = callback.parseNetworkResponse(response);
                        sendSuccessResultCallback(o, callback);
                    }
                    else {
                        sendFailResultCallback(call, null, callback, code);
                    }
                }
                catch (Exception e) {
                    sendFailResultCallback(call, e, callback, Constants.NETWORK_REQUEST_EXCEPTION);
                }
                finally {
                    if(response.body() != null)
                        response.body().close();
                }
            }
        });
    }

    public void sendFailResultCallback(final Call call, final Exception e, final MyCallback callback, final int code) {
        platform.executor(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(call, e, code);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final MyCallback callback) {
        platform.executor(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
            }
        });
    }
}
