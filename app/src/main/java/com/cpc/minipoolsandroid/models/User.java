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

public class User implements Parcelable {

    private static final String TAG = "User";

    public String name;
    public String avatarUrl;
    public JSONObject extra;
    public Date createdAt;
    public Date updatedAt;

    //<editor-fold desc="Parcelable">
    protected User(Parcel in) {
        name = in.readString();
        avatarUrl = in.readString();

        String jsonStr = in.readString();
        if (jsonStr != null) {
            try {
                extra = new JSONObject(jsonStr);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
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
        dest.writeString(avatarUrl);
        dest.writeString(extra != null ? extra.toString() : null);
        dest.writeSerializable(createdAt);
        dest.writeSerializable(updatedAt);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    //</editor-fold>

    @Override
    public String toString() {
        return name;
    }
}
