package com.cpc.minipoolsandroid.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.cpc.minipoolsandroid.models.Contribution;
import com.cpc.minipoolsandroid.services.MiniPoolsService;
import com.cpc.minipoolsandroid.services.PoolsService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ifeins on 9/15/17.
 */

public class AddContributionNetworkFragment extends Fragment {

    public interface Listener {
        void onContributionSucceeded(Contribution contribution);
        void onContributionFailed(String errorMessage);
    }

    private Listener mListener;
    private Contribution mResult;
    private String mErrorMessage;

    public static AddContributionNetworkFragment getInstance(FragmentManager fragmentManager) {
        Fragment fragment =
                fragmentManager.findFragmentByTag(AddContributionNetworkFragment.class.getSimpleName());

        if (fragment == null) {
            fragment = new AddContributionNetworkFragment();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, AddContributionNetworkFragment.class.getSimpleName())
                    .commit();
        }

        return (AddContributionNetworkFragment) fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Listener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void addContribution(int poolId, Contribution contribution) {
        PoolsService poolsService = MiniPoolsService.getInstance().getPoolsService();
        Call<Contribution> call = poolsService.contribute(poolId, contribution);
        mResult = null;
        mErrorMessage = null;
        call.enqueue(new Callback<Contribution>() {
            @Override
            public void onResponse(Call<Contribution> call, Response<Contribution> response) {
                if (response.isSuccessful()) {
                    mResult = response.body();
                    if (mListener != null) {
                        mListener.onContributionSucceeded(mResult);
                    }
                } else {
                    try {
                        mErrorMessage = response.errorBody().string();
                        if (mListener != null) {
                            mListener.onContributionFailed(mErrorMessage);
                        }
                    } catch (IOException e) {
                        mErrorMessage = "Failed to add contribution";
                        if (mListener != null) {
                            mListener.onContributionFailed(mErrorMessage);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Contribution> call, Throwable t) {
                mErrorMessage = t.getMessage();
                if (mListener != null) {
                    mListener.onContributionFailed(mErrorMessage);
                }
            }
        });

    }
}
