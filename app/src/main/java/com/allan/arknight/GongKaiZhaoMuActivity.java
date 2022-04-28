package com.allan.arknight;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GongKaiZhaoMuActivity extends AppCompatActivity {

    private List<RecyclerData> dataList = new ArrayList<>();

    public static final int SIX_STAR = 0XFFA52A5A;
    public static final int FIVE_STAR = 0XFFFF8C00;
    public static final int FOUR_STAR = 0XFF8A2BE2;
    public static final int THREE_STAR = 0XFF1E90FF;
    public static final int TWO_STAR = 0XFF575757;
    public static final int ONE_STAR = 0XFF240360;

    CheckBox xinShou, ziShen, gaoZi, jinZhan, yuanCheng,
            juJi, shuShi, xianFeng, jinWei, zhongZhuang, yiLiao,
            fuZhu, teZhong, shuChu, zhiYuanJ, fangHu, shengCun,
            zhiLiao, huiFei, qunGong, jianSu, zhiYuan, kuaiSu,
            xueRuo, weiYi, zhaoHuan, kongChang, baoFa;

    private CheckBox[] boxes = new CheckBox[28];

    private static int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongkaizhaomu);
        count = 0;
        boxes[0] = xinShou = findViewById(R.id.xinshou);
        boxes[1] = ziShen = findViewById(R.id.zishen);
        boxes[2] = gaoZi = findViewById(R.id.gaozi);
        boxes[3] = jinZhan = findViewById(R.id.jinzhan);
        boxes[4] = yuanCheng = findViewById(R.id.yuancheng);
        boxes[5] = juJi = findViewById(R.id.juji);
        boxes[6] = shuShi = findViewById(R.id.shushi);
        boxes[7] = xianFeng = findViewById(R.id.xianfeng);
        boxes[8] = jinWei = findViewById(R.id.jinwei);
        boxes[9] = zhongZhuang = findViewById(R.id.zhongzhuang);
        boxes[10] = yiLiao = findViewById(R.id.yiliao);
        boxes[11] = fuZhu = findViewById(R.id.fuzhu);
        boxes[12] = teZhong = findViewById(R.id.tezhong);
        boxes[13] = shuChu = findViewById(R.id.shuchu);
        boxes[14] = zhiYuanJ = findViewById(R.id.zhiyuanjixie);
        boxes[15] = fangHu = findViewById(R.id.fanghu);
        boxes[16] = shengCun = findViewById(R.id.shengcun);
        boxes[17] = zhiLiao = findViewById(R.id.zhiliao);
        boxes[18] = huiFei = findViewById(R.id.huifei);
        boxes[19] = qunGong = findViewById(R.id.qungong);
        boxes[20] = jianSu = findViewById(R.id.jiansu);
        boxes[21] = zhiYuan = findViewById(R.id.zhiyuan);
        boxes[22] = kuaiSu = findViewById(R.id.kuaisufuhuo);
        boxes[23] =  xueRuo = findViewById(R.id.xueruo);
        boxes[24] = weiYi = findViewById(R.id.weiyi);
        boxes[25] = zhaoHuan = findViewById(R.id.zhaohuan);
        boxes[26] = kongChang = findViewById(R.id.kongchang);
        boxes[27] = baoFa = findViewById(R.id.baofa);
        Button btn1, btn2;
        btn1 = findViewById(R.id.search_persons);
        btn2 = findViewById(R.id.reset_persons);
        btn1.setOnClickListener(new b1Click());
        btn2.setOnClickListener(new b2Click());
        for (int i = 0; i < 28; i++) {
            int finalI = i;
            boxes[i].setOnClickListener(v -> {
                if(count == 5){
                    if (boxes[finalI].isChecked()) {
                        boxes[finalI].setChecked(false);
                        Toast.makeText(this, "所选标签不能超过五个", Toast.LENGTH_SHORT).show();
                    } else {
                        boxes[finalI].setTextColor(Color.BLACK);
                        count--;
                    }
                }
                else {
                    if (boxes[finalI].isChecked()) {
                        boxes[finalI].setTextColor(Color.WHITE);
                        count++;
                    } else {
                        boxes[finalI].setTextColor(Color.BLACK);
                        count--;
                    }
                }
            });
        }
    }

    private class b1Click implements View.OnClickListener{
        AlertDialog.Builder builder = new AlertDialog.Builder(GongKaiZhaoMuActivity.this);

        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                if (nums_of_tags() == 0)
                    Toast.makeText(v.getContext(), "还未选择标签呢~", Toast.LENGTH_SHORT).show();
                else {
                    View view_v = getLayoutInflater().inflate(R.layout.result, null);
                    init();
                    RecyclerView view = view_v.findViewById(R.id.res_output);
                    view.setItemViewCacheSize(25);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    view.setLayoutManager(layoutManager);
                    RecyclerAdapter adapter = new RecyclerAdapter(dataList);
                    view.setAdapter(adapter);

                    builder.setTitle("可能出现的干员").setView(view_v);
                    builder.create();
                    builder.show().getWindow().setLayout(1000, 1600);
                }
            }
        }
    }

    private void init(){
        dataList = new ArrayList<>();
        int num = nums_of_tags();
        int [] selected_tag = new int[num];
        for (int i = 0, j = 0; i < 28;i++){
            if (boxes[i].isChecked())
                selected_tag[j++] = i;
        }
        switch (num){
            case 1:{
                if (boxes[2].isChecked()){
                    add_sixStar();
                }
                else if (boxes[1].isChecked()){
                    add_fiveStar();
                }
                else {
                    add_normal(selected_tag);
                }
                break;
            }
            case 2:{
                if (boxes[2].isChecked() && boxes[1].isChecked()){
                    add_sixStar();
                    add_fiveStar();
                }
                else if (boxes[2].isChecked()){
                    int[] t = new int[1];
                    for (int j : selected_tag) {
                        if (j == 2)
                            continue;
                        t[0] = j;
                    }
                    // 2
                    add_normal_six(selected_tag);
                    // 1
                    add_sixStar();
                    add_normal(t);
                }
                else if (boxes[1].isChecked()){
                    int[] t = new int[1];
                    for (int j : selected_tag) {
                        if (j == 1)
                            continue;
                        t[0] = j;
                    }
                    // 2
                    add_normal_five(selected_tag);
                    // 1
                    add_fiveStar();
                    add_normal(t);
                }
                else {
                    int[] t1 = new int[1];
                    int[] t2 = new int[1];
                    t1[0] = selected_tag[0];
                    t2[0] = selected_tag[1];
                    // 2
                    add_normal(selected_tag);
                    // 1
                    add_normal(t1);
                    add_normal(t2);
                }
                break;
            }
            case 3:{
                if (boxes[1].isChecked() && boxes[2].isChecked()){
                    int[] t = new int[1];
                    for (int j : selected_tag) {
                        if (j == 1 || j == 2)
                            continue;
                        t[0] = j;
                    }
                    add_normal_six(new int[]{2, t[0]});
                    add_sixStar();
                    add_normal_five(new int[]{1, t[0]});
                    add_fiveStar();
                    add_normal(t);
                }
                else if (boxes[2].isChecked()){
                    int[] t_12 = new int[2];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 2)
                            continue;
                        t_12[j++] = selected_tag[i];
                    }
                    int[] t_23 = {2, t_12[0]};
                    int[] t_13 = {2, t_12[1]};
                    int[] t_1 = {t_12[0]};
                    int[] t_2 = {t_12[1]};
                    add_normal_six(selected_tag);
                    add_normal_six(t_13);
                    add_normal_six(t_23);
                    add_sixStar();
                    add_normal(t_12);
                    add_normal(t_1);
                    add_normal(t_2);
                }
                else if (boxes[1].isChecked()){
                    int[] t_12 = new int[2];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 1)
                            continue;
                        t_12[j++] = selected_tag[i];
                    }
                    int[] t_23 = {1, t_12[0]};
                    int[] t_13 = {1, t_12[1]};
                    int[] t_1 = {t_12[0]};
                    int[] t_2 = {t_12[1]};
                    add_normal_five(selected_tag);
                    add_normal_five(t_13);
                    add_normal_five(t_23);
                    add_fiveStar();
                    add_normal(t_12);
                    add_normal(t_1);
                    add_normal(t_2);
                }
                else {
                    int[] t_12 = {selected_tag[0], selected_tag[1]};
                    int[] t_23 = {selected_tag[0], selected_tag[2]};
                    int[] t_13 = {selected_tag[1], selected_tag[2]};
                    int[] t_1 = {selected_tag[0]};
                    int[] t_2 = {selected_tag[1]};
                    int[] t_3 = {selected_tag[2]};
                    add_normal(selected_tag);
                    add_normal(t_12);
                    add_normal(t_13);
                    add_normal(t_23);
                    add_normal(t_1);
                    add_normal(t_2);
                    add_normal(t_3);
                }
                break;
            }
            case 4:{
                if (boxes[1].isChecked() && boxes[2].isChecked()){
                    int[] t_34 = new int[2];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 1 || selected_tag[i] == 2)
                            continue;
                        t_34[j++] = selected_tag[i];
                    }
                    int[] t_134 = {2, t_34[0], t_34[1]};
                    int[] t_234 = {1, t_34[0], t_34[1]};
                    int[] t_13 = {2, t_34[0]};
                    int[] t_14 = {2, t_34[1]};
                    int[] t_23 = {1, t_34[0]};
                    int[] t_24 = {1, t_34[1]};
                    int[] t_3 = {t_34[0]};
                    int[] t_4 = {t_34[1]};
                    add_normal_six(t_134);
                    add_normal_six(t_13);
                    add_normal_six(t_14);
                    add_sixStar();
                    add_normal_five(t_234);
                    add_normal_five(t_23);
                    add_normal_five(t_24);
                    add_fiveStar();
                    add_normal(t_34);
                    add_normal(t_3);
                    add_normal(t_4);
                }
                else if (boxes[2].isChecked()){
                    int[] t_234 = new int[3];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 2)
                            continue;
                        t_234[j++] = selected_tag[i];
                    }
                    int[] t_123 = {2, t_234[0], t_234[1]};
                    int[] t_124 = {2, t_234[0], t_234[2]};
                    int[] t_134 = {2, t_234[1], t_234[2]};
                    int[] t_12 = {2, t_234[0]};
                    int[] t_13 = {2, t_234[1]};
                    int[] t_14 = {2, t_234[2]};
                    int[] t_23 = {t_234[0], t_234[1]};
                    int[] t_24 = {t_234[0], t_234[2]};
                    int[] t_34 = {t_234[1], t_234[2]};
                    int[] t_2 = {t_234[0]};
                    int[] t_3 = {t_234[1]};
                    int[] t_4 = {t_234[2]};
                    add_normal_six(t_123);
                    add_normal_six(t_124);
                    add_normal_six(t_134);
                    add_normal_six(t_12);
                    add_normal_six(t_13);
                    add_normal_six(t_14);
                    add_sixStar();
                    add_normal(t_234);
                    add_normal(t_23);
                    add_normal(t_24);
                    add_normal(t_34);
                    add_normal(t_2);
                    add_normal(t_3);
                    add_normal(t_4);
                }
                else if (boxes[1].isChecked()){
                    int[] t_234 = new int[3];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 1)
                            continue;
                        t_234[j++] = selected_tag[i];
                    }
                    int[] t_123 = {1, t_234[0], t_234[1]};
                    int[] t_124 = {1, t_234[0], t_234[2]};
                    int[] t_134 = {1, t_234[1], t_234[2]};
                    int[] t_12 = {1, t_234[0]};
                    int[] t_13 = {1, t_234[1]};
                    int[] t_14 = {1, t_234[2]};
                    int[] t_23 = {t_234[0], t_234[1]};
                    int[] t_24 = {t_234[0], t_234[2]};
                    int[] t_34 = {t_234[1], t_234[2]};
                    int[] t_2 = {t_234[0]};
                    int[] t_3 = {t_234[1]};
                    int[] t_4 = {t_234[2]};
                    add_normal_five(t_123);
                    add_normal_five(t_124);
                    add_normal_five(t_134);
                    add_normal_five(t_12);
                    add_normal_five(t_13);
                    add_normal_five(t_14);
                    add_fiveStar();
                    add_normal(t_234);
                    add_normal(t_23);
                    add_normal(t_24);
                    add_normal(t_34);
                    add_normal(t_2);
                    add_normal(t_3);
                    add_normal(t_4);
                }
                else {
                    add_normal(new int[]{selected_tag[0], selected_tag[1], selected_tag[2]});
                    add_normal(new int[]{selected_tag[0], selected_tag[1], selected_tag[3]});
                    add_normal(new int[]{selected_tag[0], selected_tag[2], selected_tag[3]});
                    add_normal(new int[]{selected_tag[1], selected_tag[2], selected_tag[3]});
                    add_normal(new int[]{selected_tag[0], selected_tag[1]});
                    add_normal(new int[]{selected_tag[0], selected_tag[2]});
                    add_normal(new int[]{selected_tag[0], selected_tag[3]});
                    add_normal(new int[]{selected_tag[1], selected_tag[2]});
                    add_normal(new int[]{selected_tag[1], selected_tag[3]});
                    add_normal(new int[]{selected_tag[2], selected_tag[3]});
                    add_normal(new int[]{selected_tag[0]});
                    add_normal(new int[]{selected_tag[1]});
                    add_normal(new int[]{selected_tag[2]});
                    add_normal(new int[]{selected_tag[3]});
                }
                break;
            }
            case 5:{
                if (boxes[1].isChecked() && boxes[2].isChecked()){
                    int[] t_345 = new int[3];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 1 || selected_tag[i] == 2)
                            continue;
                        t_345[j++] = selected_tag[i];
                    }
                    int[] t_134 = {2, t_345[0], t_345[1]};
                    int[] t_135 = {2, t_345[0], t_345[2]};
                    int[] t_145 = {2, t_345[1], t_345[2]};
                    int[] t_234 = {1, t_345[0], t_345[1]};
                    int[] t_235 = {1, t_345[0], t_345[2]};
                    int[] t_245 = {1, t_345[1], t_345[2]};
                    int[] t_13 = {2, t_345[0]};
                    int[] t_14 = {2, t_345[1]};
                    int[] t_15 = {2, t_345[2]};
                    int[] t_23 = {1, t_345[0]};
                    int[] t_24 = {1, t_345[1]};
                    int[] t_25 = {1, t_345[2]};
                    int[] t_34 = {t_345[0], t_345[1]};
                    int[] t_35 = {t_345[0], t_345[2]};
                    int[] t_45 = {t_345[1], t_345[2]};
                    int[] t_3 = {t_345[0]};
                    int[] t_4 = {t_345[1]};
                    int[] t_5 = {t_345[2]};
                    add_normal_six(t_134);
                    add_normal_six(t_135);
                    add_normal_six(t_145);
                    add_normal_six(t_13);
                    add_normal_six(t_14);
                    add_normal_six(t_15);
                    add_sixStar();
                    add_normal_five(t_234);
                    add_normal_five(t_235);
                    add_normal_five(t_245);
                    add_normal_five(t_23);
                    add_normal_five(t_24);
                    add_normal_five(t_25);
                    add_fiveStar();
                    add_normal(t_345);
                    add_normal(t_34);
                    add_normal(t_35);
                    add_normal(t_45);
                    add_normal(t_3);
                    add_normal(t_4);
                    add_normal(t_5);
                }
                else if (boxes[2].isChecked()){
                    int[] t_2345 = new int[4];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 2)
                            continue;
                        t_2345[j++] = selected_tag[i];
                    }
                    int[] t_123 = {2, t_2345[0], t_2345[1]};
                    int[] t_124 = {2, t_2345[0], t_2345[2]};
                    int[] t_125 = {2, t_2345[0], t_2345[3]};
                    int[] t_134 = {2, t_2345[1], t_2345[2]};
                    int[] t_135 = {2, t_2345[1], t_2345[3]};
                    int[] t_145 = {2, t_2345[2], t_2345[3]};
                    int[] t_234 = {t_2345[0], t_2345[1], t_2345[2]};
                    int[] t_235 = {t_2345[0], t_2345[1], t_2345[3]};
                    int[] t_345 = {t_2345[1], t_2345[2], t_2345[3]};
                    int[] t_12 = {2, t_2345[0]};
                    int[] t_13 = {2, t_2345[1]};
                    int[] t_14 = {2, t_2345[2]};
                    int[] t_15 = {2, t_2345[3]};
                    int[] t_23 = {t_2345[0], t_2345[1]};
                    int[] t_24 = {t_2345[0], t_2345[2]};
                    int[] t_25 = {t_2345[0], t_2345[3]};
                    int[] t_34 = {t_2345[1], t_2345[2]};
                    int[] t_35 = {t_2345[1], t_2345[3]};
                    int[] t_45 = {t_2345[2], t_2345[3]};
                    int[] t_2 = {t_2345[0]};
                    int[] t_3 = {t_2345[1]};
                    int[] t_4 = {t_2345[2]};
                    int[] t_5 = {t_2345[3]};
                    add_normal_six(t_123);
                    add_normal_six(t_124);
                    add_normal_six(t_125);
                    add_normal_six(t_134);
                    add_normal_six(t_135);
                    add_normal_six(t_145);
                    add_normal_six(t_12);
                    add_normal_six(t_13);
                    add_normal_six(t_14);
                    add_normal_six(t_15);
                    add_sixStar();
                    add_normal(t_234);
                    add_normal(t_235);
                    add_normal(t_345);
                    add_normal(t_23);
                    add_normal(t_24);
                    add_normal(t_25);
                    add_normal(t_34);
                    add_normal(t_35);
                    add_normal(t_45);
                    add_normal(t_2);
                    add_normal(t_3);
                    add_normal(t_4);
                    add_normal(t_5);
                }
                else if (boxes[1].isChecked()){
                    int[] t_2345 = new int[4];
                    for (int i = 0, j = 0; i < selected_tag.length; i++){
                        if (selected_tag[i] == 1)
                            continue;
                        t_2345[j++] = selected_tag[i];
                    }
                    int[] t_123 = {1, t_2345[0], t_2345[1]};
                    int[] t_124 = {1, t_2345[0], t_2345[2]};
                    int[] t_125 = {1, t_2345[0], t_2345[3]};
                    int[] t_134 = {1, t_2345[1], t_2345[2]};
                    int[] t_135 = {1, t_2345[1], t_2345[3]};
                    int[] t_145 = {1, t_2345[2], t_2345[3]};
                    int[] t_234 = {t_2345[0], t_2345[1], t_2345[2]};
                    int[] t_235 = {t_2345[0], t_2345[1], t_2345[3]};
                    int[] t_345 = {t_2345[1], t_2345[2], t_2345[3]};
                    int[] t_12 = {1, t_2345[0]};
                    int[] t_13 = {1, t_2345[1]};
                    int[] t_14 = {1, t_2345[2]};
                    int[] t_15 = {1, t_2345[3]};
                    int[] t_23 = {t_2345[0], t_2345[1]};
                    int[] t_24 = {t_2345[0], t_2345[2]};
                    int[] t_25 = {t_2345[0], t_2345[3]};
                    int[] t_34 = {t_2345[1], t_2345[2]};
                    int[] t_35 = {t_2345[1], t_2345[3]};
                    int[] t_45 = {t_2345[2], t_2345[3]};
                    int[] t_2 = {t_2345[0]};
                    int[] t_3 = {t_2345[1]};
                    int[] t_4 = {t_2345[2]};
                    int[] t_5 = {t_2345[3]};
                    add_normal_five(t_123);
                    add_normal_five(t_124);
                    add_normal_five(t_125);
                    add_normal_five(t_134);
                    add_normal_five(t_135);
                    add_normal_five(t_145);
                    add_normal_five(t_12);
                    add_normal_five(t_13);
                    add_normal_five(t_14);
                    add_normal_five(t_15);
                    add_fiveStar();
                    add_normal(t_234);
                    add_normal(t_235);
                    add_normal(t_345);
                    add_normal(t_23);
                    add_normal(t_24);
                    add_normal(t_25);
                    add_normal(t_34);
                    add_normal(t_35);
                    add_normal(t_45);
                    add_normal(t_2);
                    add_normal(t_3);
                    add_normal(t_4);
                    add_normal(t_5);
                }
                else {
                    add_normal(new int[]{selected_tag[0], selected_tag[1], selected_tag[2]});
                    add_normal(new int[]{selected_tag[0], selected_tag[1], selected_tag[3]});
                    add_normal(new int[]{selected_tag[0], selected_tag[1], selected_tag[4]});
                    add_normal(new int[]{selected_tag[0], selected_tag[2], selected_tag[3]});
                    add_normal(new int[]{selected_tag[0], selected_tag[2], selected_tag[4]});
                    add_normal(new int[]{selected_tag[0], selected_tag[3], selected_tag[4]});
                    add_normal(new int[]{selected_tag[1], selected_tag[2], selected_tag[3]});
                    add_normal(new int[]{selected_tag[1], selected_tag[2], selected_tag[4]});
                    add_normal(new int[]{selected_tag[1], selected_tag[3], selected_tag[4]});
                    add_normal(new int[]{selected_tag[2], selected_tag[3], selected_tag[4]});
                    add_normal(new int[]{selected_tag[0], selected_tag[1]});
                    add_normal(new int[]{selected_tag[0], selected_tag[2]});
                    add_normal(new int[]{selected_tag[0], selected_tag[3]});
                    add_normal(new int[]{selected_tag[0], selected_tag[4]});
                    add_normal(new int[]{selected_tag[1], selected_tag[2]});
                    add_normal(new int[]{selected_tag[1], selected_tag[3]});
                    add_normal(new int[]{selected_tag[1], selected_tag[4]});
                    add_normal(new int[]{selected_tag[2], selected_tag[3]});
                    add_normal(new int[]{selected_tag[2], selected_tag[4]});
                    add_normal(new int[]{selected_tag[3], selected_tag[4]});
                    add_normal(new int[]{selected_tag[0]});
                    add_normal(new int[]{selected_tag[1]});
                    add_normal(new int[]{selected_tag[2]});
                    add_normal(new int[]{selected_tag[3]});
                    add_normal(new int[]{selected_tag[4]});
                }
                break;
            }
            default:
        }
    }

    private int nums_of_tags(){
        int num = 0;
        for (int i = 0; i < 28; i++){
            if (boxes[i].isChecked())
                num++;
        }
        return num;
    }

    private void add_sixStar(){
        String[] names = new String[100];
        int[] color = new int[100];
        int index = 0;
        for (String six_person : six_persons) {
            names[index] = six_person;
            color[index++] = SIX_STAR;
        }
        dataList.add(new RecyclerData(tags[2],
                Arrays.copyOfRange(names, 0, index),
                Arrays.copyOfRange(color, 0, index)));
    }

    private void add_fiveStar(){
        String[] names = new String[100];
        int[] color = new int[100];
        int index = 0;
        for (String five_person : five_persons) {
            names[index] = five_person;
            color[index++] = FIVE_STAR;
        }
        dataList.add(new RecyclerData(tags[1],
                Arrays.copyOfRange(names, 0, index),
                Arrays.copyOfRange(color, 0, index)));
    }

    private void add_normal(int[] selected_tag){
        String[] names = new String[100];
        int[] color = new int[100];
        int index = 0;
        int num = selected_tag.length;
        String tag = null;
        for (int i = 0; i < num; i++){
            if (i == 0)
                tag = tags[selected_tag[i]];
            else {
                tag = tag + tags[selected_tag[i]];
            }
        }
        for (int i = 0; i < five_persons.length; i++){
            if (intersection_length(selected_tag, five_persons_tags.get(i)) == num){
                names[index] = five_persons[i];
                color[index++] = FIVE_STAR;
            }
        }
        for (int i = 0; i < four_persons.length; i++){
            if (intersection_length(selected_tag, four_persons_tags.get(i)) == num){
                names[index] = four_persons[i];
                color[index++] = FOUR_STAR;
            }
        }
        for (int i = 0; i < three_persons.length; i++){
            if (intersection_length(selected_tag, three_persons_tags.get(i)) == num){
                names[index] = three_persons[i];
                color[index++] = THREE_STAR;
            }
        }
        for (int i = 0; i < two_persons.length; i++){
            if (intersection_length(selected_tag, two_persons_tags.get(i)) == num){
                names[index] = two_persons[i];
                color[index++] = TWO_STAR;
            }
        }
        for (int i = 0; i < one_persons.length; i++){
            if (intersection_length(selected_tag, one_persons_tags.get(i)) == num){
                names[index] = one_persons[i];
                color[index++] = ONE_STAR;
            }
        }
        if (index != 0) {
            dataList.add(new RecyclerData(tag,
                    Arrays.copyOfRange(names, 0, index),
                    Arrays.copyOfRange(color, 0, index)));
        }
    }

    private void add_normal_five(int[] selected_tag){
        String[] names = new String[100];
        int[] color = new int[100];
        int index = 0;
        int num = selected_tag.length - 1;
        String tag = null;
        for (int i = 0; i < selected_tag.length; i++)
            if (i == 0)
                tag = tags[selected_tag[i]];
            else {
                tag = tag + tags[selected_tag[i]];
            }
        for (int i = 0; i < five_persons.length; i++) {
            if (intersection_length(selected_tag, five_persons_tags.get(i)) == num) {
                names[index] = five_persons[i];
                color[index++] = FIVE_STAR;
            }
        }
        if (index != 0) {
            dataList.add(new RecyclerData(tag,
                    Arrays.copyOfRange(names, 0, index),
                    Arrays.copyOfRange(color, 0, index)));
        }
    }

    private void add_normal_six(int[] selected_tag) {
        String[] names = new String[100];
        int[] color = new int[100];
        int index = 0;
        int num = selected_tag.length - 1;
        String tag = null;
        for (int i = 0; i < selected_tag.length; i++)
            if (i == 0)
                tag = tags[selected_tag[i]];
            else {
                tag = tag + tags[selected_tag[i]];
            }
        for (int i = 0; i < six_persons.length; i++) {
            if (intersection_length(selected_tag, six_persons_tags.get(i)) == num) {
                names[index] = six_persons[i];
                color[index++] = SIX_STAR;
            }
        }
        if (index != 0) {
            dataList.add(new RecyclerData(tag,
                    Arrays.copyOfRange(names, 0, index),
                    Arrays.copyOfRange(color, 0, index)));
        }
    }

    private class b2Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            for (int i = 0; i < 28; i++){
                if (boxes[i].isChecked()) {
                    boxes[i].setChecked(false);
                    boxes[i].setTextColor(Color.BLACK);
                }
            }
            count = 0;
        }
    }

    public static class RecyclerData{
        private String tags;
        private String[] names;
        private int[] color;

        public RecyclerData(String tags, String[] names, int[] color){
            this.tags = tags;
            this.names = names;
            this.color = color;
        }

        public String getTags(){
            return tags;
        }

        public String[] getNames(){
            return names;
        }

        public int[] getColor(){
            return color;
        }
    }

    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
        private List<RecyclerData> list;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView tags;
            GridView gridView;
            LinearLayout linearLayout;

            public ViewHolder(View view){
                super(view);
                this.tags = view.findViewById(R.id.tags);
                this.gridView = view.findViewById(R.id.output_name);
                this.linearLayout = view.findViewById(R.id.my_layout);
            }
        }

        public RecyclerAdapter(List<RecyclerData> dataList){
            list = dataList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_output, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RecyclerData t = list.get(position);
            holder.tags.setText(t.getTags());
            GridViewAdapter adapter = new GridViewAdapter(t.getNames(), t.getColor());
            holder.gridView.setAdapter(adapter);
            ViewGroup.LayoutParams params = holder.gridView.getLayoutParams();
            if (t.getNames().length % 3 != 0)
                params.height = (t.getNames().length / 3 + 1) * 103;
            else
                params.height = (t.getNames().length / 3) * 103;
            holder.gridView.setLayoutParams(params);
            holder.linearLayout.setBackgroundResource(backgrounds[getRandomNumber(0, 3)]);
        }

        @Override
        public int getItemCount(){
            return list.size();
        }
    }

    private static class GridViewAdapter extends BaseAdapter{
        String[] name_list;
        int[] color_list;

        public GridViewAdapter(String[] names, int[] colors){
            this.name_list = names;
            this.color_list = colors;
        }

        @Override
        public int getCount() {
            return name_list.length;
        }

        @Override
        public Object getItem(int position) {
            return name_list[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_item, null);
            TextView text = view.findViewById(R.id.name_item);
            text.setText(name_list[position]);
            text.setTextColor(color_list[position]);
            return view;
        }
    }

    public static int intersection_length(int[] nums1, int[] nums2){
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2){
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2){
                if (index == 0 || num1 != intersection[index - 1])
                    index++;
                index1++;
                index2++;
            }
            else if (num1 < num2)
                index1++;
            else
                index2++;
        }
        return index;
    }

    private static int getRandomNumber(int min, int max){
        if (min >= max) {
            throw new IllegalArgumentException("最大值应大于最小值");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private final String[] six_persons = {
            "能天使", "黑", "银灰",
            "莫斯提马", "夜莺", "星熊",
            "陈", "煌", "伊芙利特",
            "麦哲伦", "赫拉格", "斯卡蒂",
            "塞雷娅", "闪灵", "推进之王",
            "阿", "刻俄柏", "风笛",
    };

    private final ArrayList<int[]> six_persons_tags = new ArrayList<>(Arrays.asList(
            new int[]{13, 5, 4}, new int[]{13, 5, 4}, new int[]{13, 21, 8, 3},
            new int[]{19, 21, 6, 26, 4}, new  int[]{17, 21, 10, 4}, new int[]{15, 13, 9, 3},
            new int[]{27, 13, 8, 3}, new int[]{13, 16, 8, 3}, new int[]{19, 23, 6, 4},
            new int[]{21, 20, 13, 11, 4}, new int[]{13, 16, 8, 3}, new int[]{13, 16, 8, 3},
            new int[]{15, 17, 21, 9, 3}, new int[]{17, 21, 10, 4}, new int[]{18, 13, 7, 3},
            new int[]{13, 21, 12, 4}, new int[]{13, 26, 6, 4}, new int[]{18, 13, 7, 3}
    ));

    private final String[] five_persons = {
            "狮蝎", "食铁兽", "蓝毒",
            "幽灵鲨", "德克萨斯", "槐琥",
            "赫默", "红", "白面鸮",
            "灰喉", "布洛卡", "苇草",
            "送葬人", "星极", "格劳克斯",
            "诗怀雅", "夜魔", "真理",
            "初雪", "崖心", "守林人",
            "普罗旺斯", "可颂", "雷蛇",
            "临光", "华法琳", "梅尔",
            "陨星", "白金", "凛冬",
            "火神", "因陀罗", "吽",
            "惊蛰", "慑砂",
    };

    private final ArrayList<int[]> five_persons_tags = new ArrayList<>(Arrays.asList(
            new int[]{13, 16, 12, 3}, new int[]{24, 20, 12, 3}, new int[]{13, 5, 4},
            new int[]{19, 16, 8, 3}, new int[]{18, 26, 7, 3}, new int[]{22, 23, 12, 3},
            new int[]{17, 10, 4}, new int[]{22, 26, 12, 3}, new int[]{17, 21, 10, 4},
            new int[]{13, 5, 4}, new int[]{19, 16, 8, 3}, new int[]{18, 13, 7, 3},
            new int[]{19, 5, 4}, new int[]{13, 15, 8, 3}, new int[]{20, 26, 11, 4},
            new int[]{13, 21, 8, 3}, new int[]{13, 17, 20, 6, 4}, new int[]{20, 13, 11, 4},
            new int[]{23, 11, 4}, new int[]{24, 13, 12, 3}, new int[]{13, 27, 5, 4},
            new int[]{13, 5, 4}, new int[]{15, 9, 24, 3}, new int[]{15, 9, 13, 3},
            new int[]{15, 17, 9, 3}, new int[]{17, 21, 10, 4}, new int[]{25, 26, 11, 4},
            new int[]{19, 23, 5, 4}, new int[]{13, 5, 4}, new int[]{18, 21, 7, 3},
            new int[]{16, 15, 13, 9, 3}, new int[]{13, 16, 8, 3}, new int[]{15, 17, 9, 3},
            new int[]{13, 6, 4}, new int[]{19, 23, 5, 4}
    ));

    private final String[] four_persons = {
            "安比尔", "梅", "红云",
            "桃金娘", "苏苏洛", "格雷伊",
            "猎蜂", "阿消", "地灵",
            "古米", "蛇屠箱", "角峰",
            "调香师", "末药", "暗索",
            "砾", "慕斯", "霜叶",
            "缠丸", "杜宾", "红豆",
            "清道夫", "白雪", "流星",
            "杰西卡", "远山", "夜烟",
            "艾丝黛尔", "清流", "宴",
    };

    private final ArrayList<int[]> four_persons_tags = new ArrayList<>(Arrays.asList(
            new int[]{13, 20, 5, 4}, new int[]{13, 20, 5, 4}, new int[]{13, 5, 4},
            new int[]{18, 17, 7, 3}, new int[]{17, 10, 4}, new int[]{19, 20, 6, 4},
            new int[]{13, 8, 3}, new int[]{24, 12, 3}, new int[]{20, 11, 4},
            new int[]{15, 17, 9, 3}, new int[]{15, 9, 3}, new int[]{15, 9, 3},
            new int[]{17, 10, 4}, new int[]{17, 10, 4}, new int[]{24, 12, 3},
            new int[]{22, 15, 12, 3}, new int[]{13, 8, 3}, new int[]{20, 13, 8, 3},
            new int[]{16, 13, 8, 3}, new int[]{13, 21, 8, 3}, new int[]{13, 18, 7, 3},
            new int[]{18, 13, 7, 3}, new int[]{19, 20, 5, 4}, new int[]{13, 23, 5, 4},
            new int[]{13, 16, 5, 4}, new int[]{19, 6, 4}, new int[]{13, 23, 6, 4},
            new int[]{19, 16, 8, 3}, new int[]{17, 21, 10, 4}, new int[]{13, 16, 8, 3}
    ));

    private final String[] three_persons = {
            "芬", "炎熔", "月见夜", "香草", "史都华德",
            "米格鲁", "斑点", "空爆", "梓兰", "芙蓉", "克洛丝",
            "玫兰莎", "翎羽", "泡普卡", "安赛尔", "安德切尔",
    };

    private final ArrayList<int[]> three_persons_tags = new ArrayList<>(Arrays.asList(
            new int[]{18, 7, 3}, new int[]{19, 6, 4}, new int[]{13, 8, 3},
            new int[]{18, 7, 3}, new int[]{13, 6, 4}, new int[]{15, 9, 3},
            new int[]{15, 17, 9, 3}, new int[]{19, 5, 4}, new int[]{20, 11, 4},
            new int[]{17, 10, 4}, new int[]{13, 5, 4}, new int[]{13, 16, 8, 3},
            new int[]{13, 18, 7, 3}, new int[]{19, 16, 8, 3}, new int[]{17, 10, 4},
            new int[]{13, 5, 4}
    ));

    private final String[] two_persons = {
            "12F", "杜林", "巡林者",
            "黑角", "夜刀",
    };

    private final ArrayList<int[]> two_persons_tags = new ArrayList<>(Arrays.asList(
            new int[]{0, 6, 4}, new int[]{0, 6, 4}, new int[]{0, 5, 4},
            new int[]{0, 9, 3}, new int[]{0, 7, 3}
    ));

    private final String[] one_persons = {
            "THRM-EX", "Castle-3", "Lancet-2",
            "正义骑士号",
    };

    private final ArrayList<int[]> one_persons_tags = new ArrayList<>(Arrays.asList(
            new int[]{27, 14, 12, 3}, new int[]{21, 14, 8, 3}, new int[]{17, 10, 14, 4},
            new int[]{14, 21, 4}
    ));

    private final String[] tags = {
            // 0         1            2             3         4
            "新手  ", "资深干员  ", "高级资深干员  ", "近战位  ", "远程位  ",
            // 5       6        7        8         9       10
            "狙击  ", "术师  ", "先锋  ", "近卫  ", "重装  ", "医疗  ",
            // 11      12       13        14         15        16
            "辅助  ", "特种  ", "输出  ", "支援机械  ", "防护  ", "生存  ",
            // 17       18         19       20       21        22
            "治疗  ", "费用回复  ", "群攻  ", "减速  ", "支援  ", "快速复活  ",
            // 23      24       25       26       27
            "削弱  ", "位移  ", "召唤  ", "控场  ", "爆发  "
    };
    private static final int[] backgrounds = {
            R.drawable.back_1,
            R.drawable.back_2,
            R.drawable.back_3,
            R.drawable.back_4
    };
}
