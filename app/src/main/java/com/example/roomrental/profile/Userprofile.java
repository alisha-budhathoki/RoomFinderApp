//package com.example.roomrental.profile;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.roomrental.R;
//import com.example.roomrental.room.FirebaseHelper;
//import com.example.roomrental.utils.UniversalImageLoader;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.nostra13.universalimageloader.core.ImageLoader;
//
//import java.io.FileNotFoundException;
//
//public class Userprofile extends AppCompatActivity {
//    Button signOutButton;
//    ImageView imgButton;
//    TextView userName, userEmail, ratedMovies, avgRating;
//    Uri profilePhoto;
//    SwipeRefreshLayout mSwipeRefreshLayout;
//    private static int RESULT_LOAD_IMG = 1;
//    private String image_link;
//    private Context mContext = Userprofile.this;
//    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//
//
//    private FirebaseHelper mFirebaseHelper;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_profile);
//        mFirebaseHelper = new FirebaseHelper(mContext);
//        initImageLoader();
//        loadImagefromGallery();
//        setRetainInstance(true);
//
//
//    }
//
//
//
//
//    private void initImageLoader() {
//        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
//        ImageLoader.getInstance().init(universalImageLoader.getConfig());
//    }
//
//    private void loadImagefromGallery() {
//        imgButton = (ImageView) findViewById(R.id.profile_photo);
//
//        imgButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, RESULT_LOAD_IMG);
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Auto-generated method stub
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            Uri targetUri = data.getData();
//            image_link = targetUri.toString();
//            Bitmap bitmap;
//            try {
//                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
//                imgButton.setImageBitmap(bitmap);
//                FirebaseUser user = mAuth.getInstance().getCurrentUser();
//                //uploadFile();
//                if (targetUri!=null){
//                    final ProgressDialog progressDialog = new ProgressDialog(mContext);
//                    progressDialog.setTitle("Uploading...");
//                    progressDialog.show();
//                    FirebaseStorage database = FirebaseStorage.getInstance();
//                    StorageReference storeref = database.getReference().child("Users/");
//                    StorageReference ref = storeref.child(user.getUid());
//                    ref.putFile(targetUri)
//                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(mContext, "Uploaded", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(mContext, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
//                                            .getTotalByteCount());
//                                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
//                                }
//                            });
//                    imgButton.setImageURI(targetUri);
//
//                }
//                else {
//                    Toast.makeText(this.mContext, "You haven't picked Image",
//                            Toast.LENGTH_LONG).show();
//                }
//
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//}
//
//
//
