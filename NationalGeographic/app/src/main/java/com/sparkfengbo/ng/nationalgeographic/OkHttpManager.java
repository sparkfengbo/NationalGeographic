package com.sparkfengbo.ng.nationalgeographic;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fengbo on 2018/1/16.
 */

public class OkHttpManager {

    private static OkHttpManager sInstance;
    private static OkHttpClient sOkHttpClient;

    public static OkHttpManager getInst() {
        if (sInstance == null) {
            synchronized (OkHttpManager.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpManager();
                }
            }
        }
        return sInstance;
    }

    private OkHttpManager() {
        sOkHttpClient = new OkHttpClient();
    }

    /**
     * 同步方法
     * @param request
     * @return
     * @throws IOException
     */
    public Response execute(Request request) throws IOException {
        return sOkHttpClient.newCall(request).execute();
    }

    /**
     * 异步方法
     * @param request
     * @param callback
     */
    public void enqueue(Request request, Callback callback) {
        sOkHttpClient.newCall(request).enqueue(callback);
    }




}
