package com.example.rpham.tonaltask.data.network;

import android.support.annotation.NonNull;

import com.example.rpham.tonaltask.data.Forecast;

import java.util.List;

/**
 * Main entry point for accessing forecast data.
 */
public interface ForecastsDataSource {

    interface GetForecastsCallback {

        void onForecastsFetched(List<Forecast> forecasts);

        void onDataNotAvailable();
    }

    void getForecastsAt(final int zipCode, @NonNull final GetForecastsCallback callback);
}
