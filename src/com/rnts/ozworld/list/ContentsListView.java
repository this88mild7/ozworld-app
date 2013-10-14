package com.rnts.ozworld.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.rnts.ozword.R;
import com.rnts.ozworld.common.CommonValue;
import com.rnts.ozworld.common.HttpUtil;
import com.rnts.ozworld.common.JsonParser;
import com.rnts.ozworld.common.XMLParser;
import com.rnts.ozworld.player.PlayerActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ContentsListView extends Activity{
	
	
	
   	private List<HashMap<String, Object>> contentsList = null;
    
    private ListView list;
    private ContentsAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.list);
        
        createThreadAndDialog(); // 진행바 호출
        
        String cateId = getIntent().getStringExtra(CommonValue.CATE_ID);
        
        HttpUtil httpUtils = new HttpUtil();
    	JsonParser jsonParser = new JsonParser();
    	
    	String response = httpUtils.executeHttpGet(CommonValue.TARGET_URL+"contents/list.do?cateId="+cateId);
    	contentsList = jsonParser.contentsJsonArrayToList(response);
 
        list=(ListView)findViewById(R.id.list);
// 
        // Getting adapter by passing xml data ArrayList
        adapter=new ContentsAdapter(this, contentsList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	Intent intent = new Intent(ContentsListView.this, PlayerActivity.class);
            	Bundle extras = new Bundle();
            	extras.putString(CommonValue.MOVIE_URL, (String)contentsList.get(position).get(CommonValue.PREFIX_URL)+(String)contentsList.get(position).get(CommonValue.SRC_PATH));
            	extras.putString(CommonValue.CATE_ID, getIntent().getStringExtra(CommonValue.CATE_ID));
            	intent.putExtras(extras);
            	startActivity(intent);
            		
            }
        });
    }
    
    @Override
    public void onBackPressed(){
    	Intent intent = new Intent(ContentsListView.this, CategoryListView.class);
    	startActivity(intent);
    	finish();
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
