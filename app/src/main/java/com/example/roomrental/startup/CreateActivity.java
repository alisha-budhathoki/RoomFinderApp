package com.example.roomrental.startup;
import android.content.Context;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.roomrental.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.app.ProgressDialog.show;

public class CreateActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private Context mContext;
    private FirebaseAuth firebaseAuth;
    public EditText useremail, password, username, confirmpwd;
    public ProgressDialog progressDialog;
    private ImageView createAccount;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
   // private FirebaseMethods firebaseMethods;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private String append = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mContext = CreateActivity.this;
        //firebaseMethods = new FirebaseMethods(mContext);
        Log.d(TAG, "onCreate: started.");


        setupUIViews();

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_email =useremail.getText().toString().trim();
                    String user_password = password.getText().toString().trim();
                   //  String username = username.getText().toString();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(CreateActivity.this,"Account Created Successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateActivity.this, LoginActivity.class));
                            }
                            else {
                                Toast.makeText(CreateActivity.this,"Account Creation Failed",Toast.LENGTH_SHORT).show();
                            }
                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(CreateActivity.this,
                                        "User with this email already exist.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }

        });
       /*ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("SignUp");*/
    }

  private boolean checkAccountEmailExistInFirebase(String useremail){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final boolean[] b = new boolean[1];
        firebaseAuth.fetchSignInMethodsForEmail(useremail).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                b[0]=!task.getResult().getSignInMethods().isEmpty();
            }
        });
        return b[0];
    }

    private void setupUIViews() {
        useremail = (EditText) findViewById(R.id.create_acc_email_text);
        username = (EditText) findViewById(R.id.create_acc_username_text);
        password = (EditText) findViewById(R.id.create_acc_pwd_text);
        confirmpwd = (EditText) findViewById(R.id.create_acc_conform_pwd_text);
        createAccount= findViewById(R.id.create_acc_arrow);
    }



    private Boolean validate(){
        Boolean res=false;
        String email = useremail.getText().toString();
        String name = username.getText().toString();
        String pwd = password.getText().toString();
        String cpwd = confirmpwd.getText().toString();



        String valid = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(email.matches(valid)){
            res=true;
        }

        if (email.isEmpty() || name.isEmpty() || pwd.isEmpty() || cpwd.isEmpty()) {
            Toast.makeText(getApplicationContext(),"email/name/pwd/cpwd is blank.",Toast.LENGTH_LONG).show();
        } else {
            res=true;
        }
        return res;
    }

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //checkIfUsernameExists(username);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    finish();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    /*@Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/


}

