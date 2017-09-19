package com.cpc.minipoolsandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpc.minipoolsandroid.R;
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
                R.layout.list_item_contributions, parent, false);
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

        private final TextView mContributorView;
        private final TextView mNoteView;
        private final TextView mAmountView;

        public ContributionViewHolder(View itemView) {
            super(itemView);
            mContributorView = (TextView) itemView.findViewById(R.id.text_contributor);
            mNoteView = (TextView) itemView.findViewById(R.id.text_note);
            mAmountView = (TextView) itemView.findViewById(R.id.text_amount);
        }

        public void bind(Contribution contribution) {
            mContributorView.setText(contribution.contributor.name);
            mNoteView.setText(contribution.note);
            mAmountView.setText(mAmountView.getContext().getString(R.string.contribution_amount, contribution.amountValue / 100.0));
        }
    }
    //</editor-fold>

    public void setContributions(List<Contribution> contributions) {
        mContributions = contributions;
        notifyDataSetChanged();
    }

}
