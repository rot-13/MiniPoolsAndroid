package com.cpc.minipoolsandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpc.minipoolsandroid.models.Pool;

import java.util.Collections;
import java.util.List;

/**
 * Created by ifeins on 9/11/17.
 */

public class PoolsListAdapter extends RecyclerView.Adapter<PoolsListAdapter.PoolViewHolder> {

    public interface Listener {
        void onPoolTapped(Pool pool);
    }

    private Listener mListener;

    private List<Pool> mPools = Collections.emptyList();

    //<editor-fold desc="RecyclerView.Adapter impl">
    @Override
    public PoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PoolViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(PoolViewHolder holder, int position) {
        holder.bind(mPools.get(position));
    }

    @Override
    public int getItemCount() {
        return mPools.size();
    }

    public static class PoolViewHolder extends RecyclerView.ViewHolder {

        private Pool mPool;

        public PoolViewHolder(View itemView, final Listener listener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onPoolTapped(mPool);
                    }
                }
            });
        }

        public void bind(Pool pool) {
            mPool = pool;
            ((TextView) itemView).setText(mPool.name);
        }
    }
    //</editor-fold>

    public void setPools(List<Pool> pools) {
        mPools = pools;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }
}
