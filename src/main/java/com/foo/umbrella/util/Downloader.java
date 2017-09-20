package com.foo.umbrella.util;

import com.foo.umbrella.data.api.DownloadStatus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bayetrax on 9/19/2017.
 */

public class Downloader {

    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    int corePoolSize = NUMBER_OF_CORES * 2;
    int MAX_POOL_SIZE = NUMBER_OF_CORES * 2;
    long KEEP_ALIVE = 60L;
    TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();


    ThreadPoolExecutor threadPoolExecutor;
    DownloadStatus Listener;

    public Downloader(DownloadStatus downloadStatus){
        Listener = downloadStatus;
        workQueue = new LinkedBlockingQueue<>();
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,MAX_POOL_SIZE,KEEP_ALIVE,TIME_UNIT,workQueue);
    }

    InputStream is;
    public void start(final String stringurl, final int id){

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection;
                try {
                    URL url = new URL(stringurl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    is = httpURLConnection.getInputStream();
                    Thread.sleep(3000);
                    Listener.OnDownloadComplete(is , id);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e2){
                    e2.printStackTrace();
                }
            }
        });
    }
}
