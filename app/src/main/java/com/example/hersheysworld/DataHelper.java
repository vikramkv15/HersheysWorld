package com.example.hersheysworld;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataHelper {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<Workout> loadWorkout(Context context) {
        ArrayList<Workout> workouts = new ArrayList<>();
        String json = "";
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("girlsBenchmark");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Workout workout = new Workout();
                workout.setTitle(jsonObject.getString("title"));
                workout.setWod(jsonObject.getString("wod"));
                workouts.add(workout);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return workouts;
    }
}
