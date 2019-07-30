package com.example.roomrental.room;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.roomrental.R;
import com.example.roomrental.models.Room;
import com.example.roomrental.utils.FilePaths;
import com.example.roomrental.utils.ImageManager;
import com.example.roomrental.utils.Permissions;
import com.example.roomrental.utils.UniversalImageLoader;
import com.example.roomrental.utils.VerifyPermissions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;



public class RoomAddActivity extends AppCompatActivity {
    private static final String TAG = "RoomAddActivity";

    private static final int VERIFY_PERMISSION_REQUEST = 100;
    private Context mContext = RoomAddActivity.this;

    private TextInputEditText name, desc, price, no_of_rooms, location , owner_name , contact_no;
    private ImageView image;
    private Button btn_save;

    private String s_name, s_desc, s_price, s_no_of_rooms, s_location, image_link , s_owner_name , s_contact_no;
    private boolean valid;

    private FirebaseHelper mFirebaseHelper;
    private ProgressDialog dialog;
    private String keyId;
    private int mPhotoUploadProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_add);

        mFirebaseHelper = new FirebaseHelper(mContext);
        ImageView main_image = findViewById(R.id.main_image);
        UniversalImageLoader.setImage(FilePaths.IMAGE_URI, main_image, null, "");

        setupToolbar();
        setupWidgets();
        setupProgressDialog();
        initData();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Room");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupProgressDialog() {
        dialog = new ProgressDialog(mContext); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
    }

    private void initData() {
        image_link = "";
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyPermissions verifyPermissions = new VerifyPermissions(mContext, RoomAddActivity.this);
                if (verifyPermissions.checkPermissionsArray(Permissions.PERMISSIONS)) {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, VERIFY_PERMISSION_REQUEST);
                } else {
                    verifyPermissions.verifyPermissionsArray(Permissions.PERMISSIONS);
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_name = name.getText().toString();
                s_desc = desc.getText().toString();
                s_location = location.getText().toString();
                s_price = price.getText().toString();
                s_no_of_rooms = no_of_rooms.getText().toString();
                s_owner_name = owner_name.getText().toString();
                s_contact_no = contact_no.getText().toString();

                if (!validateForm()) {
                    Toast.makeText(mContext, "All fields are not set correctly", Toast.LENGTH_SHORT).show();
                } else {
                    savePictureToStorage();
                }
            }
        });
    }

    private boolean validateForm() {
        valid = true;

        if (s_name.isEmpty()) {
            name.setError(mContext.getString(R.string.empty_field));
            valid = false;
        }
        if (s_price.isEmpty()) {
            price.setError(mContext.getString(R.string.empty_field));
            valid = false;
        }
        if (image_link.isEmpty()) {
            Toast.makeText(mContext, "Set an image", Toast.LENGTH_SHORT).show();
            valid = false;
        }


        return valid;
    }

    private void addRoomToDatabase(Uri downloadUrl) {



        Room post = new Room();
        post.setCategory_name("Room");
        post.setId(keyId);
        post.setName(s_name);
        post.setDesc(s_desc);
        post.setLocation(s_location);
        post.setImage(downloadUrl.toString());
        post.setPrice(Integer.parseInt(s_price));
        post.setNo_of_rooms(Integer.parseInt(s_no_of_rooms));
        post.setOwner_name(s_owner_name);
        post.setContact_no(Long.parseLong(s_contact_no));
        post.setOwnerRules("This is owner rules part");



     //   Hotel post = new Hotel(
       //         keyId,
         //       s_name,
           //     s_desc,
             //   s_location,
               // downloadUrl.toString(),
     //           FilePaths.HOTEL,
       //         s_price,
         //       s_discoumt,
           //     hotelServices,
             //   "This is owner rules part"


        showProgressDialog();
        assert keyId != null;
        mFirebaseHelper.getMyRef().child(FilePaths.ROOM)
                .child(keyId)
                .setValue(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        hideProgressDialog();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(mContext, mContext.getString(R.string.error_general), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }
        });
    }

    private void savePictureToStorage() {
        keyId = mFirebaseHelper.getMyRef().child(FilePaths.ROOM).push().getKey();
        final StorageReference storageReference = mFirebaseHelper.getmStorageReference()
                .child(FilePaths.ROOM + "/" + "room" + keyId);
        InputStream is = null;
        try {
            is = mContext.getContentResolver().openInputStream(Uri.parse(image_link));

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            byte[] data = ImageManager.getBytesFromBitmap(bitmap, 60);
            UploadTask uploadTask = storageReference.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    Toast.makeText(mContext, "Image upload failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                    if (progress - 30 > mPhotoUploadProgress) {
                        Toast.makeText(mContext, "Photo upload progress: " + String.format("%.0f", progress), Toast.LENGTH_SHORT).show();
                    }
                    Log.d(TAG, "onProgress: progress: " + String.format("%.0f", progress) + " % done!");

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    Task<Uri> firebaseUrl = storageReference.getDownloadUrl();
                    while (!firebaseUrl.isSuccessful()) ;

                    Uri downloadUrl = firebaseUrl.getResult();

                    SharedPreferenceHelper sharedPreferenceHelper = SharedPreferenceHelper.getInstance(mContext);
                    sharedPreferenceHelper.setAvatar(downloadUrl.toString());
                    Toast.makeText(mContext, "Upload success", Toast.LENGTH_SHORT).show();

                    addRoomToDatabase(downloadUrl);


                }
            });
        } catch (FileNotFoundException e) {
            //            e.printStackTrace();
            Toast.makeText(mContext, "File not found.", Toast.LENGTH_SHORT).show();
        } finally {

            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                //                e.printStackTrace();
                Toast.makeText(mContext, "Exception while closing file", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void updateRoomImageUrl(Uri downloadUrl) {
        mFirebaseHelper.getMyRef().child(FilePaths.ROOM)
                .child(keyId)
                .child("image")
                .setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                hideProgressDialog();
            }
        });
    }


    private void hideProgressDialog() {
        dialog.hide();
    }

    private void showProgressDialog() {
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            image_link = targetUri.toString();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void setupWidgets() {
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        location = findViewById(R.id.location);
        image = findViewById(R.id.image);
        price = findViewById(R.id.price);
        no_of_rooms = findViewById(R.id.no_of_rooms);
        btn_save = findViewById(R.id.btn_save);
        owner_name = findViewById(R.id.owner_name);
        contact_no = findViewById(R.id.contact_no);
    }
}
