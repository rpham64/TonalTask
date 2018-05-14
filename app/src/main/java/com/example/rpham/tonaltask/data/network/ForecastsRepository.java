package com.example.rpham.tonaltask.data.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rpham.tonaltask.data.Forecast;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A Singleton class containing the reference to the ApiService for issuing network calls.
 */
public class ForecastsRepository implements ForecastsDataSource {
    private static final String TAG = ForecastsRepository.class.getSimpleName();

    // Constants provided by Tonal's Coding Challenge.
    private static final String API_ID = "8164a613b8e6973b5c91067d6a5e1c25";
    private static final String BASE_URL = "http://api.openweathermap.org/";

    private static final String TEMPERATURE_UNIT_FORMAT = "imperial";

    // Static reference to this singleton class.
    private static ForecastsRepository sInstance;

    // Service class for accessing the OpenWeatherMap API using Retrofit.
    private ApiService mApiService;

    private ForecastsRepository() {
        // Private Constructor - Only accessed when ForecastsRepository has not been initialized.
    }

    /**
     * Issues network requests for list of forecasts, then sends the result to the Presenter using
     * a callback.
     *
     * @param zipCode
     * @param callback
     */
    @Override
    public void getForecastsAt(final int zipCode, @NonNull final GetForecastsCallback callback) {
        Call<com.example.rpham.tonaltask.data.Response> call = getApiService().getForecastsAt(zipCode);
        call.enqueue(new Callback<com.example.rpham.tonaltask.data.Response>() {
            @Override
            public void onResponse(Call<com.example.rpham.tonaltask.data.Response> call, retrofit2.Response<com.example.rpham.tonaltask.data.Response> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.d(TAG, "Network request was unsuccessful.");
                    callback.onDataNotAvailable();
                    return;
                }
                Log.i(TAG, "Forecast Result: " + response.toString());

                List<Forecast> forecastList = response.body().getForecasts();
                Log.i(TAG, "Got forecast list of size: " + forecastList.size());

                callback.onForecastsFetched(forecastList);
            }

            @Override
            public void onFailure(Call<com.example.rpham.tonaltask.data.Response> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Network request for forecasts in area " + zipCode + " failed.");
                callback.onDataNotAvailable();
            }
        });
    }

    public synchronized static ForecastsRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ForecastsRepository();
        }
        return sInstance;
    }

    public ApiService getApiService() {
        if (mApiService == null) {
            // Initialize OkHttpClient.
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            HttpUrl httpUrl = request.url().newBuilder()
                                    .addQueryParameter("appid", API_ID)
                                    .addQueryParameter("units", TEMPERATURE_UNIT_FORMAT)
                                    .build();
                            request = request.newBuilder()
                                    .url(httpUrl)
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();

            // Initialize retrofit with Gson and OkHttpClient.
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            // Initialize ApiService instance.
            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }
}
