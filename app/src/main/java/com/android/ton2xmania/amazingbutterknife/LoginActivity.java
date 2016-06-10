package com.android.ton2xmania.amazingbutterknife;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    @BindViews({R.id.input_email, R.id.input_password})
    List<TextInputLayout> inputLogin;
    @BindViews({R.id.email, R.id.password})
    List<TextInputEditText> formLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.email_sign_in_button)
    void login() {
        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
    }

    private boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword() {
        if (formLogin.get(1).getText().toString().trim().isEmpty()) {
            inputLogin.get(1).setError(getString(R.string.error_field_required));
            requestFocus(inputLogin.get(1));
            return false;
        } else if (formLogin.get(1).getText().toString().length() < 8) {
            inputLogin.get(1).setError(getString(R.string.error_invalid_password));
            requestFocus(inputLogin.get(1));
            return false;
        } else {
            inputLogin.get(1).setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = formLogin.get(0).getText().toString().trim();

        if (email.isEmpty() || !isEmailValid(email)) {
            inputLogin.get(0).setError(getString(R.string.error_invalid_email));
            requestFocus(inputLogin.get(0));
            return false;
        } else {
            inputLogin.get(0).setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}

