package com.example.adds6.verite.Newsfeed;

import android.os.AsyncTask;

import com.example.adds6.verite.List.ColumnInfoStruct;
import com.example.adds6.verite.List.UserInfoStruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by adds6 on 2017-12-09.
 */

public class ColumnLoader extends AsyncTask<String, String, String>{

    public NewsfeedActivity newsfeedActivity = new NewsfeedActivity();
    public static int loadTime;
    public static String Resultphp;

    public ColumnInfoStruct tmpColumn;
    // 칼럼 정보를 담는 변수들
    public static int Column_ID;
    public static int Column_Agency;
    public static String Column_TItle;
    public static String Column_Image;
    public static String Column_Date;
    public static int Column_TrueNum;
    public static int Column_FalseNum;
    public static int Column_Theme;
    public static int Column_ViewNum;
    public static String Column_URL;
    public static String Column_Writer;

    public void Init(NewsfeedActivity na, int time){
        this.newsfeedActivity = na;
        loadTime =time;
    }

    @Override
    protected String doInBackground(String... urls) {
        Resultphp = new String();
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
                        Resultphp += line;
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

        return Resultphp;

    }

    @Override
    protected void onPostExecute(String s) {
        JsonDecoding(s);
        System.out.println(loadTime);
        FeedUILoader feedUILoader = new FeedUILoader(newsfeedActivity,loadTime,tmpColumn);
        feedUILoader.OnLoadUI();
    }

    public void JsonDecoding(String userscore) {

        try{
            JSONObject Jobject = new JSONObject(userscore);
            JSONArray Jarray = Jobject.getJSONArray("result");

            JSONObject tmpJuser = Jarray.getJSONObject(0);

                // 변수로 변환
            Column_ID = tmpJuser.getInt("Column_ID");
            Column_Agency = tmpJuser.getInt("Column_Agency");
            Column_TItle = tmpJuser.getString("Column_Title");
            Column_Image = tmpJuser.getString("Column_Image");
            Column_Date = tmpJuser.getString("Column_Date");
            Column_TrueNum = tmpJuser.getInt("Column_TrueNum");
            Column_FalseNum = tmpJuser.getInt("Column_FalseNum");
            Column_Theme = tmpJuser.getInt("Column_Theme");
            Column_ViewNum = tmpJuser.getInt("Column_ViewNum");
            Column_URL = tmpJuser.getString("Column_URL");
            Column_Writer = tmpJuser.getString("Column_Writer");

            // 사용자 구조체에 저장하기 시작
            tmpColumn = new ColumnInfoStruct(Column_ID, Column_Agency, Column_TItle, Column_Image, Column_Date, Column_TrueNum, Column_FalseNum, Column_Theme, Column_ViewNum, Column_URL, Column_Writer);

            System.out.println("FInal"+Column_TItle);

        }
        catch (JSONException e){
            System.out.println("Json error");
        }

    }
}
