package com.example.adds6.verite.Newsfeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.adds6.verite.List.ColumnInfoStruct;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by adds6 on 2017-12-10.
 */

public class ColumnAdapter extends Activity {

    public static ArrayList<Integer> userScore = new ArrayList<>();
    public static ArrayList<Integer> calUserScore = new ArrayList<>();

    static Context v;

    // 각 테마의 확률을 저장하는 변수
    public static int[] selectColumn = new int[10];

    public static LoadingActivity LA;

    public void Init(LoadingActivity a, ArrayList<Integer> arr){
        LA = a;
        v = a;
        userScore = arr;
        settingScore(userScore);



        columnLoading();
    }

    // 사용자 점수 가공하기
    public void settingScore(ArrayList<Integer> arr){

        for(int i = 0; i<arr.size();i++){
            for(int j = 0; j<arr.get(i);j++){
                calUserScore.add(i);
            }
        }

        Collections.shuffle(calUserScore);

        for(int i = 0;i<10;i++){
            selectColumn[i] = calUserScore.get(i);
        }

    }

    public void columnLoading(){

        Intent intent = new Intent(v, NewsfeedActivity.class);
        intent.putExtra("UserNum",LA.UserNum);
        intent.putExtra("selectColumn",selectColumn);
        v.startActivities(new Intent[]{intent});

    }

}
