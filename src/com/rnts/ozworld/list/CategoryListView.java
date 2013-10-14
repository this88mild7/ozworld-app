package com.rnts.ozworld.list;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.rnts.ozword.R;
import com.rnts.ozworld.common.CommonValue;
import com.rnts.ozworld.common.HttpUtil;
import com.rnts.ozworld.common.JsonParser;

public class CategoryListView extends Activity{
	
	
	private List<HashMap<String, Object>> categoryList = null;
    
    ListView list;
    CategoryAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.list);
        
        
        HttpUtil httpUtils = new HttpUtil();
    	JsonParser jsonParser = new JsonParser();
    	
    	
    	String response = httpUtils.executeHttpGet(CommonValue.TARGET_URL+"category/list.do");
    	categoryList = jsonParser.categoryJsonArrayToList(response);
    	
        list=(ListView)findViewById(R.id.list);
        list.setSelector(new PaintDrawable( 0xFFA07A ));
// 
        // Getting adapter by passing xml data ArrayList
        adapter=new CategoryAdapter(this, categoryList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	
            	Intent intent = new Intent(CategoryListView.this, ContentsListView.class);
            	Bundle extras = new Bundle();
            	extras.putString(CommonValue.CATE_ID, ""+categoryList.get(position).get(CommonValue.CATE_ID));
            	intent.putExtras(extras);
            	
            	startActivity(intent);
            	finish();
               
            }
        });
    }
    
    @Override
    public void onBackPressed(){
    	 finish();
    }
    
}
