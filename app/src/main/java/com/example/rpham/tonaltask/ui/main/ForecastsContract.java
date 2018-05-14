package com.example.rpham.tonaltask.ui.main;

import com.example.rpham.tonaltask.base.BasePresenter;
import com.example.rpham.tonaltask.base.BaseView;
import com.example.rpham.tonaltask.data.Forecast;
import com.example.rpham.tonaltask.data.network.ApiService;

import java.util.List;

public interface ForecastsContract {

    interface View extends BaseView<Presenter> {

        void showForecasts(List<Forecast> forecastList);

        void showFetchingForecastsError();
    }

    interface Presenter extends BasePresenter {

        void getForecastsAt(int zipCode);
    }
}
