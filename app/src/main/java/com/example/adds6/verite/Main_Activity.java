package com.example.adds6.verite;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adds6.verite.Login.LoginActivity;
import com.example.adds6.verite.Login.Login_Controller;

public class Main_Activity extends AppCompatActivity {

    // 사용자 정보 저장
    private static String UserInfo;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        // 스플래시 화면을 노출 시키기 위한 변수
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);

    }

    private class splashhandler implements Runnable{
        @Override
        public void run() {
            // 로그인 컨트롤러 객체 생성
            Login_Controller loginController = new Login_Controller(Main_Activity.this);

            // 사용자 정보 불러오기
            loginController.loadUserInfo();

            finish();
        }
    }

    public void setUserInfo(String result) {
        this.UserInfo = result;
    }

    public String getUserInfo() {
        return UserInfo;
    }

    public void startLoginActivity() {
        intent = new Intent(Main_Activity.this, LoginActivity.class);
        intent.putExtra("UserInfo",UserInfo);
        startActivities(new Intent[]{this.intent});
    }
}
