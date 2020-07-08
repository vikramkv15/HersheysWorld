package com.example.hersheysworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv;
    private ArrayList<Workout> wodList;
    private ArrayList<String> titleList;
    private Adapter adapter;
    private ImageView logo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        logo1 = findViewById(R.id.logo_image);
        lv = findViewById(R.id.lv);
        wodList = DataHelper.loadWorkout(this);
        titleList = new ArrayList<>();
        for (int i = 0; i < wodList.size(); i++) {
            String str = wodList.get(i).getTitle();
            titleList.add(str);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
        lv.setAdapter((ListAdapter) adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        Intent intent = new Intent(Dashboard.this, DetailActivity.class);
        String title = wodList.get(pos).getTitle();
        String wod = wodList.get(pos).getWod();

        intent.putExtra("EXTRA_TITLE", title);
        intent.putExtra("EXTRA_WOD", wod);
        startActivity(intent);
    }
}