package ru.arlen.presentation.presenters;

import ru.arlen.data.WeatherRepository;
import ru.arlen.domain.executer.MainThread;
import ru.arlen.domain.executer.MainThreadImpl;
import ru.arlen.domain.executer.ThreadExecutor;
import ru.arlen.domain.interactors.WeatherInteractor;
import ru.arlen.domain.model.Weather;
import ru.arlen.presentation.ui.IView;

public class WeatherPresenter implements Presenter, WeatherInteractor.Callback {
    private ThreadExecutor mExecutor;
    private MainThread mMainThread;
    private IView mView;
    private WeatherRepository mRepo;

    public WeatherPresenter(IView view) {
        mView = view;
        mExecutor = ThreadExecutor.getInstance();
        mMainThread = MainThreadImpl.getInstance();
        mRepo = new WeatherRepository();
    }

    public void loadWeather(String city, String days) {
        mView.showProgress();
        WeatherInteractor interactor = new WeatherInteractor(mExecutor, mMainThread, this, mRepo);
        interactor.setCity(city);
        interactor.setDays(days);
        interactor.execute();
    }

    @Override
    public void onSuccess(Weather weather) {
        mView.hideProgress();
        mView.displayWeather(weather);
    }

    @Override
    public void onFail(String error) {
        mView.hideProgress();
        mView.showError(error);
    }

    @Override
    public String[] getCities() {
        return mRepo.getCities();
    }

    @Override
    public String[] getDays() {
        return mRepo.getDays();
    }
}
