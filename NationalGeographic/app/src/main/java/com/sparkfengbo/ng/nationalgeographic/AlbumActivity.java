package com.sparkfengbo.ng.nationalgeographic;

import com.sparkfengbo.ng.nationalgeographic.adapter.AlbumAdapter;
import com.sparkfengbo.ng.nationalgeographic.data.AlbumItemData;
import com.sparkfengbo.ng.nationalgeographic.data.CatalogItemData;
import com.sparkfengbo.ng.nationalgeographic.data.AlbumPageData;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class AlbumActivity extends Activity {
    private static final int MSG_GET_GATALOG = 1;

    @BindView(R.id.album_recycle_view)
    public RecyclerView mRecyclerView;

    private AlbumAdapter mAlbumAdapter;
    private Handler mHandler;
    private int albumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);

        mHandler = new Handler(mHanderCallback);

        Intent intent = getIntent();
        if (intent != null) {
            albumId = intent.getIntExtra(GlobalConfig.KEY_ALUBUM_ID, -1);
        }

        mAlbumAdapter = new AlbumAdapter(this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        layoutManager.setSmoothScrollbarEnabled(true);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAlbumAdapter);

        if (albumId != -1) {
            getAlbumData();
        }
    }

    private class EnterItemAnimator extends RecyclerView.ItemAnimator {

        @Override
        public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo
                preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo
                preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder
                newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public void runPendingAnimations() {

        }

        @Override
        public void endAnimation(RecyclerView.ViewHolder item) {

        }

        @Override
        public void endAnimations() {

        }

        @Override
        public boolean isRunning() {
            return false;
        }
    }

    private Handler.Callback mHanderCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case MSG_GET_GATALOG:
                    if (message.obj instanceof List) {
                        mAlbumAdapter.setData((List<AlbumItemData>) message.obj);
                        mAlbumAdapter.notifyDataSetChanged();
                    }
                    break;
            }
            return false;
        }
    };

    private void getAlbumData() {

        Request request = new Request.Builder().url(GlobalConfig.getAlbumUrlForId(albumId)).build();
        OkHttpManager.getInst().enqueue(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(GlobalConfig.TAG, "getAlbumData onFailure");
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
                            AlbumPageData albumPageData = new AlbumPageData();
                            albumPageData.parseJson(resultJson);
                            Message msg = Message.obtain();
                            msg.what = MSG_GET_GATALOG;
                            msg.obj = albumPageData.mDataList;
                            mHandler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
