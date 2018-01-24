package com.sparkfengbo.ng.nationalgeographic.data;

import org.json.JSONObject;

/**
 * Created by fengbo on 2018/1/15.
 */
public class CatalogItemData {
    /**
     * "id": "1833",
     * "title": "2018-01-14 姣忔棩绮鹃€\ufffd",
     * "url": "http://pic01.bdatu.com/Upload/appsimg/1515119725.jpg",
     * "addtime": "2018-01-14 00:04:00",
     * "adshow": "NO",
     * "fabu": "YES",
     * "encoded": "1",
     * "amd5": "cba3148d014908d908afbe9107f6ceee",
     * "sort": "1833",
     * "ds": "1",
     * "timing": "1",
     * "timingpublish": "2018-01-14 00:00:00"
     **/

    public int id;
    public String title;
    public String url;
    public String addtime;
    public String adshow;
    public String fabu;
    public int encoded;
    public String amd5;
    public int sort;
    public int ds;
    public int timing;
    public String timingpublish;

    public void parseJson(JSONObject obj) {
        if (obj == null) {
            return;
        }
        id = obj.optInt("id");
        title = obj.optString("title");
        url = obj.optString("url");
        addtime = obj.optString("addtime");
        adshow = obj.optString("adshow");
        fabu = obj.optString("fabu");
        encoded = obj.optInt("encoded");
        amd5 = obj.optString("amd5");
        sort = obj.optInt("sort");
        ds = obj.optInt("ds");
        timing = obj.optInt("timing");
        timingpublish = obj.optString("timingpublish");
    }
}
