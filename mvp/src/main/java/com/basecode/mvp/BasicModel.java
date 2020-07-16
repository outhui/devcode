package com.basecode.mvp;

import com.basecode.okhttp.OKHttpUtils;

public abstract class BasicModel {

    public void onCancle() {
        OKHttpUtils.getInstance().cancelTag(this);
    }

}
