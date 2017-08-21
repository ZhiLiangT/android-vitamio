package com.tianzl.vitamio;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tianzl.vitamio.been.VideoInfo;
import com.tianzl.vitamio.utils.Utils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class TestVitamioActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private MediaController mMediaController;
    private Intent intent;
    private VideoInfo videoInfo;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vitamio);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_test_vitamio);
        intent=getIntent();
        videoInfo=intent.getParcelableExtra(Utils.INTENT_VIDEO);
        url=videoInfo.getFilePath();
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mVideoView.setVideoPath(url);//设置播放地址
        mMediaController = new MediaController(this);//实例化控制器
        mMediaController.show(5000);//控制器显示5s后自动隐藏
        mVideoView.setMediaController(mMediaController);//绑定控制器
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//设置播放画质 高画质
        mVideoView.requestFocus();//取得焦点
    }
}
