package com.basecode.mvp;

import android.content.Context;

public interface IBasicView {

    Context getContext();

    int getContentView();

    void initViews();

    void initData();
}
