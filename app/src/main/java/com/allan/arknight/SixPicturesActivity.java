package com.allan.arknight;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;


import java.util.ArrayList;
import java.util.List;

public class SixPicturesActivity extends AppCompatActivity {

    private List<RecyclerData> dataList1 = new ArrayList<>();

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

        v.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        v.setLayoutManager(layoutManager);
        RecyclerAdapter1 adapter = new RecyclerAdapter1(dataList1);
        v.setAdapter(adapter);
    }

    private void init(){
        for (int i = 0; i < sixStar.length; i++) {
            RecyclerPIC i1 = new RecyclerPIC(six_url[i]);
            RecyclerPIC i2 = new RecyclerPIC(six_url2[i]);
            List<RecyclerPIC> dataList_i = new ArrayList<>();
            dataList_i.add(i1);
            dataList_i.add(i2);
            RecyclerAdapter2 adapter2 = new RecyclerAdapter2(dataList_i);
            RecyclerData item = new RecyclerData(sixStar[i],  adapter2, six_voice[i]);
            dataList1.add(item);
        }
    }

    public static class RecyclerData {
        private String name;
        private RecyclerAdapter2 recyclerAdapter2;
        private int voiceID;

        public RecyclerData(String name, RecyclerAdapter2 recyclerAdapter2, int voiceID){
            this.name = name;
            this.recyclerAdapter2 = recyclerAdapter2;
            this.voiceID = voiceID;
        }

        public String getName(){
            return name;
        }

        public RecyclerAdapter2 getRecyclerAdapter2(){
            return recyclerAdapter2;
        }

        public int getVoiceID() {
            return voiceID;
        }
    }

    public static class RecyclerPIC {
        private String url;

        public RecyclerPIC(String url){
            this.url = url;
        }

        public String getUrl(){
            return url;
        }
    }

    public static class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder>{
        private List<RecyclerData> list;
        private Context mContext;

        class ViewHolder extends RecyclerView.ViewHolder{
            RecyclerView recyclerView;
            TextView text;

            public ViewHolder(View view){
                super(view);
                recyclerView = view.findViewById(R.id.different_levels_recycler);
                text = view.findViewById(R.id.person_name);

            }
        }

        public RecyclerAdapter1(List<RecyclerData> dataList){
            list = dataList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pictures_of_characters, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            RecyclerData t = list.get(position);
            holder.text.setText(t.getName());
            holder.text.setOnClickListener(v -> {
                if (TuJianActivity.player == null) {
                    TuJianActivity.player = MediaPlayer.create(v.getContext(), t.getVoiceID());
                    TuJianActivity.player.start();
                }
                else {
                    MediaPlayer p = MediaPlayer.create(v.getContext(), t.getVoiceID());
                    if (TuJianActivity.player.getDuration() == p.getDuration()) {
                        if (TuJianActivity.player.isPlaying())
                            TuJianActivity.player.stop();
                        else {
                            TuJianActivity.player.reset();
                            TuJianActivity.player = p;
                            TuJianActivity.player.start();
                        }
                    }
                    else {
                        TuJianActivity.player.stop();
                        TuJianActivity.player.reset();
                        TuJianActivity.player = p;
                        TuJianActivity.player.start();
                    }
                }
            });

            holder.recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(layoutManager);
            holder.recyclerView.setAdapter(t.getRecyclerAdapter2());
        }

        @Override
        public int getItemCount(){
            return list.size();
        }
    }

    public static class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>{
        private List<RecyclerPIC> list;
        class ViewHolder extends RecyclerView.ViewHolder{
            PhotoView img;
            View v;

            public ViewHolder(View view){
                super(view);
                v = view;
                img = view.findViewById(R.id.different_levels_of_pictures);
            }
        }

        public RecyclerAdapter2(List<RecyclerPIC> dataList){
            list = dataList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.different_levels_of_pictures, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            RecyclerPIC k = list.get(position);
            Glide.with(holder.v).load(k.getUrl()).into(holder.img);
        }

        @Override
        public int getItemCount(){
            return list.size();
        }
    }

    private final String[] sixStar = {
            // 54
            // 常驻
            // 44
            "能天使", "黑", "安洁莉娜", "银灰", "莫斯提马",
            "夜莺", "星熊", "陈", "阿", "煌",
            "麦哲伦", "赫拉格", "斯卡蒂", "塞雷娅", "闪灵",
            "艾雅法拉", "伊芙利特", "推进之王", "刻俄柏", "风笛",
            "傀影", "温蒂", "早露", "铃兰", "棘刺",
            "森蚺", "史尔特尔", "瑕光", "泥岩", "山",
            "空弦", "嵯峨", "异客", "凯尔希", "卡涅利安",
            "帕拉斯", "水月", "琴柳", "远牙", "焰尾",
            "灵知", "老鲤", "澄闪", "菲亚梅塔",
            // 限定
            // 10
            "W", "歌蕾蒂娅", "灰烬", "迷迭香", "年",
            "夕", "令", "浊心斯卡蒂", "假日威龙陈", "耀骑士临光",
    };

    private final int[] six_voice = {
            R.raw.nengtianshi_report_voice,
            R.raw.hei_report_voice,
            R.raw.anjielina_report_voice,
            R.raw.yinhui_report_voice,
            R.raw.mositima_report_voice,
            R.raw.yeying_report_voice,
            R.raw.xingxiong_report_voice,
            R.raw.chen_report_voice,
            R.raw.a_report_voice,
            R.raw.huang_report_voice,
            R.raw.maizhelun_report_voice,
            R.raw.helage_report_voice,
            R.raw.sikadi_report_voice,
            R.raw.saileiya_report_voice,
            R.raw.shanling_report_voice,
            R.raw.aiyafala_report_voice,
            R.raw.yifulite_report_voice,
            R.raw.tuijinzhiwang_report_voice,
            R.raw.keebai_report_voice,
            R.raw.fengdi_report_voice,
            R.raw.guiying_report_voice,
            R.raw.wendi_report_voice,
            R.raw.zaolu_report_voice,
            R.raw.linglan_report_voice,
            R.raw.jici_report_voice,
            R.raw.senran_report_voice,
            R.raw.shierteer_report_voice,
            R.raw.xiaguang_report_voice,
            R.raw.niyan_report_voice,
            R.raw.shan_report_voice,
            R.raw.kongxian_report_voice,
            R.raw.cuoe_report_voice,
            R.raw.yike_report_voice,
            R.raw.kaierxi_report_voice,
            R.raw.kanielian_report_voice,
            R.raw.palasi_report_voice,
            R.raw.shuiyue_report_voice,
            R.raw.qinliu_report_voice,
            R.raw.yuanya_report_voice,
            R.raw.yanwei_report_voice,
            R.raw.lingzhi_report_voice,
            R.raw.laoli_report_voice,
            R.raw.chengshan_report_voice,
            R.raw.feiyameita_report_voice,
            //
            R.raw.w_report_voice,
            R.raw.geleidiya_report_voice,
            R.raw.huijin_report_voice,
            R.raw.midiexiang_report_voice,
            R.raw.nian_report_voice,
            R.raw.xi_report_voice,
            R.raw.ling_report_voice,
            R.raw.zhuoxinsikadi_report_voice,
            R.raw.jiariweilongchen_report_voice,
            R.raw.yaoqishilinguang_report_voice,
    };

    private static String[] six_url = {
            "https://prts.wiki/images/b/bd/%E7%AB%8B%E7%BB%98_%E8%83%BD%E5%A4%A9%E4%BD%BF_1.png",
            "https://prts.wiki/images/7/7b/%E7%AB%8B%E7%BB%98_%E9%BB%91_1.png",
            "https://prts.wiki/images/f/fe/%E7%AB%8B%E7%BB%98_%E5%AE%89%E6%B4%81%E8%8E%89%E5%A8%9C_1.png",
            "https://prts.wiki/images/0/03/%E7%AB%8B%E7%BB%98_%E9%93%B6%E7%81%B0_1.png",
            "https://prts.wiki/images/c/cd/%E7%AB%8B%E7%BB%98_%E8%8E%AB%E6%96%AF%E6%8F%90%E9%A9%AC_1.png",
            "https://prts.wiki/images/6/6f/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E8%8E%BA_1.png",
            "https://prts.wiki/images/d/d4/%E7%AB%8B%E7%BB%98_%E6%98%9F%E7%86%8A_1.png",
            "https://prts.wiki/images/b/bc/%E7%AB%8B%E7%BB%98_%E9%99%88_1.png",
            "https://prts.wiki/images/6/67/%E7%AB%8B%E7%BB%98_%E9%98%BF_1.png",
            "https://prts.wiki/images/3/38/%E7%AB%8B%E7%BB%98_%E7%85%8C_1.png",
            "https://prts.wiki/images/9/93/%E7%AB%8B%E7%BB%98_%E9%BA%A6%E5%93%B2%E4%BC%A6_1.png",
            "https://prts.wiki/images/4/48/%E7%AB%8B%E7%BB%98_%E8%B5%AB%E6%8B%89%E6%A0%BC_1.png",
            "https://prts.wiki/images/4/45/%E7%AB%8B%E7%BB%98_%E6%96%AF%E5%8D%A1%E8%92%82_1.png",
            "https://prts.wiki/images/4/4e/%E7%AB%8B%E7%BB%98_%E5%A1%9E%E9%9B%B7%E5%A8%85_1.png",
            "https://prts.wiki/images/e/e9/%E7%AB%8B%E7%BB%98_%E9%97%AA%E7%81%B5_1.png",
            "https://prts.wiki/images/c/c0/%E7%AB%8B%E7%BB%98_%E8%89%BE%E9%9B%85%E6%B3%95%E6%8B%89_1.png",
            "https://prts.wiki/images/5/53/%E7%AB%8B%E7%BB%98_%E4%BC%8A%E8%8A%99%E5%88%A9%E7%89%B9_1.png",
            "https://prts.wiki/images/6/6f/%E7%AB%8B%E7%BB%98_%E6%8E%A8%E8%BF%9B%E4%B9%8B%E7%8E%8B_1.png",
            "https://prts.wiki/images/3/3d/%E7%AB%8B%E7%BB%98_%E5%88%BB%E4%BF%84%E6%9F%8F_1.png",
            "https://prts.wiki/images/5/5e/%E7%AB%8B%E7%BB%98_%E9%A3%8E%E7%AC%9B_1.png",
            "https://prts.wiki/images/3/32/%E7%AB%8B%E7%BB%98_%E5%82%80%E5%BD%B1_1.png",
            "https://prts.wiki/images/2/26/%E7%AB%8B%E7%BB%98_%E6%B8%A9%E8%92%82_1.png",
            "https://prts.wiki/images/6/6f/%E7%AB%8B%E7%BB%98_%E6%97%A9%E9%9C%B2_1.png",
            "https://prts.wiki/images/f/f5/%E7%AB%8B%E7%BB%98_%E9%93%83%E5%85%B0_1.png",
            "https://prts.wiki/images/e/e2/%E7%AB%8B%E7%BB%98_%E6%A3%98%E5%88%BA_1.png",
            "https://prts.wiki/images/f/f8/%E7%AB%8B%E7%BB%98_%E6%A3%AE%E8%9A%BA_1.png",
            "https://prts.wiki/images/0/0c/%E7%AB%8B%E7%BB%98_%E5%8F%B2%E5%B0%94%E7%89%B9%E5%B0%94_1.png",
            "https://prts.wiki/images/c/ce/%E7%AB%8B%E7%BB%98_%E7%91%95%E5%85%89_1.png",
            "https://prts.wiki/images/8/80/%E7%AB%8B%E7%BB%98_%E6%B3%A5%E5%B2%A9_1.png",
            "https://prts.wiki/images/7/74/%E7%AB%8B%E7%BB%98_%E5%B1%B1_1.png",
            "https://prts.wiki/images/d/da/%E7%AB%8B%E7%BB%98_%E7%A9%BA%E5%BC%A6_1.png",
            "https://prts.wiki/images/4/40/%E7%AB%8B%E7%BB%98_%E5%B5%AF%E5%B3%A8_1.png",
            "https://prts.wiki/images/1/16/%E7%AB%8B%E7%BB%98_%E5%BC%82%E5%AE%A2_1.png",
            "https://prts.wiki/images/7/72/%E7%AB%8B%E7%BB%98_%E5%87%AF%E5%B0%94%E5%B8%8C_1.png",
            "https://prts.wiki/images/d/d0/%E7%AB%8B%E7%BB%98_%E5%8D%A1%E6%B6%85%E5%88%A9%E5%AE%89_1.png",
            "https://prts.wiki/images/e/ef/%E7%AB%8B%E7%BB%98_%E5%B8%95%E6%8B%89%E6%96%AF_1.png",
            "https://prts.wiki/images/c/c9/%E7%AB%8B%E7%BB%98_%E6%B0%B4%E6%9C%88_1.png",
            "https://prts.wiki/images/3/3c/%E7%AB%8B%E7%BB%98_%E7%90%B4%E6%9F%B3_1.png",
            "https://prts.wiki/images/d/d6/%E7%AB%8B%E7%BB%98_%E8%BF%9C%E7%89%99_1.png",
            "https://prts.wiki/images/3/3b/%E7%AB%8B%E7%BB%98_%E7%84%B0%E5%B0%BE_1.png",
            "https://prts.wiki/images/4/4b/%E7%AB%8B%E7%BB%98_%E7%81%B5%E7%9F%A5_1.png",
            "https://prts.wiki/images/5/57/%E7%AB%8B%E7%BB%98_%E8%80%81%E9%B2%A4_1.png",
            "https://prts.wiki/images/4/46/%E7%AB%8B%E7%BB%98_%E6%BE%84%E9%97%AA_1.png",
            "https://prts.wiki/images/9/9c/%E7%AB%8B%E7%BB%98_%E8%8F%B2%E4%BA%9A%E6%A2%85%E5%A1%94_1.png",
            //
            "https://prts.wiki/images/4/44/%E7%AB%8B%E7%BB%98_W_1.png",
            "https://prts.wiki/images/8/84/%E7%AB%8B%E7%BB%98_%E6%AD%8C%E8%95%BE%E8%92%82%E5%A8%85_1.png",
            "https://prts.wiki/images/3/37/%E7%AB%8B%E7%BB%98_%E7%81%B0%E7%83%AC_1.png",
            "https://prts.wiki/images/5/59/%E7%AB%8B%E7%BB%98_%E8%BF%B7%E8%BF%AD%E9%A6%99_1.png",
            "https://prts.wiki/images/c/c9/%E7%AB%8B%E7%BB%98_%E5%B9%B4_1.png",
            "https://prts.wiki/images/6/6a/%E7%AB%8B%E7%BB%98_%E5%A4%95_1.png",
            "https://prts.wiki/images/d/df/%E7%AB%8B%E7%BB%98_%E4%BB%A4_1.png",
            "https://prts.wiki/images/2/26/%E7%AB%8B%E7%BB%98_%E6%B5%8A%E5%BF%83%E6%96%AF%E5%8D%A1%E8%92%82_1.png",
            "https://prts.wiki/images/b/b9/%E7%AB%8B%E7%BB%98_%E5%81%87%E6%97%A5%E5%A8%81%E9%BE%99%E9%99%88_1.png",
            "https://prts.wiki/images/9/91/%E7%AB%8B%E7%BB%98_%E8%80%80%E9%AA%91%E5%A3%AB%E4%B8%B4%E5%85%89_1.png",
    };

    private static String[] six_url2 = {
            "https://prts.wiki/images/1/19/%E7%AB%8B%E7%BB%98_%E8%83%BD%E5%A4%A9%E4%BD%BF_2.png",
            "https://prts.wiki/images/d/db/%E7%AB%8B%E7%BB%98_%E9%BB%91_2.png",
            "https://prts.wiki/images/0/02/%E7%AB%8B%E7%BB%98_%E5%AE%89%E6%B4%81%E8%8E%89%E5%A8%9C_2.png",
            "https://prts.wiki/images/5/5f/%E7%AB%8B%E7%BB%98_%E9%93%B6%E7%81%B0_2.png",
            "https://prts.wiki/images/8/85/%E7%AB%8B%E7%BB%98_%E8%8E%AB%E6%96%AF%E6%8F%90%E9%A9%AC_2.png",
            "https://prts.wiki/images/b/b3/%E7%AB%8B%E7%BB%98_%E5%A4%9C%E8%8E%BA_2.png",
            "https://prts.wiki/images/0/0a/%E7%AB%8B%E7%BB%98_%E6%98%9F%E7%86%8A_2.png",
            "https://prts.wiki/images/d/d0/%E7%AB%8B%E7%BB%98_%E9%99%88_2.png",
            "https://prts.wiki/images/a/ac/%E7%AB%8B%E7%BB%98_%E9%98%BF_2.png",
            "https://prts.wiki/images/4/40/%E7%AB%8B%E7%BB%98_%E7%85%8C_2.png",
            "https://prts.wiki/images/c/cf/%E7%AB%8B%E7%BB%98_%E9%BA%A6%E5%93%B2%E4%BC%A6_2.png",
            "https://prts.wiki/images/1/10/%E7%AB%8B%E7%BB%98_%E8%B5%AB%E6%8B%89%E6%A0%BC_2.png",
            "https://prts.wiki/images/b/b8/%E7%AB%8B%E7%BB%98_%E6%96%AF%E5%8D%A1%E8%92%82_2.png",
            "https://prts.wiki/images/2/2e/%E7%AB%8B%E7%BB%98_%E5%A1%9E%E9%9B%B7%E5%A8%85_2.png",
            "https://prts.wiki/images/c/c7/%E7%AB%8B%E7%BB%98_%E9%97%AA%E7%81%B5_2.png",
            "https://prts.wiki/images/2/21/%E7%AB%8B%E7%BB%98_%E8%89%BE%E9%9B%85%E6%B3%95%E6%8B%89_2.png",
            "https://prts.wiki/images/f/f2/%E7%AB%8B%E7%BB%98_%E4%BC%8A%E8%8A%99%E5%88%A9%E7%89%B9_2.png",
            "https://prts.wiki/images/d/dd/%E7%AB%8B%E7%BB%98_%E6%8E%A8%E8%BF%9B%E4%B9%8B%E7%8E%8B_2.png",
            "https://prts.wiki/images/6/6d/%E7%AB%8B%E7%BB%98_%E5%88%BB%E4%BF%84%E6%9F%8F_2.png",
            "https://prts.wiki/images/9/9d/%E7%AB%8B%E7%BB%98_%E9%A3%8E%E7%AC%9B_2.png",
            "https://prts.wiki/images/6/6f/%E7%AB%8B%E7%BB%98_%E5%82%80%E5%BD%B1_2.png",
            "https://prts.wiki/images/c/c2/%E7%AB%8B%E7%BB%98_%E6%B8%A9%E8%92%82_2.png",
            "https://prts.wiki/images/8/87/%E7%AB%8B%E7%BB%98_%E6%97%A9%E9%9C%B2_2.png",
            "https://prts.wiki/images/8/87/%E7%AB%8B%E7%BB%98_%E9%93%83%E5%85%B0_2.png",
            "https://prts.wiki/images/4/41/%E7%AB%8B%E7%BB%98_%E6%A3%98%E5%88%BA_2.png",
            "https://prts.wiki/images/6/6c/%E7%AB%8B%E7%BB%98_%E6%A3%AE%E8%9A%BA_2.png",
            "https://prts.wiki/images/2/2e/%E7%AB%8B%E7%BB%98_%E5%8F%B2%E5%B0%94%E7%89%B9%E5%B0%94_2.png",
            "https://prts.wiki/images/2/2e/%E7%AB%8B%E7%BB%98_%E7%91%95%E5%85%89_2.png",
            "https://prts.wiki/images/7/72/%E7%AB%8B%E7%BB%98_%E6%B3%A5%E5%B2%A9_2.png",
            "https://prts.wiki/images/c/cf/%E7%AB%8B%E7%BB%98_%E5%B1%B1_2.png",
            "https://prts.wiki/images/4/46/%E7%AB%8B%E7%BB%98_%E7%A9%BA%E5%BC%A6_2.png",
            "https://prts.wiki/images/9/9c/%E7%AB%8B%E7%BB%98_%E5%B5%AF%E5%B3%A8_2.png",
            "https://prts.wiki/images/3/3f/%E7%AB%8B%E7%BB%98_%E5%BC%82%E5%AE%A2_2.png",
            "https://prts.wiki/images/6/65/%E7%AB%8B%E7%BB%98_%E5%87%AF%E5%B0%94%E5%B8%8C_2.png",
            "https://prts.wiki/images/e/ee/%E7%AB%8B%E7%BB%98_%E5%8D%A1%E6%B6%85%E5%88%A9%E5%AE%89_2.png",
            "https://prts.wiki/images/e/ea/%E7%AB%8B%E7%BB%98_%E5%B8%95%E6%8B%89%E6%96%AF_2.png",
            "https://prts.wiki/images/0/09/%E7%AB%8B%E7%BB%98_%E6%B0%B4%E6%9C%88_2.png",
            "https://prts.wiki/images/0/09/%E7%AB%8B%E7%BB%98_%E7%90%B4%E6%9F%B3_2.png",
            "https://prts.wiki/images/2/24/%E7%AB%8B%E7%BB%98_%E8%BF%9C%E7%89%99_2.png",
            "https://prts.wiki/images/7/7c/%E7%AB%8B%E7%BB%98_%E7%84%B0%E5%B0%BE_2.png",
            "https://prts.wiki/images/0/09/%E7%AB%8B%E7%BB%98_%E7%81%B5%E7%9F%A5_2.png",
            "https://prts.wiki/images/1/1f/%E7%AB%8B%E7%BB%98_%E8%80%81%E9%B2%A4_2.png",
            "https://prts.wiki/images/d/d0/%E7%AB%8B%E7%BB%98_%E6%BE%84%E9%97%AA_2.png",
            "https://prts.wiki/images/4/47/%E7%AB%8B%E7%BB%98_%E8%8F%B2%E4%BA%9A%E6%A2%85%E5%A1%94_2.png",
            //
            "https://prts.wiki/images/3/39/%E7%AB%8B%E7%BB%98_W_2.png",
            "https://prts.wiki/images/6/61/%E7%AB%8B%E7%BB%98_%E6%AD%8C%E8%95%BE%E8%92%82%E5%A8%85_2.png",
            "https://prts.wiki/images/2/28/%E7%AB%8B%E7%BB%98_%E7%81%B0%E7%83%AC_2.png",
            "https://prts.wiki/images/0/07/%E7%AB%8B%E7%BB%98_%E8%BF%B7%E8%BF%AD%E9%A6%99_2.png",
            "https://prts.wiki/images/1/15/%E7%AB%8B%E7%BB%98_%E5%B9%B4_2.png",
            "https://prts.wiki/images/0/0a/%E7%AB%8B%E7%BB%98_%E5%A4%95_2.png",
            "https://prts.wiki/images/a/a3/%E7%AB%8B%E7%BB%98_%E4%BB%A4_2.png",
            "https://prts.wiki/images/b/b3/%E7%AB%8B%E7%BB%98_%E6%B5%8A%E5%BF%83%E6%96%AF%E5%8D%A1%E8%92%82_2.png",
            "https://prts.wiki/images/4/4e/%E7%AB%8B%E7%BB%98_%E5%81%87%E6%97%A5%E5%A8%81%E9%BE%99%E9%99%88_2.png",
            "https://prts.wiki/images/e/ec/%E7%AB%8B%E7%BB%98_%E8%80%80%E9%AA%91%E5%A3%AB%E4%B8%B4%E5%85%89_2.png",
    };
}
