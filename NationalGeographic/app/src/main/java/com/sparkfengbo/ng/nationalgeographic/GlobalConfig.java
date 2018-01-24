package com.sparkfengbo.ng.nationalgeographic;

import android.util.Log;

/**
 * Created by fengbo on 2018/1/15.
 */

public class GlobalConfig {
    public static String TAG = "nationalgeographic";

    public static String KEY_ALUBUM_ID = "key_album_id";

    private static final String SERVER_ADDRESS = "http://dili.bdatu.com";

    public static final String GET_CATALOG = "/jiekou/mains/p[N].html";

    public static final String GET_ALBUM = "/jiekou/albums/a[N].html";



    public static String getCatalogUrlForPage(int page) {
        String result = SERVER_ADDRESS + GET_CATALOG.replace("[N]", String.valueOf(page));
        Log.d(TAG, "getCatalogUrlForPage : " + result);
        return  result;
    }

    public static String getAlbumUrlForId (int albumId) {
        String result = SERVER_ADDRESS + GET_ALBUM.replace("[N]", String.valueOf(albumId));
        Log.d(TAG, "getAlbumUrlForId : " + result);
        return  result;
    }

    public static class ConstantConfig {
        public static final String BOOLEAN_TRUE = "YES";
        public static final String BOOLEAN_FALSE = "NO";
    }

}
