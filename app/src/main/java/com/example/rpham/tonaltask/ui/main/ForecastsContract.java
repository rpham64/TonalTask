package com.example.rpham.tonaltask.ui.main;

import com.example.rpham.tonaltask.base.BasePresenter;
import com.example.rpham.tonaltask.base.BaseView;
import com.example.rpham.tonaltask.data.Forecast;
import com.example.rpham.tonaltask.data.network.ApiService;

import java.util.List;

public interface ForecastsContract {

    interface View extends BaseView {
        void showForecasts(List<Forecast> forecastList);
    }

    interface Presenter extends BasePresenter<ForecastsContract.View> {
        void getForecastsAt(int zipCode);
        ApiService getApiService();
    }
}
