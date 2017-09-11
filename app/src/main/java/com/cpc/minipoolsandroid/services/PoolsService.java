package com.cpc.minipoolsandroid.services;

import com.cpc.minipoolsandroid.models.Pool;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ifeins on 9/11/17.
 */

public interface PoolsService {

    @GET("pools")
    Call<List<Pool>> getPools();
}
