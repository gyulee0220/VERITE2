package com.example.adds6.verite.Strategy;

import com.example.adds6.verite.List.UserInfoStruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by adds6 on 2017-12-07.
 */

public class UserJsonDecoder implements JsonDecoder {

    static UserInfoStruct tmpUser;

    @Override
    public ArrayList JsonDecoding(String userinfo) {

        // 사용자 정보를 담고 있는 함수
        ArrayList<UserInfoStruct> userlist = new ArrayList<>();

        try{
            JSONObject Jobject = new JSONObject(userinfo);
            JSONArray Jarray = Jobject.getJSONArray("result");

            for(int i=0;i<Jarray.length();i++){
                // 제이슨 오브젝트 임시 생성
                JSONObject tmpJuser = Jarray.getJSONObject(i);

                // 변수로 변환
                String UserEmail = tmpJuser.getString("User_Email");
                int UserNum = tmpJuser.getInt("User_Num");
                String UserPw = tmpJuser.getString("User_password");
                String UserName = tmpJuser.getString("User_Name");

                // 사용자 구조체에 저장하기 시작
                tmpUser = new UserInfoStruct(UserEmail, UserNum, UserPw, UserName);

                userlist.add(tmpUser);
            }
        }
        catch (JSONException e){
            System.out.println("Json error");
        }

        return userlist;

    }
}
