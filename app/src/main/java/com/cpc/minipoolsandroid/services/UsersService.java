package com.cpc.minipoolsandroid.services;

import com.cpc.minipoolsandroid.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ifeins on 9/14/17.
 */

public interface UsersService {

    @GET("users")
    Call<List<User>> getUsers();
}
