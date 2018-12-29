package ru.arlen.presentation.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import ru.arlen.androidcleanarchitecture.R;
import ru.arlen.domain.model.Weather;
import ru.arlen.presentation.presenters.WeatherPresenter;

public class MainActivity extends Activity implements IView {
    private WeatherPresenter mPresenter;
    private View progressBar;
    private Spinner cityDropdown;
    private Spinner daysDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new WeatherPresenter(this);

        cityDropdown = findViewById(R.id.city);
        daysDropdown = findViewById(R.id.days);

        ArrayAdapter<String> cAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mPresenter.getCities());
        cityDropdown.setAdapter(cAdapter);

        ArrayAdapter<String> dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mPresenter.getDays());
        daysDropdown.setAdapter(dAdapter);

        View update = findViewById(R.id.updateBtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityDropdown.getSelectedItem().toString();
                String days = daysDropdown.getSelectedItem().toString();
                mPresenter.loadWeather(city, days);
            }
        });

        progressBar = findViewById(R.id.progress);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String city = cityDropdown.getSelectedItem().toString();
        String days = daysDropdown.getSelectedItem().toString();
        mPresenter.loadWeather(city, days);
    }

    @Override
    public void displayWeather(Weather weather) {
        final RecyclerView weatherList = findViewById(R.id.recycler);
        weatherList.setAdapter(new RecyclerWeatherAdapter(weather));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, "Error:" + msg, Toast.LENGTH_SHORT).show();
    }
}
