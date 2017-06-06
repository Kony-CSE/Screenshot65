package com.kony.customwidgets.cwebview;

import com.konylabs.android.KonyMain;
import com.konylabs.api.ui.KonyCustomWidget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AWWebview extends KonyCustomWidget {

	 private int width;
	 private int height;
	 LinearLayout linearLayout;
	 private String PROP_HOMEPAGE = "defaultURL";
     private Object preShowURLParam;
     static String url;
	 WebView mWebView;
	 
	@Override
	public View onCreateView(Context context) {
		    String widthParam = (String)getPropertyFromModel("width");
		    Log.e("AWWebView", "widthParam: " + widthParam);

		    if (widthParam != null) {
		      if (widthParam.endsWith("px"))
		        widthParam = widthParam.substring(0, widthParam.indexOf("px") - 1);
		      else if (widthParam.endsWith("%")) {
		        widthParam = widthParam.substring(0, widthParam.indexOf("%") - 1);
		      }

		      this.width = (int)(Double.parseDouble(widthParam) / 100.0D * getDisplayWidth());
		      Log.i("AWWebView", "calculated width: " + this.width);
		    }

		    String heightParam = (String)getPropertyFromModel("height");
		    Log.i("AWWebView", "heightParam: " + heightParam);

		    if (heightParam != null) {
		      if (heightParam.endsWith("px"))
		        heightParam = heightParam.substring(0, heightParam.indexOf("px") - 1);
		      else if (heightParam.endsWith("%")) {
		        heightParam = heightParam.substring(0, heightParam.indexOf("%") - 1);
		      }
		      this.height = (int)(Double.parseDouble(heightParam) / 100.0D * getDisplayHeight());
		      Log.i("AWWebView", "calculated height: " + this.height);
		    }
		preShowURLParam = getProperty(PROP_HOMEPAGE);
        Log.i("AWWebView", "defaultURL: " + preShowURLParam);
		 mWebView = new WebView(context); 
		final Context mContext = context;
		 WebSettings mWebSettings = mWebView.getSettings();
         mWebSettings.setBuiltInZoomControls(true);
         //mWebSettings.setJavaScriptEnabled(true);
         mWebSettings.setDomStorageEnabled(true);
         mWebSettings.setAllowFileAccess(true);
         //mWebSettings.setAllowUniversalAccessFromFileURLs(true);
         mWebView.setScrollBarStyle(mWebView.SCROLLBARS_OUTSIDE_OVERLAY);
         mWebView.setScrollbarFadingEnabled(false);
         mWebView.setVerticalScrollBarEnabled(true);
		 mWebView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		/* mWebView.setWebChromeClient(new CustomWebChromeClient(activity));
	        mWebView.setWebViewClient(new WebViewClient() {
	            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	                Toast.makeText(mContext, "Oh no! " + description, Toast.LENGTH_SHORT).show();
	            }
	        });*/
		 mWebView.post(new Runnable() {
	            @Override
	            public void run() {
	            	mWebView.getSettings().setJavaScriptEnabled(true);
	            	mWebView.setWebViewClient(new WebViewClient());
	            }
	        });
		 this.linearLayout = new LinearLayout(context);
		
		return linearLayout;
	}

	@Override
	public void onDestroyView(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 private int getDisplayWidth() {
	        DisplayMetrics metrics = new DisplayMetrics();
	        KonyMain.getActContext().getWindowManager().getDefaultDisplay()
	                .getMetrics(metrics);
	        return metrics.widthPixels;
	    }

	    private int getDisplayHeight() {
	        DisplayMetrics metrics = new DisplayMetrics();
	        KonyMain.getActContext().getWindowManager().getDefaultDisplay()
	                .getMetrics(metrics);
	        return metrics.heightPixels;
	    }
	    
	    public void loadURL(String URL) {
	    	AWWebview.url = URL;

	        this.mWebView.post(new Runnable() {
	            @Override
	            public void run() {
	                Log.i("AWWebView", "loading started for " + AWWebview.url);
	                AWWebview.this.mWebView.loadUrl(AWWebview.url);
	            }
	        });

	        Log.i("AWWebView", "loading completed for" + AWWebview.url);

	    }

	    @Override
	    public Object onPropertyGet(View arg0, Object key) {
	    	// TODO Auto-generated method stub
	    	String k = ((String)key).intern();
	    	return null;
	    }
	    
	    @Override
	    public void onPropertySet(View widgetInstance, Object key, Object value) {
	    	// TODO Auto-generated method stub
	    	String k = ((String)key).intern();
	    	//super.onPropertySet(widgetInstance, key, value);
	    }
}
