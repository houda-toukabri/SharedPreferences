package com.houda.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAB = "MainActivity";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditer;

    private EditText mName, mPassword;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.editTextName);
        mPassword = findViewById(R.id.editTextPassword);
        Button btnLogin = findViewById(R.id.buttonLogin);
        mCheckBox = findViewById(R.id.checkBoxRemember);

        mPreferences = getSharedPreferences("com.houda.sharedpreferencestest", Context.MODE_PRIVATE);
        mEditer = mPreferences.edit();

        checkSharedPreferences();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the checkbox preference
                if (mCheckBox.isChecked()) {
                    //set a checkbox when the application starts
                    mEditer.putString(getString(R.string.checkbox), "True");
                    mEditer.commit();

                    //save the name
                    String name = mName.getText().toString();
                    mEditer.putString(getString(R.string.name), name);
                    mEditer.commit();

                    //save the password
                    String password = mPassword.getText().toString();
                    mEditer.putString(getString(R.string.password), password);
                    mEditer.commit();
                } else {
                    //set a checkbox when the application starts
                    mEditer.putString(getString(R.string.checkbox), "False");
                    mEditer.commit();

                    //save the name
                    mEditer.putString(getString(R.string.name), "");
                    mEditer.commit();

                    //save the password
                    mEditer.putString(getString(R.string.password), "");
                    mEditer.commit();
                }

            }
        });
    }

    /*
     * Check the shared preferences and set then accordingly
     */
    private void checkSharedPreferences() {
        String checkbox = mPreferences.getString(getString(R.string.checkbox), "False");
        String name = mPreferences.getString(getString(R.string.name), "");
        String password = mPreferences.getString(getString(R.string.password), "");

        mName.setText(name);
        mPassword.setText(password);

        if (checkbox.equals("True")) {
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }
    }
}