package com.kony.ffi.screenshot;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.konylabs.android.KonyMain;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ScreenShotFFI {

	static Context context = KonyMain.getAppContext();
	
	  public static void callingFFI()
	  {
		  Date now = new Date();
		  android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
		  try {
		        // image naming and path  to include sd card  appending name you choose for file
		        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

		        // create bitmap screen capture
		        View v1 = KonyMain.getActivityContext().getWindow().getDecorView().getRootView();
		        v1.setDrawingCacheEnabled(true);
		        Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
		        v1.setDrawingCacheEnabled(false);

		        File imageFile = new File(mPath);

		        FileOutputStream outputStream = new FileOutputStream(imageFile);
		        int quality = 100;
		        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
		        outputStream.flush();
		        outputStream.close();

		        openScreenshot(imageFile);
		    } catch (Throwable e) {
		        // Several error may come out with file handling or OOM
		        e.printStackTrace();
		    }
	  }

	  private static void openScreenshot(File imageFile) {
		  Intent intent = new Intent();
		    intent.setAction(Intent.ACTION_VIEW);
		    Uri uri = Uri.fromFile(imageFile);
		    intent.setDataAndType(uri, "image/*");
		    KonyMain.getActivityContext().startActivity(intent);
		
	}

	public static Bitmap getScreenShot(View view) {
	    View screenView = view.getRootView();
	    screenView.setDrawingCacheEnabled(true);
	    Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
	    screenView.setDrawingCacheEnabled(false);
	    return bitmap;
	  }
	  public static void store(Bitmap bm, String fileName) {
	    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
	    File dir = new File(dirPath);
	    if (!dir.exists())
	      dir.mkdirs();
	    File file = new File(dirPath, fileName);
	    try {
	      FileOutputStream fOut = new FileOutputStream(file);
	      bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
	      fOut.flush();
	      fOut.close();
	      shareImage(file);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public static void shareImage(File file) {
	    Uri uri = Uri.fromFile(file);
	    Intent intent = new Intent();
	    intent.setAction("android.intent.action.SEND");
	    intent.setType("image/*");

	    intent.putExtra("android.intent.extra.SUBJECT", "");
	    intent.putExtra("android.intent.extra.TEXT", "");
	    intent.putExtra("android.intent.extra.STREAM", uri);
	    try {
	      context.startActivity(Intent.createChooser(intent, "Share Screenshot"));
	    } catch (ActivityNotFoundException e) {
	      Toast.makeText(context, "No App Available", 0).show();
	    }
	  }
}
