package com.rnts.ozworld.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	
	private final String DATA = "data";
	
	// category
	private final String CATE_ID = "cateId";
	private final String CATE_NM = "cateNm";
	private final String CATE_LEVEL = "cateLevel";
	private final String ORDERING_NO = "orderingNo";
	
	private final String CONTENTS_ID = "contentsId";
	private final String CONTENTS_NM = "contentsNm";
	private final String CONTENTS_DESC = "contentsDesc";
	private final String IMG_PATH = "imgPath";
	private final String SRC_PATH = "srcPath";
	private final String PREFIX_URL = "prefixUrl";
	
	// contents
	
	public List<HashMap<String, Object>> categoryJsonArrayToList(String json){
		List<HashMap<String, Object>> hashMapList = new ArrayList<HashMap<String, Object>>();
		
		try {
			JSONObject result = new JSONObject(json);
			JSONArray jsonArr = new JSONArray(result.getString(DATA));
			
			for(int x=0;x<jsonArr.length();x++){
				JSONObject jObject = jsonArr.getJSONObject(x);
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put(CATE_ID, jObject.get(CATE_ID));
				hashMap.put(CATE_NM, jObject.get(CATE_NM));
				hashMap.put(CATE_LEVEL, jObject.get(CATE_LEVEL));
				hashMap.put(ORDERING_NO, jObject.get(ORDERING_NO));
				hashMapList.add(hashMap);
			}
			
			return hashMapList;
		} catch (Exception e) {
			e.printStackTrace();
			return hashMapList;
		}
	}
	
	public List<HashMap<String, Object>> contentsJsonArrayToList(String json){
		List<HashMap<String, Object>> hashMapList = new ArrayList<HashMap<String, Object>>();
		
		try {
			JSONObject result = new JSONObject(json);
			JSONArray jsonArr = new JSONArray(result.getString(DATA));
			
			for(int x=0;x<jsonArr.length();x++){
				JSONObject jObject = jsonArr.getJSONObject(x);
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put(CONTENTS_ID, jObject.get(CONTENTS_ID));
				hashMap.put(CONTENTS_NM, jObject.get(CONTENTS_NM));
				hashMap.put(CONTENTS_DESC, jObject.get(CONTENTS_DESC));
				hashMap.put(IMG_PATH, jObject.get(IMG_PATH));
				hashMap.put(SRC_PATH, jObject.get(SRC_PATH));
				hashMap.put(PREFIX_URL, jObject.get(PREFIX_URL));
				hashMapList.add(hashMap);
			}
			
			return hashMapList;
		} catch (Exception e) {
			e.printStackTrace();
			return hashMapList;
		}
	}

}
