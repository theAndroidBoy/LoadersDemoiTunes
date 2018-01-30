package com.easyapps.loadersdemoitunes;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = "flow";
    TextView tvJsonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(0, null, this);

        Log.i(TAG, "onCreate: if loader with id 0 does'nt exist onCreateLoader will be called ");
        Log.i(TAG, "onCreate: if loader with id 0 exists onLoadFinished will be called");
        tvJsonResult = (TextView) findViewById(R.id.tv_json_result);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader: " + Thread.currentThread().getId());
        return new SimpleLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.i(TAG, "onLoadFinished: " + Thread.currentThread().getId());
        tvJsonResult.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.i(TAG, "onLoaderReset: " + Thread.currentThread().getId());
    }
}