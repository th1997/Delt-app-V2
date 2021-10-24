package com.deltapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterApp extends Activity {
    private static final String TAG = "RegisterApp";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText emailReg = (EditText) findViewById(R.id.emailRegister);
        EditText passwdReg = (EditText) findViewById(R.id.pwdRegister);
        EditText passwdConfirmReg = (EditText) findViewById(R.id.pwdConfRegister);
        Button btnSubscribe = (Button) findViewById(R.id.btnSubs);

        btnSubscribe.setOnClickListener(v -> {
            String userLoginEmail = emailReg.getText().toString();
            String userLoginPassword = passwdReg.getText().toString();
            String userPasswdConfirmation = passwdConfirmReg.getText().toString();
            createAccount(userLoginEmail, userLoginPassword, userPasswdConfirmation);
        });
    }

    protected void createAccount(String email, String password, String Confirmation) {
        if (password.equals(Confirmation)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
