package com.example.adds6.verite.Newsfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adds6.verite.List.ColumnInfoStruct;
import com.example.adds6.verite.R;

import java.io.Serializable;
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

    }
}
