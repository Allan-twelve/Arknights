package com.allan.arknight;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    public static int CountsNotSix, CountsNotFS, CountsAll, Counts3, Counts4, Counts5, Counts6 = 0;
    public static int TenCounts = 1;
    public static long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = System.currentTimeMillis();
        Button btn1, btn2, btn3, btn4;
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn1.setOnClickListener(new b1Click());
        btn2.setOnClickListener(new b2Click());
        btn3.setOnClickListener(new b3Click());
        btn4.setOnClickListener(new b4Click());
    }


    class b1Click implements OnClickListener {
        @Override
        public void onClick(View view) {
            if (notFastClick()){
                Intent intent = new Intent(MainActivity.this, GongKaiZhaoMuActivity.class);
                startActivity(intent);
            }
        }
    }

    class b2Click implements OnClickListener {
        @Override
        public void onClick(View view) {
            if (notFastClick()) {
                Intent intent = new Intent(MainActivity.this, SelectedSearchingActivity.class);
                startActivity(intent);
            }
        }
    }

    class b3Click implements OnClickListener{
        @Override
        public void onClick(View v) {
            if (notFastClick()){
                Intent intent = new Intent(MainActivity.this, MaterialActivity.class);
                startActivity(intent);
            }
        }
    }

    class b4Click implements OnClickListener {
        @Override
        public void onClick(View view) {
            if (notFastClick()) {
                Intent intent = new Intent(MainActivity.this, TuJianActivity.class);
                startActivity(intent);
            }
        }
    }

    public static boolean notFastClick(){
        long t, delta;
        t = System.currentTimeMillis();
        delta =  t - MainActivity.time;
        MainActivity.time = t;
        return delta >= 500;
    }
}