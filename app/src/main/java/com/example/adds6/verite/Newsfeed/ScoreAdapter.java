package com.example.adds6.verite.Newsfeed;

import android.os.AsyncTask;

import com.example.adds6.verite.Strategy.ScoreJsonDecoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by adds6 on 2017-12-10.
 */

public class ScoreAdapter extends AsyncTask<String, String, String> {

    protected String Resultphp;

    public NewsfeedActivity newsfeedActivity = new NewsfeedActivity();

    public static ArrayList<Integer> userScore = new ArrayList<>();

    public void Init(NewsfeedActivity na){
        newsfeedActivity = na;
    }

    public String doInBackground(String...urls){
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

    public void onPostExecute(String result){

        System.out.println("외부"+result);

        // 사용자 점수를 저장하는 변수
        ScoreJsonDecoder scoreJsonDecoder = new ScoreJsonDecoder();
        userScore = scoreJsonDecoder.JsonDecoding(result);

        // 칼럼을 불러오는 어댑터 호출
        ColumnAdapter columnAdapter = new ColumnAdapter();
        columnAdapter.Init(newsfeedActivity,userScore);

    }

}
