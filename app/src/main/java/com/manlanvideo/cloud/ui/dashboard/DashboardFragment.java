package com.manlanvideo.cloud.ui.dashboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.manlanvideo.cloud.R;
import com.manlanvideo.cloud.api.SSL;

import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertPathValidatorException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private Context context;
    private TextView textView;
    private ImageView imageView;
    private WebView webView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context =  inflater.getContext();
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textView = root.findViewById(R.id.textview);
        imageView = root.findViewById(R.id.imageview);
        webView = root.findViewById(R.id.webview);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest (final WebView view, WebResourceRequest interceptedRequest) {
                return processRequest(interceptedRequest.getUrl());
            }

            //此处用来发送证书的网络请求
            private WebResourceResponse processRequest(final Uri uri) {
                try {
                    URL url = new URL(uri.toString());
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    urlConnection.setSSLSocketFactory(SSL.getSSLSocketFactory(context));
                    InputStream is = urlConnection.getInputStream();
                    String contentType = urlConnection.getContentType();
                    String encoding = urlConnection.getContentEncoding();
                    if(contentType != null) {
                        String mimeType = contentType;
                        if (contentType.contains(";")) {
                            mimeType = contentType.split(";")[0].trim();
                        }
                        return new WebResourceResponse(mimeType, encoding, is);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            //将不需要发送证书的https访问验证忽略
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        webView.loadUrl("https://nerapp.xxicp.cn/nerapi/app/news/index.html?id=c0310c0851364a7d84f1d3d2f47e1432");
//        webView.loadUrl("https://nerapp.xxicp.cn/nerapi/app/news/detail?id=c0310c0851364a7d84f1d3d2f47e1432");

        dashboardViewModel.getText(context).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String str) {
                textView.setText(str);
            }
        });

        dashboardViewModel.getImage(context).observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        });
        return root;
    }
}