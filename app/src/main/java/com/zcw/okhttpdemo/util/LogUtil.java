package com.zcw.okhttpdemo.util;

import android.util.Log;

import com.zcw.okhttpdemo.BuildConfig;

/**
 * Created by 朱城委 on 2017/6/16.<br><br>
 *
 * Log函数有2个重载方法,x(string)和x(string, string)。<br>
 * 一个参数的方法，可定位并跳转到log所在的代码行位置;<br>
 * 二个参数的方法，只会在debug模式显示log，release模式会自动屏蔽。
 */

public class LogUtil {
    private static String className;    //类名
    private static String methodName;   //方法名
    private static int lineNumber;      //行数

    private LogUtil(){
    }

    private static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    private static String createLog( String log ) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] element){
        className = element[1].getFileName();
        methodName = element[1].getMethodName();
        lineNumber = element[1].getLineNumber();
    }

    public static void e(String tag, String message) {
        if(!isDebuggable())
            return ;

        Log.e(tag, message);
    }

    public static void e(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }


    public static void i(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void i(String tag, String message) {
        if(!isDebuggable())
            return ;

        Log.i(tag, message);
    }

    public static void d(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void d(String tag, String message) {
        if(!isDebuggable())
            return ;

        Log.d(tag, message);
    }

    public static void v(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void v(String tag, String message) {
        if(!isDebuggable())
            return ;

        Log.v(tag, message);
    }

    public static void w(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void w(String tag, String message) {
        if(!isDebuggable())
            return ;

        Log.w(tag, message);
    }

    public static void wtf(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message));
    }

    public static void wtf(String tag, String message) {
        if(!isDebuggable())
            return ;

        Log.wtf(tag, message);
    }
}
