package com.example.rpham.tonaltask.ui.main;

import com.example.rpham.tonaltask.base.BasePresenter;
import com.example.rpham.tonaltask.base.BaseView;
import com.example.rpham.tonaltask.data.network.ApiService;
import com.example.rpham.tonaltask.data.network.RestClient;

public interface ForecastsContract {

    interface View extends BaseView {
        void showForecasts();  // TODO
    }

    interface Presenter extends BasePresenter<BaseView> {
        ApiService getApiService();
    }
}
