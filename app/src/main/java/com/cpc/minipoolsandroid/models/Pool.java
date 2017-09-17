package com.cpc.minipoolsandroid.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ifeins on 9/11/17.
 */

public class Pool implements Parcelable {

    private static final String LOG_TAG = Pool.class.getSimpleName();

    public int id;
    public String name;
    public User creator;
    public int goalAmountValue;
    public String goalAmountCurrency;
    public JSONObject extra;
    public Date createdAt;
    public Date updatedAt;
    public List<Contribution> contributions;


    //<editor-fold desc="Parcelable">
    protected Pool(Parcel in) {
        id = in.readInt();
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
        contributions = new ArrayList<>();
        in.readTypedList(contributions, Contribution.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelable(creator, flags);
        dest.writeInt(goalAmountValue);
        dest.writeString(goalAmountCurrency);
        dest.writeString(extra != null ? extra.toString() : null);
        dest.writeSerializable(createdAt);
        dest.writeSerializable(updatedAt);
        dest.writeTypedList(contributions);
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
