package com.example.uipracticefigma.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uipracticefigma.R;

public class LoginPage extends AppCompatActivity {
    TextView termsTextView, loginTextView, registerTextView;
    EditText usernameTF, passwordTF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        termsTextView = findViewById(R.id.terms);

        String termsText = "By signing in you are agreeing to our Term and privacy policy";
        SpannableString spannableStringTerms = new SpannableString(termsText);

        // Apply blue color to "Term"
        spannableStringTerms.setSpan(new ForegroundColorSpan(Color.BLUE), 38, 61, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        termsTextView.setText(spannableStringTerms);
        registerTextView = findViewById(R.id.registerTextView);
        loginTextView = findViewById(R.id.loginTextView);
        passwordTF = findViewById(R.id.passwordTF);
        usernameTF = findViewById(R.id.usernameTF);

        String email = "darwin@gmail.com";
        String password = "123456";
        String text = "Login";
        SpannableString spannableStringLogin = new SpannableString(text);

// Apply underline
        spannableStringLogin.setSpan(new UnderlineSpan(), 0, text.length(), 0);

        loginTextView.setText(spannableStringLogin);

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this,RegisterPage.class);
                if(usernameTF.getText().toString().equals(email) && passwordTF.getText().toString().equals(password)){
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in_1000, R.anim.fade_out_200);
                    finish();
                }
            }
        });

        passwordTF.setOnTouchListener(new View.OnTouchListener() {
            final int DRAWABLE_RIGHT = 2;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordTF.getRight() - passwordTF.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        togglePasswordVisibility(passwordTF);
                        toggleEyeIcon(passwordTF);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void togglePasswordVisibility(EditText editText) {
        // Toggle password visibility
        int inputType = editText.getInputType();
        if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        editText.setSelection(editText.getText().length()); // Keep cursor at the end of the text
    }
    private void toggleEyeIcon(EditText editText) {
        Drawable[] drawables = editText.getCompoundDrawables();
        if (editText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Show closed eye icon
            drawables[2] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_eyes);
        } else {
            // Show open eye icon
            drawables[2] = ContextCompat.getDrawable(getApplicationContext(), R.drawable.scaled_ic_close_eyes);
        }
        editText.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

}