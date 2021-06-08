package com.sz.apollo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sz.apollo.R;
import com.sz.apollo.commons.constants.Constant;
import com.sz.apollo.ui.basic.BasicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareNboAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    String coinType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_nbo);
        ButterKnife.bind(this);

        switch (coinType) {
            case Constant.TYPE_NBO:
                ivBg.setImageResource(R.drawable.share_nbo);
                break;
            case Constant.TYPE_DDAO:
                ivBg.setImageResource(R.drawable.share_ddao);
                break;
            case Constant.TYPE_LON:
                ivBg.setImageResource(R.drawable.share_lon);
                break;
        }


        /**
         * android4.3以上的沉浸式 ，4.3以下没效果，所以不要头部填充状态栏高度
         */
        int sysVersion = Build.VERSION.SDK_INT;
        if (sysVersion > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            int result = 0;
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
            LinearLayout toptop = (LinearLayout) this.findViewById(R.id.toptop);
            RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(this.getWindowManager().getDefaultDisplay().getWidth(), result);
            //设置修改后的布局。
            toptop.setLayoutParams(para);
            tvTitle.setText(getString(R.string.tip92));
        }
    }

    @Override
    protected void getIntentForBundle() {
        super.getIntentForBundle();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            coinType = bundle.getString("coinType");
        }
    }

}
