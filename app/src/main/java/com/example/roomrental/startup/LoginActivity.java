package com.example.roomrental.startup;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.example.roomrental.Home.HomeActivity;
import com.example.roomrental.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    String email, password;
   // private EditText emailField, passwordField;
    public Button signbtn;
    public TextView crtacnt;
    private TextView frgtpwd;

    //Firebase

    private FirebaseAuth auth;
    private AssistStructure.ViewNode inputEmail;
    private AssistStructure.ViewNode inputPassword;
    private ProgressBar progressBar;
    private EditText login_email;
    private EditText login_Password;
    //   private Object Room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        login_email = findViewById(R.id.login_email);
        login_Password = findViewById(R.id.login_Password);

        signbtn = findViewById(R.id.login_sinup_button);
        crtacnt = findViewById(R.id.login_create_acc);
        frgtpwd = findViewById(R.id.login_acc_fgt_pwd);

        progressBar = findViewById(R.id.progressBar);

        frgtpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
            }
        });

        crtacnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, CreateActivity.class));
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent signinIntent = new Intent(LoginActivity.this, HomeActivity.class);
            LoginActivity.this.startActivity(signinIntent);
        } else {
            // No user is signed in
        }
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // email.getText().toString(), pwd.getText().toString());
                email = login_email.getText().toString();
                password = login_Password.getText().toString();
                if(validateEmail(email) && validatePassword(password)) {
                    //Toast.makeText(getApplicationContext(), "E:" + email + "P:" + password, Toast.LENGTH_LONG).show();
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = auth.getCurrentUser();
                                        //updateUI(user);
                                        Intent signinIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                        signinIntent.putExtra("email", email);
                                        signinIntent.putExtra("password", password);
                                        LoginActivity.this.startActivity(signinIntent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                    // ...
                                }
                            });
                
    }
                else if (validatePassword(password)) {
                    Toast.makeText(getApplicationContext(), "Password has to be atleast 6 characters", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter Valid Email and Password", Toast.LENGTH_LONG).show();
                }
            }
        });
crtacnt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent signupIntent = new Intent(LoginActivity.this, CreateActivity.class);
        LoginActivity.this.startActivity(signupIntent);

    }
});
    }
    public boolean validateEmail(String email){
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    public boolean validatePassword(String password){
        if(password.length() < 6 )
            return false;
        else
            return true;
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}



