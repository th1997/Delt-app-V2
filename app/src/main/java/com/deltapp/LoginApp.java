package com.deltapp;

import android.app.Activity;
import android.os.Bundle;

public class LoginApp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*Button btnConnect = findViewById(R.id.btnLogin);
        Typeface font = Typeface.createFromAsset(getAssets(),"font\\arefruqaa_regular.ttf");
        btnConnect.setTypeface(font);*/
    }
}
