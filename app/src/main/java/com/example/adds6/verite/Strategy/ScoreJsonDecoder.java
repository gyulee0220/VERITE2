package com.example.adds6.verite.Strategy;

import com.example.adds6.verite.List.UserInfoStruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.Attributes;

import static com.example.adds6.verite.Strategy.UserJsonDecoder.tmpUser;

/**
 * Created by adds6 on 2017-12-10.
 */

public class ScoreJsonDecoder implements JsonDecoder {

    public static ArrayList<Integer> userScore = new ArrayList<>();

    @Override
    public ArrayList JsonDecoding(String userscore) {

        try{
            JSONObject Jobject = new JSONObject(userscore);
            JSONArray Jarray = Jobject.getJSONArray("result");
            JSONObject tmpjson = Jarray.getJSONObject(0);

            for(int i=1;i<=20;i++){

                String is = "Theme"+ String.valueOf(i)+"_Score";

                int tmpScore = tmpjson.getInt(is);

                userScore.add(tmpScore);

            }
        }
        catch (JSONException e){
            System.out.println("Json error");
        }

        return userScore;

    }
}
