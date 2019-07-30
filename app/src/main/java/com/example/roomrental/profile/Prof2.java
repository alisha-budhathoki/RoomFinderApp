package com.example.roomrental.profile;

//package com.example.roomrental.Upload;

//import android.Manifest;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.roomrental.R;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//
//import java.io.IOException;
//import java.util.UUID;
//
//public class UploadActivity extends AppCompatActivity {
//    private TextView textView;
//
//
//    private static final int PICK_IMAGE_REQUEST = 71;
//    private Uri filePath;
//    private Button btnChoose, btnUpload;
//    private ImageView imageView;
//    //Firebase
//    FirebaseStorage storage;
//    StorageReference storageReference;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.actvity_upload);
//        imageView = findViewById(R.id.image);
//        btnChoose = findViewById(R.id.btnChoose);
//        Button button = findViewById(R.id.button);
//        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadFile();
//            }
//        });
//        btnChoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chooseImage();
//            }
//        });
//
//        permissionCheck();
//
//    }
//
//    private void permissionCheck() {
//        Dexter.withActivity(this)
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        Toast.makeText(UploadActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//                        Toast.makeText(UploadActivity.this, "Permission denied!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
//                }).check();
//    }
//
//    private void chooseImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//            filePath = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void uploadFile() {
//        textView = findViewById(R.id.textview);
//        if (filePath != null) {
//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();
//
//            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
//            ref.putFile(filePath)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressDialog.dismiss();
//                            Toast.makeText(UploadActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            progressDialog.dismiss();
//                            Toast.makeText(UploadActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
//                                    .getTotalByteCount());
//                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
//                        }
//                    });
//        }
//    }
//}
//
//
//
