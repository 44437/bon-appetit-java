package com.bonappetit.usgchallenge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

public class introActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean internetConnection = new connectionControl().internetConnection(introActivity.this);
                if (internetConnection) {
                    Intent i = new Intent(introActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {


                    AlertDialog.Builder ad = new AlertDialog.Builder(introActivity.this);
                    ad.setMessage("Please connect to the internet.");
                    ad.setTitle("Network Error");
                    ad.setIcon(R.drawable.ic_baseline_error_24);
                    ad.setCancelable(false);
                    ad.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            run();
                        }
                    });
                    ad.setNegativeButton("Exıt", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    ad.create().show();
                }
            }
        }, 3000);

    }

}
