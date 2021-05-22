package com.bonappetit.usgchallenge;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class starControl {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    starControl() {
    }
    public boolean control(Context context, String id) {
        sharedPreferences = Objects.requireNonNull(context).getSharedPreferences("strControl", MODE_PRIVATE);
        return sharedPreferences.getBoolean(id, false);
    }
    public void addOrRemove(Context context, String id) {
        sharedPreferences = Objects.requireNonNull(context).getSharedPreferences("strControl", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean attached = sharedPreferences.getBoolean(id, false);
        if (attached) {
            editor.putBoolean(id, false);
            editor.apply();
        } else {
            editor.putBoolean(id, true);
            editor.apply();
        }
    }
}
