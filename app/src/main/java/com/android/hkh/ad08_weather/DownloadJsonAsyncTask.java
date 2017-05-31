package com.android.hkh.ad08_weather;

import android.net.http.HttpsConnection;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by hieu.truongvan on 5/27/2017.
 */

public class DownloadJsonAsyncTask extends AsyncTask<String, Void, String> {
    private DownloadJsonAsyncTaskListener downloadJsonAsyncTaskListener=null;
    public void SetLister(DownloadJsonAsyncTaskListener _downloadJsonAsyncTaskListener){
        downloadJsonAsyncTaskListener = _downloadJsonAsyncTaskListener;
    }
    @Override
    protected String doInBackground(String... jsonUrl) {
        String dataUrl = jsonUrl[0]; InputStream inputStream = null; String strResult = "";
        try {
            URL url = new URL(dataUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            strResult = convertInputStreamToString(inputStream);//;string return json
        }catch (Exception e) {
            Log.i("Demo download json", "Error download json");
            e.printStackTrace();
        }
        Log.i("Weather","Json:"+ strResult);
        return strResult;
    }
    @Override
    protected void onPostExecute(String strResult) {
        super.onPostExecute(strResult);
        if(downloadJsonAsyncTaskListener != null) {
            downloadJsonAsyncTaskListener.OnFinisheDownload(strResult);
        }
    }
    private static String convertInputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line="", result = "";
        try {
            while ( (line = bufferedReader.readLine() ) != null) {
                result += line;
            }
        }catch (Exception e) {
            Log.i("Weather", "Error!");
            e.printStackTrace();
        }
        return result;
    }
}
