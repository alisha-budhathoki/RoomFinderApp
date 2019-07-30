package com.example.roomrental.room;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roomrental.R;
import com.example.roomrental.models.Room;
import com.example.roomrental.utils.UniversalImageLoader;

public class RoomDetailActivity extends AppCompatActivity {

    private Intent in;
    private Context mContext = RoomDetailActivity.this;
    private Room post;
    private TextView roomName, location, price, contact_no, owner_name, no_of_rooms, description, bookmark;
    private ImageView house_pic;
    private Button call_now;

    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        firebaseHelper = new FirebaseHelper(mContext);
        setupWidgets();
        getIncomingIntent();

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupWidgets() {
//        title = findViewById(R.id.title);
//        title.setText(post.getDesc());

        house_pic = findViewById(R.id.house_pic);
        roomName = findViewById(R.id.roomName);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        no_of_rooms = findViewById(R.id.no_of_rooms);
        price = findViewById(R.id.price);
        call_now = findViewById(R.id.call_now);
        contact_no = findViewById(R.id.contact_no);
        owner_name = findViewById(R.id.owner_name);

    }

    private void getIncomingIntent() {
        in = getIntent();

        post = in.getParcelableExtra(mContext.getString(R.string.calling_room_detail));
        UniversalImageLoader.setImage(post.getImage(), house_pic, null, "");
        roomName.setText(post.getName());
        location.setText(post.getLocation());
        description.setText(post.getDesc());
        no_of_rooms.setText(post.getNo_of_rooms() + "");
        location.setText(post.getLocation());
        price.setText(post.getPrice()+"");
        contact_no.setText(post.getContact_no() + "");
        owner_name.setText(post.getOwner_name());

        call_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + post.getContact_no()));
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.delete_menu, menu);
//        menu.findItem(R.id.edit_post_action).setVisible(false);
//        menu.findItem(R.id.delete_post_action).setVisible(false);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

//        //TODO instead of directly deleting post by admin eiher flag the content or send msg of deletion
//        if (SharedPreferenceHelper.getInstance(mContext).getUserType() == UserTypes.ADMIN) {
//            menu.findItem(R.id.delete).setVisible(true);
//        } else {
//            menu.findItem(R.id.delete).setVisible(false);
//        }

        return super.onPrepareOptionsMenu(menu);
    }



    private void confirmDelDialog() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AlertDialog);
//        builder.setTitle("Delete post " + post.getName() + " ?");
//        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                firebaseHelper.getMyRef().child(FilePaths.ROOM).child(post.getId())
//                        .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                dialog.cancel();
//
//
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        builder.show();
    }
}
