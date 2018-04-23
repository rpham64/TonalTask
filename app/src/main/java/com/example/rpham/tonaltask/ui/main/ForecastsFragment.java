package com.example.rpham.tonaltask.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rpham.tonaltask.R;
import com.example.rpham.tonaltask.data.Forecast;
import com.example.rpham.tonaltask.ui.main.adapter.ForecastsAdapter;
import com.example.rpham.tonaltask.util.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ForecastsFragment extends Fragment implements ForecastsContract.View,
        SearchView.OnQueryTextListener {
    private static final String TAG = ForecastsFragment.class.getSimpleName();

    @BindView(R.id.search_view_zip_code) SearchView mSearchViewZipCode;
    @BindView(R.id.recycler_view_forecast_list) RecyclerView mRecyclerViewForecastList;

    // ButterKnife's Unbinder Contract
    private Unbinder mUnbinder;

    private ForecastsPresenter mPresenter;
    private ForecastsAdapter mAdapter;

    public static ForecastsFragment newInstance() {
        return new ForecastsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mPresenter = new ForecastsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        mSearchViewZipCode.setOnQueryTextListener(this);
        mRecyclerViewForecastList.setLayoutManager(new LinearLayoutManager(getContext()));
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
        mAdapter = new ForecastsAdapter(getContext(), forecastList);
        mRecyclerViewForecastList.setAdapter(mAdapter);
    }

    @Override
    public void showResponseErrorToast() {
        Toast.makeText(getContext(), getString(R.string.toast_forecast_response_error),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String zipCode) {
        Log.i(TAG, "Zip Code submitted: " + zipCode);

        // Issue network request for the next five forecasts if the given zip code is valid.
        if (StringUtils.isValidZipCode(zipCode)) {
            mPresenter.getForecastsAt(Integer.valueOf(zipCode));
        } else {
            Toast.makeText(getContext(), "The zip code " + zipCode + " is invalid.",
                    Toast.LENGTH_SHORT).show();
        }

        // Hide keyboard after hitting the Enter button.
        mSearchViewZipCode.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(TAG, "Query: " + newText);
        return false;
    }
}
