package com.example.roomrental.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {
    public String id;
    public String name;
    public String desc;
    public String location;
    public String geo_location;
    public String image;
    public String category_name;
    public long contact_no;
    public int discount;
    public long price;
    public String owner_name;
    public int no_of_rooms;
    public boolean is_full_flat;
    public String ownerRules;
    public String date_added;
    public String updated_at;
    public String deleted_at;


    public Room() {
    }

    public Room(String id, String name, String desc, String location, String geo_location, String image, String category_name, long contact_no, int discount, long price, String owner_name, int no_of_rooms, boolean is_full_flat, String ownerRules, String date_added, String updated_at, String deleted_at) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.location = location;
        this.geo_location = geo_location;
        this.image = image;
        this.category_name = category_name;
        this.contact_no = contact_no;
        this.discount = discount;
        this.price = price;
        this.owner_name = owner_name;
        this.no_of_rooms = no_of_rooms;
        this.is_full_flat = is_full_flat;
        this.ownerRules = ownerRules;
        this.date_added = date_added;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    protected Room(Parcel in) {
        id = in.readString();
        name = in.readString();
        desc = in.readString();
        location = in.readString();
        geo_location = in.readString();
        image = in.readString();
        category_name = in.readString();
        contact_no = in.readLong();
        discount = in.readInt();
        price = in.readLong();
        owner_name = in.readString();
        no_of_rooms = in.readInt();
        is_full_flat = in.readByte() != 0;
        ownerRules = in.readString();
        date_added = in.readString();
        updated_at = in.readString();
        deleted_at = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGeo_location() {
        return geo_location;
    }

    public void setGeo_location(String geo_location) {
        this.geo_location = geo_location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public long getContact_no() {
        return contact_no;
    }

    public void setContact_no(long contact_no) {
        this.contact_no = contact_no;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getNo_of_rooms() {
        return no_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        this.no_of_rooms = no_of_rooms;
    }

    public boolean isIs_full_flat() {
        return is_full_flat;
    }

    public void setIs_full_flat(boolean is_full_flat) {
        this.is_full_flat = is_full_flat;
    }

    public String getOwnerRules() {
        return ownerRules;
    }

    public void setOwnerRules(String ownerRules) {
        this.ownerRules = ownerRules;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(location);
        parcel.writeString(geo_location);
        parcel.writeString(image);
        parcel.writeString(category_name);
        parcel.writeLong(contact_no);
        parcel.writeInt(discount);
        parcel.writeLong(price);
        parcel.writeString(owner_name);
        parcel.writeInt(no_of_rooms);
        parcel.writeByte((byte) (is_full_flat ? 1 : 0));
        parcel.writeString(ownerRules);
        parcel.writeString(date_added);
        parcel.writeString(updated_at);
        parcel.writeString(deleted_at);
    }
}
