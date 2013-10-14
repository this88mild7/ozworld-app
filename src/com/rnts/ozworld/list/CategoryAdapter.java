package com.rnts.ozworld.list;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter{
	
	private Activity activity;
	
	private List<HashMap<String, Object>> data;
	private static LayoutInflater inflater = null;
	
	
	public ImageLoader imageLoader;
	
	

	public CategoryAdapter(Activity activity,
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
			vi = inflater.inflate(R.layout.cate_list_row, null);
		}
		
		TextView title = (TextView)vi.findViewById(R.id.cate_title);
		TextView cateId = (TextView)vi.findViewById(R.id.cate_id);
		
		
		ImageView thumb_image = (ImageView)vi.findViewById(R.id.list_image);
		
		HashMap<String, Object> song = new HashMap<String, Object>();
		song = data.get(position);
		
		// Setting all values in listview
		cateId.setText(""+(Integer)song.get(CommonValue.CATE_ID));
		title.setText((String)song.get(CommonValue.CATE_NM));
//		duration.setText(song.get(ContentsListView.KEY_DURATION));
//		imageLoader.DisplayImage(song.get(ContentsListView.KEY_THUMB_URL), thumb_image);
		return vi;
	}
	

}
