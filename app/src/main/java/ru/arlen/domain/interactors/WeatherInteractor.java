package ru.arlen.domain.interactors;

import ru.arlen.data.WeatherRepository;
import ru.arlen.domain.executer.MainThread;
import ru.arlen.domain.executer.ThreadExecutor;
import ru.arlen.domain.model.Weather;

public class WeatherInteractor extends AbstractInteractor {
    private WeatherInteractor.Callback mCallback;
    private WeatherRepository mRepository;
    private String city;
    private String days;

    public WeatherInteractor(ThreadExecutor threadExecutor, MainThread mainThread, WeatherInteractor.Callback callback, WeatherRepository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDays(String days) {
        this.days = days;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFail("Data retrieval error!");
            }
        });
    }

    private void postMessage(final Weather weather) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(weather);
            }
        });
    }

    @Override
    public void run() {
        Weather weather = mRepository.getWeather(city, days);
        if (weather == null) {
            notifyError();
            return;
        }
        postMessage(weather);
    }

    public interface Callback {
        void onSuccess(Weather weather);
        void onFail(String error);
    }
}
