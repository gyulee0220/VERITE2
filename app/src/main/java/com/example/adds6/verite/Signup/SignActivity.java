package com.example.adds6.verite.Signup;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.example.adds6.verite.R;

/**
 * Created by adds6 on 2017-12-07.
 */

public class SignActivity extends Activity {

    // EditText 객체 선언
    EditText editSignEmail;
    EditText editSignName;
    EditText editSignPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        // 버튼 객체 선언
        Button SignupButton = findViewById(R.id.signbutton);

        // 사용자 정보 입력 UI와 연결하기
        editSignEmail = findViewById(R.id.signupedit1);
        editSignName = findViewById(R.id.signupedit2);
        editSignPassword = findViewById(R.id.signupedit3);


    }
}
