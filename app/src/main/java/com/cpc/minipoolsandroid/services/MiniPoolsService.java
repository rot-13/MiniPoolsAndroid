package com.cpc.minipoolsandroid.services;

import com.cpc.minipoolsandroid.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ifeins on 9/11/17.
 */

public class MiniPoolsService {

    private static MiniPoolsService sInstance = new MiniPoolsService();

    private PoolsService mPoolsService;
    private UsersService mUsersService;

    public static MiniPoolsService getInstance() {
        return sInstance;
    }

    private MiniPoolsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPoolsService = retrofit.create(PoolsService.class);
        mUsersService = retrofit.create(UsersService.class);
    }

    public PoolsService getPoolsService() {
        return mPoolsService;
    }

    public UsersService getUsersService() {
        return mUsersService;
    }
}
