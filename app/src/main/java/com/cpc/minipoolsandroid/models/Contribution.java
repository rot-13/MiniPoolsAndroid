package com.cpc.minipoolsandroid.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by ifeins on 9/13/17.
 */

public class Contribution implements Parcelable {

    private static final String TAG = "Contribution";

    public int amountValue;
    public String amountCurrency;
    public String note;
    public JSONObject extra;
    public Date createdAt;
    public Date updatedAt;
    public int poolId;
    public User contributor;
    public int contributorId;

    public Contribution() {
    }

    //<editor-fold desc="Parcelable implementation">
    protected Contribution(Parcel in) {
        amountValue = in.readInt();
        amountCurrency = in.readString();
        note = in.readString();

        String jsonStr = in.readString();
        try {
            extra = (jsonStr != null ? new JSONObject(jsonStr) : null);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        createdAt = (Date) in.readSerializable();
        updatedAt = (Date) in.readSerializable();
        poolId = in.readInt();
        contributor = in.readParcelable(User.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(amountValue);
        dest.writeString(amountCurrency);
        dest.writeString(note);
        dest.writeString(extra != null ? extra.toString() : null);
        dest.writeSerializable(createdAt);
        dest.writeSerializable(updatedAt);
        dest.writeInt(poolId);
        dest.writeParcelable(contributor, flags);
    }

    public static final Creator<Contribution> CREATOR = new Creator<Contribution>() {
        @Override
        public Contribution createFromParcel(Parcel in) {
            return new Contribution(in);
        }

        @Override
        public Contribution[] newArray(int size) {
            return new Contribution[size];
        }
    };
    //</editor-fold>

    public static class Builder {
        private Contribution mContribution = new Contribution();

        public Contribution build() {
            Contribution result = mContribution;
            mContribution = new Contribution();

            return result;
        }

        public Builder withAmount(int amountValue, String amountCurrency) {
            mContribution.amountValue = amountValue;
            mContribution.amountCurrency = amountCurrency;
            return this;
        }

        public Builder withNote(String note) {
            mContribution.note = note;
            return this;
        }

        public Builder withContributor(User contributor) {
            mContribution.contributor = contributor;
            mContribution.contributorId = contributor.id;
            return this;
        }
    }
}
