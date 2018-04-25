package com.vzw.hss.myverizon.a20180424kbsnycschools.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score implements Parcelable {

    @Expose
    @SerializedName("school_name")
    String name;

    @Expose
    @SerializedName("sat_math_avg_score")
    int math;

    @Expose
    @SerializedName("sat_critical_reading_avg_score")
    int reading;

    @Expose
    @SerializedName("sat_writing_avg_score")
    int writing;

    @Expose
    @SerializedName("num_of_sat_test_takers")
    int test_takers;

    protected Score(Parcel in) {
        name = in.readString();
        math = in.readInt();
        reading = in.readInt();
        writing = in.readInt();
        test_takers = in.readInt();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getMath() {
        return math;
    }

    public int getReading() {
        return reading;
    }

    public int getWriting() {
        return writing;
    }

    public int getTest_takers() {
        return test_takers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(math);
        dest.writeInt(reading);
        dest.writeInt(writing);
        dest.writeInt(test_takers);
    }
}
