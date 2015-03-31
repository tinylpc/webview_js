package com.example.tiny.webviewjs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by tiny on 2015/3/25.
 */
public abstract class CustomAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;

    protected Context mContext;

    protected List<T> mDatas;

    protected final int mItemLayoutId;

    public CustomAdapter(Context c, List<T> d, int itemLayoutId) {
        mInflater = LayoutInflater.from(c);
        this.mContext = c;
        mDatas = d;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }
}
