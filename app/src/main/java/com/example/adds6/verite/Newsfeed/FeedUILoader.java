package com.example.adds6.verite.Newsfeed;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.adds6.verite.List.ColumnInfoStruct;
import com.example.adds6.verite.R;

/**
 * Created by adds6 on 2017-12-10.
 */

public class FeedUILoader extends Activity {

    NewsfeedActivity newsfeedActivity = new NewsfeedActivity();
    ColumnInfoStruct columnInfo;
    static int time;

    // UI 가져오기

    public FeedUILoader(NewsfeedActivity na, int time, ColumnInfoStruct cis){
        this.newsfeedActivity = na;
        this.time = time;
        this.columnInfo = cis;
    }

    public void OnLoadUI(){
        String feed = "feed" + String.valueOf(time+1);
        int feedIndex = getResources().getIdentifier(feed,"id",getPackageName());
        View feedview = (View) findViewById(feedIndex);

        Button button1 = feedview.findViewById(R.id.Feed_Agency);
        SettingAgency settingAgency = new SettingAgency();
        String Agency = settingAgency.settingAgency(columnInfo.Column_Agency);
        button1.setText(Agency);
    }

}
