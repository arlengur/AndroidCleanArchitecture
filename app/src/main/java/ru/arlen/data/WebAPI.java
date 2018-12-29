package ru.arlen.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.arlen.domain.model.Weather;

public interface WebAPI {
    @GET("/data/2.5/forecast/daily?")
    Call<Weather> getWeather(
            @Query("q") String city,
            @Query("units") String metric,
            @Query("cnt") String days,
            @Query("appid") String key);
}
