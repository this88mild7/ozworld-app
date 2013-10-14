package com.rnts.ozworld.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpUtil {
	
	public String executeHttpGet(String url){
		BufferedReader in = null;
		
		String res = "";
		
		HttpClient client = new DefaultHttpClient();
		
		HttpGet request = new HttpGet(url);
		
		
		try {
			HttpResponse response = client.execute(request);
			
			HttpEntity resEntity = response.getEntity();
			if(resEntity != null){
	    	  res = EntityUtils.toString(resEntity);
			}
			return res;
            
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

}
