package com.example.rpham.tonaltask.base;

public interface BasePresenter<T> {
    void attachView(T view);
    void detachView();
    T getView();
}
