package com.example.roomrental.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roomrental.R;
import com.example.roomrental.room.FirebaseHelper;
import com.example.roomrental.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Userprofile extends AppCompatActivity {
    Button signOutButton;
    ImageView imgButton;
    TextView userName, userEmail, ratedMovies, avgRating;
    Uri profilePhoto;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static int RESULT_LOAD_IMG = 1;

    private Context mContext = Userprofile.this;


    private FirebaseHelper mFirebaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useractivity);
        mFirebaseHelper = new FirebaseHelper(mContext);
        initImageLoader();
        loadImagefromGallery();


    }
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    private void loadImagefromGallery(){
                imgButton = (ImageView) findViewById(R.id.profile_photo);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMG);
            }
        });
//¤¥¤¥¤asdasjgkkkb


    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_LOAD_IMG && resultCode == Activity.RESULT_OK
//                && null != data) {
//            // Get the Image from data
//            filePath = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }



}
