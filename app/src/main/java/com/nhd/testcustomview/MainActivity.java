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
        PathView pathView = (PathView) findViewById(R.id.myView);
        List<Double> mDatas = new ArrayList<>();
        mDatas.add(52.00);
        mDatas.add(55.5);
        mDatas.add(65.0);
        mDatas.add(65.0);
        mDatas.add(55.0);
        mDatas.add(99.0);
        pathView.setData(mDatas);
    }
}
