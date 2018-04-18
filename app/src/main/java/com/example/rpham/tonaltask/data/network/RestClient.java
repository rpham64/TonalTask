package com.example.rpham.tonaltask.data.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A Singleton class containing the reference to the ApiService for issuing network calls.
 */
public class RestClient {

    // Constants provided by Tonal's Coding Challenge.
    private static final String API_ID = "8164a613b8e6973b5c91067d6a5e1c25";
    private static final String BASE_URL = "http://api.openweathermap.org/";

    private static final String TEMPERATURE_UNIT_FORMAT = "imperial";

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
