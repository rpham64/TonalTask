package com.example.rpham.tonaltask.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpham.tonaltask.R;
import com.example.rpham.tonaltask.data.Forecast;
import com.example.rpham.tonaltask.data.ForecastMainInfo;
import com.example.rpham.tonaltask.util.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastsAdapter extends RecyclerView.Adapter<ForecastsAdapter.ForecastsViewHolder> {

    private Context mContext;
    private List<Forecast> mForecastList;

    public ForecastsAdapter(Context context, List<Forecast> forecastList) {
        mContext = context;
        mForecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.viewholder_forecast, parent, false);
        return new ForecastsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastsViewHolder holder, int position) {
        Forecast forecast = mForecastList.get(position);
        holder.bindForecast(forecast);
    }

    @Override
    public int getItemCount() {
        return mForecastList == null ? 0 : mForecastList.size();
    }

    public void setForecasts(List<Forecast> forecasts) {
        mForecastList.clear();
        mForecastList.addAll(forecasts);
        notifyDataSetChanged();
    }

    class ForecastsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_time) TextView mTextViewTime;
        @BindView(R.id.textview_max_temperature) TextView mTextViewMaxTemperature;
        @BindView(R.id.textview_min_temperature) TextView mTextViewMinTemperature;
        @BindView(R.id.textview_humidity) TextView mTextViewHumidity;

        public ForecastsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindForecast(Forecast forecast) {
            // Convert forecast unix time to modern date and time.
            String time = TimeUtils.toString(forecast.getTime());
            mTextViewTime.setText(time);

            ForecastMainInfo mainInfo = forecast.getMainInfo();

            // Convert max and min temperature values to String as TextViews cannot take float.
            String textMaxTemp = String.valueOf(mainInfo.getMaxTemp());
            String textMinTemp = String.valueOf(mainInfo.getMinTemp());
            String textHumidity = String.valueOf(mainInfo.getHumidity());

            mTextViewMaxTemperature.setText(textMaxTemp);
            mTextViewMinTemperature.setText(textMinTemp);
            mTextViewHumidity.setText(textHumidity);
        }
    }
}
