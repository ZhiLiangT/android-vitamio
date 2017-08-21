package com.tianzl.vitamio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tianzl.vitamio.adapter.VideoAdapter;
import com.tianzl.vitamio.been.VideoInfo;
import com.tianzl.vitamio.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<VideoInfo> mData;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }
    private void initData() {
        mData= Utils.getVideoList(this);
        adapter=new VideoAdapter(this,mData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(VideoInfo videoInfo) {
                Intent intent=new Intent(MainActivity.this,TestVitamioActivity.class);
                intent.putExtra(Utils.INTENT_VIDEO,videoInfo);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
    }

}
