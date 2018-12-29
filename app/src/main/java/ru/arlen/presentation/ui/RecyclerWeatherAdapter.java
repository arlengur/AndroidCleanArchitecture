package ru.arlen.presentation.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ru.arlen.androidcleanarchitecture.R;
import ru.arlen.domain.model.DayWeather;
import ru.arlen.domain.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecyclerWeatherAdapter extends RecyclerView.Adapter<RecyclerWeatherAdapter.Holder> {
    private Weather mWeather;

    public RecyclerWeatherAdapter(Weather weather) {
        mWeather = weather;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new Holder(inflater.inflate(R.layout.item_weather, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        DayWeather dayWeather = mWeather.getList()[i];
        String date = df.format(new Date(Long.parseLong(dayWeather.getDt()) * 1000));
        String temp = String.valueOf(dayWeather.getTemp().getDay());

        holder.bind(date + ": " + temp, i + 1);
    }

    @Override
    public int getItemCount() {
        return mWeather.getList().length;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView weatherItem;

        public Holder(@NonNull View itemView) {
            super(itemView);
            weatherItem = itemView.findViewById(R.id.weatherItem);
        }

        public void bind(String text, int id) {
            weatherItem.setText(text);
            weatherItem.setTag(id);
        }
    }
}
