package com.allan.arknight;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class FivePicturesActivity extends AppCompatActivity {

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
        for (int i = 0; i < fiveStar.length; i++) {
            SixPicturesActivity.RecyclerPIC i1 = new SixPicturesActivity.RecyclerPIC(five_url[i]);
            SixPicturesActivity.RecyclerPIC i2 = new SixPicturesActivity.RecyclerPIC(five_url2[i]);
            List<SixPicturesActivity.RecyclerPIC> dataList_i = new ArrayList<>();
            dataList_i.add(i1);
            if (fiveStar[i].equals("阿米娅")) {
                SixPicturesActivity.RecyclerPIC i3 = new SixPicturesActivity.RecyclerPIC(ex_five[0]);
                SixPicturesActivity.RecyclerPIC i4 = new SixPicturesActivity.RecyclerPIC(ex_five[1]);
                dataList_i.add(i3);
                dataList_i.add(i2);
                dataList_i.add(i4);
            }
            else
                dataList_i.add(i2);
            SixPicturesActivity.RecyclerAdapter2 adapter2 = new SixPicturesActivity.RecyclerAdapter2(dataList_i);
            SixPicturesActivity.RecyclerData item = new SixPicturesActivity.RecyclerData(fiveStar[i], adapter2, five_voice[i]);
            dataList1.add(item);
        }
    }

    private final String[] ex_five = {
            "https://prts.wiki/images/3/34/%E7%AB%8B%E7%BB%98_%E9%98%BF%E7%B1%B3%E5%A8%85_1%2B.png",
            "https://prts.wiki/images/9/95/%E7%AB%8B%E7%BB%98_%E9%98%BF%E7%B1%B3%E5%A8%85%28%E8%BF%91%E5%8D%AB%29_2.png"
    };

    private final String[] fiveStar = {
            // 98
            // 常驻
            // 64
            "狮蝎", "食铁兽", "蓝毒", "拉普兰德", "幽灵鲨",
            "德克萨斯", "槐琥", "赫默", "红", "白面鸮",
            "空", "吽", "灰喉", "布洛卡", "苇草",
            "送葬人", "星极", "格劳克斯", "诗怀雅", "夜魔",
            "真理", "初雪", "崖心", "守林人", "普罗旺斯",
            "可颂", "雷蛇", "临光", "华法琳", "梅尔",
            "天火", "陨星", "白金", "芙兰卡", "凛冬",
            "惊蛰", "慑砂", "巫恋", "极境", "石棉",
            "月禾", "莱恩哈特", "断崖", "蜜蜡", "贾维",
            "安哲拉", "燧石", "四月", "奥斯塔", "絮雨",
            "卡夫卡", "爱丽丝", "乌有", "熔泉", "赤冬",
            "绮良", "羽毛笔", "桑葚", "灰毫", "蚀清",
            "极光", "夜半", "夏栎", "风丸",
            // 非寻访
            // 34
            "阿米娅", "柏喙", "拜松", "薄绿", "暴行",
            "暴雨", "贝娜", "鞭刃", "格拉尼", "火神",
            "苦艾", "罗宾", "闪击", "霜华", "特米米",
            "图耶", "微风", "稀音", "锡兰", "雪雉",
            "亚叶", "炎客", "炎狱炎熔", "因陀罗", "战车",
            "铸铁", "龙舌兰", "蜜莓", "野鬃", "耶拉",
            "暮落", "九色鹿", "寒芒克洛丝", "见行者",
    };

    private final int[] five_voice = {
            R.raw.shixie_report_voice,
            R.raw.shitieshou_report_voice,
            R.raw.landu_report_voice,
            R.raw.lapulande_report_voice,
            R.raw.youlingsha_report_voice,
            R.raw.dekesasi_report_voice,
            R.raw.huaihu_report_voice,
            R.raw.hemo_report_voice,
            R.raw.hong2_report_voice,
            R.raw.baimianxiao_report_voice,
            R.raw.kong_report_voice,
            R.raw.hong1_report_voice,
            R.raw.huihou_report_voice,
            R.raw.buluoka_report_voice,
            R.raw.weicao_report_voice,
            R.raw.songzangren_report_voice,
            R.raw.xingji_report_voice,
            R.raw.gelaokesi_report_voice,
            R.raw.shihuaiya_report_voice,
            R.raw.yemo_report_voice,
            R.raw.zhenli_report_voice,
            R.raw.chuxue_report_voice,
            R.raw.yaxin_report_voice,
            R.raw.shoulinren_report_voice,
            R.raw.puluowangsi_report_voice,
            R.raw.kesong_report_voice,
            R.raw.leishe_report_voice,
            R.raw.linguang_report_voice,
            R.raw.huafalin_report_voice,
            R.raw.meier_report_voice,
            R.raw.tianhuo_report_voice,
            R.raw.yunxing_report_voice,
            R.raw.baijin_report_voice,
            R.raw.fulanka_report_voice,
            R.raw.lindong_report_voice,
            R.raw.jingzhe_report_voice,
            R.raw.shesha_report_voice,
            R.raw.wulian_report_voice,
            R.raw.jijing_report_voice,
            R.raw.shimian_report_voice,
            R.raw.yuehe_report_voice,
            R.raw.laienhate_report_voice,
            R.raw.duanya_report_voice,
            R.raw.mila_report_voice,
            R.raw.jiawei_report_voice,
            R.raw.anzhela_report_voice,
            R.raw.suishi_report_voice,
            R.raw.siyue_report_voice,
            R.raw.aosita_report_voice,
            R.raw.xuyu_report_voice,
            R.raw.kafuka_report_voice,
            R.raw.ailisi_report_voice,
            R.raw.wuyou_report_voice,
            R.raw.rongquan_report_voice,
            R.raw.chidong_report_voice,
            R.raw.qiliang_report_voice,
            R.raw.yumaobi_report_voice,
            R.raw.sangshen_report_voice,
            R.raw.huihao_report_voice,
            R.raw.shiqing_report_voice,
            R.raw.jiguang_report_voice,
            R.raw.yeban_report_voice,
            R.raw.xiali_report_voice,
            R.raw.fengwan_report_voice,
            //
            R.raw.amiya_report_voice,
            R.raw.baihui_report_voice,
            R.raw.baisong_report_voice,
            R.raw.baolv_report_voice,
            R.raw.baoxing_report_voice,
            R.raw.baoyu_report_voice,
            R.raw.beina_report_voice,
            R.raw.bianren_report_voice,
            R.raw.gelani_report_voice,
            R.raw.huoshen_report_voice,
            R.raw.kuai_report_voice,
            R.raw.luobin_report_voice,
            R.raw.shanji_report_voice,
            R.raw.shuanghua_report_voice,
            R.raw.temimi_report_voice,
            R.raw.tuye_report_voice,
            R.raw.weifeng_report_voice,
            R.raw.xiyin_report_voice,
            R.raw.xilan_report_voice,
            R.raw.xuezhi_report_voice,
            R.raw.yaye_report_voice,
            R.raw.yanke_report_voice,
            R.raw.yanyuyanrong_report_voice,
            R.raw.yintuoluo_report_voice,
            R.raw.zhanche_report_voice,
            R.raw.zhutie_report_voice,
            R.raw.longshelan_report_voice,
            R.raw.mimei_report_voice,
            R.raw.yezong_report_voice,
            R.raw.yela_report_voice,
            R.raw.muluo_report_voice,
            R.raw.jiuselu_report_voice,
            R.raw.hanmangkeluosi_report_voice,
            R.raw.jianxingzhe_report_voice,
    };

    private static final String[] five_url = {
            "https://prts.wiki/images/9/98/%E7%AB%8B%E7%BB%98_%E7%8B%AE%E8%9D%8E_1.png",
            "https://prts.wiki/images/e/ef/%E7%AB%8B%E7%BB%98_%E9%A3%9F%E9%93%81%E5%85%BD_1.png",
            "https://prts.wiki/images/6/66/%E7%AB%8B%E7%BB%98_%E8%93%9D%E6%AF%92_1.png",
            "https://prts.wiki/images/7/75/%E7%AB%8B%E7%BB%98_%E6%8B%89%E6%99%AE%E5%85%B0%E5%BE%B7_1.png",
            "https://prts.wiki/images/5/50/%E7%AB%8B%E7%BB%98_%E5%B9%BD%E7%81%B5%E9%B2%A8_1.png",
            "https://prts.wiki/images/f/fc/%E7%AB%8B%E7%BB%98_%E5%BE%B7%E5%85%8B%E8%90%A8%E6%96%AF_1.png",
            "https://prts.wiki/images/a/ae/%E7%AB%8B%E7%BB%98_%E6%A7%90%E7%90%A5_1.png",
            "https://prts.wiki/images/7/7f/%E7%AB%8B%E7%BB%98_%E8%B5%AB%E9%BB%98_1.png",
            "https://prts.wiki/images/c/c4/%E7%AB%8B%E7%BB%98_%E7%BA%A2_1.png",
            "https://prts.wiki/images/a/ac/%E7%AB%8B%E7%BB%98_%E7%99%BD%E9%9D%A2%E9%B8%AE_1.png",
            "https://prts.wiki/images/f/f0/%E7%AB%8B%E7%BB%98_%E7%A9%BA_1.png",
            "https://prts.wiki/images/e/e4/%E7%AB%8B%E7%BB%98_%E5%90%BD_1.png",
            "https://prts.wiki/images/2/23/%E7%AB%8B%E7%BB%98_%E7%81%B0%E5%96%89_1.png",
            "https://prts.wiki/images/1/1c/%E7%AB%8B%E7%BB%98_%E5%B8%83%E6%B4%9B%E5%8D%A1_1.png",
            "https://prts.wiki/images/0/0a/%E7%AB%8B%E7%BB%98_%E8%8B%87%E8%8D%89_1.png",
            "https://prts.wiki/images/7/76/%E7%AB%8B%E7%BB%98_%E9%80%81%E8%91%AC%E4%BA%BA_1.png",
            "https://prts.wiki/images/b/bb/%E7%AB%8B%E7%BB%98_%E6%98%9F%E6%9E%81_1.png",
            "https://prts.wiki/images/5/52/%E7%AB%8B%E7%BB%98_%E6%A0%BC%E5%8A%B3%E5%85%8B%E6%96%AF_1.png",
            "https://prts.wiki/images/b/bc/%E7%AB%8B%E7%BB%98_%E8%AF%97%E6%80%80%E9%9B%85_1.png",
            "https://prts.wiki/images/5/50/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E9%AD%94_1.png",
            "https://prts.wiki/images/1/19/%E7%AB%8B%E7%BB%98_%E7%9C%9F%E7%90%86_1.png",
            "https://prts.wiki/images/d/de/%E7%AB%8B%E7%BB%98_%E5%88%9D%E9%9B%AA_1.png",
            "https://prts.wiki/images/a/a7/%E7%AB%8B%E7%BB%98_%E5%B4%96%E5%BF%83_1.png",
            "https://prts.wiki/images/1/1f/%E7%AB%8B%E7%BB%98_%E5%AE%88%E6%9E%97%E4%BA%BA_1.png",
            "https://prts.wiki/images/c/c4/%E7%AB%8B%E7%BB%98_%E6%99%AE%E7%BD%97%E6%97%BA%E6%96%AF_1.png",
            "https://prts.wiki/images/6/62/%E7%AB%8B%E7%BB%98_%E5%8F%AF%E9%A2%82_1.png",
            "https://prts.wiki/images/3/39/%E7%AB%8B%E7%BB%98_%E9%9B%B7%E8%9B%87_1.png",
            "https://prts.wiki/images/e/e4/%E7%AB%8B%E7%BB%98_%E4%B8%B4%E5%85%89_1.png",
            "https://prts.wiki/images/e/ee/%E7%AB%8B%E7%BB%98_%E5%8D%8E%E6%B3%95%E7%90%B3_1.png",
            "https://prts.wiki/images/f/f0/%E7%AB%8B%E7%BB%98_%E6%A2%85%E5%B0%94_1.png",
            "https://prts.wiki/images/c/c2/%E7%AB%8B%E7%BB%98_%E5%A4%A9%E7%81%AB_1.png",
            "https://prts.wiki/images/f/f8/%E7%AB%8B%E7%BB%98_%E9%99%A8%E6%98%9F_1.png",
            "https://prts.wiki/images/5/56/%E7%AB%8B%E7%BB%98_%E7%99%BD%E9%87%91_1.png",
            "https://prts.wiki/images/6/6c/%E7%AB%8B%E7%BB%98_%E8%8A%99%E5%85%B0%E5%8D%A1_1.png",
            "https://prts.wiki/images/6/6e/%E7%AB%8B%E7%BB%98_%E5%87%9B%E5%86%AC_1.png",
            "https://prts.wiki/images/9/9f/%E7%AB%8B%E7%BB%98_%E6%83%8A%E8%9B%B0_1.png",
            "https://prts.wiki/images/0/0b/%E7%AB%8B%E7%BB%98_%E6%85%91%E7%A0%82_1.png",
            "https://prts.wiki/images/e/e3/%E7%AB%8B%E7%BB%98_%E5%B7%AB%E6%81%8B_1.png",
            "https://prts.wiki/images/5/5a/%E7%AB%8B%E7%BB%98_%E6%9E%81%E5%A2%83_1.png",
            "https://prts.wiki/images/4/43/%E7%AB%8B%E7%BB%98_%E7%9F%B3%E6%A3%89_1.png",
            "https://prts.wiki/images/8/8f/%E7%AB%8B%E7%BB%98_%E6%9C%88%E7%A6%BE_1.png",
            "https://prts.wiki/images/b/bf/%E7%AB%8B%E7%BB%98_%E8%8E%B1%E6%81%A9%E5%93%88%E7%89%B9_1.png",
            "https://prts.wiki/images/1/1b/%E7%AB%8B%E7%BB%98_%E6%96%AD%E5%B4%96_1.png",
            "https://prts.wiki/images/3/3d/%E7%AB%8B%E7%BB%98_%E8%9C%9C%E8%9C%A1_1.png",
            "https://prts.wiki/images/2/2d/%E7%AB%8B%E7%BB%98_%E8%B4%BE%E7%BB%B4_1.png",
            "https://prts.wiki/images/2/2e/%E7%AB%8B%E7%BB%98_%E5%AE%89%E5%93%B2%E6%8B%89_1.png",
            "https://prts.wiki/images/2/24/%E7%AB%8B%E7%BB%98_%E7%87%A7%E7%9F%B3_1.png",
            "https://prts.wiki/images/2/2c/%E7%AB%8B%E7%BB%98_%E5%9B%9B%E6%9C%88_1.png",
            "https://prts.wiki/images/5/51/%E7%AB%8B%E7%BB%98_%E5%A5%A5%E6%96%AF%E5%A1%94_1.png",
            "https://prts.wiki/images/3/38/%E7%AB%8B%E7%BB%98_%E7%B5%AE%E9%9B%A8_1.png",
            "https://prts.wiki/images/e/ef/%E7%AB%8B%E7%BB%98_%E5%8D%A1%E5%A4%AB%E5%8D%A1_1.png",
            "https://prts.wiki/images/6/62/%E7%AB%8B%E7%BB%98_%E7%88%B1%E4%B8%BD%E4%B8%9D_1.png",
            "https://prts.wiki/images/b/bb/%E7%AB%8B%E7%BB%98_%E4%B9%8C%E6%9C%89_1.png",
            "https://prts.wiki/images/1/14/%E7%AB%8B%E7%BB%98_%E7%86%94%E6%B3%89_1.png",
            "https://prts.wiki/images/b/b2/%E7%AB%8B%E7%BB%98_%E8%B5%A4%E5%86%AC_1.png",
            "https://prts.wiki/images/7/78/%E7%AB%8B%E7%BB%98_%E7%BB%AE%E8%89%AF_1.png",
            "https://prts.wiki/images/3/37/%E7%AB%8B%E7%BB%98_%E7%BE%BD%E6%AF%9B%E7%AC%94_1.png",
            "https://prts.wiki/images/2/21/%E7%AB%8B%E7%BB%98_%E6%A1%91%E8%91%9A_1.png",
            "https://prts.wiki/images/e/ea/%E7%AB%8B%E7%BB%98_%E7%81%B0%E6%AF%AB_1.png",
            "https://prts.wiki/images/f/f2/%E7%AB%8B%E7%BB%98_%E8%9A%80%E6%B8%85_1.png",
            "https://prts.wiki/images/c/c5/%E7%AB%8B%E7%BB%98_%E6%9E%81%E5%85%89_1.png",
            "https://prts.wiki/images/a/ac/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E5%8D%8A_1.png",
            "https://prts.wiki/images/4/4d/%E7%AB%8B%E7%BB%98_%E5%A4%8F%E6%A0%8E_1.png",
            "https://prts.wiki/images/8/88/%E7%AB%8B%E7%BB%98_%E9%A3%8E%E4%B8%B8_1.png",
            //
            "https://prts.wiki/images/d/dd/%E7%AB%8B%E7%BB%98_%E9%98%BF%E7%B1%B3%E5%A8%85_1.png",
            "https://prts.wiki/images/4/4e/%E7%AB%8B%E7%BB%98_%E6%9F%8F%E5%96%99_1.png",
            "https://prts.wiki/images/8/80/%E7%AB%8B%E7%BB%98_%E6%8B%9C%E6%9D%BE_1.png",
            "https://prts.wiki/images/d/d8/%E7%AB%8B%E7%BB%98_%E8%96%84%E7%BB%BF_1.png",
            "https://prts.wiki/images/6/6e/%E7%AB%8B%E7%BB%98_%E6%9A%B4%E8%A1%8C_1.png",
            "https://prts.wiki/images/c/c4/%E7%AB%8B%E7%BB%98_%E6%9A%B4%E9%9B%A8_1.png",
            "https://prts.wiki/images/f/f5/%E7%AB%8B%E7%BB%98_%E8%B4%9D%E5%A8%9C_1.png",
            "https://prts.wiki/images/7/7a/%E7%AB%8B%E7%BB%98_%E9%9E%AD%E5%88%83_1.png",
            "https://prts.wiki/images/c/c4/%E7%AB%8B%E7%BB%98_%E6%A0%BC%E6%8B%89%E5%B0%BC_1.png",
            "https://prts.wiki/images/9/92/%E7%AB%8B%E7%BB%98_%E7%81%AB%E7%A5%9E_1.png",
            "https://prts.wiki/images/9/9f/%E7%AB%8B%E7%BB%98_%E8%8B%A6%E8%89%BE_1.png",
            "https://prts.wiki/images/8/88/%E7%AB%8B%E7%BB%98_%E7%BD%97%E5%AE%BE_1.png",
            "https://prts.wiki/images/d/df/%E7%AB%8B%E7%BB%98_%E9%97%AA%E5%87%BB_1.png",
            "https://prts.wiki/images/8/82/%E7%AB%8B%E7%BB%98_%E9%9C%9C%E5%8D%8E_1.png",
            "https://prts.wiki/images/0/0a/%E7%AB%8B%E7%BB%98_%E7%89%B9%E7%B1%B3%E7%B1%B3_1.png",
            "https://prts.wiki/images/8/8a/%E7%AB%8B%E7%BB%98_%E5%9B%BE%E8%80%B6_1.png",
            "https://prts.wiki/images/e/e7/%E7%AB%8B%E7%BB%98_%E5%BE%AE%E9%A3%8E_1.png",
            "https://prts.wiki/images/d/dd/%E7%AB%8B%E7%BB%98_%E7%A8%80%E9%9F%B3_1.png",
            "https://prts.wiki/images/c/c2/%E7%AB%8B%E7%BB%98_%E9%94%A1%E5%85%B0_1.png",
            "https://prts.wiki/images/8/89/%E7%AB%8B%E7%BB%98_%E9%9B%AA%E9%9B%89_1.png",
            "https://prts.wiki/images/5/5f/%E7%AB%8B%E7%BB%98_%E4%BA%9A%E5%8F%B6_1.png",
            "https://prts.wiki/images/6/64/%E7%AB%8B%E7%BB%98_%E7%82%8E%E5%AE%A2_1.png",
            "https://prts.wiki/images/7/79/%E7%AB%8B%E7%BB%98_%E7%82%8E%E7%8B%B1%E7%82%8E%E7%86%94_1.png",
            "https://prts.wiki/images/d/d4/%E7%AB%8B%E7%BB%98_%E5%9B%A0%E9%99%80%E7%BD%97_1.png",
            "https://prts.wiki/images/7/7b/%E7%AB%8B%E7%BB%98_%E6%88%98%E8%BD%A6_1.png",
            "https://prts.wiki/images/1/16/%E7%AB%8B%E7%BB%98_%E9%93%B8%E9%93%81_1.png",
            "https://prts.wiki/images/4/41/%E7%AB%8B%E7%BB%98_%E9%BE%99%E8%88%8C%E5%85%B0_1.png",
            "https://prts.wiki/images/f/f3/%E7%AB%8B%E7%BB%98_%E8%9C%9C%E8%8E%93_1.png",
            "https://prts.wiki/images/b/bc/%E7%AB%8B%E7%BB%98_%E9%87%8E%E9%AC%83_1.png",
            "https://prts.wiki/images/0/03/%E7%AB%8B%E7%BB%98_%E8%80%B6%E6%8B%89_1.png",
            "https://prts.wiki/images/3/33/%E7%AB%8B%E7%BB%98_%E6%9A%AE%E8%90%BD_1.png",
            "https://prts.wiki/images/5/5e/%E7%AB%8B%E7%BB%98_%E4%B9%9D%E8%89%B2%E9%B9%BF_1.png",
            "https://prts.wiki/images/5/51/%E7%AB%8B%E7%BB%98_%E5%AF%92%E8%8A%92%E5%85%8B%E6%B4%9B%E4%B8%9D_1.png",
            "https://prts.wiki/images/5/54/%E7%AB%8B%E7%BB%98_%E8%A7%81%E8%A1%8C%E8%80%85_1.png",
    };

    private static String[] five_url2 = {
            "https://prts.wiki/images/6/61/%E7%AB%8B%E7%BB%98_%E7%8B%AE%E8%9D%8E_2.png",
            "https://prts.wiki/images/8/81/%E7%AB%8B%E7%BB%98_%E9%A3%9F%E9%93%81%E5%85%BD_2.png",
            "https://prts.wiki/images/3/36/%E7%AB%8B%E7%BB%98_%E8%93%9D%E6%AF%92_2.png",
            "https://prts.wiki/images/f/fe/%E7%AB%8B%E7%BB%98_%E6%8B%89%E6%99%AE%E5%85%B0%E5%BE%B7_2.png",
            "https://prts.wiki/images/d/d3/%E7%AB%8B%E7%BB%98_%E5%B9%BD%E7%81%B5%E9%B2%A8_2.png",
            "https://prts.wiki/images/f/fc/%E7%AB%8B%E7%BB%98_%E5%BE%B7%E5%85%8B%E8%90%A8%E6%96%AF_2.png",
            "https://prts.wiki/images/a/ae/%E7%AB%8B%E7%BB%98_%E6%A7%90%E7%90%A5_2.png",
            "https://prts.wiki/images/e/e2/%E7%AB%8B%E7%BB%98_%E8%B5%AB%E9%BB%98_2.png",
            "https://prts.wiki/images/a/ab/%E7%AB%8B%E7%BB%98_%E7%BA%A2_2.png",
            "https://prts.wiki/images/a/a2/%E7%AB%8B%E7%BB%98_%E7%99%BD%E9%9D%A2%E9%B8%AE_2.png",
            "https://prts.wiki/images/4/46/%E7%AB%8B%E7%BB%98_%E7%A9%BA_2.png",
            "https://prts.wiki/images/e/e6/%E7%AB%8B%E7%BB%98_%E5%90%BD_2.png",
            "https://prts.wiki/images/6/66/%E7%AB%8B%E7%BB%98_%E7%81%B0%E5%96%89_2.png",
            "https://prts.wiki/images/f/fb/%E7%AB%8B%E7%BB%98_%E5%B8%83%E6%B4%9B%E5%8D%A1_2.png",
            "https://prts.wiki/images/5/59/%E7%AB%8B%E7%BB%98_%E8%8B%87%E8%8D%89_2.png",
            "https://prts.wiki/images/f/fa/%E7%AB%8B%E7%BB%98_%E9%80%81%E8%91%AC%E4%BA%BA_2.png",
            "https://prts.wiki/images/1/13/%E7%AB%8B%E7%BB%98_%E6%98%9F%E6%9E%81_2.png",
            "https://prts.wiki/images/4/45/%E7%AB%8B%E7%BB%98_%E6%A0%BC%E5%8A%B3%E5%85%8B%E6%96%AF_2.png",
            "https://prts.wiki/images/b/b8/%E7%AB%8B%E7%BB%98_%E8%AF%97%E6%80%80%E9%9B%85_2.png",
            "https://prts.wiki/images/0/0d/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E9%AD%94_2.png",
            "https://prts.wiki/images/3/30/%E7%AB%8B%E7%BB%98_%E7%9C%9F%E7%90%86_2.png",
            "https://prts.wiki/images/0/05/%E7%AB%8B%E7%BB%98_%E5%88%9D%E9%9B%AA_2.png",
            "https://prts.wiki/images/d/da/%E7%AB%8B%E7%BB%98_%E5%B4%96%E5%BF%83_2.png",
            "https://prts.wiki/images/4/4c/%E7%AB%8B%E7%BB%98_%E5%AE%88%E6%9E%97%E4%BA%BA_2.png",
            "https://prts.wiki/images/9/94/%E7%AB%8B%E7%BB%98_%E6%99%AE%E7%BD%97%E6%97%BA%E6%96%AF_2.png",
            "https://prts.wiki/images/8/8d/%E7%AB%8B%E7%BB%98_%E5%8F%AF%E9%A2%82_2.png",
            "https://prts.wiki/images/b/b5/%E7%AB%8B%E7%BB%98_%E9%9B%B7%E8%9B%87_2.png",
            "https://prts.wiki/images/5/5f/%E7%AB%8B%E7%BB%98_%E4%B8%B4%E5%85%89_2.png",
            "https://prts.wiki/images/7/77/%E7%AB%8B%E7%BB%98_%E5%8D%8E%E6%B3%95%E7%90%B3_2.png",
            "https://prts.wiki/images/b/b5/%E7%AB%8B%E7%BB%98_%E6%A2%85%E5%B0%94_2.png",
            "https://prts.wiki/images/8/8c/%E7%AB%8B%E7%BB%98_%E5%A4%A9%E7%81%AB_2.png",
            "https://prts.wiki/images/9/9b/%E7%AB%8B%E7%BB%98_%E9%99%A8%E6%98%9F_2.png",
            "https://prts.wiki/images/7/77/%E7%AB%8B%E7%BB%98_%E7%99%BD%E9%87%91_2.png",
            "https://prts.wiki/images/5/50/%E7%AB%8B%E7%BB%98_%E8%8A%99%E5%85%B0%E5%8D%A1_2.png",
            "https://prts.wiki/images/2/2c/%E7%AB%8B%E7%BB%98_%E5%87%9B%E5%86%AC_2.png",
            "https://prts.wiki/images/c/cf/%E7%AB%8B%E7%BB%98_%E6%83%8A%E8%9B%B0_2.png",
            "https://prts.wiki/images/9/99/%E7%AB%8B%E7%BB%98_%E6%85%91%E7%A0%82_2.png",
            "https://prts.wiki/images/6/65/%E7%AB%8B%E7%BB%98_%E5%B7%AB%E6%81%8B_2.png",
            "https://prts.wiki/images/4/40/%E7%AB%8B%E7%BB%98_%E6%9E%81%E5%A2%83_2.png",
            "https://prts.wiki/images/a/a5/%E7%AB%8B%E7%BB%98_%E7%9F%B3%E6%A3%89_2.png",
            "https://prts.wiki/images/2/22/%E7%AB%8B%E7%BB%98_%E6%9C%88%E7%A6%BE_2.png",
            "https://prts.wiki/images/6/61/%E7%AB%8B%E7%BB%98_%E8%8E%B1%E6%81%A9%E5%93%88%E7%89%B9_2.png",
            "https://prts.wiki/images/a/a6/%E7%AB%8B%E7%BB%98_%E6%96%AD%E5%B4%96_2.png",
            "https://prts.wiki/images/2/2d/%E7%AB%8B%E7%BB%98_%E8%9C%9C%E8%9C%A1_2.png",
            "https://prts.wiki/images/8/80/%E7%AB%8B%E7%BB%98_%E8%B4%BE%E7%BB%B4_2.png",
            "https://prts.wiki/images/4/45/%E7%AB%8B%E7%BB%98_%E5%AE%89%E5%93%B2%E6%8B%89_2.png",
            "https://prts.wiki/images/4/49/%E7%AB%8B%E7%BB%98_%E7%87%A7%E7%9F%B3_2.png",
            "https://prts.wiki/images/7/77/%E7%AB%8B%E7%BB%98_%E5%9B%9B%E6%9C%88_2.png",
            "https://prts.wiki/images/d/df/%E7%AB%8B%E7%BB%98_%E5%A5%A5%E6%96%AF%E5%A1%94_2.png",
            "https://prts.wiki/images/8/87/%E7%AB%8B%E7%BB%98_%E7%B5%AE%E9%9B%A8_2.png",
            "https://prts.wiki/images/a/a1/%E7%AB%8B%E7%BB%98_%E5%8D%A1%E5%A4%AB%E5%8D%A1_2.png",
            "https://prts.wiki/images/e/ef/%E7%AB%8B%E7%BB%98_%E7%88%B1%E4%B8%BD%E4%B8%9D_2.png",
            "https://prts.wiki/images/6/68/%E7%AB%8B%E7%BB%98_%E4%B9%8C%E6%9C%89_2.png",
            "https://prts.wiki/images/b/b9/%E7%AB%8B%E7%BB%98_%E7%86%94%E6%B3%89_2.png",
            "https://prts.wiki/images/0/07/%E7%AB%8B%E7%BB%98_%E8%B5%A4%E5%86%AC_2.png",
            "https://prts.wiki/images/c/ce/%E7%AB%8B%E7%BB%98_%E7%BB%AE%E8%89%AF_2.png",
            "https://prts.wiki/images/b/be/%E7%AB%8B%E7%BB%98_%E7%BE%BD%E6%AF%9B%E7%AC%94_2.png",
            "https://prts.wiki/images/d/d6/%E7%AB%8B%E7%BB%98_%E6%A1%91%E8%91%9A_2.png",
            "https://prts.wiki/images/3/37/%E7%AB%8B%E7%BB%98_%E7%81%B0%E6%AF%AB_2.png",
            "https://prts.wiki/images/2/2b/%E7%AB%8B%E7%BB%98_%E8%9A%80%E6%B8%85_2.png",
            "https://prts.wiki/images/d/da/%E7%AB%8B%E7%BB%98_%E6%9E%81%E5%85%89_2.png",
            "https://prts.wiki/images/7/7e/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E5%8D%8A_2.png",
            "https://prts.wiki/images/0/02/%E7%AB%8B%E7%BB%98_%E5%A4%8F%E6%A0%8E_2.png",
            "https://prts.wiki/images/b/b8/%E7%AB%8B%E7%BB%98_%E9%A3%8E%E4%B8%B8_2.png",
            //
            "https://prts.wiki/images/3/3f/%E7%AB%8B%E7%BB%98_%E9%98%BF%E7%B1%B3%E5%A8%85_2.png",
            "https://prts.wiki/images/2/2d/%E7%AB%8B%E7%BB%98_%E6%9F%8F%E5%96%99_2.png",
            "https://prts.wiki/images/7/72/%E7%AB%8B%E7%BB%98_%E6%8B%9C%E6%9D%BE_2.png",
            "https://prts.wiki/images/2/20/%E7%AB%8B%E7%BB%98_%E8%96%84%E7%BB%BF_2.png",
            "https://prts.wiki/images/2/2c/%E7%AB%8B%E7%BB%98_%E6%9A%B4%E8%A1%8C_2.png",
            "https://prts.wiki/images/4/45/%E7%AB%8B%E7%BB%98_%E6%9A%B4%E9%9B%A8_2.png",
            "https://prts.wiki/images/b/b1/%E7%AB%8B%E7%BB%98_%E8%B4%9D%E5%A8%9C_2.png",
            "https://prts.wiki/images/4/4a/%E7%AB%8B%E7%BB%98_%E9%9E%AD%E5%88%83_2.png",
            "https://prts.wiki/images/2/22/%E7%AB%8B%E7%BB%98_%E6%A0%BC%E6%8B%89%E5%B0%BC_2.png",
            "https://prts.wiki/images/0/03/%E7%AB%8B%E7%BB%98_%E7%81%AB%E7%A5%9E_2.png",
            "https://prts.wiki/images/a/a8/%E7%AB%8B%E7%BB%98_%E8%8B%A6%E8%89%BE_2.png",
            "https://prts.wiki/images/8/8d/%E7%AB%8B%E7%BB%98_%E7%BD%97%E5%AE%BE_2.png",
            "https://prts.wiki/images/9/96/%E7%AB%8B%E7%BB%98_%E9%97%AA%E5%87%BB_2.png",
            "https://prts.wiki/images/d/d8/%E7%AB%8B%E7%BB%98_%E9%9C%9C%E5%8D%8E_2.png",
            "https://prts.wiki/images/a/af/%E7%AB%8B%E7%BB%98_%E7%89%B9%E7%B1%B3%E7%B1%B3_2.png",
            "https://prts.wiki/images/a/a9/%E7%AB%8B%E7%BB%98_%E5%9B%BE%E8%80%B6_2.png",
            "https://prts.wiki/images/0/01/%E7%AB%8B%E7%BB%98_%E5%BE%AE%E9%A3%8E_2.png",
            "https://prts.wiki/images/f/f5/%E7%AB%8B%E7%BB%98_%E7%A8%80%E9%9F%B3_2.png",
            "https://prts.wiki/images/c/c6/%E7%AB%8B%E7%BB%98_%E9%94%A1%E5%85%B0_2.png",
            "https://prts.wiki/images/2/24/%E7%AB%8B%E7%BB%98_%E9%9B%AA%E9%9B%89_2.png",
            "https://prts.wiki/images/a/a7/%E7%AB%8B%E7%BB%98_%E4%BA%9A%E5%8F%B6_2.png",
            "https://prts.wiki/images/2/22/%E7%AB%8B%E7%BB%98_%E7%82%8E%E5%AE%A2_2.png",
            "https://prts.wiki/images/d/dd/%E7%AB%8B%E7%BB%98_%E7%82%8E%E7%8B%B1%E7%82%8E%E7%86%94_2.png",
            "https://prts.wiki/images/6/61/%E7%AB%8B%E7%BB%98_%E5%9B%A0%E9%99%80%E7%BD%97_2.png",
            "https://prts.wiki/images/3/33/%E7%AB%8B%E7%BB%98_%E6%88%98%E8%BD%A6_2.png",
            "https://prts.wiki/images/8/81/%E7%AB%8B%E7%BB%98_%E9%93%B8%E9%93%81_2.png",
            "https://prts.wiki/images/8/87/%E7%AB%8B%E7%BB%98_%E9%BE%99%E8%88%8C%E5%85%B0_2.png",
            "https://prts.wiki/images/f/fa/%E7%AB%8B%E7%BB%98_%E8%9C%9C%E8%8E%93_2.png",
            "https://prts.wiki/images/4/4d/%E7%AB%8B%E7%BB%98_%E9%87%8E%E9%AC%83_2.png",
            "https://prts.wiki/images/7/75/%E7%AB%8B%E7%BB%98_%E8%80%B6%E6%8B%89_2.png",
            "https://prts.wiki/images/1/13/%E7%AB%8B%E7%BB%98_%E6%9A%AE%E8%90%BD_2.png",
            "https://prts.wiki/images/b/b6/%E7%AB%8B%E7%BB%98_%E4%B9%9D%E8%89%B2%E9%B9%BF_2.png",
            "https://prts.wiki/images/2/2f/%E7%AB%8B%E7%BB%98_%E5%AF%92%E8%8A%92%E5%85%8B%E6%B4%9B%E4%B8%9D_2.png",
            "https://prts.wiki/images/9/95/%E7%AB%8B%E7%BB%98_%E8%A7%81%E8%A1%8C%E8%80%85_2.png",
    };
}
