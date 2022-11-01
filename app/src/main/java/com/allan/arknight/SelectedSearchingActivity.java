package com.allan.arknight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectedSearchingActivity extends AppCompatActivity {

    // 修改常驻干员数量
    private final int SEARCHING_SIX = 51, SEARCHING_FIVE = 71, SEARCHING_FOUR = 43;

    ImageView img;
    EditText percent;
    String[] names = new String[10];
    int[] color = new int[10];
    private float percent_of_six = -1, percent_of_five = -1, percent_of_four = -1;
    CheckBox checkbtn, select_all;
    Button btnh, enter, reset_all;
    TextView ten_name;
    MediaPlayer player = null;
    Spinner star_select, person_select, up_select;
    private List<StarPersonData> dataList_six = new ArrayList<>(),
            dataList_five = new ArrayList<>(),
            dataList_four = new ArrayList<>(),
            dataList_six_up = new ArrayList<>(),
            dataList_five_up = new ArrayList<>(),
            dataList_four_up = new ArrayList<>();
    private OperatorAdapter adapter_six, adapter_five, adapter_four;
    private mOperatorAdapter adapter_six_up, adapter_five_up, adapter_four_up;

    int showNum = 0;
    int rate;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_searching);
        Button btn1, btn2, btn3, btn4, btn5;
        btn1 = findViewById(R.id.search_once);
        btn2 = findViewById(R.id.search_ten_times);
        btn3 = findViewById(R.id.cal_total);
        btn4 = findViewById(R.id.reset_data);
        btn5 = findViewById(R.id.searching_setting);
        btnh = findViewById(R.id.hide);
        btnh.setVisibility(View.GONE);
        ten_name = findViewById(R.id.tenText);
        img = findViewById(R.id.tenPictures);
        checkbtn = findViewById(R.id.tenChoice);
        btn1.setOnClickListener(new b1Click());
        btn2.setOnClickListener(new b2Click());
        btn3.setOnClickListener(new b3Click());
        btn4.setOnClickListener(new b4Click());
        btn5.setOnClickListener(new b5Click());
        btnh.setOnClickListener(new bhClick());
        img.setOnClickListener(new p1Click());
        if (player != null) {
            player.stop();
        }
    }

    public class StarPersonData{
        private String name;
        private int voiceID;
        private String url;
        private boolean checked_status;

        public StarPersonData(String name, int voiceID, String url, boolean checked_status){
            this.name = name;
            this.voiceID = voiceID;
            this.url = url;
            this.checked_status = checked_status;
        }

        public String getName() {
            return name;
        }

        public int getVoiceID() {
            return voiceID;
        }

        public String getUrl() {return  url;}

        public boolean isChecked_status() {
            return checked_status;
        }
    }

    private boolean firstAdapterInit(CheckBox box){
        if (star_select.getSelectedItemPosition() == 0) {
            if (dataList_six.get(0).getName().equals(box.getText().toString())) {
                dataList_six.get(0).checked_status = box.isChecked();
                person_select.setAdapter(adapter_six);
                return false;
            }
        }
        else if (star_select.getSelectedItemPosition() == 1) {
            if (dataList_five.get(0).getName().equals(box.getText().toString())) {
                dataList_five.get(0).checked_status = box.isChecked();
                person_select.setAdapter(adapter_five);
                return false;
            }
        }
        else {
            if (dataList_four.get(0).getName().equals(box.getText().toString())) {
                dataList_four.get(0).checked_status = box.isChecked();
                person_select.setAdapter(adapter_four);
                return false;
            }
        }
        return true;
    }

    private boolean secondAdapterInit(CheckBox box) {
        if (star_select.getSelectedItemPosition() == 0) {
            if (dataList_six_up.get(0).getName().equals(box.getText().toString())) {
                dataList_six_up.get(0).checked_status = box.isChecked();
                up_select.setAdapter(adapter_six_up);
                return false;
            }
        } else if (star_select.getSelectedItemPosition() == 1) {
            if (dataList_five_up.get(0).getName().equals(box.getText().toString())) {
                dataList_five_up.get(0).checked_status = box.isChecked();
                up_select.setAdapter(adapter_five_up);
                return false;
            }
        } else {
            if (dataList_four_up.get(0).getName().equals(box.getText().toString())) {
                dataList_four_up.get(0).checked_status = box.isChecked();
                up_select.setAdapter(adapter_four_up);
                return false;
            }
        }
        return true;
    }


    public class OperatorAdapter extends BaseAdapter{
        private List<StarPersonData> list;

        public OperatorAdapter(List<StarPersonData> dataList){
            list = dataList;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            StarPersonData t = list.get(position);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.operators_list, parent, false);
            if (view != null){
                CheckBox box = view.findViewById(R.id.all_operator);
                box.setText(t.getName());
                box.setChecked(t.isChecked_status());
                box.setOnClickListener(v -> {
                    if (firstAdapterInit(box))
                        t.checked_status = box.isChecked();
                    select_all.setChecked(isAllSelected());
                    upAdapterUpInit(true);
                });
            }
            return view;
        }
    }

    private boolean isAllSelected(){
        if (star_select.getSelectedItemPosition() == 0){
            for (StarPersonData data : dataList_six) {
                if (!data.isChecked_status())
                    return false;
            }
            return true;
        }
        else if (star_select.getSelectedItemPosition() == 1){
            for (StarPersonData data : dataList_five) {
                if (!data.isChecked_status())
                    return false;
            }
            return true;
        }
        else {
            for (StarPersonData data : dataList_four) {
                if (!data.isChecked_status())
                    return false;
            }
            return true;
        }
    }

    public class mOperatorAdapter extends OperatorAdapter {
        List<StarPersonData> list;
        public mOperatorAdapter(List<StarPersonData> dataList) {
            super(dataList);
            list = dataList;
        }

        private int checkedNum = 0;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            StarPersonData t = list.get(position);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.operators_list, parent, false);
            if (view != null){
                CheckBox box = view.findViewById(R.id.all_operator);
                box.setText(t.getName());
                box.setChecked(t.isChecked_status());
                box.setOnClickListener(v -> {
                    if (box.isChecked()) {
                        if (checkedNum < 4) {
                            if (secondAdapterInit(box))
                                t.checked_status = box.isChecked();
                            checkedNum += 1;
                        }
                        else {
                            box.setChecked(!box.isChecked());
                            Toast.makeText(view.getContext(), "最多选择四个哦！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        if (secondAdapterInit(box))
                            t.checked_status = box.isChecked();
                        checkedNum -= 1;
                    }
                });
            }
            return view;
        }
    }

    class b1Click implements View.OnClickListener{
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedSearchingActivity.this);

        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                View view = getLayoutInflater().inflate(R.layout.oneoutput, null);
                if (player != null) {
                    player.stop();
                    player.reset();
                }
                DrawPerson(view);
                builder.setOnDismissListener(new SelectedSearchingActivity.dialogDismiss());
                builder.setTitle("寻访结果");
                builder.setView(view);
                builder.create();
                builder.show();
                player.start();
            }
        }
    }

    class b2Click implements View.OnClickListener {
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedSearchingActivity.this);
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                for (int i = 0; i < 9; i++) {
                    DrawTen(i);
                }
                if (MainActivity.TenCounts == 1) {
                    DrawProtect();
                } else
                    DrawTen(9);
                if (checkbtn.isChecked()) {
                    Glide.with(v).load(show[showNum]).placeholder(R.drawable.transparent).into(img);
                    ten_name.setText(names[showNum]);
                    ten_name.setTextColor(color[showNum]);
                    ten_name.setTextSize(30);
                    btnh.setVisibility(View.VISIBLE);
                    if (player != null) {
                        player.stop();
                        player.reset();
                    }
                    player = MediaPlayer.create(v.getContext(), voices[showNum++]);
                    player.start();
                } else {
                    View view = getLayoutInflater().inflate(R.layout.tenoutput, null);
                    show_name(view);
                    builder.setTitle("寻访结果");
                    builder.setView(view);
                    builder.create();
                    builder.show();
                }
            }
        }
    }

    class b3Click implements View.OnClickListener {
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedSearchingActivity.this);
        TextView counts3, counts4, counts5, counts6, countsAll, counts;

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                View view = getLayoutInflater().inflate(R.layout.tongji, null);
                counts3 = view.findViewById(R.id.counts3);
                counts4 = view.findViewById(R.id.counts4);
                counts5 = view.findViewById(R.id.counts5);
                counts6 = view.findViewById(R.id.counts6);
                countsAll = view.findViewById(R.id.countsAll);
                counts = view.findViewById(R.id.counts);
                counts3.setText("三星次数：" + MainActivity.Counts3);
                counts4.setText("四星次数：" + MainActivity.Counts4);
                counts5.setText("五星次数：" + MainActivity.Counts5);
                counts6.setText("六星次数：" + MainActivity.Counts6);
                countsAll.setText("总共抽到干员次数：" + MainActivity.CountsAll);
                counts.setText("连续没有抽到六星次数：" + MainActivity.CountsNotSix);
                builder.setTitle("统计").setView(view);
                builder.create();
                builder.show();
            }
        }
    }

    class dialogDismiss implements DialogInterface.OnDismissListener{
        @Override
        public void onDismiss(DialogInterface dialog){
            if (player != null) {
                player.stop();
                player.release();
                player = null;
            }
        }
    }

    class b4Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MainActivity.TenCounts = 1;
            MainActivity.CountsNotFS = 0;
            MainActivity.CountsNotSix = 0;
            MainActivity.CountsAll = 0;
            MainActivity.Counts6 = 0;
            MainActivity.Counts5 = 0;
            MainActivity.Counts4 = 0;
            MainActivity.Counts3 = 0;
            Toast.makeText(SelectedSearchingActivity.this, "重置成功", Toast.LENGTH_SHORT).show();
        }
    }

    class b5Click implements View.OnClickListener {
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedSearchingActivity.this);
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                View view = getLayoutInflater().inflate(R.layout.searching_setting, null);
                star_select = view.findViewById(R.id.star_spinner);
                person_select = view.findViewById(R.id.person_spinner);
                up_select = view.findViewById(R.id.up_spinner);
                percent = view.findViewById(R.id.input_percent);
                select_all = view.findViewById(R.id.select_all_or_not);
                enter = view.findViewById(R.id.enter_input_percent);
                reset_all = view.findViewById(R.id.reset_searching_setting);
                select_all.setOnClickListener(v1 -> {
                    if (select_all.isChecked()){
                        if (star_select.getSelectedItemPosition() == 0) {
                            dataList_six.clear();
                            dataList_six_up.clear();
                            for (int i = 0; i < sixStars.length; i++) {
                                StarPersonData item1, item2;
                                item1 = new StarPersonData(sixStars[i], six_voice[i], six_url[i], true);
                                item2 = new StarPersonData(sixStars[i], six_voice[i], six_url[i], false);
                                dataList_six.add(item1);
                                dataList_six_up.add(item2);
                            }
                            adapter_six = new OperatorAdapter(dataList_six);
                            person_select.setAdapter(adapter_six);
                            adapter_six_up = new mOperatorAdapter(dataList_six_up);
                        }
                        else if (star_select.getSelectedItemPosition() == 1){
                            dataList_five.clear();
                            dataList_five_up.clear();
                            for (int i = 0; i < fiveStars.length; i++) {
                                StarPersonData item1, item2;
                                item1 = new StarPersonData(fiveStars[i], five_voice[i], five_url[i], true);
                                item2 = new StarPersonData(fiveStars[i], five_voice[i], five_url[i], false);
                                dataList_five.add(item1);
                                dataList_five_up.add(item2);
                            }
                            adapter_five = new OperatorAdapter(dataList_five);
                            person_select.setAdapter(adapter_five);
                            adapter_five_up = new mOperatorAdapter(dataList_five_up);
                        }
                        else {
                            dataList_four.clear();
                            dataList_four_up.clear();
                            for (int i = 0; i < fourStars.length; i++) {
                                StarPersonData item1, item2;
                                item1 = new StarPersonData(fourStars[i], four_voice[i], four_url[i], true);
                                item2 = new StarPersonData(fourStars[i], four_voice[i], four_url[i], false);
                                dataList_four.add(item1);
                                dataList_four_up.add(item2);
                            }
                            adapter_four = new OperatorAdapter(dataList_four);
                            person_select.setAdapter(adapter_four);
                            adapter_four_up = new mOperatorAdapter(dataList_four_up);
                        }
                    }
                    else{
                        if (star_select.getSelectedItemPosition() == 0) {
                            dataList_six.clear();
                            dataList_six_up.clear();
                            for (int i = 0; i < sixStars.length; i++) {
                                StarPersonData item;
                                item = new StarPersonData(sixStars[i], six_voice[i], six_url[i], false);
                                dataList_six.add(item);
                            }
                            adapter_six = new OperatorAdapter(dataList_six);
                            person_select.setAdapter(adapter_six);
                        }
                        else if (star_select.getSelectedItemPosition() == 1){
                            dataList_five.clear();
                            dataList_five_up.clear();
                            for (int i = 0; i < fiveStars.length; i++) {
                                StarPersonData item;
                                item = new StarPersonData(fiveStars[i], five_voice[i], five_url[i], false);
                                dataList_five.add(item);
                            }
                            adapter_five = new OperatorAdapter(dataList_five);
                            person_select.setAdapter(adapter_five);
                        }
                        else {
                            dataList_four.clear();
                            dataList_four_up.clear();
                            for (int i = 0; i < fourStars.length; i++) {
                                StarPersonData item;
                                item = new StarPersonData(fourStars[i], four_voice[i], four_url[i], false);
                                dataList_four.add(item);
                            }
                            adapter_four = new OperatorAdapter(dataList_four);
                            person_select.setAdapter(adapter_four);
                        }
                    }
                });
                enter.setOnClickListener(v2 -> {
                    if (!percent.getText().toString().equals("")) {
                        if (Float.parseFloat(percent.getText().toString()) > 100 ||
                                Float.parseFloat(percent.getText().toString()) <= 0) {
                            Toast.makeText(view.getContext(), "概率应在0~100之间哦！", Toast.LENGTH_SHORT).show();
                            percent.setText("");
                        }
                        else {
                            String s = percent.getText().toString();
                            if (star_select.getSelectedItemPosition() == 0) {
                                if (isCheckedZero(dataList_six_up)) {
                                    Toast.makeText(view.getContext(), "请先选择up干员哦！",
                                            Toast.LENGTH_SHORT).show();
                                    percent.setText("");
                                }
                                else if (percentCorrectJudge(dataList_six_up) ||
                                        percent.getText().toString().equals("100") ||
                                                percent.getText().toString().equals("100.0")) {
                                    percent_of_six = Float.parseFloat(s);
                                    Toast.makeText(view.getContext(), "设置成功！",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(view.getContext(), "全选时概率只能为100%哦！",
                                            Toast.LENGTH_SHORT).show();
                                    percent.setText("100.0");
                                    percent_of_six = 100;
                                }
                            }
                            if (star_select.getSelectedItemPosition() == 1) {
                                if (isCheckedZero(dataList_five_up)) {
                                    Toast.makeText(view.getContext(), "请先选择up干员哦！",
                                            Toast.LENGTH_SHORT).show();
                                    percent.setText("");
                                }
                                else if (percentCorrectJudge(dataList_five_up) ||
                                        percent.getText().toString().equals("100") ||
                                        percent.getText().toString().equals("100.0")) {
                                    percent_of_five = Float.parseFloat(s);
                                    Toast.makeText(view.getContext(), "设置成功！", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(view.getContext(), "全选时概率只能为100%哦！", Toast.LENGTH_SHORT).show();
                                    percent.setText("100.0");
                                    percent_of_five = 100;
                                }
                            }
                            if (star_select.getSelectedItemPosition() == 2) {
                                if (isCheckedZero(dataList_four_up)) {
                                    Toast.makeText(view.getContext(), "请先选择up干员哦！",
                                            Toast.LENGTH_SHORT).show();
                                    percent.setText("");
                                }
                                else if (percentCorrectJudge(dataList_four_up) ||
                                        percent.getText().toString().equals("100") ||
                                        percent.getText().toString().equals("100.0")) {
                                    percent_of_four = Float.parseFloat(s);
                                    Toast.makeText(view.getContext(), "设置成功！", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(view.getContext(), "全选时概率只能为100%哦！", Toast.LENGTH_SHORT).show();
                                    percent.setText("100.0");
                                    percent_of_four = 100;
                                }
                            }
                        }
                    }
                });
                reset_all.setOnClickListener(v3 -> {
                    dataList_four.clear();
                    dataList_five.clear();
                    dataList_six.clear();
                    dataList_four_up.clear();
                    dataList_five_up.clear();
                    dataList_six_up.clear();
                    percent_of_four = -1;
                    percent_of_five = -1;
                    percent_of_six = -1;
                    star_select.setSelection(0);
                    for (int i = 0; i < sixStars.length; i++) {
                        StarPersonData item;
                        if (i < SEARCHING_SIX)
                            item = new StarPersonData(sixStars[i], six_voice[i], six_url[i], true);
                        else
                            item = new StarPersonData(sixStars[i], six_voice[i], six_url[i], false);
                        dataList_six.add(item);
                    }
                    adapter_six = new OperatorAdapter(dataList_six);
                    person_select.setAdapter(adapter_six);
                    upAdapterUpInit(false);
                    percent.setText("");
                    select_all.setChecked(false);
                });
                star_select.setOnItemSelectedListener(new MyOnSelectedListener());
                builder.setTitle("寻访设置");
                builder.setView(view);
                builder.create();
                builder.show();
            }
        }
    }

    private boolean percentCorrectJudge(List<StarPersonData> list){
        for (StarPersonData data : list){
            if (!data.isChecked_status())
                return true;
        }
        return false;
    }

    private StarPersonData upSearching(List<StarPersonData> list, float percent){
        if (isCheckedZero(list) || percent == -1)
            return list.get(getRandomNumber(0, list.size() - 1));
        else {
            List<StarPersonData> upList = new ArrayList<>(),
                    normalList = new ArrayList<>();
            for (StarPersonData starPersonData : list) {
                if (starPersonData.isChecked_status())
                    upList.add(starPersonData);
                else
                    normalList.add(starPersonData);
            }
            int num = getRandomNumber(1, 1000);
            int up = getRandomNumber(1, upList.size());
            int normal = getRandomNumber(1, normalList.size());
            if (num <= percent * 10)
                return upList.get(up - 1);
            else return normalList.get(normal - 1);
        }
    }

    public void upAdapterUpInit(boolean reset){
        StarPersonData data;
        if (star_select.getSelectedItemPosition() == 0 && !dataList_six.isEmpty()) {
            if (reset || dataList_six_up.isEmpty()) {
                if (!dataList_six_up.isEmpty())
                    dataList_six_up.clear();
                for (StarPersonData dataListSix : dataList_six)
                    if (dataListSix.isChecked_status()) {
                        data = new StarPersonData(dataListSix.getName(),
                                dataListSix.getVoiceID(), dataListSix.getUrl(), false);
                        dataList_six_up.add(data);
                    }
                adapter_six_up = new mOperatorAdapter(dataList_six_up);
            }
            up_select.setAdapter(adapter_six_up);
        }
        else if (star_select.getSelectedItemPosition() == 1 && !dataList_five.isEmpty()) {
            if (reset || dataList_five_up.isEmpty()) {
                if (!dataList_five_up.isEmpty())
                    dataList_five_up.clear();
                for (StarPersonData dataListFive : dataList_five)
                    if (dataListFive.isChecked_status()) {
                        data = new StarPersonData(dataListFive.getName(),
                                dataListFive.getVoiceID(), dataListFive.getUrl(), false);
                        dataList_five_up.add(data);
                    }
                adapter_five_up = new mOperatorAdapter(dataList_five_up);
            }
            up_select.setAdapter(adapter_five_up);
        }
        else {
            if (reset || dataList_four_up.isEmpty()) {
                if (!dataList_four_up.isEmpty())
                    dataList_four_up.clear();
                for (StarPersonData dataListFour : dataList_four)
                    if (dataListFour.isChecked_status()) {
                        data = new StarPersonData(dataListFour.getName(),
                                dataListFour.getVoiceID(), dataListFour.getUrl(), false);
                        dataList_four_up.add(data);
                    }
                adapter_four_up = new mOperatorAdapter(dataList_four_up);
            }
            up_select.setAdapter(adapter_four_up);
        }
    }

    class MyOnSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                if (dataList_six.isEmpty()) {
                    for (int i = 0; i < sixStars.length; i++) {
                        StarPersonData item;
                        if (i < SEARCHING_SIX)
                            item = new StarPersonData(sixStars[i], six_voice[i], six_url[i], true);
                        else
                            item = new StarPersonData(sixStars[i], six_voice[i], six_url[i], false);
                        dataList_six.add(item);
                    }
                    adapter_six = new OperatorAdapter(dataList_six);
                }
                person_select.setAdapter(adapter_six);
                if (percent_of_six == -1)
                    percent.setText("");
                else percent.setText(String.valueOf(percent_of_six));
            }
            else if (position == 1){
                if (dataList_five.isEmpty()) {
                    for (int i = 0; i < fiveStars.length; i++) {
                        StarPersonData item;
                        if (i < SEARCHING_FIVE)
                            item = new StarPersonData(fiveStars[i], five_voice[i], five_url[i], true);
                        else
                            item = new StarPersonData(fiveStars[i], five_voice[i], five_url[i], false);
                        dataList_five.add(item);
                    }
                    adapter_five = new OperatorAdapter(dataList_five);
                }
                person_select.setAdapter(adapter_five);
                if (percent_of_five == -1)
                    percent.setText("");
                else percent.setText(String.valueOf(percent_of_five));
            }
            else {
                if (dataList_four.isEmpty()) {
                    for (int i = 0; i < fourStars.length; i++) {
                        StarPersonData item;
                        if (i < SEARCHING_FOUR)
                            item = new StarPersonData(fourStars[i], four_voice[i], four_url[i], true);
                        else
                            item = new StarPersonData(fourStars[i], four_voice[i], four_url[i], false);
                        dataList_four.add(item);
                    }
                    adapter_four = new OperatorAdapter(dataList_four);
                }
                person_select.setAdapter(adapter_four);
                if (percent_of_four == -1)
                    percent.setText("");
                else percent.setText(String.valueOf(percent_of_four));
            }
            upAdapterUpInit(false);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    class bhClick implements View.OnClickListener {
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedSearchingActivity.this);

        @Override
        public void onClick(View v) {
            if (MainActivity.notFastClick()) {
                View view = getLayoutInflater().inflate(R.layout.tenoutput, null);
                player.stop();
                player.release();
                player = null;
                show_name(view);
                img.setImageDrawable(null);
                ten_name.setText(null);
                ten_name.setTextSize(0);
                showNum = 0;
                btnh.setVisibility(View.GONE);
                builder.setTitle("寻访结果");
                builder.setView(view);
                builder.create();
                builder.show();
            }
        }
    }

    class p1Click implements View.OnClickListener {
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedSearchingActivity.this);

        @Override
        public void onClick(View v) {
            if (showNum != 10) {
                if (player != null) {
                    Glide.with(v).load(show[showNum]).placeholder(R.drawable.transparent).into(img);
                    ten_name.setText(names[showNum]);
                    ten_name.setTextColor(color[showNum]);
                    ten_name.setTextSize(30);
                    player.stop();
                    player.reset();
                    player = MediaPlayer.create(v.getContext(), voices[showNum++]);
                    player.start();
                }
            } else {
                if (MainActivity.notFastClick()) {
                    View view = getLayoutInflater().inflate(R.layout.tenoutput, null);
                    if (player != null) {
                        player.stop();
                        player.release();
                        player = null;
                    }
                    show_name(view);
                    img.setImageDrawable(null);
                    ten_name.setText(null);
                    ten_name.setTextSize(0);
                    showNum = 0;
                    btnh.setVisibility(View.GONE);
                    builder.setTitle("寻访结果");
                    builder.setView(view);
                    builder.create();
                    builder.show();
                }
            }
        }
    }

    private void show_name(View view){
        TextView[] name = new TextView[10];
        name[0] = view.findViewById(R.id.tenNames1);
        name[1] = view.findViewById(R.id.tenNames2);
        name[2] = view.findViewById(R.id.tenNames3);
        name[3] = view.findViewById(R.id.tenNames4);
        name[4] = view.findViewById(R.id.tenNames5);
        name[5] = view.findViewById(R.id.tenNames6);
        name[6] = view.findViewById(R.id.tenNames7);
        name[7] = view.findViewById(R.id.tenNames8);
        name[8] = view.findViewById(R.id.tenNames9);
        name[9] = view.findViewById(R.id.tenNames10);
        for (int i = 0; i < 10; i++){
            name[i].setText(names[i]);
            name[i].setTextColor(color[i]);
        }
    }

    private static int getRandomNumber(int min, int max){
        if (min > max) {
            throw new IllegalArgumentException("最大值应大于等于最小值");
        }
        else if (min == max)
            return min;
        else {
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }
    }

    private boolean isCheckedZero(List<StarPersonData> list){
        for (StarPersonData i : list){
            if (i.isChecked_status())
                return false;
        }
        return true;
    }

    private void DrawPerson(View view){
        StarPersonData data;
        int six = getRandomNumber(0, SEARCHING_SIX - 1);
        int five = getRandomNumber(0, SEARCHING_FIVE - 1);
        int four = getRandomNumber(0, SEARCHING_FOUR - 1);
        int three = getRandomNumber(0, threeStars.length - 1);
        int num = getRandomNumber(1, 100);
        TextView name = view.findViewById(R.id.text1);
        PhotoView image = view.findViewById(R.id.picture);
        if (MainActivity.CountsNotFS == 9 && MainActivity.TenCounts == 1) {
            num = getRandomNumber(1, 10);
            if (num <= 2) {
                if (dataList_six_up.isEmpty()) {
                    name.setText(sixStars[six]);
                    Glide.with(this).load(six_url[six]).into(image);
                    player = MediaPlayer.create(view.getContext(), six_voice[six]);
                }
                else {
                    data = upSearching(dataList_six_up, percent_of_six);
                    name.setText(data.getName());
                    Glide.with(this).load(data.getUrl()).into(image);
                    player = MediaPlayer.create(view.getContext(), data.getVoiceID());
                }
                name.setTextColor(0XFFA52A5A);
                MainActivity.Counts6++;
                MainActivity.CountsNotSix = 0;
            }
            else {
                if (dataList_five_up.isEmpty()) {
                    name.setText(fiveStars[five]);
                    Glide.with(this).load(five_url[five]).into(image);
                    player = MediaPlayer.create(view.getContext(), five_voice[five]);
                }
                else {
                    data = upSearching(dataList_five_up, percent_of_five);
                    name.setText(data.getName());
                    Glide.with(this).load(data.getUrl()).into(image);
                    player = MediaPlayer.create(view.getContext(), data.getVoiceID());
                }
                name.setTextColor(0XFFFF8C00);
                MainActivity.Counts5++;
                MainActivity.CountsNotSix++;
            }
            MainActivity.CountsNotFS = 0;
        }
        else {
            if (MainActivity.CountsNotSix > 50) {
                rate = (MainActivity.CountsNotSix - 50) * 2 + 2;
            } else
                rate = 2;
            if (num <= rate) {
                if (dataList_six_up.isEmpty()) {
                    name.setText(sixStars[six]);
                    Glide.with(this).load(six_url[six]).into(image);
                    player = MediaPlayer.create(view.getContext(), six_voice[six]);
                }
                else {
                    data = upSearching(dataList_six_up, percent_of_six);
                    name.setText(data.getName());
                    Glide.with(this).load(data.getUrl()).into(image);
                    player = MediaPlayer.create(view.getContext(), data.getVoiceID());
                }
                name.setTextColor(0XFFA52A5A);
                MainActivity.Counts6++;
                MainActivity.CountsNotSix = 0;
                MainActivity.CountsNotFS = 0;
            } else {
                num = getRandomNumber(3, 100);
                if (num <= 10) {
                    if (dataList_five_up.isEmpty()) {
                        name.setText(fiveStars[five]);
                        Glide.with(this).load(five_url[five]).into(image);
                        player = MediaPlayer.create(view.getContext(), five_voice[five]);
                    }
                    else {
                        data = upSearching(dataList_five_up, percent_of_five);
                        name.setText(data.getName());
                        Glide.with(this).load(data.getUrl()).into(image);
                        player = MediaPlayer.create(view.getContext(), data.getVoiceID());
                    }
                    name.setTextColor(0XFFFF8C00);
                    MainActivity.Counts5++;
                    MainActivity.CountsNotSix++;
                    MainActivity.CountsNotFS = 0;
                } else if (num <= 60) {
                    if (dataList_four_up.isEmpty()) {
                        name.setText(fourStars[four]);
                        Glide.with(this).load(four_url[four]).into(image);
                        player = MediaPlayer.create(view.getContext(), four_voice[four]);
                    }
                    else {
                        data = upSearching(dataList_four_up, percent_of_four);
                        name.setText(data.getName());
                        Glide.with(this).load(data.getUrl()).into(image);
                        player = MediaPlayer.create(view.getContext(), data.getVoiceID());
                    }
                    name.setTextColor(0XFF8A2BE2);
                    MainActivity.Counts4++;
                    MainActivity.CountsNotSix++;
                    MainActivity.CountsNotFS++;
                } else {
                    name.setText(threeStars[three]);
                    name.setTextColor(0XFF1E90FF);
                    Glide.with(this).load(three_url[three]).into(image);
                    player = MediaPlayer.create(view.getContext(), three_voice[three]);
                    MainActivity.Counts3++;
                    MainActivity.CountsNotSix++;
                    MainActivity.CountsNotFS++;
                }
            }
        }
        MainActivity.CountsAll ++;
    }

    public String[] show = new String[10];

    public int[] voices = new int[10];

    private void DrawTen(int i) {
        StarPersonData data;
        int six = getRandomNumber(0, SEARCHING_SIX - 1);
        int five = getRandomNumber(0, SEARCHING_FIVE - 1);
        int four = getRandomNumber(0, SEARCHING_FOUR - 1);
        int three = getRandomNumber(0, threeStars.length - 1);
        int num = getRandomNumber(1, 100);
        if (MainActivity.CountsNotSix > 50) {
            rate = (MainActivity.CountsNotSix - 50) * 2 + 2;
        } else
            rate = 2;
        if (num <= rate) {
            if (dataList_six_up.isEmpty()) {
                names[i] = sixStars[six];
                show[i] = six_url[six];
                voices[i] = six_voice[six];
            }
            else {
                data = upSearching(dataList_six_up, percent_of_six);
                names[i] = data.getName();
                show[i] = data.getUrl();
                voices[i] = data.getVoiceID();
            }
            color[i] = 0XFFA52A5A;
            MainActivity.Counts6++;
            MainActivity.CountsNotSix = 0;
            MainActivity.CountsNotFS = 0;
            MainActivity.TenCounts = 0;
        } else {
            num = getRandomNumber(3, 100);
            if (num <= 10) {
                if (dataList_five_up.isEmpty()) {
                    names[i] = fiveStars[five];
                    show[i] = five_url[five];
                    voices[i] = five_voice[five];
                }
                else {
                    data = upSearching(dataList_five_up, percent_of_five);
                    names[i] = data.getName();
                    show[i] = data.getUrl();
                    voices[i] = data.getVoiceID();
                }
                color[i] = 0XFFFF8C00;
                MainActivity.Counts5++;
                MainActivity.CountsNotSix++;
                MainActivity.CountsNotFS = 0;
                MainActivity.TenCounts = 0;
            } else if (num <= 60) {
                if (dataList_four_up.isEmpty()) {
                    names[i] = fourStars[four];
                    show[i] = four_url[four];
                    voices[i] = four_voice[four];
                }
                else {
                    data = upSearching(dataList_four_up, percent_of_four);
                    names[i] = data.getName();
                    show[i] = data.getUrl();
                    voices[i] = data.getVoiceID();
                }
                color[i] = 0XFF8A2BE2;
                MainActivity.Counts4++;
                MainActivity.CountsNotSix++;
                MainActivity.CountsNotFS++;
            } else {
                names[i] = threeStars[three];
                color[i] = 0XFF1E90FF;
                show[i] = three_url[three];
                voices[i] = three_voice[three];
                MainActivity.Counts3++;
                MainActivity.CountsNotSix++;
                MainActivity.CountsNotFS++;
            }
        }
        MainActivity.CountsAll++;
    }

    private void DrawProtect(){
        StarPersonData data;
        int six = getRandomNumber(0, SEARCHING_SIX - 1);
        int five = getRandomNumber(0, SEARCHING_FIVE - 1);
        int num = getRandomNumber(1, 10);
        if (num <= 2) {
            if (dataList_six_up.isEmpty()) {
                names[9] = sixStars[six];
                show[9] = six_url[six];
                voices[9] = six_voice[six];
            }
            else {
                data = upSearching(dataList_six_up, percent_of_six);
                names[9] = data.getName();
                show[9] = data.getUrl();
                voices[9] = data.getVoiceID();
            }
            color[9] = 0XFFA52A5A;
            MainActivity.Counts6++;
            MainActivity.CountsNotSix = 0;
            MainActivity.CountsNotFS = 0;
        }
        else{
            if (dataList_five_up.isEmpty()) {
                names[9] = fiveStars[five];
                show[9] = five_url[five];
                voices[9] = five_voice[five];
            }
            else {
                data = upSearching(dataList_five_up, percent_of_five);
                names[9] = data.getName();
                show[9] = data.getUrl();
                voices[9] = data.getVoiceID();
            }
            color[9] = 0XFFFF8C00;
            MainActivity.Counts5++;
            MainActivity.CountsNotSix++;
            MainActivity.CountsNotFS = 0;
        }
        MainActivity.TenCounts = 0;
        MainActivity.CountsAll++;
    }

    private final String[] sixStars = {
            // 64
            // 常驻
            // 51
            "能天使", "黑", "安洁莉娜", "银灰", "莫斯提马",
            "夜莺", "星熊", "陈", "阿", "煌",
            "麦哲伦", "赫拉格", "斯卡蒂", "塞雷娅", "闪灵",
            "艾雅法拉", "伊芙利特", "推进之王", "刻俄柏", "风笛",
            "傀影", "温蒂", "早露", "铃兰", "棘刺",
            "森蚺", "史尔特尔", "瑕光", "泥岩", "山",
            "空弦", "嵯峨", "异客", "凯尔希", "卡涅利安",
            "帕拉斯", "水月", "琴柳", "远牙", "焰尾",
            "灵知", "老鲤", "澄闪", "菲亚梅塔", "号角",
            "艾丽妮", "黑键", "多萝西", "鸿雪", "玛恩纳",
            "白铁",
            // 限定
            // 13
            "W", "歌蕾蒂娅", "灰烬", "迷迭香", "年",
            "夕", "令", "浊心斯卡蒂", "假日威龙陈", "耀骑士临光",
            "流明", "归溟幽灵鲨", "百炼嘉维尔",
    };

    private final String[] fiveStars = {
            // 113
            // 常驻
            // 71
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
            "极光", "夜半", "夏栎", "风丸", "洛洛",
            "掠风", "濯尘芙蓉", "承曦格雷伊", "晓歌", "但书",
            "明椒",
            // 非寻访
            // 42
            "阿米娅", "柏喙", "拜松", "薄绿", "暴行",
            "暴雨", "贝娜", "鞭刃", "格拉尼", "火神",
            "苦艾", "罗宾", "闪击", "霜华", "特米米",
            "图耶", "微风", "稀音", "锡兰", "雪雉",
            "亚叶", "炎客", "炎狱炎熔", "因陀罗", "战车",
            "铸铁", "龙舌兰", "蜜莓", "野鬃", "耶拉",
            "暮落", "九色鹿", "寒芒克洛丝", "见行者",
            "海蒂", "埃拉托", "车尔尼", "星源", "至简",
            "海沫", "达格达",
    };

    private final String[] fourStars = {
            // 52
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
            // 9
            "艾丝黛尔", "清流", "断罪者", "嘉维尔", "坚雷",
            "讯使", "伊桑", "布丁", "罗小黑",
    };

    private final String[] threeStars = {
            "芬", "炎熔", "月见夜", "香草", "史都华德",
            "卡缇", "米格鲁", "斑点", "空爆", "梓兰",
            "芙蓉", "克洛丝", "玫兰莎", "翎羽", "泡普卡",
            "安赛尔"
    };
    // 16

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
            "https://prts.wiki/images/4/49/%E7%AB%8B%E7%BB%98_%E5%8F%B7%E8%A7%92_1.png",
            "https://prts.wiki/images/5/55/%E7%AB%8B%E7%BB%98_%E8%89%BE%E4%B8%BD%E5%A6%AE_1.png",
            "https://prts.wiki/images/8/88/%E7%AB%8B%E7%BB%98_%E9%BB%91%E9%94%AE_1.png",
            "https://prts.wiki/images/7/7b/%E7%AB%8B%E7%BB%98_%E5%A4%9A%E8%90%9D%E8%A5%BF_1.png",
            "https://prts.wiki/images/5/54/%E7%AB%8B%E7%BB%98_%E9%B8%BF%E9%9B%AA_1.png",
            "https://prts.wiki/images/6/66/%E7%AB%8B%E7%BB%98_%E7%8E%9B%E6%81%A9%E7%BA%B3_1.png",
            "https://prts.wiki/images/2/28/%E7%AB%8B%E7%BB%98_%E7%99%BD%E9%93%81_1.png",
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
            "https://prts.wiki/images/9/96/%E7%AB%8B%E7%BB%98_%E6%B5%81%E6%98%8E_1.png",
            "https://prts.wiki/images/f/fc/%E7%AB%8B%E7%BB%98_%E5%BD%92%E6%BA%9F%E5%B9%BD%E7%81%B5%E9%B2%A8_1.png",
            "https://prts.wiki/images/d/d8/%E7%AB%8B%E7%BB%98_%E7%99%BE%E7%82%BC%E5%98%89%E7%BB%B4%E5%B0%94_1.png",
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
            "https://prts.wiki/images/a/a5/%E7%AB%8B%E7%BB%98_%E6%B4%9B%E6%B4%9B_1.png",
            "https://prts.wiki/images/0/05/%E7%AB%8B%E7%BB%98_%E6%8E%A0%E9%A3%8E_1.png",
            "https://prts.wiki/images/d/dc/%E7%AB%8B%E7%BB%98_%E6%BF%AF%E5%B0%98%E8%8A%99%E8%93%89_1.png",
            "https://prts.wiki/images/b/b7/%E7%AB%8B%E7%BB%98_%E6%89%BF%E6%9B%A6%E6%A0%BC%E9%9B%B7%E4%BC%8A_1.png",
            "https://prts.wiki/images/4/46/%E7%AB%8B%E7%BB%98_%E6%99%93%E6%AD%8C_1.png",
            "https://prts.wiki/images/c/c1/%E7%AB%8B%E7%BB%98_%E4%BD%86%E4%B9%A6_1.png",
            "https://prts.wiki/images/9/94/%E7%AB%8B%E7%BB%98_%E6%98%8E%E6%A4%92_1.png",
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
            "https://prts.wiki/images/6/6a/%E7%AB%8B%E7%BB%98_%E6%B5%B7%E8%92%82_1.png",
            "https://prts.wiki/images/f/f2/%E7%AB%8B%E7%BB%98_%E5%9F%83%E6%8B%89%E6%89%98_1.png",
            "https://prts.wiki/images/8/8a/%E7%AB%8B%E7%BB%98_%E8%BD%A6%E5%B0%94%E5%B0%BC_1.png",
            "https://prts.wiki/images/a/ad/%E7%AB%8B%E7%BB%98_%E6%98%9F%E6%BA%90_1.png",
            "https://prts.wiki/images/d/d5/%E7%AB%8B%E7%BB%98_%E8%87%B3%E7%AE%80_1.png",
            "https://prts.wiki/images/d/df/%E7%AB%8B%E7%BB%98_%E6%B5%B7%E6%B2%AB_1.png",
            "https://prts.wiki/images/c/c8/%E7%AB%8B%E7%BB%98_%E8%BE%BE%E6%A0%BC%E8%BE%BE_1.png",
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

    };

    private final String[] three_url = {
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
            R.raw.haojiao_report_voice,
            R.raw.ailini_report_voice,
            R.raw.heijian_report_voice,
            R.raw.duoluoxi_report_voice,
            R.raw.hongxue_report_voice,
            R.raw.maenna_report_voice,
            R.raw.baitie_report_voice,
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
            R.raw.liuming_report_voice,
            R.raw.guimingyoulingsha_report_voice,
            R.raw.bailianjiaweier_report_voice,
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
            R.raw.luoluo_report_voice,
            R.raw.lvefeng_report_voice,
            R.raw.zhuochenfurong_report_voice,
            R.raw.chengxigeleiyi_report_voice,
            R.raw.xiaoge_report_voice,
            R.raw.danshu_report_voice,
            R.raw.mingjiao_report_voice,
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
            R.raw.haidi_report_voice,
            R.raw.ailatuo_report_voice,
            R.raw.cheerni_report_voice,
            R.raw.xingyuan_report_voice,
            R.raw.zhijian_report_voice,
            R.raw.haimo_report_voice,
            R.raw.dageda_report_voice,
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
    };
}