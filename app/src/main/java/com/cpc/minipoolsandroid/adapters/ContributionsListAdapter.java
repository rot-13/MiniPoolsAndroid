package com.cpc.minipoolsandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpc.minipoolsandroid.models.Contribution;

import java.util.List;

/**
 * Created by ifeins on 9/13/17.
 */

public class ContributionsListAdapter extends RecyclerView.Adapter<ContributionsListAdapter.ContributionViewHolder> {

    private List<Contribution> mContributions;

    public ContributionsListAdapter(List<Contribution> contributions) {
        mContributions = contributions;
    }

    //<editor-fold desc="RecyclerView.Adapter implementation">
    @Override
    public ContributionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                android.R.layout.simple_list_item_2, parent, false);
        return new ContributionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContributionViewHolder holder, int position) {
        holder.bind(mContributions.get(position));
    }

    @Override
    public int getItemCount() {
        return (mContributions == null ? 0 : mContributions.size());
    }

    public static class ContributionViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitleView;
        private final TextView mSubtitleView;

        public ContributionViewHolder(View itemView) {
            super(itemView);
            mTitleView = (TextView) itemView.findViewById(android.R.id.text1);
            mSubtitleView = (TextView) itemView.findViewById(android.R.id.text2);
        }

        public void bind(Contribution contribution) {
            mTitleView.setText(contribution.contributor.name);
            mSubtitleView.setText(contribution.note);
        }
    }
    //</editor-fold>

}
