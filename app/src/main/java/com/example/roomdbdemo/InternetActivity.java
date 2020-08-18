package com.example.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InternetActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        String sticker = "";
        String link;

        Bundle data = getIntent().getExtras();
        if (data != null) {
            sticker = data.getString("ticker");
        }

        link = "http://finance.yahoo.com/quote/" + sticker + "?p=" + sticker;

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}