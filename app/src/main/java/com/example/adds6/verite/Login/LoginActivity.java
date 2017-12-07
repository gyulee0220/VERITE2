package com.example.adds6.verite.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.adds6.verite.List.UserInfoStruct;
import com.example.adds6.verite.R;
import com.example.adds6.verite.Signup.SignActivity;
import com.example.adds6.verite.Strategy.JsonDecoder;
import com.example.adds6.verite.Strategy.UserJsonDecoder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by adds6 on 2017-12-06.
 */

public class LoginActivity extends Activity {

    // 사용자 정보 JsonCode
    private static String UserInfo;
    private static int thisUserNum;

    // Decoding 이후 사용자 정보 저장
    static ArrayList<UserInfoStruct> userlist = new ArrayList<>();

    // UI 연결 객체 선언 부분
    EditText editTextID;
    EditText editTextPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        // 메인 액티비티에서 유저 정보를 받아오기
        UserInfo = getUserInfo();

        // 사용자 정보(Json Code) 디코딩 작업
        JsonDecoder jd = new UserJsonDecoder();
        userlist = jd.JsonDecoding(UserInfo);

        // 가입 인텐트 생성
        Intent signupintent;

        // 가입하기 버튼 선언
        Button signupButton = (Button) findViewById(R.id.joinbutton);

        // 가입하기 버튼 리스너 생성
        signupButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupintent = new Intent(LoginActivity.this, SignActivity.class);
                startActivities(new Intent[]{signupintent});
            }
        });


        // 로그인 버튼 선언
        Button loginButton = (Button) findViewById(R.id.loginbutton) ;

        // ID, 비밀번호 입력하는 에딧 텍스트 객체 생성
        editTextID = findViewById(R.id.editText1);
        editTextPW = findViewById(R.id.editText2);

        // 버튼 클릭 리스너 생성
        loginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                String signID = editTextID.getText().toString();
                String signPW = editTextPW.getText().toString();
                thisUserNum = findingUser(signID, signPW);

                if(thisUserNum == -1) System.out.println("없는 사용자입니다.");

                //Intent loginIntent = new Intent(LoginActivity.this, SignActivity.class);
                //startActivities(new Intent[]{loginIntent});

            }
        });
    }

    // 사용자 정보를 받아오는 메소드
    public String getUserInfo(){
        Intent intent = getIntent();
        return intent.getExtras().getString("UserInfo");
    }

    // 유저리스트에서 사용자 정보를 찾아내는 메소드
    public int findingUser(String id, String password){
        System.out.println("ID:"+id+"  PW:"+password);
        // 사용자 ID를 이용해서 사용자 정보를 찾아낸다
        for(int i =0; i<userlist.size();i++){
            UserInfoStruct tmpuser = userlist.get(i);

            // id와 password 비교하기
            if((tmpuser.UserEmail.equals(id))&&(tmpuser.UserPassword.equals(password))){
                System.out.println("일치 성공");
                return tmpuser.UserNumber;
            }
        }

        return -1;
    }

}
