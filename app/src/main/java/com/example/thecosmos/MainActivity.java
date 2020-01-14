package com.example.thecosmos;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient httpClient;
    private String link;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //need to allow network on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        fetchData();
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Picasso.get().load(link).fit().centerInside().into(imageView);

    }


    private void fetchData() {
        httpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("https://api.nasa.gov/planetary/apod?api_key=XvRIO52PawM8LiVREtR42ni1sY1jPkJfcbiIucOL")
                .build();
        Response response;
        try {
            response = httpClient.newCall(request).execute();
            JSONObject data =  new JSONObject(response.body().string());
            link = data.getString("hdurl");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

}
