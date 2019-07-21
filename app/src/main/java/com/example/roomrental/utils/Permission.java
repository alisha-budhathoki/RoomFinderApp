package com.example.roomrental.utils;

import android.Manifest;

public class Permission {
    public static final String[] PERMISSIONS ={
           android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA

    };
    public static final String[] CAMERA_PERMISSION = {
            android.Manifest.permission.CAMERA
    };
    public static final String[] WRITE_STORAGE_PERMISSION = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    public static final String[] READ_STORAGE_PERMISSION = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
    };
}
