package com.example.karakoram.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.karakoram.R;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleForm extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_form);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                if (url.startsWith("intent://")) {
//                    String formLink = url.substring(9,36);
//                    System.out.println(formLink);
//                    view.loadUrl("https://"+formLink);
//                    try {
//                        Context context = view.getContext();
//                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
//
//                        if (intent != null) {
//                            view.stopLoading();
//
//                            PackageManager packageManager = context.getPackageManager();
//                            ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
//                            if (info != null) {
//                                context.startActivity(intent);
//                            } else {
//                                String fallbackUrl = intent.getStringExtra("browser_fallback_url");
//                                view.loadUrl(fallbackUrl);
//
//                                // or call external broswer
////                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fallbackUrl));
////                    context.startActivity(browserIntent);
//                            }
//
//                            return true;
//                        }
//                    } catch (URISyntaxException e) {
//                    }

//                    return false;
//                }
//                return false;
//            }
        });
        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfBR-XfNE6oPFbFd0asGhJyJ9jw7BxgtZ_A3Z0K4iAgzwSDfg/viewform");
    }


}