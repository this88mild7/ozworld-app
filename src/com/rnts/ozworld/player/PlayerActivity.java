package com.rnts.ozworld.player;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.MediaController;
import android.widget.VideoView;

import com.rnts.ozword.R;
import com.rnts.ozworld.common.CommonValue;
import com.rnts.ozworld.list.CategoryListView;
import com.rnts.ozworld.list.ContentsListView;

public class PlayerActivity extends Activity{
	
	MediaController mediaContr = null;
	
	MediaPlayer mPlayer = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        
        createThreadAndDialog(); // 진행바 호출
        
        Intent intent = getIntent();
        String movieUrl = intent.getStringExtra(CommonValue.MOVIE_URL);
        
        
        VideoView videoView = (VideoView) findViewById(R.id.player);
        
        // 비디오뷰를 커스텀하기 위해서 미디어 컨트롤러 객체 생성
        mediaContr = new MediaController(this){
        	@Override
        	public boolean dispatchKeyEvent(KeyEvent event) {
        		
        		
        		return true;
        	}
        	
        };
        
        // 비디오뷰에 연결(재생 할 View를 타겟팅??)
        mediaContr.setAnchorView(videoView);
        
        // 안드로이드 res 폴더에 raw 폴더를 생성 후 재생할 동영상 파일을 넣습니다.
        Uri video = Uri.parse(movieUrl);
        
        // 비디오뷰의 컨트롤러를 미디어 컨트롤러 사용
        videoView.setMediaController(mediaContr);
        
        // 비디오부에 재생할 동영상 주소를 연결
        videoView.setVideoURI(video);
        
        // 비디오뷰에를 포커스 하도록 지정
        videoView.requestFocus();
        
        // 동영상 재생
        videoView.start();
        		
    }
    
    private ProgressDialog loagindDialog; // Loading Dialog
    void createThreadAndDialog() {
        /* ProgressDialog */
        loagindDialog = ProgressDialog.show(this, "Connecting",
                "Loading. Please wait...", true, false);
        
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // 시간걸리는 처리
                handler.sendEmptyMessage(0);
            }
        });
        thread.start();
    }
    
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            loagindDialog.dismiss(); // 다이얼로그 삭제
            // View갱신
        }
    };
    
    
}
