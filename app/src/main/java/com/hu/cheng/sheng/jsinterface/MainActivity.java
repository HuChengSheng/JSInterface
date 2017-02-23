package com.hu.cheng.sheng.jsinterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    WebView wv_test ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv_test = (WebView)findViewById(R.id.wv_test) ;
        wv_test.getSettings().setJavaScriptEnabled(true);
        wv_test.addJavascriptInterface(new JSInterface(MainActivity.this),"JSInterface");
        wv_test.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true ;
            }
        });
        File path = getFilesDir();
        System.out.println(path.getAbsoluteFile());
        wv_test.loadUrl("file:///android_asset/JSInterface.html");
        //wv_test.loadUrl("http://127.0.0.1:8080/JSInterface.html");
    }



    public void inputText(View view){
        wv_test.loadUrl("javascript:inputText('"+new Date()+"')");
    }
}
