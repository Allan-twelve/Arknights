package com.allan.arknight;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class FourPicturesActivity extends AppCompatActivity {

    private List<SixPicturesActivity.RecyclerData> dataList1 = new ArrayList<>();

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
        SixPicturesActivity.RecyclerAdapter1 adapter = new SixPicturesActivity.RecyclerAdapter1(dataList1);
        v.setAdapter(adapter);
    }

    private void init(){
        for (int i = 0; i < fourStar.length; i++) {
            SixPicturesActivity.RecyclerPIC i1 = new SixPicturesActivity.RecyclerPIC(four_url[i]);
            SixPicturesActivity.RecyclerPIC i2 = new SixPicturesActivity.RecyclerPIC(four_url2[i]);
            List<SixPicturesActivity.RecyclerPIC> dataList_i = new ArrayList<>();
            dataList_i.add(i1);
            dataList_i.add(i2);
            SixPicturesActivity.RecyclerAdapter2 adapter2 = new SixPicturesActivity.RecyclerAdapter2(dataList_i);
            SixPicturesActivity.RecyclerData item = new SixPicturesActivity.RecyclerData(fourStar[i],  adapter2, four_voice[i]);
            dataList1.add(item);
        }
    }

    private final String[] fourStar = {
            // 53
            // 常驻
            // 43
            "安比尔", "梅", "红云", "桃金娘", "苏苏洛",
            "格雷伊", "猎蜂", "阿消", "地灵", "深海色",
            "古米", "蛇屠箱", "角峰", "调香师", "末药",
            "暗索", "砾", "慕斯", "霜叶", "缠丸",
            "杜宾", "红豆", "清道夫", "白雪", "流星",
            "杰西卡", "远山", "夜烟", "宴", "刻刀",
            "波登可", "卡达", "孑", "酸糖", "芳汀",
            "泡泡", "杰克", "松果", "豆苗", "深靛",
            "罗比菈塔", "褐果", "铅踝",
            // 非寻访
            // 10
            "艾丝黛尔", "清流", "断罪者", "嘉维尔", "坚雷",
            "讯使", "伊桑", "布丁", "罗小黑", "石英",
    };

    private final int[] four_voice = {
            R.raw.anbier_report_voice,
            R.raw.mei_report_voice,
            R.raw.hongyun_report_voice,
            R.raw.taojinniang_report_voice,
            R.raw.susuluo_report_voice,
            R.raw.geleiyi_report_voice,
            R.raw.liefeng_report_voice,
            R.raw.axiao_report_voice,
            R.raw.diling_report_voice,
            R.raw.shenhaise_report_voice,
            R.raw.gumi_report_voice,
            R.raw.shetuxiang_report_voice,
            R.raw.jiaofeng_report_voice,
            R.raw.diaoxiangshi_report_voice,
            R.raw.moyao_report_voice,
            R.raw.ansuo_report_voice,
            R.raw.li_report_voice,
            R.raw.musi_report_voice,
            R.raw.shuangye_report_voice,
            R.raw.chanwan_report_voice,
            R.raw.dubin_report_voice,
            R.raw.hongdou_report_voice,
            R.raw.qingdaofu_report_voice,
            R.raw.baixue_report_voice,
            R.raw.liuxing_report_voice,
            R.raw.jiexika_report_voice,
            R.raw.yuanshan_report_voice,
            R.raw.yeyan_report_voice,
            R.raw.yan_report_voice,
            R.raw.kedao_report_voice,
            R.raw.bodengke_report_voice,
            R.raw.kada_report_voice,
            R.raw.jie_report_voice,
            R.raw.suantang_report_voice,
            R.raw.fangting_report_voice,
            R.raw.paopao_report_voice,
            R.raw.jieke_report_voice,
            R.raw.songguo_report_voice,
            R.raw.doumiao_report_voice,
            R.raw.shendian_report_voice,
            R.raw.luobilata_report_voice,
            R.raw.heguo_report_voice,
            R.raw.qianhuai_report_voice,
            //
            R.raw.aisidaier_report_voice,
            R.raw.qingliu_report_voice,
            R.raw.duanzuizhe_report_voice,
            R.raw.jiaweier_report_voice,
            R.raw.jianlei_report_voice,
            R.raw.xunshi_report_voice,
            R.raw.yisang_report_voice,
            R.raw.buding_report_voice,
            R.raw.luoxiaohei_report_voice,
            R.raw.shiying_report_voice,
    };

    private static String[] four_url = {
            "https://prts.wiki/images/3/3e/%E7%AB%8B%E7%BB%98_%E5%AE%89%E6%AF%94%E5%B0%94_1.png",
            "https://prts.wiki/images/a/a4/%E7%AB%8B%E7%BB%98_%E6%A2%85_1.png",
            "https://prts.wiki/images/3/3d/%E7%AB%8B%E7%BB%98_%E7%BA%A2%E4%BA%91_1.png",
            "https://prts.wiki/images/4/42/%E7%AB%8B%E7%BB%98_%E6%A1%83%E9%87%91%E5%A8%98_1.png",
            "https://prts.wiki/images/1/1c/%E7%AB%8B%E7%BB%98_%E8%8B%8F%E8%8B%8F%E6%B4%9B_1.png",
            "https://prts.wiki/images/9/93/%E7%AB%8B%E7%BB%98_%E6%A0%BC%E9%9B%B7%E4%BC%8A_1.png",
            "https://prts.wiki/images/f/f8/%E7%AB%8B%E7%BB%98_%E7%8C%8E%E8%9C%82_1.png",
            "https://prts.wiki/images/c/c6/%E7%AB%8B%E7%BB%98_%E9%98%BF%E6%B6%88_1.png",
            "https://prts.wiki/images/1/15/%E7%AB%8B%E7%BB%98_%E5%9C%B0%E7%81%B5_1.png",
            "https://prts.wiki/images/5/52/%E7%AB%8B%E7%BB%98_%E6%B7%B1%E6%B5%B7%E8%89%B2_1.png",
            "https://prts.wiki/images/1/16/%E7%AB%8B%E7%BB%98_%E5%8F%A4%E7%B1%B3_1.png",
            "https://prts.wiki/images/c/c7/%E7%AB%8B%E7%BB%98_%E8%9B%87%E5%B1%A0%E7%AE%B1_1.png",
            "https://prts.wiki/images/6/6c/%E7%AB%8B%E7%BB%98_%E8%A7%92%E5%B3%B0_1.png",
            "https://prts.wiki/images/5/5c/%E7%AB%8B%E7%BB%98_%E8%B0%83%E9%A6%99%E5%B8%88_1.png",
            "https://prts.wiki/images/e/e4/%E7%AB%8B%E7%BB%98_%E6%9C%AB%E8%8D%AF_1.png",
            "https://prts.wiki/images/0/00/%E7%AB%8B%E7%BB%98_%E6%9A%97%E7%B4%A2_1.png",
            "https://prts.wiki/images/3/38/%E7%AB%8B%E7%BB%98_%E7%A0%BE_1.png",
            "https://prts.wiki/images/c/c5/%E7%AB%8B%E7%BB%98_%E6%85%95%E6%96%AF_1.png",
            "https://prts.wiki/images/5/50/%E7%AB%8B%E7%BB%98_%E9%9C%9C%E5%8F%B6_1.png",
            "https://prts.wiki/images/1/11/%E7%AB%8B%E7%BB%98_%E7%BC%A0%E4%B8%B8_1.png",
            "https://prts.wiki/images/2/25/%E7%AB%8B%E7%BB%98_%E6%9D%9C%E5%AE%BE_1.png",
            "https://prts.wiki/images/7/70/%E7%AB%8B%E7%BB%98_%E7%BA%A2%E8%B1%86_1.png",
            "https://prts.wiki/images/3/3a/%E7%AB%8B%E7%BB%98_%E6%B8%85%E9%81%93%E5%A4%AB_1.png",
            "https://prts.wiki/images/1/10/%E7%AB%8B%E7%BB%98_%E7%99%BD%E9%9B%AA_1.png",
            "https://prts.wiki/images/b/be/%E7%AB%8B%E7%BB%98_%E6%B5%81%E6%98%9F_1.png",
            "https://prts.wiki/images/9/96/%E7%AB%8B%E7%BB%98_%E6%9D%B0%E8%A5%BF%E5%8D%A1_1.png",
            "https://prts.wiki/images/4/4a/%E7%AB%8B%E7%BB%98_%E8%BF%9C%E5%B1%B1_1.png",
            "https://prts.wiki/images/a/a0/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E7%83%9F_1.png",
            "https://prts.wiki/images/d/de/%E7%AB%8B%E7%BB%98_%E5%AE%B4_1.png",
            "https://prts.wiki/images/5/51/%E7%AB%8B%E7%BB%98_%E5%88%BB%E5%88%80_1.png",
            "https://prts.wiki/images/5/56/%E7%AB%8B%E7%BB%98_%E6%B3%A2%E7%99%BB%E5%8F%AF_1.png",
            "https://prts.wiki/images/1/1a/%E7%AB%8B%E7%BB%98_%E5%8D%A1%E8%BE%BE_1.png",
            "https://prts.wiki/images/3/37/%E7%AB%8B%E7%BB%98_%E5%AD%91_1.png",
            "https://prts.wiki/images/b/bd/%E7%AB%8B%E7%BB%98_%E9%85%B8%E7%B3%96_1.png",
            "https://prts.wiki/images/6/6c/%E7%AB%8B%E7%BB%98_%E8%8A%B3%E6%B1%80_1.png",
            "https://prts.wiki/images/3/39/%E7%AB%8B%E7%BB%98_%E6%B3%A1%E6%B3%A1_1.png",
            "https://prts.wiki/images/1/1d/%E7%AB%8B%E7%BB%98_%E6%9D%B0%E5%85%8B_1.png",
            "https://prts.wiki/images/d/de/%E7%AB%8B%E7%BB%98_%E6%9D%BE%E6%9E%9C_1.png",
            "https://prts.wiki/images/4/4b/%E7%AB%8B%E7%BB%98_%E8%B1%86%E8%8B%97_1.png",
            "https://prts.wiki/images/a/a1/%E7%AB%8B%E7%BB%98_%E6%B7%B1%E9%9D%9B_1.png",
            "https://prts.wiki/images/9/9d/%E7%AB%8B%E7%BB%98_%E7%BD%97%E6%AF%94%E8%8F%88%E5%A1%94_1.png",
            "https://prts.wiki/images/0/04/%E7%AB%8B%E7%BB%98_%E8%A4%90%E6%9E%9C_1.png",
            "https://prts.wiki/images/4/42/%E7%AB%8B%E7%BB%98_%E9%93%85%E8%B8%9D_1.png",
            //
            "https://prts.wiki/images/a/a3/%E7%AB%8B%E7%BB%98_%E8%89%BE%E4%B8%9D%E9%BB%9B%E5%B0%94_1.png",
            "https://prts.wiki/images/f/f3/%E7%AB%8B%E7%BB%98_%E6%B8%85%E6%B5%81_1.png",
            "https://prts.wiki/images/e/e2/%E7%AB%8B%E7%BB%98_%E6%96%AD%E7%BD%AA%E8%80%85_1.png",
            "https://prts.wiki/images/f/f0/%E7%AB%8B%E7%BB%98_%E5%98%89%E7%BB%B4%E5%B0%94_1.png",
            "https://prts.wiki/images/9/9c/%E7%AB%8B%E7%BB%98_%E5%9D%9A%E9%9B%B7_1.png",
            "https://prts.wiki/images/1/16/%E7%AB%8B%E7%BB%98_%E8%AE%AF%E4%BD%BF_1.png",
            "https://prts.wiki/images/e/e0/%E7%AB%8B%E7%BB%98_%E4%BC%8A%E6%A1%91_1.png",
            "https://prts.wiki/images/3/30/%E7%AB%8B%E7%BB%98_%E5%B8%83%E4%B8%81_1.png",
            "https://prts.wiki/images/5/5b/%E7%AB%8B%E7%BB%98_%E7%BD%97%E5%B0%8F%E9%BB%91_1.png",
            "https://prts.wiki/images/d/da/%E7%AB%8B%E7%BB%98_%E7%9F%B3%E8%8B%B1_1.png",
    };

    private static String[] four_url2 = {
            "https://prts.wiki/images/4/4e/%E7%AB%8B%E7%BB%98_%E5%AE%89%E6%AF%94%E5%B0%94_2.png",
            "https://prts.wiki/images/8/86/%E7%AB%8B%E7%BB%98_%E6%A2%85_2.png",
            "https://prts.wiki/images/d/d9/%E7%AB%8B%E7%BB%98_%E7%BA%A2%E4%BA%91_2.png",
            "https://prts.wiki/images/d/dc/%E7%AB%8B%E7%BB%98_%E6%A1%83%E9%87%91%E5%A8%98_2.png",
            "https://prts.wiki/images/6/6e/%E7%AB%8B%E7%BB%98_%E8%8B%8F%E8%8B%8F%E6%B4%9B_2.png",
            "https://prts.wiki/images/5/56/%E7%AB%8B%E7%BB%98_%E6%A0%BC%E9%9B%B7%E4%BC%8A_2.png",
            "https://prts.wiki/images/d/de/%E7%AB%8B%E7%BB%98_%E7%8C%8E%E8%9C%82_2.png",
            "https://prts.wiki/images/e/e1/%E7%AB%8B%E7%BB%98_%E9%98%BF%E6%B6%88_2.png",
            "https://prts.wiki/images/4/46/%E7%AB%8B%E7%BB%98_%E5%9C%B0%E7%81%B5_2.png",
            "https://prts.wiki/images/7/7c/%E7%AB%8B%E7%BB%98_%E6%B7%B1%E6%B5%B7%E8%89%B2_2.png",
            "https://prts.wiki/images/1/1f/%E7%AB%8B%E7%BB%98_%E5%8F%A4%E7%B1%B3_2.png",
            "https://prts.wiki/images/e/eb/%E7%AB%8B%E7%BB%98_%E8%9B%87%E5%B1%A0%E7%AE%B1_2.png",
            "https://prts.wiki/images/4/47/%E7%AB%8B%E7%BB%98_%E8%A7%92%E5%B3%B0_2.png",
            "https://prts.wiki/images/b/b0/%E7%AB%8B%E7%BB%98_%E8%B0%83%E9%A6%99%E5%B8%88_2.png",
            "https://prts.wiki/images/8/86/%E7%AB%8B%E7%BB%98_%E6%9C%AB%E8%8D%AF_2.png",
            "https://prts.wiki/images/5/57/%E7%AB%8B%E7%BB%98_%E6%9A%97%E7%B4%A2_2.png",
            "https://prts.wiki/images/6/62/%E7%AB%8B%E7%BB%98_%E7%A0%BE_2.png",
            "https://prts.wiki/images/d/d3/%E7%AB%8B%E7%BB%98_%E6%85%95%E6%96%AF_2.png",
            "https://prts.wiki/images/7/7a/%E7%AB%8B%E7%BB%98_%E9%9C%9C%E5%8F%B6_2.png",
            "https://prts.wiki/images/2/22/%E7%AB%8B%E7%BB%98_%E7%BC%A0%E4%B8%B8_2.png",
            "https://prts.wiki/images/e/e8/%E7%AB%8B%E7%BB%98_%E6%9D%9C%E5%AE%BE_2.png",
            "https://prts.wiki/images/2/26/%E7%AB%8B%E7%BB%98_%E7%BA%A2%E8%B1%86_2.png",
            "https://prts.wiki/images/d/d8/%E7%AB%8B%E7%BB%98_%E6%B8%85%E9%81%93%E5%A4%AB_2.png",
            "https://prts.wiki/images/4/40/%E7%AB%8B%E7%BB%98_%E7%99%BD%E9%9B%AA_2.png",
            "https://prts.wiki/images/b/ba/%E7%AB%8B%E7%BB%98_%E6%B5%81%E6%98%9F_2.png",
            "https://prts.wiki/images/3/3b/%E7%AB%8B%E7%BB%98_%E6%9D%B0%E8%A5%BF%E5%8D%A1_2.png",
            "https://prts.wiki/images/f/f3/%E7%AB%8B%E7%BB%98_%E8%BF%9C%E5%B1%B1_2.png",
            "https://prts.wiki/images/0/08/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E7%83%9F_2.png",
            "https://prts.wiki/images/0/05/%E7%AB%8B%E7%BB%98_%E5%AE%B4_2.png",
            "https://prts.wiki/images/e/e8/%E7%AB%8B%E7%BB%98_%E5%88%BB%E5%88%80_2.png",
            "https://prts.wiki/images/2/2c/%E7%AB%8B%E7%BB%98_%E6%B3%A2%E7%99%BB%E5%8F%AF_2.png",
            "https://prts.wiki/images/b/b8/%E7%AB%8B%E7%BB%98_%E5%8D%A1%E8%BE%BE_2.png",
            "https://prts.wiki/images/a/a3/%E7%AB%8B%E7%BB%98_%E5%AD%91_2.png",
            "https://prts.wiki/images/2/2e/%E7%AB%8B%E7%BB%98_%E9%85%B8%E7%B3%96_2.png",
            "https://prts.wiki/images/e/ee/%E7%AB%8B%E7%BB%98_%E8%8A%B3%E6%B1%80_2.png",
            "https://prts.wiki/images/4/49/%E7%AB%8B%E7%BB%98_%E6%B3%A1%E6%B3%A1_2.png",
            "https://prts.wiki/images/0/0e/%E7%AB%8B%E7%BB%98_%E6%9D%B0%E5%85%8B_2.png",
            "https://prts.wiki/images/2/24/%E7%AB%8B%E7%BB%98_%E6%9D%BE%E6%9E%9C_2.png",
            "https://prts.wiki/images/8/87/%E7%AB%8B%E7%BB%98_%E8%B1%86%E8%8B%97_2.png",
            "https://prts.wiki/images/0/09/%E7%AB%8B%E7%BB%98_%E6%B7%B1%E9%9D%9B_2.png",
            "https://prts.wiki/images/c/c4/%E7%AB%8B%E7%BB%98_%E7%BD%97%E6%AF%94%E8%8F%88%E5%A1%94_2.png",
            "https://prts.wiki/images/1/1b/%E7%AB%8B%E7%BB%98_%E8%A4%90%E6%9E%9C_2.png",
            "https://prts.wiki/images/c/c7/%E7%AB%8B%E7%BB%98_%E9%93%85%E8%B8%9D_2.png",
            //
            "https://prts.wiki/images/e/e8/%E7%AB%8B%E7%BB%98_%E8%89%BE%E4%B8%9D%E9%BB%9B%E5%B0%94_2.png",
            "https://prts.wiki/images/4/48/%E7%AB%8B%E7%BB%98_%E6%B8%85%E6%B5%81_2.png",
            "https://prts.wiki/images/d/d9/%E7%AB%8B%E7%BB%98_%E6%96%AD%E7%BD%AA%E8%80%85_2.png",
            "https://prts.wiki/images/3/3a/%E7%AB%8B%E7%BB%98_%E5%98%89%E7%BB%B4%E5%B0%94_2.png",
            "https://prts.wiki/images/9/98/%E7%AB%8B%E7%BB%98_%E5%9D%9A%E9%9B%B7_2.png",
            "https://prts.wiki/images/c/cf/%E7%AB%8B%E7%BB%98_%E8%AE%AF%E4%BD%BF_2.png",
            "https://prts.wiki/images/b/b6/%E7%AB%8B%E7%BB%98_%E4%BC%8A%E6%A1%91_2.png",
            "https://prts.wiki/images/e/ed/%E7%AB%8B%E7%BB%98_%E5%B8%83%E4%B8%81_2.png",
            "https://prts.wiki/images/8/88/%E7%AB%8B%E7%BB%98_%E7%BD%97%E5%B0%8F%E9%BB%91_2.png",
            "https://prts.wiki/images/7/78/%E7%AB%8B%E7%BB%98_%E7%9F%B3%E8%8B%B1_2.png",
    };
}
