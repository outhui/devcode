package com.basecode.mvp.proxy;


import com.basecode.mvp.inject.InjectPresenter;
import com.basecode.mvp.BasicPresenter;
import com.basecode.mvp.IBasicView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProxyImpl implements IProxy {

    private IBasicView mView;
    private List<BasicPresenter> mInjectPresenters;

    public ProxyImpl(IBasicView view) {
        this.mView = view;
        mInjectPresenters = new ArrayList<>();
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    public void bindPresenter() {
        //获得已经申明的变量，包括私有的
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            //获取变量上面的注解类型
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                try {
                    Class<? extends BasicPresenter> type = (Class<? extends BasicPresenter>) field.getType();
                    BasicPresenter mInjectPresenter = type.newInstance();
                    mInjectPresenter.attach(mView);
                    field.setAccessible(true);
                    field.set(mView, mInjectPresenter);
                    mInjectPresenters.add(mInjectPresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    throw new RuntimeException("SubClass must extends Class:BasePresenter");
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        /**
         * 解绑，避免内存泄漏
         */
        for (BasicPresenter presenter : mInjectPresenters) {
            presenter.detach();
        }
        mInjectPresenters.clear();
        mInjectPresenters = null;
    }
}
