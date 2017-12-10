package com.example.adds6.verite.Newsfeed;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by adds6 on 2017-12-10.
 */

public class ColumnAdapter {

    public NewsfeedActivity newsfeedActivity = new NewsfeedActivity();

    public static ArrayList<Integer> userScore = new ArrayList<>();
    public static ArrayList<Integer> calUserScore = new ArrayList<>();

    // 각 테마의 확률을 저장하는 변수
    public static int[] selectColumn = new int[10];

    public void Init(NewsfeedActivity na, ArrayList<Integer> arr){
        newsfeedActivity = na;
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
        for(int i = 0;i<10;i++) {
            ColumnLoader columnLoader = new ColumnLoader();
            columnLoader.Init(newsfeedActivity,i);
            columnLoader.execute("http://13.125.66.109/Columnload.php?id="+selectColumn[i]);
        }


    }

}
