package com.example.rpham.tonaltask.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpham.tonaltask.R;
import com.example.rpham.tonaltask.data.Forecast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ForecastsFragment extends Fragment implements ForecastsContract.View {
    private static final String TAG = ForecastsFragment.class.getSimpleName();

    @BindView(R.id.search_view_zip_code) SearchView mSearchViewZipCode;
    @BindView(R.id.recycler_view_forecast_list) RecyclerView mRecyclerViewForecastList;

    // ButterKnife's Unbinder Contract
    private Unbinder mUnbinder;

    private ForecastsPresenter mPresenter;

    public static ForecastsFragment newInstance() {
        return new ForecastsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ForecastsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        mPresenter.getForecasts(95132);
        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showForecasts(List<Forecast> forecastList) {
        Log.i(TAG, "Got forecast list of size: " + forecastList.size());
    }
}
