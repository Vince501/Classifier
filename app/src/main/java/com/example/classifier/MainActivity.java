package com.example.classifier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etPassword = (EditText) findViewById(R.id.password_login);

        etPassword.setTransformationMethod(new PasswordTransformationMethod()); // Hide password initially

        CheckBox checkBoxShowPwd = (CheckBox) findViewById(R.id.checkBox_login);
        checkBoxShowPwd.setText(getString(R.string.show_password_checkboxLogin)); // Hide initially, but prompting "Show Password"
        checkBoxShowPwd.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg5, boolean isChecked) {
                // Prompting "Show Password"
                if (isChecked) {
                    etPassword.setTransformationMethod(null); // Show password when box checked
                } else {
                    etPassword.setTransformationMethod(new PasswordTransformationMethod()); // Hide password when box not checked
                }
                checkBoxShowPwd.setText(getString(R.string.show_password_checkboxLogin)); // Prompting "Hide Password"
            }
        } );
    }
}