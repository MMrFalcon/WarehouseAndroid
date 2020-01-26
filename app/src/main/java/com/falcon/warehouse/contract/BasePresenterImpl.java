package com.falcon.warehouse.contract;

public abstract class BasePresenterImpl<T> implements BasePresenter<T> {

    protected T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView(T view) {
        this.view = null;
    }

    @Override
    public T getView() {
        return this.view;
    }
}
