package com.hu.cheng.sheng.jsinterface;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    WebView wv_test ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv_test = (WebView)findViewById(R.id.wv_test) ;
        wv_test.getSettings().setJavaScriptEnabled(true);
        /* "JSInterface" ==> window.JSInterface
         * class JSInterface all @javascriptInterface methods
         * js can call JSInterface.method()*/
        wv_test.addJavascriptInterface(new JSInterface(MainActivity.this),"JSInterface");
        /* at webivew not brower*/
        wv_test.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true ;
            }
        });
        wv_test.loadUrl("file:///android_asset/JSInterface.html");
        //wv_test.loadUrl("http://127.0.0.1:8080/JSInterface.html");
    }
    /* button SET*/
    public void inputText(View view){
        wv_test.loadUrl("javascript:inputText('"+new Date()+"')");
    }

    /*button GET*/
    @TargetApi(Build.VERSION_CODES.N)
    public void getText(View view){
        wv_test.evaluateJavascript("getText()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Toast.makeText(MainActivity.this,value,Toast.LENGTH_LONG).show();
            }
        });
    }
}
