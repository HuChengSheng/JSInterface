package com.hu.cheng.sheng.jsinterface;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.Date;

public class JSInterface {
    private Context context ;

    public JSInterface(Context context){
        super();
        this.context = context ;
    }

    @JavascriptInterface
    public void startNewActivity(){
        Intent intent = new Intent();
        intent.setClass(context, NewActivity.class);
        context.startActivity(intent);
    }

    @JavascriptInterface
    public void toast(String value){
        Toast.makeText(context,value,Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public String getValue(){
        return new Date().toString();
    }
}
