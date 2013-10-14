package com.rnts.ozworld;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.widget.ListView;

import com.rnts.ozword.R;
import com.rnts.ozworld.list.CategoryListView;
import com.rnts.ozworld.list.CategoryAdapter;

public class MainActivity extends Activity{
	
    // All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    
    ListView list;
    CategoryAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        
        // 갤럭시 노트 네트워크 에러 처리....
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy); 
        }
        
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
             super.handleMessage(msg);
             //startActivity(intent);
             startActivity(new Intent(MainActivity.this, CategoryListView.class));
             finish();
            }
        };
        
        handler.sendEmptyMessageDelayed(0, 3000);
                
            
    }
}
