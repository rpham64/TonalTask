package com.example.rpham.tonaltask.ui.main;

import com.example.rpham.tonaltask.base.BaseView;
import com.example.rpham.tonaltask.data.network.ApiService;
import com.example.rpham.tonaltask.data.network.RestClient;

public class ForecastsPresenter implements ForecastsContract.Presenter {
    private static final String TAG = ForecastsPresenter.class.getSimpleName();

    private BaseView mForecastsView;

    @Override
    public void attachView(BaseView view) {
        mForecastsView = view;
    }

    @Override
    public void detachView() {
        mForecastsView = null;
    }

    @Override
    public BaseView getView() {
        return mForecastsView;
    }

    @Override
    public ApiService getApiService() {
        return RestClient.getInstance().getApiService();
    }
}
