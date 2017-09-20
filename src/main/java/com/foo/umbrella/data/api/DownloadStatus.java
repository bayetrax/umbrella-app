package com.foo.umbrella.data.api;

import java.io.InputStream;

/**
 * Created by bayetrax on 9/19/2017.
 */

public interface DownloadStatus {
    void OnDownloadComplete(InputStream is, int id);
}
