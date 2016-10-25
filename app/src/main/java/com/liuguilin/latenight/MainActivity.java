package com.liuguilin.latenight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.activity.WeatherActivity;
import com.liuguilin.latenight.adapter.HorizontalPagerAdapter;
import com.liuguilin.latenight.util.L;

public class MainActivity extends AppCompatActivity {

    //launcher卡片
    private HorizontalInfiniteCycleViewPager infiniteCycleViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //初始化View
    private void initView() {
        infiniteCycleViewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        HorizontalPagerAdapter horizontalPagerAdapter = new HorizontalPagerAdapter(this,false);
        infiniteCycleViewPager.setAdapter(horizontalPagerAdapter);
        horizontalPagerAdapter.setOnPagerItemClickListener(new HorizontalPagerAdapter.PagerItemClickListener() {
            @Override
            public void onPagerItemClickListener(View view, int postion) {
                L.i("position:" + postion);
                switch (postion){
                    case 0:
                        startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                        break;
                }
            }
        });
    }

}