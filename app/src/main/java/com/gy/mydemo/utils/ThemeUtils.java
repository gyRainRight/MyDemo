package com.gy.mydemo.utils;

import com.gy.mydemo.R;

import java.util.HashMap;
import java.util.Map;



public class ThemeUtils
{
	public static String themeType = "";

	private static Map<String, Integer> iconMap = new HashMap<String, Integer>()
	{
		private static final long serialVersionUID = 6253933483178551542L;

		public Integer get(Object key)
		{
			Integer v = super.get(key);
			if (v == null)
			{
				return null;
			}
			return v;

		}
	};

	static
	{
		init();
	}

	public static Integer get(Object key)
	{
		Integer value = iconMap.get(key);
		value = value == null ? R.drawable.selector_button : value;
		return value;
	}

	public static void init()
	{
		if (themeType.length() == 0)
		{
			iconMap.put("uikit_header_back", R.drawable.uikit_header_back);
			iconMap.put("back", R.drawable.uikit_header_back);
			iconMap.put("favorite", R.drawable.uikit_header_favorite);
			iconMap.put("unfavorite", R.drawable.uikit_header_unfavorite);
			iconMap.put("list", R.drawable.uikit_header_list);
			iconMap.put("config", R.drawable.uikit_header_config);
			iconMap.put("add", R.drawable.uikit_header_add);
			iconMap.put("navdrawer", R.drawable.uikit_header_navdrawer);
			iconMap.put("search", R.drawable.uikit_header_search);
			iconMap.put("title", R.drawable.uikit_header_title);
			iconMap.put("refrash", R.drawable.uikit_header_refrash);
			iconMap.put("patient", R.drawable.uikit_header_patient);
			
		}
	}
}
