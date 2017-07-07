package com.zcw.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zcw.okhttp.Constants;
import com.zcw.okhttp.OkHttpUtils;
import com.zcw.okhttp.callback.StringCallback;
import com.zcw.okhttpdemo.util.CommonUtils;
import com.zcw.okhttpdemo.util.LogUtil;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView requestResult;

    private OkHttpUtils okHttpUtils;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_request) {
                okHttpUtils.getString("https://www.baidu.com", new StringCallback() {
                    @Override
                    public void onFailure(Call call, Exception e, int code) {
                        CommonUtils.toast(MainActivity.this, Constants.RESULTS.get(code));
                        LogUtil.e(TAG, Constants.RESULTS.get(code));
                    }

                    @Override
                    public void onResponse(String response) {
                        requestResult.setText(response);
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button button = (Button) findViewById(R.id.btn_request);
        button.setOnClickListener(listener);

        requestResult = (TextView) findViewById(R.id.txt_request_result);
        okHttpUtils = OkHttpUtils.getInstance();
    }
}
