package edu.temple.basicbrowser

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {


    lateinit var urlEditText: EditText
    lateinit var goButton: ImageButton
    lateinit var webView: WebView
    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // using Volly
        requestQueue = Volley.newRequestQueue(this)
        // this will load the url of the website
      //  webView.loadUrl("https://temple.edu")

        goButton.setOnClickListener{
            var text = urlEditText.text.toString()

            this.setTitle("Temple.edu")
            if (text.substring(0,7) != "https://")
                text = "https://"+ text


            requestQueue.add(
                StringRequest(Request.Method.GET, text, {
                    webView.loadDataWithBaseURL("", it, "text/html", "utf-8", null)
                }, {})
            )
        }


        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
    }
}