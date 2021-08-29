package com.example.practice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        //웹뷰의 자바스크립트 기능을 활성화 시킵니다.
        webview.settings.javaScriptEnabled = true

        //BlackJin 명의 JavascriptInterface 를 추가해 줍니다.
        webview.addJavascriptInterface(WebViewJavascriptBridge(this), "BlackJin")

        //assets에 있는 sample.html을 로딩합니다.
        webview.loadUrl("file:///android_asset/sample.html")

        button.setOnClickListener {
            webview.loadUrl("javascript:math.double(" + edittext.text + ")")
            webview.loadUrl("javascript:math.triple(" + edittext.text + ")")
        }
    }

    inner class WebViewJavascriptBridge(private val context: Context) {
        @JavascriptInterface
        fun getDoubleNum(num: Int) {
            result1_tv.text = num.toString()
        }

        @JavascriptInterface
        fun getTripleNum(num: Int){
            result2_tv.text = num.toString()
        }

        @JavascriptInterface
        fun showToast(message: String){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}