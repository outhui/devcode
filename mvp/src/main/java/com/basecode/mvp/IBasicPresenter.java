package com.basecode.mvp;

public interface IBasicPresenter {

    void attach(IBasicView view);

    void detach();
}
