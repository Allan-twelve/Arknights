package com.allan.arknight;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ThreePicturesActivity extends AppCompatActivity {

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
        for (int i = 0; i < threeStar.length; i++) {
            OnePicturesActivity.RecyclerData item = new OnePicturesActivity.RecyclerData(threeStar[i], three_voice[i], three_url[i]);
            dataList.add(item);
        }
    }

    private final String[] threeStar = {
            "芬", "炎熔", "月见夜", "香草", "史都华德", "卡缇", "米格鲁", "斑点", "空爆", "梓兰", "芙蓉",
            "克洛丝", "玫兰莎", "翎羽", "泡普卡", "安赛尔",
            // 非寻访
            "安德切尔"
    };

    private final int[] three_voice = {
            R.raw.fen_report_voice,
            R.raw.yanrong_report_voice,
            R.raw.yuejianye_report_voice,
            R.raw.xiangcao_report_voice,
            R.raw.shidouhuade_report_voice,
            R.raw.kati_report_voice,
            R.raw.migelu_report_voice,
            R.raw.bandian_report_voice,
            R.raw.kongbao_report_voice,
            R.raw.zilan_report_voice,
            R.raw.furong_report_voice,
            R.raw.keluosi_report_voice,
            R.raw.meilansha_report_voice,
            R.raw.lingyu_report_voice,
            R.raw.paopuka_report_voice,
            R.raw.ansaier_report_voice,
            R.raw.andeqieer_report_voice,
    };

    private static String[] three_url = {
            "https://prts.wiki/images/a/af/%E7%AB%8B%E7%BB%98_%E8%8A%AC_1.png",
            "https://prts.wiki/images/8/80/%E7%AB%8B%E7%BB%98_%E7%82%8E%E7%86%94_1.png",
            "https://prts.wiki/images/0/02/%E7%AB%8B%E7%BB%98_%E6%9C%88%E8%A7%81%E5%A4%9C_1.png",
            "https://prts.wiki/images/a/a0/%E7%AB%8B%E7%BB%98_%E9%A6%99%E8%8D%89_1.png",
            "https://prts.wiki/images/4/44/%E7%AB%8B%E7%BB%98_%E5%8F%B2%E9%83%BD%E5%8D%8E%E5%BE%B7_1.png",
            "https://prts.wiki/images/f/f6/%E7%AB%8B%E7%BB%98_%E5%8D%A1%E7%BC%87_1.png",
            "https://prts.wiki/images/4/44/%E7%AB%8B%E7%BB%98_%E7%B1%B3%E6%A0%BC%E9%B2%81_1.png",
            "https://prts.wiki/images/8/8a/%E7%AB%8B%E7%BB%98_%E6%96%91%E7%82%B9_1.png",
            "https://prts.wiki/images/8/87/%E7%AB%8B%E7%BB%98_%E7%A9%BA%E7%88%86_1.png",
            "https://prts.wiki/images/b/bb/%E7%AB%8B%E7%BB%98_%E6%A2%93%E5%85%B0_1.png",
            "https://prts.wiki/images/b/b9/%E7%AB%8B%E7%BB%98_%E8%8A%99%E8%93%89_1.png",
            "https://prts.wiki/images/b/ba/%E7%AB%8B%E7%BB%98_%E5%85%8B%E6%B4%9B%E4%B8%9D_1.png",
            "https://prts.wiki/images/0/09/%E7%AB%8B%E7%BB%98_%E7%8E%AB%E5%85%B0%E8%8E%8E_1.png",
            "https://prts.wiki/images/8/84/%E7%AB%8B%E7%BB%98_%E7%BF%8E%E7%BE%BD_1.png",
            "https://prts.wiki/images/5/5d/%E7%AB%8B%E7%BB%98_%E6%B3%A1%E6%99%AE%E5%8D%A1_1.png",
            "https://prts.wiki/images/e/e4/%E7%AB%8B%E7%BB%98_%E5%AE%89%E8%B5%9B%E5%B0%94_1.png",
            "https://prts.wiki/images/9/94/%E7%AB%8B%E7%BB%98_%E5%AE%89%E5%BE%B7%E5%88%87%E5%B0%94_1.png",
    };
}
