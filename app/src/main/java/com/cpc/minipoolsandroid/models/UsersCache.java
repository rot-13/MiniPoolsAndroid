package com.cpc.minipoolsandroid.models;

import java.util.ArrayList;

/**
 * Created by ifeins on 9/14/17.
 */

public class UsersCache {

    private static UsersCache sInstance = new UsersCache();

    private ArrayList<User> mUsers;

    public static UsersCache getInstance() {
        return sInstance;
    }

    private UsersCache() {
    }

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public void setUsers(ArrayList<User> users) {
        mUsers = users;
    }


}
