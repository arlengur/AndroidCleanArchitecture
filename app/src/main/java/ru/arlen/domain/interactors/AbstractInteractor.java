package ru.arlen.domain.interactors;

import ru.arlen.domain.executer.MainThread;
import ru.arlen.domain.executer.ThreadExecutor;

public abstract class AbstractInteractor implements Interactor {
    private ThreadExecutor mThreadExecutor;
    protected MainThread mMainThread;

    public AbstractInteractor(ThreadExecutor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    public abstract void run();


    public void execute() {
        // start running this interactor in a background thread
        mThreadExecutor.execute(this);
    }
}