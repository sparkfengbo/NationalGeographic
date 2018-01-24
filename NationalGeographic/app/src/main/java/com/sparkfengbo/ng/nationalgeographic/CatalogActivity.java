package com.sparkfengbo.ng.nationalgeographic;

import com.sparkfengbo.ng.nationalgeographic.adapter.CatalogAdapter;
import com.sparkfengbo.ng.nationalgeographic.data.CatalogItemData;
import com.sparkfengbo.ng.nationalgeographic.data.CatalogData;
import com.sparkfengbo.ng.nationalgeographic.model.CatalogModel;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CatalogActivity extends Activity {

    @BindView(R.id.catalog_recycle_view)
    public RecyclerView mRecyclerView;

    private CatalogAdapter mCatalogAdapter;
    private CatalogModel mCatalogModel;
    private CatalogData mData;
    private Handler mHandler;

    private static final int MSG_GET_GATALOG = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        ButterKnife.bind(this);
        mHandler = new Handler(mHanderCallback);

        mCatalogAdapter = new CatalogAdapter(this);
        mCatalogAdapter.setOnItemClickListener(mOnItemClickListener);

        mRecyclerView.setAdapter(mCatalogAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        TODO 确认是否能够提高性能
        mRecyclerView.setHasFixedSize(true);

        mCatalogModel = new CatalogModel(mCallback);
        mCatalogModel.getPageData(1);
    }

    private CatalogAdapter.OnItemClickListener mOnItemClickListener = new CatalogAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int albumId) {
            Log.d(GlobalConfig.TAG, "albumId : " + albumId);
            Intent intent = new Intent();
            intent.setClass(CatalogActivity.this.getBaseContext(), AlbumActivity.class);
            intent.putExtra(GlobalConfig.KEY_ALUBUM_ID, albumId);
            startActivity(intent);
        }
    };

    private Handler.Callback mHanderCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case MSG_GET_GATALOG:
                    if (message.obj instanceof List) {
                        mCatalogAdapter.setData((List<CatalogItemData>) message.obj);
                        mCatalogAdapter.notifyDataSetChanged();
                    }
                    break;
            }
            return false;
        }
    };


    private Callback mCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(GlobalConfig.TAG, "getPageData onFailure");
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String resultBody = response.body().string();
                JSONObject resultJson = null;
                try {
                    resultJson = new JSONObject(resultBody);
                    if (resultJson != null) {
                        CatalogData catalogData = new CatalogData();
                        catalogData.parseJson(resultJson);
                        Message msg = Message.obtain();
                        msg.what = MSG_GET_GATALOG;
                        msg.obj = catalogData.albums;
                        mHandler.sendMessage(msg);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };


}
