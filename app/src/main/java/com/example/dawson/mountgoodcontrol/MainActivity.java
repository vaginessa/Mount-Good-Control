package com.example.dawson.mountgoodcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    public static RequestQueue queue;
    public static String IP = "";
    public static final String PREF_NAME = "com.dawson.mtgdctl.PREF";
    public static final float SWIPE_THRESHOLD_VELOCITY = 200;
    public static final DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public static void writeData(String url) {
        String finalurl = "http://" + IP + ":5000/" + url;
        //System.out.println(finalurl);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, finalurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("That didn't work!");
                    }
                }
        );

        stringRequest.setRetryPolicy(retryPolicy);
        queue.add(stringRequest);
    }

    public static void writeMovieData(String url) {
        String finalurl = "http://" + IP + ":5050/" + url;
        //System.out.println(finalurl);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, finalurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("That didn't work!");
                    }
                }
        );

        stringRequest.setRetryPolicy(retryPolicy);
        queue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IP = getSharedPreferences(PREF_NAME, 0).getString("IP", "");
        queue = Volley.newRequestQueue(this);
        startActivity(new Intent(this, LightsActivity.class));
    }

    public void startLights(View v) { startActivity(new Intent(this, LightsActivity.class)); }
    public void startMusic(View v) { startActivity(new Intent(this, MusicActivity.class)); }
    public void startMovies(View v) { startActivity(new Intent(this, MoviesActivity.class)); }
    public void startShows(View v) { startActivity(new Intent(this, ShowsActivity.class)); }
    public void startRemote(View v) { startActivity(new Intent(this, MovieRemoteActivity.class)); }
    public void startSettings(View v) { startActivity(new Intent(this, SettingsActivity.class)); }
}
