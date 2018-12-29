package ru.arlen.data;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.arlen.domain.model.Weather;

import java.io.IOException;

public class WeatherRepository {
    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static final String METRIC_CELSIUS = "metric";
    private static final String USER_KEY = "eb8b1a9405e659b2ffc78f0a520b1a46";

    public Weather getWeather(String city, String days) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebAPI webAPI = retrofit.create(WebAPI.class);
        final Call<Weather> weatherCall = webAPI
                .getWeather(city, METRIC_CELSIUS, days, USER_KEY);
        try {
            Response<Weather> weatherResponse = weatherCall.execute();
            return weatherResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getCities() {
        return new String[]{"Nizhniy Novgorod", "Moscow"};
    }

    public String[] getDays() {
        return new String[]{"1", "2", "3", "4"};
    }
}
