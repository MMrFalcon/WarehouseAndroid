package com.falcon.warehouse.contract;

public abstract class BasePresenterImpl<T> implements BasePresenter<T> {

    protected T view;

    public void attachView(T view) {
        this.view = view;
    }
    public void detachView(T view) {
        this.view = null;
    }
}
