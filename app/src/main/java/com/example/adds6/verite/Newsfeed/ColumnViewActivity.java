package com.example.adds6.verite.Newsfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adds6.verite.List.ColumnInfoStruct;
import com.example.adds6.verite.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by adds6 on 2017-12-11.
 */

public class ColumnViewActivity extends Activity {

    public static ColumnInfoStruct cis;
    ImageButton imgButton;
    URL imageSource;
    TextView title;
    TextView url;
    TextView true_num;
    TextView false_num;
    Button true_click;
    Button false_click;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed_view);

        Intent intent = getIntent();
        cis = (ColumnInfoStruct) intent.getSerializableExtra("Column");

        OnLoadUI();

    }

    public void OnLoadUI(){
        imgButton = findViewById(R.id.thumbnail);

        try {
            if (cis.Column_Image != null) {
                imageSource = new URL(cis.Column_Image);
                Glide.with(this).load(imageSource).into(imgButton);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        title = findViewById(R.id.show_title);
        title.setText(cis.Column_TItle);
        url = findViewById(R.id.show_url);
        url.setText(cis.Column_URL);
        true_num = findViewById(R.id.true_num);
        true_num.setText(String.valueOf(cis.Column_TrueNum));
        false_num = findViewById(R.id.false_num);
        false_num.setText(String.valueOf(cis.Column_FalseNum));

        final Insertdb insertTrue = new Insertdb();
        final Insertdb insertFalse = new Insertdb();

        true_click = findViewById(R.id.true_button);
        true_click.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                insertTrue.execute("http://13.125.66.109/Realupdatetrue.php?id="+cis.Column_ID);
            }
        });

        false_click = findViewById(R.id.true_button);
        false_click.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                insertFalse.execute("http://13.125.66.109/Realupdatefalse.php?id="+cis.Column_ID);
            }
        });

    }

    class Insertdb extends AsyncTask<String, Integer, String> {

        public String doInBackground(String...urls) {
            BufferedReader bufferedReader;
            String ResultCode = null;

            try {

                // 앞에서 생성한 URL 객체에 대해 url 값을 전달
                URL url = new URL(urls[0]);

                // Http URL 과 연결하기 위한 객체 생성 코드
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn != null) {
                    //연결된 코드가 들어오면

                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
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
                            ResultCode += line;
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

            return ResultCode;

        }
    }
}
