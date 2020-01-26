package com.falcon.warehouse.contract;

public interface BasePresenter<T> {
    void attachView(T view);
    void detachView(T view);
    T getView();
}
