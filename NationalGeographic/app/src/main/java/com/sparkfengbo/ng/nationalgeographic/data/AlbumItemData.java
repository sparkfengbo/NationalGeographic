package com.sparkfengbo.ng.nationalgeographic.data;

import org.json.JSONObject;

/**
 * Created by fengbo on 2018/1/15.
 */
public class AlbumItemData {
    /**
     * "id": "17035",
     * "albumid": "1836",
     * "title": "鏉伴噷绫陈风惣鏂€屽嵎璧峰崈鍫嗛洩銆\ufffd",
     * "content": "鏉扮憺绫筹紟鐞兼柉锛圝eremy
     * Jones锛夋鍦ㄤ韩鍙椾竴涓垚鍔熺殑鍒囧洖杞悜锛屼互鍙婂畠婵€璧风殑绾烽闆姳銆傚緢澶氫汉涓嶇煡閬撳唴鍗庤揪宸炴嫢鏈変笉灏戠編鍥芥渶濂界殑婊戦洩鍦猴紝涓斾汉娼篃涓嶉偅楹芥嫢鎸ゃ€傛鏃舵鍒伙紝鏉伴噷绫虫鐙嚜浜彈杩欓噷鐨勯鏅紒",
     * "url": "http://pic01.bdatu.com/Upload/picimg/1515122356.jpg",
     * "size": "344892",
     * "addtime": "2018-01-05 11:19:20",
     * "author": "MING POON",
     * "thumb": "http://pic01.bdatu.com/Upload/picimg/1515122356.jpg",
     * "encoded": "1",
     * "weburl": "http://",
     * "type": "pic",
     * "yourshotlink": "http://yourshot.nationalgeographic.com/photos/10971385/",
     * "copyright": "",
     * "pmd5": "f346a191cbc02d9a49786f1eccd091a8",
     * "sort": "17035"
     **/

    public long id;
    public long albumid;
    public String title;
    public String content;
    public String url;
    public long size;
    public String addtime;
    public String author;

    public String thumb;
    public int encoded;
    public String weburl;
    public String type;
    public String yourshotlink;

    public String copyright;
    public String pmd5;
    public long sort;

    public void parseJson(JSONObject obj) {
        if (obj == null) {
            return;
        }
        id = obj.optLong("id");
        albumid = obj.optLong("albumid");
        title = obj.optString("title");
        content = obj.optString("content");
        url = obj.optString("url");
        size = obj.optLong("size");
        addtime = obj.optString("addtime");
        author = obj.optString("author");
        thumb = obj.optString("thumb");
        encoded = obj.optInt("encoded");
        weburl = obj.optString("weburl");
        type = obj.optString("type");
        yourshotlink = obj.optString("yourshotlink");
        copyright = obj.optString("copyright");
        pmd5 = obj.optString("amd5");
        sort = obj.optLong("sort");
    }
}
