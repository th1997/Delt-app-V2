package com.deltapp;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginApp extends Activity {

    private static final String TAG = "LoginApp";
    private FirebaseAuth mAuth;

    //Value : 129	Text that is a password. Corresponds to InputType.TYPE_CLASS_TEXT(1) | InputType.TYPE_TEXT_VARIATION_PASSWORD(128).
    private final int TEXT_PASSWORD_VALUE = TYPE_TEXT_VARIATION_PASSWORD + TYPE_CLASS_TEXT;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText emailLog = findViewById(R.id.emailLogin);
        EditText passwdLog = findViewById(R.id.pwdLogin);
        Button btnConnect = findViewById(R.id.btnConnect);
        Button btnSubs = findViewById(R.id.subsButton);

        btnConnect.setOnClickListener(v -> {
            String userLoginEmail = emailLog.getText().toString();
            String userLoginPassword = passwdLog.getText().toString();
            signIn(userLoginEmail, userLoginPassword);
        });

        btnSubs.setOnClickListener(v -> {
            Intent myIntent = new Intent(LoginApp.this, RegisterApp.class);
            LoginApp.this.startActivity(myIntent);
        });

        passwdLog.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwdLog.getRight() - passwdLog.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    //
                    if (passwdLog.getInputType() == TEXT_PASSWORD_VALUE)
                        passwdLog.setInputType(TYPE_CLASS_TEXT);
                    else
                        passwdLog.setInputType(TEXT_PASSWORD_VALUE);
                    return true;
                }
            }
            return false;
        });
    }

    protected void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginApp.this, "Connecté", Toast.LENGTH_LONG).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginApp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
