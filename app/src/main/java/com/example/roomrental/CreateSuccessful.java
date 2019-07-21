package com.example.roomrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.roomrental.startup.LoginActivity;

import static java.lang.Thread.sleep;

public class CreateSuccessful extends AppCompatActivity {
    private int SLEEP_TIMER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

        setContentView(R.layout.activity_create_successful);
        
    }

    private class LogoLauncher {
        public void start() {
            try{
                sleep(1000 * SLEEP_TIMER);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(CreateSuccessful.this, LoginActivity.class);
            startActivity(intent);
            CreateSuccessful.this.finish();
        }
    }
}
