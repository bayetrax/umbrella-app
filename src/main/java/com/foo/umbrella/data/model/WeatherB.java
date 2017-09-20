package com.foo.umbrella.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bayetrax on 9/19/2017.
 */

public class WeatherB {
    @SerializedName("current_observation")
    CurrentObservation currentObservation;

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public static class CurrentObservation {

        @SerializedName("display_location")
        DisplayLocation display_location;

        @SerializedName("temp_f")
        String temp_f;

        @SerializedName("temp_c")
        String temp_c;

        /**
         * @return String with a single word "weather" description such as "Sunny" or "Overcast"
         */
        @SerializedName("weather")
        String weather;

        @SerializedName("icon")
        String icon;

        public DisplayLocation getDisplay_location() {
            return display_location;
        }

        public void setDisplay_location(DisplayLocation display_location) {
            this.display_location = display_location;
        }

        public String getTemp_f() {
            return temp_f;
        }

        public void setTemp_f(String temp_f) {
            this.temp_f = temp_f;
        }

        public String getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(String temp_c) {
            this.temp_c = temp_c;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public static class DisplayLocation{
            @SerializedName("full")
            String full;

            public String getFull() {
                return full;
            }

            public void setFull(String full) {
                this.full = full;
            }
        }
    }

    @Override
    public String toString() {
        return currentObservation.display_location.full+"-"+currentObservation.temp_c;
    }
}
