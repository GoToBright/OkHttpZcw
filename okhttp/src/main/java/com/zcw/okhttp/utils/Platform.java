package com.zcw.okhttp.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by 朱城委 on 2017/6/23.<br><br>
 */

public class Platform {
    private static final Platform INSTANCE = new Platform();

    public static Platform getInstance() {
        return INSTANCE;
    }

    private Platform() {
    }

    public Executor getExecutor() {
        return new MainThreadExecutor();
    }

    public void executor(Runnable runnable) {
        getExecutor().execute(runnable);
    }

    static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }
}
