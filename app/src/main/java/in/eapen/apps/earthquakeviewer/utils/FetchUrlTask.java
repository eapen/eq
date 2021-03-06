package in.eapen.apps.earthquakeviewer.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchUrlTask extends AsyncTask<String, Void, JSONObject> {


    @Override
    protected JSONObject doInBackground(String... urls) {
        JSONObject jsonResponse = new JSONObject();
        StringBuilder result = new StringBuilder();
        try {
            URL urlObj = new URL(urls[0]);
            // preferred http client
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(false);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.connect();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.d("debug", "Response: " + result.toString());
            jsonResponse = new JSONObject(result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }
}
