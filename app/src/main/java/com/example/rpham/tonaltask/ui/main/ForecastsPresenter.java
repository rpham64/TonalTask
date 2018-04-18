package com.example.rpham.tonaltask.ui.main;

import android.util.Log;

import com.example.rpham.tonaltask.data.Forecast;
import com.example.rpham.tonaltask.data.Response;
import com.example.rpham.tonaltask.data.network.ApiService;
import com.example.rpham.tonaltask.data.network.RestClient;

import java.util.ArrayList;
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

    /**
     * Issues an HTTP GET request for forecasts at the given zip code.
     *
     * @param zipCode Zip code of location.
     */
    @Override
    public void getForecastsAt(final int zipCode) {
        Call<Response> call = getApiService().getForecastsAt(zipCode);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.d(TAG, "Network request was unsuccessful.");
                    if (getView() != null) {
                        getView().showResponseErrorToast();
                    }
                    return;
                }
                List<Forecast> forecastList = response.body().getForecasts();
                Log.i(TAG, "Got forecast list of size: " + forecastList.size());

                // Create forecast list for only the next 5 days.
                List<Forecast> nextFiveForecastsList = new ArrayList<>();

                for (int i = 0; i < 5; ++i) {
                    nextFiveForecastsList.add(forecastList.get(i));
                }

                // Send the next five forecasts list to the View for displaying.
                if (getView() != null) {
                    getView().showForecasts(nextFiveForecastsList);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Network request for forecasts in area " + zipCode + " failed.");
                if (getView() != null) {
                    getView().showResponseErrorToast();
                }
            }
        });
    }

    @Override
    public ApiService getApiService() {
        return RestClient.getInstance().getApiService();
    }
}
