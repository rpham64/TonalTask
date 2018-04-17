package com.example.rpham.tonaltask.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Handles RESTful network calls via Retrofit.
 */
public class RestClient {

    // Constants provided by Tonal's Coding Challenge.
    private static final String API_ID = "8164a613b8e6973b5c91067d6a5e1c25";
    private static final String BASE_URL = "http://api.openweathermap.org/";

    // Static reference to this singleton class.
    private static RestClient sInstance;

    // Service class for accessing the OpenWeatherMap API using Retrofit.
    private ApiService mApiService;

    private RestClient() {
        // Private Constructor - Only accessed when RestClient has not been initialized.
    }

    public synchronized static RestClient getInstance() {
        if (sInstance == null) {
            sInstance = new RestClient();
        }
        return sInstance;
    }

    public ApiService getApiService() {
        if (mApiService == null) {
            // Initialize Retrofit client and ApiService class.
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }
}
