package com.easyapps.loadersdemoitunes;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SimpleLoader extends AsyncTaskLoader<String> {
    private static final String TAG = "flow";

    public SimpleLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        Log.i(TAG, "onStartLoading: without ForceLoad it won't go to loadinBackground");
        Log.i(TAG, "onStartLoading: "+ Thread.currentThread().getId());
    }

    @Override
    public String loadInBackground() {
        Log.i(TAG, "loadInBackground: "+ Thread.currentThread().getId());

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;
        String line;
        try {
            URL url = new URL("https://itunes.apple.com/search?term=classic");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) return null;

            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) buffer.append(line);

            if (buffer.length() == 0) return null;
            jsonStr = buffer.toString();

        } catch (IOException e) {
            Log.e("MainActivity", "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }
        }

        return jsonStr;
    }

}
