package com.sparkfengbo.ng.nationalgeographic.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengbo on 2018/1/17.
 */

public class AlbumPageData {
    public int counttotal;
    public List<AlbumItemData> mDataList;

    public void parseJson(JSONObject obj) {
        if (obj == null) {
            return;
        }

        counttotal = obj.optInt("counttotal");

        JSONArray albumsArray = obj.optJSONArray("picture");
        if (albumsArray != null && albumsArray.length() > 0) {
            mDataList = new ArrayList<AlbumItemData>();
            for (int i = 0; i < albumsArray.length(); i++) {
                try {
                    JSONObject albumJson = albumsArray.getJSONObject(i);
                    AlbumItemData data = new AlbumItemData();
                    data.parseJson(albumJson);
                    mDataList.add(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
