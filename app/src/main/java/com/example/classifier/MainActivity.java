package com.example.classifier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    ImageView google_img;
    EditText etPassword = findViewById(R.id.password_login);

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google_img = findViewById(R.id.google);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        gsc = GoogleSignIn.getClient(this,gso);

        google_img.setOnClickListener(view -> SignIn());

        etPassword.setTransformationMethod(new PasswordTransformationMethod()); // Hide password initially

        CheckBox checkBoxShowPwd = findViewById(R.id.checkBox_login);
        checkBoxShowPwd.setText(getString(R.string.show_password_checkboxLogin)); // Hide initially, but prompting "Show Password"
        checkBoxShowPwd.setOnCheckedChangeListener((arg5, isChecked) -> {
            // Prompting "Show Password"
            if (isChecked) {
                etPassword.setTransformationMethod(null); // Show password when box checked
            } else {
                etPassword.setTransformationMethod(new PasswordTransformationMethod()); // Hide password when box not checked
            }
            checkBoxShowPwd.setText(getString(R.string.show_password_checkboxLogin)); // Prompting "Hide Password"
        });
    }

    private void SignIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                homeActivity();
            } catch (ApiException e) {
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void homeActivity() {

        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }
}