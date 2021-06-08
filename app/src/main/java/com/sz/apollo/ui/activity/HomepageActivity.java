package com.sz.apollo.ui.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.gyf.barlibrary.ImmersionBar;
import com.sz.apollo.R;
import com.sz.apollo.application.ApolloApplication;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.adapters.ViewPageAdapter;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.fragments.MyFragment;
import com.sz.apollo.ui.fragments.OneFragment;
import com.sz.apollo.ui.fragments.ThreeFragment;
import com.sz.apollo.ui.fragments.TwoFragment;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.ToastUtil;
import com.sz.apollo.ui.views.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页
 */
public class HomepageActivity extends BasicActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.viewPager)
    ViewPagerSlide viewPager;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.ll_three)
    LinearLayout llThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.ll_four)
    LinearLayout llFour;
    @BindView(R.id.lottie_1)
    LottieAnimationView lottie1;
    @BindView(R.id.lottie_2)
    LottieAnimationView lottie2;
    @BindView(R.id.lottie_3)
    LottieAnimationView lottie3;
    @BindView(R.id.lottie_4)
    LottieAnimationView lottie4;
    private List<Fragment> listFragment;
    private int currentItem = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        initView();
    }

    @SuppressLint("Range")
    private void initView() {
        listFragment = new ArrayList<>();
        listFragment.add(new OneFragment());
        listFragment.add(new TwoFragment());
        listFragment.add(new ThreeFragment());
        listFragment.add(new MyFragment());
        viewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), listFragment));
        viewPager.setCurrentItem(currentItem);
        viewPager.setNoScroll(true);
        viewPager.setOffscreenPageLimit(4);
        tabSelected(llOne, currentItem);
        lottie1.setProgress(100);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        viewPager.setCurrentItem(currentItem);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void tabSelected(LinearLayout linearLayout, int position) {
        llOne.setSelected(false);
        llTwo.setSelected(false);
        llThree.setSelected(false);
        llFour.setSelected(false);
        linearLayout.setSelected(true);
        lottie1.cancelAnimation();
        lottie2.cancelAnimation();
        lottie3.cancelAnimation();
        lottie4.cancelAnimation();
        lottie1.setProgress(0);
        lottie2.setProgress(0);
        lottie3.setProgress(0);
        lottie4.setProgress(0);
        switch (position) {
            case 0:
                lottie1.playAnimation();
                break;
            case 1:
                lottie2.playAnimation();
                break;
            case 2:
                lottie3.playAnimation();
                break;
            case 3:
                lottie4.playAnimation();
                break;
        }
    }

    private long exitTime = 0;

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                ToastUtil.show(this, getString(R.string.tips_exit));
//                exitTime = System.currentTimeMillis();
//            } else {
//                ApolloApplication.getInstance().exit();
//            }
//            return true;
//        }
//        return true;
//    }

    @OnClick({R.id.ll_one, R.id.ll_two, R.id.ll_three, R.id.ll_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_one:
                if (currentItem == 0) {
                    break;
                }
                currentItem = 0;
                viewPager.setCurrentItem(currentItem);
                tabSelected(llOne, currentItem);
                break;
            case R.id.ll_two:
                if (DaoUtil.selectNowWallet().getType().equals(Constant.TYPE_APOLLO)) {
                    if (currentItem == 1) {
                        break;
                    }
                    currentItem = 1;
                    viewPager.setCurrentItem(currentItem);
                    tabSelected(llTwo, currentItem);
                } else {
                    ToastUtil.show(this, getString(R.string.tip157));
                }
                break;
            case R.id.ll_three:
                if (currentItem == 2) {
                    break;
                }
                currentItem = 2;
                viewPager.setCurrentItem(currentItem);
                tabSelected(llThree, currentItem);
                break;
            case R.id.ll_four:
                if (currentItem == 3) {
                    break;
                }
                currentItem = 3;
                viewPager.setCurrentItem(currentItem);
                tabSelected(llFour, currentItem);
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.show(this, getString(R.string.tips_exit));
                exitTime = System.currentTimeMillis();
            } else {
                ApolloApplication.getInstance().exit();
            }
            return true;
        }
        return true;
    }
}