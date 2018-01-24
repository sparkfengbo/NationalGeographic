package com.sparkfengbo.ng.nationalgeographic.adapter;

import com.bumptech.glide.Glide;
import com.sparkfengbo.ng.nationalgeographic.R;
import com.sparkfengbo.ng.nationalgeographic.data.CatalogItemData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by fengbo on 2018/1/15.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogItemViewHolder> implements View
        .OnClickListener {

    private List<CatalogItemData> mData;
    private Context mContext;


    private OnItemClickListener mOnItemClickListener;

    public CatalogAdapter(Context context) {
        mContext = context;
    }

    @Override
    public CatalogItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO parent ???
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item_view, parent, false);
        view.setOnClickListener(this);
        CatalogItemViewHolder viewHolder = new CatalogItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CatalogItemViewHolder holder, int position) {
        if (mData != null && mData.get(position) != null) {
            CatalogItemData data = mData.get(position);
            holder.rootView.setTag(data.id);
            String url = data.url;
            Glide.with(mContext).load(url).into(holder.entry_list_ige);
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setData(List<CatalogItemData> data) {
        mData = data;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null && view.getTag() instanceof Integer) {
            mOnItemClickListener.onItemClick(view, (Integer)view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position) ;
    }

    class CatalogItemViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public ImageView entry_list_ige;

        public CatalogItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            entry_list_ige = itemView.findViewById(R.id.entry_list_ige);
        }
    }
}
