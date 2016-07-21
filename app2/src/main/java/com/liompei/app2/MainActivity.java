package com.liompei.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 深入recyclerView二
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //必备
        adapter = new RvAdapter(this, getData());
        recyclerView.setAdapter(adapter);  //必备
    }


    private List<Map<String, String>> getData() {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "liompei" + i);
            map.put("num", "" + i);
            mapList.add(map);
        }
        return mapList;
    }
}
