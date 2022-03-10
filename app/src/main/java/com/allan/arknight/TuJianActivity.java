package com.allan.arknight;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TuJianActivity extends AppCompatActivity {

    static MediaPlayer player = null;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null){
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujian);
        Button btn1, btn2, btn3, btn4, btn5, btn6;
        btn1 = findViewById(R.id.onePictures);
        btn2 = findViewById(R.id.twoPictures);
        btn3 = findViewById(R.id.threePictures);
        btn4 = findViewById(R.id.fourPictures);
        btn5 = findViewById(R.id.fivePictures);
        btn6 = findViewById(R.id.sixPictures);
        btn1.setOnClickListener(new b1Click());
        btn2.setOnClickListener(new b2Click());
        btn3.setOnClickListener(new b3Click());
        btn4.setOnClickListener(new b4Click());
        btn5.setOnClickListener(new b5Click());
        btn6.setOnClickListener(new b6Click());
    }

    class b1Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                Intent intent = new Intent(TuJianActivity.this, OnePicturesActivity.class);
                startActivity(intent);
            }
        }
    }

    class b2Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                Intent intent = new Intent(TuJianActivity.this, TwoPicturesActivity.class);
                startActivity(intent);
            }
        }
    }

    class b3Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                Intent intent = new Intent(TuJianActivity.this, ThreePicturesActivity.class);
                startActivity(intent);
            }
        }
    }

    class b4Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                Intent intent = new Intent(TuJianActivity.this, FourPicturesActivity.class);
                startActivity(intent);
            }
        }
    }

    class b5Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                Intent intent = new Intent(TuJianActivity.this, FivePicturesActivity.class);
                startActivity(intent);
            }
        }
    }

    class b6Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                Intent intent = new Intent(TuJianActivity.this, SixPicturesActivity.class);
                startActivity(intent);
            }
        }
    }

}
