package com.zcw.okhttp.callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 朱城委 on 2017/6/22.<br /><br />
 */

public abstract class MyCallback<T> {
    public abstract T parseNetworkResponse(Response response) throws Exception;
    public abstract void onFailure(Call call, Exception e, int code);
    public abstract void onResponse(T response);
}
