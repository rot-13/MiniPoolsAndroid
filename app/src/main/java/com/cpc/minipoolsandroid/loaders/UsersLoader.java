package com.cpc.minipoolsandroid.loaders;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import com.cpc.minipoolsandroid.models.User;
import com.cpc.minipoolsandroid.services.MiniPoolsService;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ifeins on 9/11/17.
 */

public class UsersLoader extends Loader<List<User>> {

    private static final String LOG_TAG = UsersLoader.class.getSimpleName();

    public UsersLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Call<List<User>> usersCall = MiniPoolsService.getInstance().getUsersService().getUsers();
        Log.i(LOG_TAG, "Fetching users");
        usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                deliverResult(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(LOG_TAG, "Could not fetch users", t);
                deliverResult(Collections.<User>emptyList());
            }
        });
    }

    @Override
    protected void onForceLoad() {
        startLoading();
    }
}
