package com.cpc.minipoolsandroid.loaders;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import com.cpc.minipoolsandroid.models.Pool;
import com.cpc.minipoolsandroid.services.MiniPoolsService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ifeins on 9/19/17.
 */

public class PoolDetailsLoader extends Loader<Pool> {

    private static final String TAG = "PoolDetailsLoader";

    private final int mPoolId;

    public PoolDetailsLoader(Context context, int poolId) {
        super(context);
        mPoolId = poolId;
    }

    @Override
    protected void onForceLoad() {
        startLoading();
    }

    @Override
    protected void onStartLoading() {
        Call<Pool> call = MiniPoolsService.getInstance().getPoolsService().getPoolDetails(mPoolId);
        call.enqueue(new Callback<Pool>() {
            @Override
            public void onResponse(Call<Pool> call, Response<Pool> response) {
                if (response.isSuccessful()) {
                    deliverResult(response.body());
                } else {
                    deliverResult(null);
                }
            }

            @Override
            public void onFailure(Call<Pool> call, Throwable t) {
                Log.e(TAG, "Could not fetch pool details", t);
                deliverResult(null);
            }
        });
    }
}
