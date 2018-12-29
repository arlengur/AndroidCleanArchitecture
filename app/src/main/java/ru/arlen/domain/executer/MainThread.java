package ru.arlen.domain.executer;

public interface MainThread {
    void post(final Runnable runnable);
}
