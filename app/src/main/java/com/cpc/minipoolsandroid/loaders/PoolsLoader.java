package com.cpc.minipoolsandroid.loaders;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import com.cpc.minipoolsandroid.models.Pool;
import com.cpc.minipoolsandroid.services.MiniPoolsService;

import java.io.IOException;
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

    private String mErrorMessage;

    public PoolsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        mErrorMessage = null;
        Call<List<Pool>> poolsCall = MiniPoolsService.getInstance().getPoolsService().getPools();
        Log.i(LOG_TAG, "Fetching pools");
        poolsCall.enqueue(new Callback<List<Pool>>() {
            @Override
            public void onResponse(Call<List<Pool>> call, Response<List<Pool>> response) {
                if (response.isSuccessful()) {
                    deliverResult(response.body());
                } else {
                    deliverResult(null);
                    try {
                        mErrorMessage = response.errorBody().string();
                    } catch (IOException e) {
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pool>> call, Throwable t) {
                Log.e(LOG_TAG, "Could not fetch pools", t);
                mErrorMessage = t.getMessage();
                deliverResult(null);
            }
        });
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    @Override
    protected void onForceLoad() {
        startLoading();
    }
}
