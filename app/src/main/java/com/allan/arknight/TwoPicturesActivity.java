package com.allan.arknight;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TwoPicturesActivity extends AppCompatActivity {

    private List<OnePicturesActivity.RecyclerData> dataList = new ArrayList<>();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (TuJianActivity.player != null){
            TuJianActivity.player.stop();
            TuJianActivity.player.release();
            TuJianActivity.player = null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_star_of_person_picture);
        init();
        RecyclerView v = findViewById(R.id.each_star_person_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        v.setLayoutManager(layoutManager);
        OnePicturesActivity.RecyclerAdapter adapter = new OnePicturesActivity.RecyclerAdapter(dataList);
        v.setAdapter(adapter);
    }

    private void init(){
        for (int i = 0; i < twoStar.length; i++) {
            OnePicturesActivity.RecyclerData item = new OnePicturesActivity.RecyclerData(twoStar[i], two_voice[i], two_url[i]);
            dataList.add(item);
        }
    }

    private final String[] twoStar = {
            "12F", "杜林", "巡林者", "黑角", "夜刀",
    };

    private final int[] two_voice = {
            R.raw.shierf_report_voice,
            R.raw.dulin_report_voice,
            R.raw.xunlinzhe_report_voice,
            R.raw.heijiao_report_voice,
            R.raw.yedao_report_voice,
    };

    private final String[] two_url = {
           "https://prts.wiki/images/6/61/%E7%AB%8B%E7%BB%98_12F_1.png",
           "https://prts.wiki/images/f/f7/%E7%AB%8B%E7%BB%98_%E6%9D%9C%E6%9E%97_1.png",
           "https://prts.wiki/images/c/c8/%E7%AB%8B%E7%BB%98_%E5%B7%A1%E6%9E%97%E8%80%85_1.png",
           "https://prts.wiki/images/d/dc/%E7%AB%8B%E7%BB%98_%E9%BB%91%E8%A7%92_1.png",
           "https://prts.wiki/images/a/ad/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E5%88%80_1.png"
    };
}
