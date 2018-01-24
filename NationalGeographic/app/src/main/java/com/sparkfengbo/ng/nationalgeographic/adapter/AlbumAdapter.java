package com.sparkfengbo.ng.nationalgeographic.adapter;

import com.bumptech.glide.Glide;
import com.sparkfengbo.ng.nationalgeographic.R;
import com.sparkfengbo.ng.nationalgeographic.data.AlbumItemData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by fengbo on 2018/1/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<AlbumItemData> mData;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;

    public AlbumAdapter(Context context) {
        mContext = context;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item_view, parent, false);
        AlbumViewHolder viewHolder = new AlbumViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        if (mData != null && mData.get(position) != null) {
            AlbumItemData data = mData.get(position);
            String url = data.url;
            Glide.with(mContext).load(url).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setData(List<AlbumItemData> data) {
        mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position) ;
    }


    class AlbumViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public AlbumViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
