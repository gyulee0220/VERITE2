package com.example.adds6.verite.Newsfeed;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.adds6.verite.List.ColumnInfoStruct;
import com.example.adds6.verite.R;

import java.util.ArrayList;

/**
 * Created by adds6 on 2017-12-10.
 */

public class FeedUILoader extends Activity {

    Activity activity;
    Context NA;
    ColumnInfoStruct columnInfo;
    static int time;
    public static ArrayList<ColumnInfoStruct> arrColumn = new ArrayList<>();

    // UI 가져오기
    public FeedUILoader(Context NA, ColumnInfoStruct a, int time){
        this.columnInfo = a;
        this.time = time;
        this.NA = NA;

    }

    public void OnLoadUI(){
        activity = (Activity) NA;
        String feed = "feed" + String.valueOf(time+1);
        int feedIndex = 11111;
        View feedview = null;
        Button button1 = null;
        try{
            feedIndex = activity.getResources().getIdentifier(feed,"id",getPackageName());
            feedview = (View) findViewById(feedIndex);
            System.out.println("kddkdkdkdkd");
            button1 = feedview.findViewById(R.id.Feed_Agency);
        } catch (Exception e){
            e.printStackTrace();
        }

        SettingAgency settingAgency = new SettingAgency();
        String Agency = settingAgency.settingAgency(columnInfo.Column_Agency);
        button1.setText(Agency);

    }

}
