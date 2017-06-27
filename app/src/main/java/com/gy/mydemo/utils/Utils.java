package com.gy.mydemo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;


import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;


import android.telephony.TelephonyManager;
import android.text.TextUtils;

import android.widget.Toast;



/**
 * 工具类
 * 
 * @author zhudf13586
 *
 */
public class Utils {

	public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
	
	/**
	 * 将图片设置为圆角
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 字符串过长时，截取特定长度的字符加...显示
	 * 
	 * @author zdf
	 * @param str
	 *            所获得的整个字符
	 * @param len
	 *            只可显示的长度
	 * @return 截取后的字符，过长会加...
	 */
	public static String subTextString(String str, int len) {
		if (TextUtils.isEmpty(str)) {
			return null;
		}

		if (str.length() < len / 2) {
			return str;
		}

		int count = 0;
		StringBuffer sb = new StringBuffer();
		String[] ss = str.split("");
		for (int i = 1; i < ss.length; i++) {
			count += ss[i].getBytes().length > 1 ? 2 : 1;
			sb.append(ss[i]);
			if (count >= len) {
				break;
			}
		}

		// 不需要显示...的可以直接return sb.toString();
		return (sb.toString().length() < str.length()) ? sb.append("...")
				.toString() : str;
	}

	public static boolean existSim(final Context pContext) {
		TelephonyManager mTelephonyManager = (TelephonyManager) pContext
				.getSystemService(Service.TELEPHONY_SERVICE);
		if (mTelephonyManager.getSimState() != TelephonyManager.SIM_STATE_READY) // SIM卡没有就绪
		{
			// Notify.I("没有检测到sim卡，不能进行呼叫");
			Toast.makeText(pContext, "没有检测到sim卡，不能进行呼叫", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		return true;
	}

	/**
	 * 针对TextView显示中文中出现的排版错乱问题，通过调用此方法得以解决
	 * 
	 * @param str
	 * @return 返回全部为全角字符的字符串
	 */
	public static String toDBC(String str) {
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375) {
				c[i] = (char) (c[i] - 65248);
			}

		}
		return new String(c);
	}


}
