package ru.arlen.domain.executer;

import android.os.Handler;
import android.os.Looper;

public class MainThreadImpl implements MainThread {
    private static MainThreadImpl sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThreadImpl getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }
        return sMainThread;
    }
}
