package com.example.adds6.verite.Newsfeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adds6.verite.List.ColumnInfoStruct;
import com.example.adds6.verite.List.UserInfoStruct;
import com.example.adds6.verite.Login.LoginActivity;
import com.example.adds6.verite.R;

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

    public Context newsfeedActivity;
    public static ArrayList<ColumnInfoStruct> arrColumn = new ArrayList<>();
    public static String Resultphp;

    public static ColumnInfoStruct tmpColumn;

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
    public static ImageButton imgButton;

    //public static boolean running = false;

    static Context v;

    public int time;

    public static NewsfeedActivity LA;

    public void Init(NewsfeedActivity c, int time){
        this.LA = c;
        this.time = time;
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
                        System.out.println("Success final php : "+line);
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
        OnLoadUI();

        v = LA;
        imgButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(v,ColumnViewActivity.class);
                intent1.putExtra("Column", tmpColumn);
                v.startActivities(new Intent[]{intent1});
            }
        });
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

        }
        catch (Exception e){
            System.out.println("Json error");
        }

    }

    public void OnLoadUI(){
        String feed = "feed" + String.valueOf(time+1);
        System.out.println("009877"+feed);
        int feedIndex = 11111;
        View feedview = null;
        Button button1 = null;
        URL imageSource = null;

        TextView TextTrue = null;
        TextView TextFalse = null;
        TextView TextViewNum = null;
        TextView TextDate = null;
        TextView TextTitle = null;
        try{
            feedIndex = LA.getResources().getIdentifier(feed,"id",LA.getPackageName());
            feedview =  LA.findViewById(feedIndex);
            System.out.println("kddkdkdkdkd"+feedIndex);
            button1 = feedview.findViewById(R.id.Feed_Agency);
            imgButton = feedview.findViewById(R.id.Feed_Image);
            if (Column_Image != null) {
                imageSource = new URL(Column_Image);
                Glide.with(LA).load(imageSource).into(imgButton);
            }
            TextTrue = feedview.findViewById(R.id.Feed_true);
            TextFalse = feedview.findViewById(R.id.Feed_false);
            TextViewNum = feedview.findViewById(R.id.Feed_View);
            TextDate = feedview.findViewById(R.id.Feed_Time);
            TextTitle = feedview.findViewById(R.id.Feed_title);
        } catch (Exception e){
            e.printStackTrace();
        }

        SettingAgency settingAgency = new SettingAgency();
        String Agency = settingAgency.settingAgency(Column_Agency);
        button1.setText(Agency);
        try {
            TextTitle.setText(Column_TItle);
            TextTrue.setText(String.valueOf(Column_TrueNum));
            TextFalse.setText(String.valueOf(Column_FalseNum));
            TextViewNum.setText(String.valueOf(Column_ViewNum));
            TextDate.setText(String.valueOf(Column_Date));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
