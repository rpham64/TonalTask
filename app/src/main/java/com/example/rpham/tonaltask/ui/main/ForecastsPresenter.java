package com.example.rpham.tonaltask.ui.main;

import android.support.annotation.NonNull;

import com.example.rpham.tonaltask.data.Forecast;
import com.example.rpham.tonaltask.data.network.ForecastsDataSource;
import com.example.rpham.tonaltask.data.network.ForecastsRepository;

import java.util.List;

public class ForecastsPresenter implements ForecastsContract.Presenter {
    private static final String TAG = ForecastsPresenter.class.getSimpleName();

    private final ForecastsRepository mForecastsRepository;

    private final ForecastsContract.View mForecastsView;

    public ForecastsPresenter(@NonNull ForecastsRepository forecastsRepository,
                              @NonNull ForecastsContract.View forecastsView) {
        mForecastsRepository = forecastsRepository;
        mForecastsView = forecastsView;

        mForecastsView.setPresenter(this);
    }

    /**
     * Issues an HTTP GET request for forecasts at the given zip code.
     *
     * @param zipCode Zip code of location.
     */
    @Override
    public void getForecastsAt(final int zipCode) {
        mForecastsRepository.getForecastsAt(zipCode, new ForecastsDataSource.GetForecastsCallback() {
            @Override
            public void onForecastsFetched(List<Forecast> forecasts) {
                mForecastsView.showForecasts(forecasts);
            }

            @Override
            public void onDataNotAvailable() {
                mForecastsView.showFetchingForecastsError();
            }
        });
    }
}
