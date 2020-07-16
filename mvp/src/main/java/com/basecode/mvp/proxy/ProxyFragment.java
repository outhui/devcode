package com.basecode.mvp.proxy;


import com.basecode.mvp.IBasicView;

public class ProxyFragment<V extends IBasicView> extends ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}
