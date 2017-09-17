package com.cpc.minipoolsandroid.services;

import com.cpc.minipoolsandroid.models.Contribution;
import com.cpc.minipoolsandroid.models.Pool;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ifeins on 9/11/17.
 */

public interface PoolsService {

    @GET("pools")
    Call<List<Pool>> getPools();

    @POST("pools/{id}/contributions")
    Call<Contribution> contribute(@Path("id") int poolId, @Body Contribution contribution);
}
