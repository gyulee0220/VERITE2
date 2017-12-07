package com.example.adds6.verite.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.adds6.verite.Main_Activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by adds6 on 2017-12-06.
 */

public class UserAdapter extends AsyncTask<String, String, String> {

    // SQL 참조 결과 노출
    protected String ResultJson;

    // 메인 액티비티를 담을 수 있는 변수
    private static Main_Activity main_activity;

    public void Init(Main_Activity ma){
        this.main_activity = ma;
    }

    public String doInBackground(String...urls) {
        ResultJson = new String();
        BufferedReader bufferedReader;

        try {

            // 앞에서 생성한 URL 객체에 대해 url 값을 전달
            URL url = new URL(urls[0]);

            // Http URL 과 연결하기 위한 객체 생성 코드
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn != null) {
                //연결된 코드가 들어오면

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    System.out.println("두번째 이프문 실행 됨???");
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    int i = 0 ;
                    for(;;){
                        //웹상에 보이는 텍스트를 라인단위로 읽어 저장
                        String line = bufferedReader.readLine();
                        if(line == null) {
                            System.out.println("End of file :" + i);
                            break;
                        }
                        System.out.println("Success read php : "+line);
                        i++;
                        ResultJson += line;
                    }
                    bufferedReader.close();
                }
                conn.disconnect();
            } else {
                System.out.println("Fail to connect php");
            }
        } catch (Exception e) {
            return null;
        }

        return ResultJson;

    }

    public void onPostExecute(String result){

        System.out.println("외부"+result);

       // 다시 메인으로 돌아가는 메소드
        main_activity.setUserInfo(result);
        main_activity.startLoginActivity();
    }
}
