package com.foo.umbrella;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class UmbrellaApp extends Application {
  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
  }

  UmbrellaApp umbrellaApp;
  public UmbrellaApp getInstance(){

    if(umbrellaApp==null){
      umbrellaApp = new UmbrellaApp();
    }
    return umbrellaApp;
  }

  public static String zipcode = null;
  public String units;

}
