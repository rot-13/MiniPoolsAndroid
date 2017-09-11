package com.cpc.minipoolsandroid.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ifeins on 9/11/17.
 */

public class MiniPoolsService {

    private static MiniPoolsService sInstance = new MiniPoolsService();

    private PoolsService mPoolsService;

    public static MiniPoolsService getInstance() {
        return sInstance;
    }

    private MiniPoolsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mini-pools.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPoolsService = retrofit.create(PoolsService.class);
    }

    public PoolsService getPoolsService() {
        return mPoolsService;
    }
}