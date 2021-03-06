package com.liompei.app2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);  //context,布局走向,是否反转
        recyclerView.setLayoutManager(linearLayoutManager);  //必备
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        adapter = new RvAdapter(this, getData());
        recyclerView.setAdapter(adapter);  //必备
        adapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, TextView textName) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
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
