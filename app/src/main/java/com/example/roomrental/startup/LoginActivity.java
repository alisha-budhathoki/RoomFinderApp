package com.example.roomrental.startup;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.example.roomrental.HomeActivity;
import com.example.roomrental.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText email, pwd;
    public Button signbtn;
    public TextView crtacnt;
    private TextView frgtpwd;

    //Firebase

    private FirebaseAuth auth;
    private AssistStructure.ViewNode inputEmail;
    private AssistStructure.ViewNode inputPassword;
    private ProgressBar progressBar;
    //   private Object User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
  if (auth.getCurrentUser() != null) {
 startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
  }
setContentView(R.layout.activity_login);
  email = findViewById(R.id.login_email);
        pwd = findViewById(R.id.login_Password);
        signbtn = findViewById(R.id.login_sinup_button);
        crtacnt = findViewById(R.id.login_create_acc);
        frgtpwd = findViewById(R.id.login_acc_fgt_pwd);

        auth = FirebaseAuth.getInstance();




        frgtpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Forget.class));
            }
        });

        crtacnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, CreateActivity.class));
            }
        });
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // email.getText().toString(), pwd.getText().toString());
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    //if (pwd.length() < 6) {
                                      //  inputPassword.getString(R.string.minimum_password);
                                    //}
                                    // else {
                                    //Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    //}
                                }
                                else {
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }



    private void validate(String userName, String userPassword) {
       auth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
          @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()) {

                   Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                  checkEmailVerification();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = auth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        if(emailflag) {
            finish();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
        else{
            Toast.makeText(this,"Verify your email", Toast.LENGTH_SHORT);
            auth.signOut();
        }
    }
}
