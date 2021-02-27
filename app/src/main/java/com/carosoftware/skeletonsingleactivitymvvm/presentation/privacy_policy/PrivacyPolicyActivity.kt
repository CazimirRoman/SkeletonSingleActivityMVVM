package com.carosoftware.skeletonsingleactivitymvvm.presentation.privacy_policy

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.carosoftware.skeletonsingleactivitymvvm.R
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import java.util.*


class PrivacyPolicyActivity : AppCompatActivity() {

    // TODO: Uncomment this
//    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        // TODO: Load ads here
//        initializeAdView()

        // TODO: 28.04.2020 refactor this - create single object
        val adsBought = intent?.getBooleanExtra("ads_bought", false)
        val proBought = intent?.getBooleanExtra("pro_bought", false)

        // TODO: Uncomment this
//        adsBought?.let {
//            if (it) {
//                removeAdsView()
//            } else {
//                loadAds()
//            }
//        }
//
//        proBought?.let {
//            if (it) {
//                removeAdsView()
//            } else {
//                loadAds()
//            }
//        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.privacy_policy_title)

        webView.webViewClient = WebViewClient()
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        // TODO: Create privacy policy and add it to the assets folder
        val languagename: String = Locale.getDefault().displayLanguage

        var pathToHtml = "file:///android_asset/privacy_policy.html"

        when (languagename) {
            // TODO: 10.06.2020 Translate privacy policies
            Locale.GERMAN.displayLanguage -> pathToHtml =
                "file:///android_asset/privacy_policy_de.html"
            Locale.FRENCH.displayLanguage -> pathToHtml =
                "file:///android_asset/privacy_policy_fr.html"
            Locale("es", "ES").displayLanguage -> pathToHtml =
                "file:///android_asset/privacy_policy_sp.html"
            Locale.ITALIAN.displayLanguage -> pathToHtml =
                "file:///android_asset/privacy_policy_it.html"
            Locale.CHINESE.displayLanguage -> pathToHtml =
                "file:///android_asset/privacy_policy_ch.html"
            Locale("ru", "RU").displayLanguage -> pathToHtml =
                "file:///android_asset/privacy_policy_ru.html"
        }

        webView.loadUrl(pathToHtml)
    }

    // TODO: Uncomment this
//    private fun initializeAdView() {
//        this.adView = AdView(this)
//        adView.adSize = AdSize.SMART_BANNER
//        adView.id = View.generateViewId()
//
//        if (BuildConfig.DEBUG) {
//            adView.adUnitId = resources.getString(R.string.ad_test)
//        } else {
//            adView.adUnitId = resources.getString(R.string.ad_prod)
//        }
//    }

//    private fun removeAdsView() {
//        adView.parent?.let {
//            val parent: ViewGroup = it as ViewGroup
//            parent.removeView(adView)
//            parent.invalidate()
//        }
//    }

//    private fun loadAds() {
//        this.adView = AdView(this)
//        adView.adSize = AdSize.SMART_BANNER
//        adView.id = View.generateViewId()
//
//        if (BuildConfig.DEBUG) {
//            adView.adUnitId = resources.getString(R.string.ad_test)
//        } else {
//            adView.adUnitId = resources.getString(R.string.ad_prod)
//        }
//
//        adMobView.addView(adView)
//
//        val adRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
