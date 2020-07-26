package com.example.selectcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
//        WebView webview=findViewById(R.id.webview);
//        webview.setWebViewClient(new WebViewClient());
//        WebSettings webSettings=webview.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        String htm_str="<?xml version='1.0' encoding='utf-8'?><!DOCTYPE html><html class=\"lora nightMode textSizeThree\" onclick=\"onClickHtml()\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:epub=\"http://www.idpf.org/2007/ops\" epub:prefix=\"z3998: http://www.daisy.org/z3998/2012/vocab/structure/#\" lang=\"en\" xml:lang=\"en\">  <head><meta name=\"viewport\" content=\"width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"/styles/ltr-before.css\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"/styles/ltr-default.css\"/>    <title>7</title>  <link rel=\"stylesheet\" type=\"text/css\" href=\"/styles/ltr-after.css\"/><script type=\"text/javascript\" src=\"/scripts/touchHandling.js\"></script><script type=\"text/javascript\" src=\"/scripts/utils.js\"></script><style>@import url('https://fonts.googleapis.com/css?family=PT+Serif|Roboto|Source+Sans+Pro|Vollkorn');</style><style type=\"text/css\"> @font-face{font-family: \"OpenDyslexic\"; src:url(\"/fonts/OpenDyslexic-Regular.otf\") format('truetype');}</style>\n" +
//                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/css/Style.css\" />\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/jsface.min.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/jquery-3.4.1.min.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/rangy-core.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/rangy-highlighter.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/rangy-classapplier.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/rangy-serializer.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/Bridge.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/rangefix.js\"></script>\n" +
//                "    <script type=\"text/javascript\" src=\"file:///android_asset/js/readium-cfi.umd.js\"></script>\n" +
//                "    <script type=\"text/javascript\">setMediaOverlayStyleColors('#C0ED72','#C0ED72')</script>\n" +
//                "    <meta name=\"viewport\" content=\"height=device-height, user-scalable=no\" />\n" +
//                "    </head>  <body>    <h3 style=\"text-align:center;margin-top:15px;text-transform: uppercase;\"> <pl id=\"138089\">7</pl></h3>    <p style=\"text-align:justify;\"> <pl id=\"138090\">m</pl> <pl id=\"138091\">velit</pl> <pl id=\"138092\">ut</pl> <pl id=\"138093\">scelerisque.</pl> <pl id=\"138094\">In</pl> <pl id=\"138095\">lectus</pl> <pl id=\"138096\">massa,</pl> <pl id=\"138097\">lacinia</pl> <pl id=\"138098\">malesuada</pl> <pl id=\"138099\">suscipit</pl> <pl id=\"138100\">sit</pl> <pl id=\"138101\">amet,</pl> <pl id=\"138102\">imperdiet</pl> <pl id=\"138103\">vitae</pl> <pl id=\"138104\">nisl.</pl> <pl id=\"138105\">Quisque</pl> <pl id=\"138106\">a</pl> <pl id=\"138107\">lectus</pl> <pl id=\"138108\">ac</pl> <pl id=\"138109\">ipsum</pl> <pl id=\"138110\">vehicula</pl> <pl id=\"138111\">aliquet.</pl> <pl id=\"138112\">Praesent</pl> <pl id=\"138113\">nisi</pl> <pl id=\"138114\">mi,</pl> <pl id=\"138115\">commodo</pl> <pl id=\"138116\">in</pl> <pl id=\"138117\">ullamcorper</pl> <pl id=\"138118\">sit</pl> <pl id=\"138119\">amet,</pl> <pl id=\"138120\">auctor</pl> <pl id=\"138121\">a</pl> <pl id=\"138122\">tellus.</pl> <pl id=\"138123\">Nulla</pl> <pl id=\"138124\">quis</pl> <pl id=\"138125\">nisi</pl> <pl id=\"138126\">vel</pl> <pl id=\"138127\">orci</pl> <pl id=\"138128\">dapibus</pl> <pl id=\"138129\">mattis</pl> <pl id=\"138130\">sollicitudin</pl> <pl id=\"138131\">a</pl> <pl id=\"138132\">eros.</pl> <pl id=\"138133\">Phasellus</pl> <pl id=\"138134\">nec</pl> <pl id=\"138135\">pulvinar</pl> <pl id=\"138136\">tortor.</pl> <pl id=\"138137\">Donec</pl> <pl id=\"138138\">non</pl> <pl id=\"138139\">molestie</pl> <pl id=\"138140\">tortor,</pl> <pl id=\"138141\">ut</pl> <pl id=\"138142\">ultricies</pl> <pl id=\"138143\">felis.</pl> <pl id=\"138144\">Suspendisse</pl> <pl id=\"138145\">faucibus</pl> <pl id=\"138146\">vestibulum</pl> <pl id=\"138147\">est,</pl> <pl id=\"138148\">in</pl> <pl id=\"138149\">auctor</pl> <pl id=\"138150\">felis</pl> <pl id=\"138151\">convallis</pl> <pl id=\"138152\">nec.</pl></p><pl id=\"138090\">m</pl> <pl id=\"138091\">velit</pl> <pl id=\"138092\">ut</pl> <pl id=\"138093\">scelerisque.</pl> <pl id=\"138094\">In</pl> <pl id=\"138095\">lectus</pl> <pl id=\"138096\">massa,</pl> <pl id=\"138097\">lacinia</pl> <pl id=\"138098\">malesuada</pl> <pl id=\"138099\">suscipit</pl> <pl id=\"138100\">sit</pl> <pl id=\"138101\">amet,</pl> <pl id=\"138102\">imperdiet</pl> <pl id=\"138103\">vitae</pl> <pl id=\"138104\">nisl.</pl> <pl id=\"138105\">Quisque</pl> <pl id=\"138106\">a</pl> <pl id=\"138107\">lectus</pl> <pl id=\"138108\">ac</pl> <pl id=\"138109\">ipsum</pl> <pl id=\"138110\">vehicula</pl> <pl id=\"138111\">aliquet.</pl> <pl id=\"138112\">Praesent</pl> <pl id=\"138113\">nisi</pl> <pl id=\"138114\">mi,</pl> <pl id=\"138115\">commodo</pl> <pl id=\"138116\">in</pl> <pl id=\"138117\">ullamcorper</pl> <pl id=\"138118\">sit</pl> <pl id=\"138119\">amet,</pl> <pl id=\"138120\">auctor</pl> <pl id=\"138121\">a</pl> <pl id=\"138122\">tellus.</pl> <pl id=\"138123\">Nulla</pl> <pl id=\"138124\">quis</pl> <pl id=\"138125\">nisi</pl> <pl id=\"138126\">vel</pl> <pl id=\"138127\">orci</pl> <pl id=\"138128\">dapibus</pl> <pl id=\"138129\">mattis</pl> <pl id=\"138130\">sollicitudin</pl> <pl id=\"138131\">a</pl> <pl id=\"138132\">eros.</pl> <pl id=\"138133\">Phasellus</pl> <pl id=\"138134\">nec</pl> <pl id=\"138135\">pulvinar</pl> <pl id=\"138136\">tortor.</pl> <pl id=\"138137\">Donec</pl> <pl id=\"138138\">non</pl> <pl id=\"138139\">molestie</pl> <pl id=\"138140\">tortor,</pl> <pl id=\"138141\">ut</pl> <pl id=\"138142\">ultricies</pl> <pl id=\"138143\">felis.</pl> <pl id=\"138144\">Suspendisse</pl> <pl id=\"138145\">faucibus</pl> <pl id=\"138146\">vestibulum</pl> <pl id=\"138147\">est,</pl> <pl id=\"138148\">in</pl> <pl id=\"138149\">auctor</pl> <pl id=\"138150\">felis</pl> <pl id=\"138151\">convallis</pl> <pl id=\"138152\">nec.</pl></p>    <p style=\"text-align:justify;\"> <pl id=\"138153\">Donec</pl>";
//
//        webview.loadDataWithBaseURL("http://127.0.0.1:8080/temp-1919/EPUB/",htm_str,"application/xhtml+xml","UTF-8",null);
    }

}