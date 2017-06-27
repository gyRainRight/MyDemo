package com.gy.mydemo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Toast;

//import com.hundsun.medutilities.BaseUtil;

public final class MessageUtils {
	static Toast theToast;

	static Handler handler = new Handler(Looper.getMainLooper());

	private static Runnable shower = new Runnable() {

		@Override
		public void run() {
			theToast.show();
		}
	};

	static Runnable hider = new Runnable() {

		@Override
		public void run() {
			// BaseUtil.involkeMethod(BaseUtil.getFieldValue(theToast, "mTN"),
			// "hide", null);
		}
	};

	public static void showMessage(Context context, String message) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();

		if (TextUtils.isEmpty(message)) {
			return;
		}
		if (null == theToast) {
			theToast = Toast.makeText(context.getApplicationContext(), message,
					Toast.LENGTH_LONG);
		} else {
			theToast.setText(message);
		}
		//theToast.setDuration(5000);
		theToast.setGravity(Gravity.CENTER, 0, (int) (dm.density * 75));
		handler.post(shower);
	    //handler.postDelayed(shower, 5000);
	}

	public static void showMessage(Context context, String title, String message) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(message)
				.setNeutralButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).show();
	}
}
