package com.mvp.demo.acy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lsw.home.HomeFragment;
import com.mvp.demo.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: Created by lsw on 2018/8/9 15:15
 * description:
 */
public class HomeActivity  extends FragmentActivity implements  View.OnClickListener {

    private String accessTicket;

    private Dialog dialog;
    private Context context;
    private List<Fragment> list = new ArrayList<>();
//    private TabFragmentPagerAdapter adapter;
    private ViewPager mViewPager;

    private TextView text_recovery, text_promotion, text_personal;
    private ImageView image_personal, image_promotion, image_recovery;
    private LinearLayout promotion, recovery, personal_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_bak);
        //初始化变量
        context = this;

/*        mNavigation = (BottomNavigationView) findViewById(R.id.navigation);*/
/*        mNavigation.setOnNavigationItemSelectedListener(this);*/

        mViewPager = (ViewPager) findViewById(R.id.content);

        mViewPager.addOnPageChangeListener(new MyPagerChangeListener());

        list.add(new HomeFragment());
        /*list.add(new MyMessageFragment());*/
        /*list.add(new MyInfosFragment());*/
//        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
//        mViewPager.setAdapter(adapter);
        /*初始化显示第一个页面*/
        mViewPager.setCurrentItem(0);


        text_recovery = (TextView) findViewById(R.id.text_recovery);
        text_promotion = (TextView) findViewById(R.id.text_promotion);
        text_personal = (TextView) findViewById(R.id.text_personal);
        image_recovery = (ImageView) findViewById(R.id.image_recovery);
        image_promotion = (ImageView) findViewById(R.id.image_promotion);
        image_personal = (ImageView) findViewById(R.id.image_personal);
        promotion = (LinearLayout) findViewById(R.id.promotion);
        recovery = (LinearLayout) findViewById(R.id.recovery);
        personal_info = (LinearLayout) findViewById(R.id.personal_info);
        promotion.setOnClickListener(this);
        recovery.setOnClickListener(this);
        personal_info.setOnClickListener(this);
        recovery.performClick();

        loadData();
    }


    private void loadData() {

    }

    /**
     * 显示Dialog
     */
    public void showDialog() {
        if (dialog == null) {

        }
    }

    /**
     * 关闭Dialog
     */
    public void closeDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }


    class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                /** mNavigation.setSelectedItemId(R.id.navigation_home);*/
                    recovery.performClick();
                    break;
                case 1:
                /**  mNavigation.setSelectedItemId(R.id.navigation_message); */
                    promotion.performClick();
                    break;
                case 2:
                  /**  mNavigation.setSelectedItemId(R.id.navigation_my_info);*/
                    personal_info.performClick();
                    break;
                default:
                    break;
            }

        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.recovery:

                mViewPager.setCurrentItem(0);

                image_recovery.setBackgroundResource(R.mipmap.icon_recycles);
                image_promotion.setBackgroundResource(R.mipmap.icon_messged);
                image_personal.setBackgroundResource(R.mipmap.icon_mine);

                recovery.setBackgroundColor(getResources().getColor(R.color.app));
                promotion.setBackgroundColor(getResources().getColor(R.color.white));
                personal_info.setBackgroundColor(getResources().getColor(R.color.white));

                text_personal.setTextColor(getResources().getColor(R.color.gray_black));
                text_promotion.setTextColor(getResources().getColor(R.color.gray_black));
                text_recovery.setTextColor(getResources().getColor(R.color.black));


                break;
            case R.id.promotion:

                mViewPager.setCurrentItem(1);

                image_recovery.setBackgroundResource(R.mipmap.icon_recycled);
                image_promotion.setBackgroundResource(R.mipmap.icon_messges);
                image_personal.setBackgroundResource(R.mipmap.icon_mine);
                recovery.setBackgroundColor(getResources().getColor(R.color.white));
                promotion.setBackgroundColor(getResources().getColor(R.color.app));
                personal_info.setBackgroundColor(getResources().getColor(R.color.white));
                text_personal.setTextColor(getResources().getColor(R.color.gray_black));
                text_promotion.setTextColor(getResources().getColor(R.color.black));
                text_recovery.setTextColor(getResources().getColor(R.color.gray_black));

                break;
            case R.id.personal_info:

                mViewPager.setCurrentItem(2);


                image_recovery.setBackgroundResource(R.mipmap.icon_recycled);
                image_promotion.setBackgroundResource(R.mipmap.icon_messged);
                image_personal.setBackgroundResource(R.mipmap.icon_mined);
                recovery.setBackgroundColor(getResources().getColor(R.color.white));
                promotion.setBackgroundColor(getResources().getColor(R.color.white));
                personal_info.setBackgroundColor(getResources().getColor(R.color.app));
                text_personal.setTextColor(getResources().getColor(R.color.black));
                text_promotion.setTextColor(getResources().getColor(R.color.gray_black));
                text_recovery.setTextColor(getResources().getColor(R.color.gray_black));

                break;
            default:
                    break;
        }

    }
}