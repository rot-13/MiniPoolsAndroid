package com.cpc.minipoolsandroid.loaders;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import com.cpc.minipoolsandroid.models.Pool;
import com.cpc.minipoolsandroid.services.MiniPoolsService;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ifeins on 9/11/17.
 */

public class PoolsLoader extends Loader<List<Pool>> {

    private static final String LOG_TAG = PoolsLoader.class.getSimpleName();

    public PoolsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Call<List<Pool>> poolsCall = MiniPoolsService.getInstance().getPoolsService().getPools();
        Log.i(LOG_TAG, "Fetching pools");
        poolsCall.enqueue(new Callback<List<Pool>>() {
            @Override
            public void onResponse(Call<List<Pool>> call, Response<List<Pool>> response) {
                deliverResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Pool>> call, Throwable t) {
                Log.e(LOG_TAG, "Could not fetch pools", t);
                deliverResult(Collections.<Pool>emptyList());
            }
        });
    }

    @Override
    protected void onForceLoad() {
        startLoading();
    }
}
