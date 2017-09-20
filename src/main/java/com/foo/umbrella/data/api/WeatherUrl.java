package com.foo.umbrella.data.api;

import com.foo.umbrella.BuildConfig;
import com.foo.umbrella.UmbrellaApp;


/**
 * Created by bayetrax on 9/19/2017.
 */

public class WeatherUrl {

    public String forecastapi;

    public String getForecastapi(String zipcode){
        forecastapi = "http://api.wunderground.com/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/"+ zipcode +".json";
        return forecastapi;
    }


}
