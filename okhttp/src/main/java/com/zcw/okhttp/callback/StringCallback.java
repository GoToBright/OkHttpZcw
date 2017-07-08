package com.zcw.okhttp.callback;

import okhttp3.Response;

/**
 * Created by 朱城委 on 2017/6/23.<br><br>
 */

public abstract class StringCallback extends MyCallback<String> {

    @Override
    public String parseNetworkResponse(Response response) throws Exception {
        return response.body().string();
    }
}
