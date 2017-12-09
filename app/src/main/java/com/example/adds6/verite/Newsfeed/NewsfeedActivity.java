package com.example.adds6.verite.Newsfeed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adds6.verite.Favorite.FavorityActivity;
import com.example.adds6.verite.List.UserInfoStruct;
import com.example.adds6.verite.R;
import com.example.adds6.verite.Search.SearchActivity;
import com.example.adds6.verite.setting.SettingActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Random;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by adds6 on 2017-12-07.
 */

public class NewsfeedActivity extends Activity {

    protected int UserNum;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed_main);

        // 가입에 성공한 사용자 정보를 받아오는 부분
        Intent intent = getIntent();
        UserNum = intent.getIntExtra("UserNum",0);
        // 삭제 필요
        System.out.println(UserNum);

        // 하단 버튼
        // 하단 바 버튼 선언
        Button ntb_Newsfeed = findViewById(R.id.ntb_Button1);
        Button ntb_favorite = findViewById(R.id.ntb_Button2);
        Button ntb_search = findViewById(R.id.ntb_Button3);
        Button ntb_setting = findViewById(R.id.ntb_Button4);

        // 뉴스피드 리스너
        /*
        ntb_Newsfeed.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NewsfeedActivity.this,NewsfeedActivity.class);
                intent1.putExtra("UserNum",UserNum);
                startActivities(new Intent[]{intent1});
            }
        });
        */
        // 관심글 리스너
        ntb_favorite.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(NewsfeedActivity.this,FavorityActivity.class);
                intent2.putExtra("UserNum",UserNum);
                startActivities(new Intent[]{intent2});
            }
        });
        // 검색 리스너
        ntb_search.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(NewsfeedActivity.this,SearchActivity.class);
                intent3.putExtra("UserNum",UserNum);
                startActivities(new Intent[]{intent3});

            }
        });
        // 세팅 리스너
        ntb_setting.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(NewsfeedActivity.this,SettingActivity.class);
                intent4.putExtra("UserNum",UserNum);
                startActivities(new Intent[]{intent4});

            }
        });


        // 칼럼 가져오기


    }


}


