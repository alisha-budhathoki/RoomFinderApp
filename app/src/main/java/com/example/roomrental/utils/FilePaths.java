package com.example.roomrental.utils;

import android.os.Environment;

public class FilePaths {
    //*storage/emulated/0**
    public  String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
    public String CAMERA = ROOT_DIR + "DCIM/camera";
    public String PICTURE = ROOT_DIR + "/Pictures";
}
