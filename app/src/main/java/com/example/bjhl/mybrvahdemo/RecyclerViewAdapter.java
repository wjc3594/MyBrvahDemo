package com.example.bjhl.mybrvahdemo;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by bjhl on 2019/5/5.
 */

public class RecyclerViewAdapter extends BaseQuickAdapter<DataModel, RecyclerViewAdapter.ViewHolder> {

    public RecyclerViewAdapter(int layoutResId, @Nullable List<DataModel> data) {
        super(layoutResId, data);
    }

    static class ViewHolder extends BaseViewHolder{
        public TextView tv_title;
        public TextView tv_text;
        public ViewHolder(View view) {
            super(view);
            tv_title=view.findViewById(R.id.recycler_view_title);
            tv_text=view.findViewById(R.id.recycler_view_text);
        }
    }
    @Override
    protected void convert(ViewHolder helper, DataModel item) {
        helper.setText(R.id.recycler_view_title,item.title)
                .setText(R.id.recycler_view_text,item.text)
                .addOnClickListener(R.id.recycler_view_title)
                .addOnClickListener(R.id.recycler_view_text)
                .addOnLongClickListener(R.id.recycler_view_title)
                .addOnLongClickListener(R.id.recycler_view_text);
    }
}
