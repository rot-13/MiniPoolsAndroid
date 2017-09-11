package com.cpc.minipoolsandroid.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by ifeins on 9/11/17.
 */

public class Pool implements Parcelable {

    private static final String LOG_TAG = Pool.class.getSimpleName();

    public String name;
    public User creator;
    public int goalAmountValue;
    public String goalAmountCurrency;
    public JSONObject extra;
    public Date createdAt;
    public Date updatedAt;


    public Pool() {
    }

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

    //<editor-fold desc="Parcelable">
    protected Pool(Parcel in) {
        name = in.readString();
        creator = in.readParcelable(User.class.getClassLoader());
        goalAmountValue = in.readInt();
        goalAmountCurrency = in.readString();

        String jsonStr = in.readString();
        if (jsonStr != null) {
            try {
                extra = new JSONObject(jsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Could not parcel extra field", e);
            }
        }

        createdAt = (Date) in.readSerializable();
        updatedAt = (Date) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(creator, flags);
        dest.writeInt(goalAmountValue);
        dest.writeString(goalAmountCurrency);
        dest.writeString(extra != null ? extra.toString() : null);
        dest.writeSerializable(createdAt);
        dest.writeSerializable(updatedAt);
    }

    public static final Parcelable.Creator<Pool> CREATOR = new Parcelable.Creator<Pool>() {
        @Override
        public Pool createFromParcel(Parcel in) {
            return new Pool(in);
        }

        @Override
        public Pool[] newArray(int size) {
            return new Pool[size];
        }
    };
    //</editor-fold>
}
