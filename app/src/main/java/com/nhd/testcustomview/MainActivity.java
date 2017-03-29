package com.nhd.testcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PieView pieView = (PieView) findViewById(R.id.myView);
        List<PieBean> mDatas = new ArrayList<>();
        mDatas.add(new PieBean(80));
        mDatas.add(new PieBean(230));
        mDatas.add(new PieBean(170));
        mDatas.add(new PieBean(140));
        pieView.setData(mDatas);
    }
}
