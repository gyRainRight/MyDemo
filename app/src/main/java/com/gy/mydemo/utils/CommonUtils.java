package com.gy.mydemo.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

public final class CommonUtils {
	
	public static boolean isEmptyString(String strValue)
	{
		if (null != strValue && strValue.length() > 0)
			return false;
		
		return true;
	}
	
	// 判断是否为手机号码
	public static boolean isValidIDNumber(String idNumber)
	{
		Pattern pattern = Pattern.compile("^(\\d{14}|\\d{17})(\\d|[xX])$");
		Matcher matcher = pattern.matcher(idNumber);
		return matcher.matches();

	}
	// 判断是否为手机号码
	public static boolean isValidPhoneNumber(String phoneNumber)
	{
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();

	}
	
	// 判断点是否在多边形内
	public static boolean isPointInPolygon(PointF point, ArrayList<PointF> polygon)
	{
		if (null == point || null == polygon)
			return false;
		int nCross = 0;
		for (int i = 0; i < polygon.size(); i++)
		{
			PointF p1 = polygon.get(i);
			PointF p2 = polygon.get((i + 1) % polygon.size());
			if (p1.y == p2.y)
				continue;
			if (point.y < Math.min(p1.y, p2.y))
				continue;
			if (point.y >= Math.max(p1.y, p2.y))
				continue;
			float x = (float)(point.y - p1.y) * (float)(p2.x - p1.x) / (float)(p2.y - p1.y) + p1.x;
			if (x > point.x)
				nCross++;
		}
		return (nCross % 2 == 1);
	}
	
	public static String getStringFromTime(long time)
	{
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
			Calendar dateSend = Calendar.getInstance(Locale.CHINA);
			Calendar dateCurrent = Calendar.getInstance(Locale.CHINA);
			dateSend.setTimeInMillis(time);

			if (dateSend.get(Calendar.YEAR) == dateCurrent.get(Calendar.YEAR)
					&& dateSend.get(Calendar.MONTH) == dateCurrent.get(Calendar.MONTH))
			{
				if (dateCurrent.get(Calendar.DAY_OF_MONTH) == dateSend.get(Calendar.DAY_OF_MONTH))
				{// 当天消息，显示时间
					return timeFormat.format(dateSend.getTime());
				}
				else if (1 == (dateCurrent.get(Calendar.DAY_OF_MONTH) - dateSend.get(Calendar.DAY_OF_MONTH)))
				{// 昨天消息
					return "昨天";
				}
			}
			return dateFormat.format(dateSend.getTime());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getStringFromStream(InputStream utf8Stream) {
		String statement = null;
		
		if (utf8Stream != null) {
			BufferedInputStream inputStream = new BufferedInputStream(utf8Stream);
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			
		    try {
		    	int byteRead = inputStream.read();
				
			    while(byteRead != -1) {
				    byte b = (byte)byteRead;
				      
				    outputStream.write(b);				      
					byteRead = inputStream.read();
			    }
			    
			    statement = outputStream.toString();	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
	    return statement;
	}	
	
	public static boolean isExternalStorageAvailable() {
		String state = Environment.getExternalStorageState();
		
		return Environment.MEDIA_MOUNTED.equals(state);
	}
	
	@SuppressLint("NewApi")
	public static String getFilePathFromURI(Context context, Uri contentUri) {
		String filePath = null;
		int version = Build.VERSION.SDK_INT;		

		String scheme = contentUri.getScheme();
		if(scheme != null && scheme.equals("content")) {
        	if (version >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, contentUri)){
    		    String wholeID = DocumentsContract.getDocumentId(contentUri);
    		    String id = wholeID.split(":")[1];
    		    String[] column = { MediaStore.Images.Media.DATA };
    		    String sel = MediaStore.Images.Media._ID + "=?";
    		    Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column,
    		            sel, new String[] { id }, null);
    		    int columnIndex = cursor.getColumnIndex(column[0]);
    		    if (cursor.moveToFirst())
    		        filePath = cursor.getString(columnIndex);
    		    cursor.close();
    		}
        	else {
        		if (contentUri.toString().startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
                	String[] projection = { MediaStore.Images.Media.DATA };
        		    Cursor cursor = context.getContentResolver().query(contentUri, projection, null, null, null);
        		    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        		    cursor.moveToFirst();
        		    filePath = cursor.getString(column_index);
        		    cursor.close();
                }
        	}
        }
        else if(scheme != null && scheme.equals("file")) {
        	filePath = contentUri.getPath();
        }		
		
		return filePath;
	}		
	
	public static void makeHeaderStyle(int type, 
			String title, String tileIcon, 
			String leftIcon, String leftText, 
			String rightIcon, String rightText, 
			JSONObject json) throws JSONException
	{
		json.put("type", type);

		JSONObject config = new JSONObject();

//		if (null != title && title.length() > 0)
//		{
			JSONObject header = new JSONObject();
			
			header.put("title", title);
			
			if (!CommonUtils.isEmptyString(tileIcon))
			{
				if ("titleicon".equals(tileIcon)) {
					tileIcon = "app_icon";
				} 
				JSONObject titleBtn = new JSONObject();
				titleBtn.put("icon", tileIcon);
				header.put("titleBtn", titleBtn);
			}
			
			if (null != leftText && leftText.length() > 0)
			{
				JSONObject leftBtn = new JSONObject();
				if (null != leftIcon && leftIcon.length() > 0)
				{
					if ("back".equals(leftIcon)) {
					leftIcon = "uikit_header_back";
					}
					leftBtn.put("icon", leftIcon);
				}
				leftBtn.put("text", leftText);
				header.put("leftBtn", leftBtn);
			}

			JSONObject rightBtn = new JSONObject();
			if (null != rightText && rightText.length() > 0)
			{
				if (null != rightIcon && rightIcon.length() > 0)
				{
					if ("righticon".equals(rightIcon)) {
						rightIcon = "icon_search";
					}
					rightBtn.put("icon", rightIcon);
				}
				rightBtn.put("text", rightText);
				header.put("rightBtn", rightBtn);
			}
			config.put("header", header);
//		}

		json.put("config", config);
	}	
	
}
