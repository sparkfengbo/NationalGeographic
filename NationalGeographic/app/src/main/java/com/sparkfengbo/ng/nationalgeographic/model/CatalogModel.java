package com.sparkfengbo.ng.nationalgeographic.model;

import com.sparkfengbo.ng.nationalgeographic.GlobalConfig;
import com.sparkfengbo.ng.nationalgeographic.OkHttpManager;
import com.sparkfengbo.ng.nationalgeographic.data.CatalogData;

import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by fengbo on 2018/1/15.
 */

public class CatalogModel {

    private CatalogData mCatalogData;
    private Callback mCallback;

    public CatalogModel(Callback callback) {
        mCallback = callback;
    }

    public void getPageData(int page) {
        Request request = new Request.Builder().url(GlobalConfig.getCatalogUrlForPage(page)).build();
        OkHttpManager.getInst().enqueue(request, mCallback);
    }



}
