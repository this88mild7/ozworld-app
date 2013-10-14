package com.rnts.ozworld.list;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rnts.ozword.R;
import com.rnts.ozword.R.id;
import com.rnts.ozword.R.layout;
import com.rnts.ozworld.common.CommonValue;
import com.rnts.ozworld.common.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentsAdapter extends BaseAdapter{
	
	private Activity activity;
	
	private List<HashMap<String, Object>> data;
	private static LayoutInflater inflater = null;
	
	
	
	public ImageLoader imageLoader;
	
	

	public ContentsAdapter(Activity activity,
			List<HashMap<String, Object>> data) {
		this.activity = activity;
		this.data = data;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if(convertView == null){
			vi = inflater.inflate(R.layout.contents_list_row, null);
		}
		
		TextView titleTextView = (TextView)vi.findViewById(R.id.contents_title);
		TextView descTextView = (TextView)vi.findViewById(R.id.contents_desc);
		
		ImageView thumb_image = (ImageView)vi.findViewById(R.id.list_image);
		
		 HashMap<String, Object> contents = data.get(position);
		String desc = (String)contents.get(CommonValue.CONTENTS_DESC);
		if(desc.length() > 20){
			Log.i("oz-world", "desc length="+desc.length());
			desc = desc.substring(0, 20)+"...";
		}
		descTextView.setText(desc);
		titleTextView.setText((String)contents.get(CommonValue.CONTENTS_NM));
		imageLoader.DisplayImage((String)contents.get(CommonValue.PREFIX_URL)+(String)contents.get(CommonValue.IMG_PATH), thumb_image);
		return vi;
	}
	

}
