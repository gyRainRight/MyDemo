package com.gy.mydemo.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * 解析JSON格式数据
 * 
 * @author tianwei
 * 
 */

public class JsonUtils {

	public static boolean getBoolean(JSONObject json, String key) {
		return getBoolean(json, key, false);
	}

	public static boolean getBoolean(JSONObject json, String key,
			boolean defaultValue) {
		boolean value = false;
		try {
			value = json.getBoolean(key);
		} catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}

	public static float getFloat(JSONObject json, String key) {
		float value = -1;
		try {
			value = Float.parseFloat(json.getString(key));
		} catch (Exception e) {

		}
		return value;
	}
	
	public static Double getDouble(JSONObject json, String key) {
		double value = -1;
		try {
			value = Double.parseDouble(json.getString(key));
		} catch (Exception e) {

		}
		return value;
	}

	public static int getInt(JSONObject json, String key) {
		int value = -1;
		try {
			value = json.getInt(key);
		} catch (Exception e) {

		}
		return value;
	}

	public static JSONObject getJson(JSONArray array, int i) {
		try {
			return array.getJSONObject(i);
		} catch (JSONException e) {
			return null;
		}
	}

	public static JSONObject getJson(JSONObject json, String key) {
		JSONObject value = null;
		try {
			value = json.getJSONObject(key);
		} catch (Exception e) {
		}
		return value;
	}

	public static JSONArray getJsonArray(JSONObject json, String key) {
		JSONArray value = null;
		try {
			value = json.getJSONArray(key);
		} catch (Exception e) {
		}
		return value;
	}

	public static long getLong(JSONObject json, String key) {
		long value = -1;
		try {
			value = Long.parseLong(json.getString(key));
		} catch (Exception e) {

		}
		return value;
	}

	public static String getStr(JSONObject json, String key) {
		String value = null;
		try {
			value = json.getString(key);
		} catch (Exception e) {
		}
		return value;
	}



	public static JSONObject put(JSONObject json, String key, Object value) {
		try {
			return json.put(key, value);
		} catch (Exception e) {

		}
		return json;
	}
}
