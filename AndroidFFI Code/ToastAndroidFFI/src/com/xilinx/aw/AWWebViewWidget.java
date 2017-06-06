package com.xilinx.aw;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.konylabs.android.KonyMain;
import com.konylabs.api.ui.KonyCustomWidget;


/**
 * Created by pmarconi on 14/03/2017.
 */

public class AWWebViewWidget extends KonyCustomWidget {

    private final String TAG = "AWWebView";

    private LinearLayout layout;

    private String PROP_HOMEPAGE = "defaultURL";

    private Object preShowURLParam;
    static String URL = "" ;


    private int width;
    private int height;

    private WebView mAWProxyView;

    /**
     * This method is called by the platform whenever a new instance of custom
     * widget view is required. The container which will hold the mapview will
     * be created in this method. The properties given in IDE and preShow are
     * retrieved and stored in this method.
     *
     * @param context context is used for creating the layout
     * @return View the container of the mapview
     */

    @Override
    public View onCreateView(Context context) {

        /// Commenting out. May need to get properties for the browser

        preShowURLParam = getProperty(PROP_HOMEPAGE);
        Log.e(TAG, "defaultURL: " + preShowURLParam);

        String widthParam = (String) getPropertyFromModel("width");
        Log.e(TAG, "widthParam: " + widthParam);

        if (widthParam != null) {
            if (widthParam.endsWith("px")) {
                widthParam = widthParam.substring(0, widthParam.indexOf("px") - 1);
            } else if (widthParam.endsWith("%")) {
                widthParam = widthParam.substring(0, widthParam.indexOf("%") - 1);
            }

            width = (int) (((Double.parseDouble(widthParam)) / 100) * getDisplayWidth());
            Log.e(TAG, "calculated width: " + width);
        }

        String heightParam = (String) getPropertyFromModel("height");
        Log.e(TAG, "heightParam: " + heightParam);

        if (heightParam != null) {
            if (heightParam.endsWith("px")) {
                heightParam = heightParam.substring(0, heightParam.indexOf("px") - 1);
            } else if (heightParam.endsWith("%")) {
                heightParam = heightParam.substring(0, heightParam.indexOf("%") - 1);
            }
            height = (int) (((Double.parseDouble(heightParam)) / 100) * getDisplayHeight());
            Log.e(TAG, "calculated height: " + height);
        }


        mAWProxyView = new WebView(KonyMain.getActContext());

        WebSettings mWebSettings = mAWProxyView.getSettings();
        mWebSettings.setBuiltInZoomControls(true);
        //mAWProxyView.setScrollBarStyle(mAWProxyView.SCROLLBARS_OUTSIDE_OVERLAY);
        mAWProxyView.setScrollbarFadingEnabled(false);
        //mAWProxyView.setVerticalScrollBarEnabled(true);
        mAWProxyView.setVerticalScrollBarEnabled(true);
        mAWProxyView.setHorizontalScrollBarEnabled(true);

        layout = new LinearLayout(KonyMain.getAppContext());

        mAWProxyView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.addView(mAWProxyView);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        mAWProxyView.post(new Runnable() {
            @Override
            public void run() {
                mAWProxyView.getSettings().setJavaScriptEnabled(true);
                 mAWProxyView.setWebViewClient(new WebViewClient());
            }
        });

        return layout;
    }

    /**
     * This method is called by the platform just before View instance is freed
     * by the platform. all the objects intialized in onCreateView method should
     * be nullified, otherwise would lead to Out of Memory issues.
     *
     * @param view the view that created in onCreateView Method
     * @return void
     */
    @Override
    public void onDestroyView(View view) {
      //  mLayout = null;
        //gMap = null;
    }

    @Override
    public void setHeight(int height) {
    }

    @Override
    public void setWidth(int width) {

    }


    public void loadURL(final String url) {
    	AWWebViewWidget.URL = url;
        mAWProxyView.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "loading started for " + AWWebViewWidget.URL);
                mAWProxyView.loadUrl(AWWebViewWidget.URL);
            }
        });

        Log.i(TAG, "loading completed for" + url);

    }

    /**
     * This method is called by the platform whenever a value is assigned to
     * custom widget property by the application.
     *
     * @param key   propertyName that to be set
     * @param value the property value that to be set
     * @return void returns nothing
     */
    @Override
    public void onPropertySet(View arg0, Object key, Object value) {

    }


    private void init() {

    }

    /**
     * this method is used for getting the constant id of the resource by
     * passing the name
     *
     * @return int constant id of the resource
     */

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


}