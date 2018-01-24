package com.sparkfengbo.ng.nationalgeographic.data;

import com.sparkfengbo.ng.nationalgeographic.GlobalConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengbo on 2018/1/15.
 */

public class CatalogData {

    public long total;
    public int page;
    public int pageCount;
    public List<CatalogItemData> albums;

    public void parseJson(JSONObject obj) {
        if (obj == null) {
            return;
        }

        total = obj.optLong("total");
        page = obj.optInt("page");
        pageCount = obj.optInt("pageCount");

        Log.d(GlobalConfig.TAG, "CatalogData ： total ： " + total + " page ： " + page + " pageCount ： " + pageCount);

        JSONArray albumsArray = obj.optJSONArray("album");
        if (albumsArray != null && albumsArray.length() > 0) {
            albums = new ArrayList<CatalogItemData>();
            for (int i = 0; i < albumsArray.length(); i++) {
                try {
                    JSONObject albumJson = albumsArray.getJSONObject(i);
                    CatalogItemData data = new CatalogItemData();
                    data.parseJson(albumJson);
                    albums.add(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
