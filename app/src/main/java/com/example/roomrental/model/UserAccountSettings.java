package com.example.roomrental.model;

import android.location.Location;

import org.xml.sax.Locator;

public class UserAccountSettings {
    //private String description;
    //private String display_name;
    //private long followers;
    //private long following;
    private long posts;
    private String profile_photo;
    private String owner;
    private String username;
    private String Location;
    //private String website;

    public UserAccountSettings(String Location, String owner,long posts, String profile_photo, String username) {
        //this.owner = description;
        //this.display_name = display_name;
        //this.followers = followers;
        this.Location= Location;
        this.owner = owner;
        //this.following = following;
        this.posts = posts;
        this.profile_photo = profile_photo;
        this.username = username;
        //this.website = website;
    }
    public UserAccountSettings() {

    }

    //public String getDescription() {
      //  return description;
    //}

    //public void setDescription(String description) {
      //  this.description = description;
    //}

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    //public long getFollowers() {
      //  return followers;
    //}

    //public void setFollowers(long followers) {
      //  this.followers = followers;
    //}

    //public long getFollowing() {
      //  return following;
    //}

    //public void setFollowing(long following) {
      //  this.following = following;
    //}

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //public String getWebsite() {
      //  return website;
    //}

    //public void setWebsite(String website) {
      //  this.website = website;
    //}


    @Override
    public String toString() {
        return "UserAccountSettings{" +
                //"description='" + description + '\'' +
                //", display_name='" + display_name + '\'' +
                //", followers=" + followers +
                //", following=" + following +
               ", owner=" + owner +
        "Location=" + Location +
                ", posts=" + posts +
                ", profile_photo='" + profile_photo + '\'' +
                ", username='" + username + '\'' +
                //", website='" + website + '\'' +
                '}';
    }
}
