package com.basecode.mvp.proxy;


import com.basecode.mvp.IBasicView;

public class ProxyActivity<V extends IBasicView> extends ProxyImpl {
    public ProxyActivity(V view) {
        super(view);
    }
}
