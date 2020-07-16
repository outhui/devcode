package com.basecode.mvp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.basecode.mvp.proxy.ProxyActivity;
import com.basecode.manager.ActivityManager;

public abstract class BasicActivity<B extends ViewDataBinding> extends AppCompatActivity implements IBasicView {

    protected Handler mHandler = new Handler();
    private ProxyActivity mProxyActivity;
    protected B mBinding;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getAppInstance().pushActivity(this);
        if (isScreenPortrait()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mBinding = DataBindingUtil.setContentView(this, getContentView());
        mProxyActivity = createProxyActivity();
        mProxyActivity.bindPresenter();
        initViews();
        initData();
    }


    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppInstance().popActivity(this);
        mHandler.removeCallbacksAndMessages(null);
        mProxyActivity.unbindPresenter();
    }

    public boolean isScreenPortrait() {
        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
