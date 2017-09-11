package com.cpc.minipoolsandroid.models;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by ifeins on 9/11/17.
 */

public class Pool {

    public String name;
    public int goalAmountValue;
    public String goalAmountCurrency;
    public JSONObject extra;
    public Date createdAt;
    public Date updatedAt;
    public int creatorId;

    public static class Builder {

        Pool mPool = new Pool();

        public Builder withName(String name) {
            mPool.name = name;
            return this;
        }

        public Builder withGoalAmount(int amountValue, String amountCurrency) {
            mPool.goalAmountValue = amountValue;
            mPool.goalAmountCurrency = amountCurrency;
            return this;
        }

        public Pool build() {
            return mPool;
        }
    }
}
