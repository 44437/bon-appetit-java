package com.bonappetit.usgchallenge;

import android.content.Context;
import android.net.ConnectivityManager;


public class connectionControl {

    public connectionControl() {

    }

    public boolean internetConnection(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() &&
                conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
