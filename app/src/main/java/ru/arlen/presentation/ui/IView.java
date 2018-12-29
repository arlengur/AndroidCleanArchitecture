package ru.arlen.presentation.ui;

import ru.arlen.domain.model.Weather;

public interface IView {
    void showProgress();
    void hideProgress();
    void showError(String msg);
    void displayWeather(Weather weather);
}
