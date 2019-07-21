package com.example.roomrental;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.widget.TableLayout;

import com.example.roomrental.utils.Permission;
import com.example.roomrental.utils.SectionsPagerAdapter;
public class Upload extends AppCompatActivity {
    private static final String TAG ="Upload" ;
    private static final int VERIFY_PERMISSION_REQUEST = 1;
    private ViewPager mViewPager;
    //private MyAdapter adapter;
    private SectionsPagerAdapter SectionsPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        if (checkPermissionArray(Permission.PERMISSIONS)){
            setupViewPager();

        }else {
            verifyPermission(Permission.PERMISSIONS);
        }
    }
    /*
    return the current tab number
    *Gallery Fragment
    * Photo Fragment
     */
    int getCurrentTabNumber(){
        return mViewPager.getCurrentItem();
    }
    /*
    *setu viewpager for manager the tabs
     */
    private void setupViewPager(){
       SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GalleryFragment());
        adapter.addFragment(new PhotoFragment());
        mViewPager =(ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsBottom);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setText(getString(R.string.gallery));
        tabLayout.getTabAt(1).setText(getString(R.string.photo));
    }
    public void verifyPermission(String[] permission){
        Log.d(TAG, "verifyPermission:verifying permission.");
        ActivityCompat.requestPermissions(
                Upload.this,
                permission,
                VERIFY_PERMISSION_REQUEST
        );
    }

    public boolean checkPermissionArray(String[] permission){
        Log.d(TAG,"checkPermissionArray: checking permission array.");
        for(int i=0; i<permission.length; i++){
            String check = permission[i];
            if(!checkPermission(check)){
                return false;

            }
        }
        return true;
    }
    public boolean checkPermission(String permission){
        Log.d(TAG,"checkpermission: checking permission:"+ permission);
        int permissionRequest = ActivityCompat.checkSelfPermission(Upload.this,permission);
        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"checkPermission:\n Permission was not granted for:" + permission);
            return false;
        }
        else
        {
            Log.d(TAG,"checkPermission: \n Permission was granted for:" + permission);
            return true;
        }
    }
}

