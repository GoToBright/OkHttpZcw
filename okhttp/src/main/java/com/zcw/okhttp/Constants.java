package com.zcw.okhttp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 朱城委 on 2017/7/6.<br><br>
 * 常量类
 */

public class Constants {
    public static final int NETWORK_REQUEST_EXCEPTION = 10;     // 网络请求异常

    public static final Map<Integer, String> RESULTS = new HashMap<Integer, String>();

    static {
        RESULTS.put(NETWORK_REQUEST_EXCEPTION, "网络请求异常");
    }
}
