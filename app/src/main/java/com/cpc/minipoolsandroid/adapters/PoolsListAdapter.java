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

    private List<Pool> mPools = Collections.emptyList();

    //<editor-fold desc="RecyclerView.Adapter impl">
    @Override
    public PoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PoolViewHolder(view);
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

        public PoolViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Pool pool) {
            ((TextView) itemView).setText(pool.name);
        }
    }
    //</editor-fold>

    public void setPools(List<Pool> pools) {
        mPools = pools;
        notifyDataSetChanged();
    }
}
