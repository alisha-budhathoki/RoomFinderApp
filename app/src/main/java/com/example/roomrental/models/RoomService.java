package com.example.roomrental.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomService implements Parcelable {
    public String name;
    public String desc;
    public String facilities;

    public RoomService() {
    }

    public RoomService(String name, String desc, String facilities) {
        this.name = name;
        this.desc = desc;
        this.facilities = facilities;
    }

    protected RoomService(Parcel in) {
        name = in.readString();
        desc = in.readString();
        facilities = in.readString();
    }

    public static final Creator<RoomService> CREATOR = new Creator<RoomService>() {
        @Override
        public RoomService createFromParcel(Parcel in) {
            return new RoomService(in);
        }

        @Override
        public RoomService[] newArray(int size) {
            return new RoomService[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(facilities);
    }
}
