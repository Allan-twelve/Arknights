package com.allan.arknight;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.*;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MaterialActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        webView = findViewById(R.id.webview_of_material);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);//设置允许js
        settings.setDomStorageEnabled(true);//设置允许缓存
        settings.setLoadsImagesAutomatically(true);//设置图片自动加载
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js自动打开窗口
        settings.setAllowFileAccess(true);//设置访问文件file
        settings.setAllowContentAccess(true);//设置访问文件content
        settings.setUseWideViewPort(true);//设置自动适应屏幕
        settings.setSupportMultipleWindows(true);//设置支持多窗口
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//去除缓存

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("https://penguin-stats.cn/?utm_source=penguin-stats&utm_medium=mirror-notification");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}
