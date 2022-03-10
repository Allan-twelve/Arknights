package com.allan.arknight;

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

public class OnePicturesActivity extends AppCompatActivity {

    private List<RecyclerData> dataList = new ArrayList<>();

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

        v.setItemViewCacheSize(4);
        v.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        v.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(dataList);
        v.setAdapter(adapter);
    }

    private void init(){
        for (int i = 0; i < oneStar.length; i++) {
            RecyclerData item = new RecyclerData(oneStar[i], one_voice[i], one_url[i]);
            dataList.add(item);
        }
    }

    public static class RecyclerData {
        private String name;
        private int voiceID;
        private String url;

        public RecyclerData(String name, int voiceID, String url){
            this.name = name;
            this.voiceID = voiceID;
            this.url = url;
        }

        public String getName(){
            return name;
        }

        public int getVoiceID() {
            return voiceID;
        }

        public String getUrl() {return  url;}
    }

    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
        private List<RecyclerData> list;
        class ViewHolder extends RecyclerView.ViewHolder{
            PhotoView img;
            TextView text;
            View v;
            public ViewHolder(View view){
                super(view);
                v=view;
                img = view.findViewById(R.id.image_simple);
                text = view.findViewById(R.id.person_name_simple);
            }
        }

        public RecyclerAdapter(List<RecyclerData> dataList){
            list = dataList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pictures_of_characters_simple, parent,
                    false);
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
            Glide.with(holder.v).load(t.getUrl()).into(holder.img);
        }

        @Override
        public int getItemCount(){
            return list.size();
        }
    }

    private final String[] oneStar = {
            // 4个
            // 非寻访
            "THRM-EX", "Castle-3", "Lancet-2", "正义骑士号",
    };

    private final int[] one_voice = {
            R.raw.thrm_ex_report_voice,
            R.raw.castle_3_report_voice,
            R.raw.lancet_2_report_voice,
            R.raw.zhengyiqishihao_report_voice,
    };

    private final String[] one_url = {
            "https://prts.wiki/images/8/8a/%E7%AB%8B%E7%BB%98_THRM-EX_1.png",
            "https://prts.wiki/images/9/95/%E7%AB%8B%E7%BB%98_Castle-3_1.png",
            "https://prts.wiki/images/8/8e/%E7%AB%8B%E7%BB%98_Lancet-2_1.png",
            "https://prts.wiki/images/e/e2/%E7%AB%8B%E7%BB%98_%E6%AD%A3%E4%B9%89%E9%AA%91%E5%A3%AB%E5%8F%B7_1.png"
    };
}
