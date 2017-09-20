package com.foo.umbrella.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.UmbrellaApp;
import com.foo.umbrella.data.ApiServicesProvider;
import com.foo.umbrella.data.api.DownloadStatus;
import com.foo.umbrella.data.api.WeatherService;
import com.foo.umbrella.data.api.WeatherUrl;
import com.foo.umbrella.data.model.WeatherB;
import com.foo.umbrella.util.Downloader;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity implements DownloadStatus{

    WeatherUrl weatherUrl;
    UmbrellaApp umbrellaApp;
    WeatherB weather;
    Downloader downloader;
    TextView full;
    TextView temperature;
    TextView weatherDescription;
    LinearLayout currentcontainer;
    ImageButton gearIcon;

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 100:
                    full.setText(weather.getCurrentObservation().getDisplay_location().getFull());
                    temperature.setText(weather.getCurrentObservation().getTemp_c());
                    weatherDescription.setText(weather.getCurrentObservation().getWeather());
                    currentcontainer.setBackgroundColor(getResources().getColor(R.color.weather_cool));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloader = new Downloader(this);
        weatherUrl = new WeatherUrl();
        umbrellaApp = new UmbrellaApp();

        full = (TextView) findViewById(R.id.full);
        temperature = (TextView) findViewById(R.id.temperature);
        weatherDescription = (TextView) findViewById(R.id.weather);
        currentcontainer = (LinearLayout) findViewById(R.id.currentcontainer);
        gearIcon = (ImageButton) findViewById(R.id.gear);


        gearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (umbrellaApp.zipcode == null) {
            startActivity(new Intent(this, SettingsActivity.class));
        }else{
            Log.d("XXX",weatherUrl.getForecastapi(umbrellaApp.zipcode));
            downloader.start(weatherUrl.getForecastapi(umbrellaApp.zipcode),100);
        }

    }


    Reader reader;
    @Override
    public void OnDownloadComplete(InputStream is, int id) {

        switch (id){
            case 100:
                try {
                    reader  = new InputStreamReader(is, "UTF-8");
                }catch (Exception e){

                }
                weather  = new Gson().fromJson(reader, WeatherB.class);
                Message msg = Message.obtain(handler);
                msg.what = id;
                msg.obj = weather;
                handler.sendMessage(msg);

                break;
        }

    }
}
