package com.example.rpham.tonaltask.ui.main;

import android.util.Log;

import com.example.rpham.tonaltask.data.Forecast;
import com.example.rpham.tonaltask.data.Response;
import com.example.rpham.tonaltask.data.network.ApiService;
import com.example.rpham.tonaltask.data.network.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ForecastsPresenter implements ForecastsContract.Presenter {
    private static final String TAG = ForecastsPresenter.class.getSimpleName();

    private ForecastsContract.View mForecastsView;

    @Override
    public void attachView(ForecastsContract.View view) {
        mForecastsView = view;
    }

    @Override
    public void detachView() {
        mForecastsView = null;
    }

    @Override
    public ForecastsContract.View getView() {
        return mForecastsView;
    }

    @Override
    public void getForecasts(final int zipCode) {
        Call<Response> call = getApiService().getForecast(zipCode);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Network response was unsuccessful.");
                }

                List<Forecast> forecastList = response.body().getForecasts();
                Log.i(TAG, "ForecastList: " + forecastList.toString());

                if (getView() != null) {
                    getView().showForecasts(forecastList);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Network request for forecasts in area " + zipCode + " failed.");
            }
        });
    }

    @Override
    public ApiService getApiService() {
        return RestClient.getInstance().getApiService();
    }
}
