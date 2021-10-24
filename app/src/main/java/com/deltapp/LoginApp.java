package com.deltapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginApp extends Activity {

    private static final String TAG = "LoginApp";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText emailLog = (EditText) findViewById(R.id.emailLogin);
        EditText passwdLog = (EditText) findViewById(R.id.pwdLogin);
        Button btnConnect = (Button) findViewById(R.id.btnConnect);
        Button btnSubs = (Button) findViewById(R.id.subsButton);

        btnConnect.setOnClickListener(v -> {
            String userLoginEmail = emailLog.getText().toString();
            String userLoginPassword = passwdLog.getText().toString();
            signIn(userLoginEmail, userLoginPassword);
        });

        btnSubs.setOnClickListener(v -> {
            Intent myIntent = new Intent(LoginApp.this, RegisterApp.class);
            LoginApp.this.startActivity(myIntent);
        });
    }

    protected void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
